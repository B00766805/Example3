package ie.jody;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


	
@Theme("mytheme")
public class MyUI extends UI {

    private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        // Create the connection object
    Connection connection = null;  

        String connectionString = "jdbc:sqlserver://databaseforclass.database.windows.net:1433;" +
        "database=classdatabase;user=jody@databaseforclass;password=DataBaseForClass1;" +
        "encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;" +
        "loginTimeout=30;jdbc:sqlserver://.database.windows.net:1433;";
			  
              //jdbc:sqlserver://databaseforclass.database.windows.net:1433;database=classdatabase;user=jody@databaseforclass;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;

    
    final VerticalLayout layout = new VerticalLayout();

    try 
    {
        // Connect with JDBC driver to a database
        connection = DriverManager.getConnection(connectionString);
        // Add a label to the web app with the message and name of the database we connected to 
        layout.addComponent(new Label("Connected to database: " + connection.getCatalog()));
    } 
    catch (Exception e) 
    {
        // This will show an error message if something went wrong
        layout.addComponent(new Label(e.getMessage()));
    }
    setContent(layout);
    }
@WebServlet(urlPatterns="/*", name = "MyUIServlet",asyncSupported=true)
@VaadinServletConfiguration(ui=MyUI.class, productionMode=false)
public static class MyUIServlet extends VaadinServlet{
}
}