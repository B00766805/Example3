package ie.jody;

class Person{
	private int staffNumber;
	private String name;
	private String phone;
	
	public Person(int s, String n, String p){
		staffNumber = s;
		name = n;
		phone = p;
	}
	public Person(String s, String n, String p){

		staffNumber = cleanStaffNumber(s);
		name = cleanName(n);
		phone = cleanPhone(p);
	}	
	private int cleanStaffNumber(String s){
		for (int x =0; x<s.length();x++){
            char c = s.charAt(x);
            if (c<48 || c>57){
                return 0;
            }
        }
        return  (Integer.parseInt(s));

	}

	private String cleanName(String n){
		if (n.length()==0){return "Invalid input";}
		char c = n.charAt(0);
		c = Character.toUpperCase(c);
		n = c + n.substring(1);
		for	(int x = 1; x<n.length();x++){
			c = n.charAt(x-1);
			if (c == ' '||c=='-'||c=='\''){
				c = n.charAt(x);
				c = Character.toUpperCase(c);
				n = n.substring(0, x) + c+  n.substring(x+1);
			}
		}
		return n;

	}
	private String cleanPhone(String p){
		
		
		if(p.length()==10){
			p = '('+p.substring(0,3)+')'+p.substring(3);
		}
		else if (p.length()==9){
			p = '('+p.substring(0,2)+')'+p.substring(2);
		}
		else{
			p =  "Invalid input";
		}
		return p;

	}
	public String getName(){return name;}
	public String getPhone(){return phone;}
	public int getStaffNumber(){return staffNumber;}
	
}	