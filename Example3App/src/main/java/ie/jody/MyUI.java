package ie.jody;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.TextSyntax;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.data.validator.IntegerRangeValidator;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout layout = new HorizontalLayout();
        Label logo = new Label("<H1>Staff Details</H1> <p/> <h3>Please enter the staff details and click Book</h3><br>", ContentMode.HTML);
      
        TextField staffNumber = new TextField("Staff Number");
        staffNumber.setMaxLength(5);
        TextField name = new TextField("Staff Name");
        TextField phone = new TextField("Enter staff phone number");
        
        
        Button addButton = new Button("Add");
 
        final VerticalLayout vlayout = new VerticalLayout();

        List<Person> staffList = new ArrayList<Person>();
               
        Grid<Person> myGrid = new Grid<> ();
        myGrid.setItems(staffList);
        myGrid.addColumn(Person::getStaffNumber).setCaption("Staff Number");
        myGrid.addColumn(Person::getName).setCaption("Name");
        myGrid.addColumn(Person::getPhone).setCaption("phone");
        myGrid.setSelectionMode(SelectionMode.MULTI);
        myGrid.setSizeFull();

        Button clear = new Button("Clear");
        clear.addClickListener(e -> {
            myGrid.removeAllColumns();
            staffList.clear(); // clear the list of staff
            staffNumber.setValue("");
            name.setValue("");
            phone.setValue("");
        });  
        addButton.addClickListener(e -> {
            staffList.add(new Person(staffNumber.getValue(), name.getValue(), phone.getValue()));
            myGrid.removeAllColumns();
            myGrid.setItems(staffList);
            myGrid.addColumn(Person::getStaffNumber).setCaption("Staff Number");
            myGrid.addColumn(Person::getName).setCaption("Name");
            myGrid.addColumn(Person::getPhone).setCaption("phone");
           
        });  
        vlayout.addComponents(staffNumber, name, phone, addButton,clear);
                
        layout.addComponents(logo, vlayout , myGrid); 
           
        setContent(layout);
       
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}