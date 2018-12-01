package ie.jody;

class Cleaner{

    public double cleanInput (String s){
    
        for (int x =0; x<s.length();x++){
            char c = s.charAt(x);
            if (c<48 && c>57){
                return 25.0;
            }
        }
        return  Double.parseDouble(s);
    }

}