/*
 *Brent Walther
 *02.17.10
 *Desc: A class to perform simple arithmetic operations in other bases as well as convert from base to base
 */
public class Base {
    
    public static String toBase(String input, int in_base, int out_base) { //CONVERTS A DECIMAL NUMBER TO A DIFFERENT BASE YO
    	String output = "";
    	long dec = 0;
    	
    	for(int i = input.length()-1; i >=0; i--) {
    		dec += (int)((input.charAt(i) - (input.charAt(i) > 57 ? input.charAt(i) >  90 ? 87 : 55 : 48)) * Math.pow(in_base,(input.length() - 1 - i)));
    	}
    	
    	while (dec > 0) {
    		if(dec % out_base > 9)
    			output = (char)(dec % out_base - 10 + 'A') + output;
    		else 
    			output = dec % out_base + output; //TAKES THE REMAINDER AND ADDS IT TO THE BEGINNING YO
    		dec /= out_base; 
    	}
    	return output;
    }
    
    public static String addBase(String[] num, int base) {
    	long decimal = 0;
    	
    	for(int i=0; i < num.length; i++)
    		decimal += Integer.parseInt(toBase(num[i],base,10));
    		
    	
    	return toBase(""+decimal,10,base);  	
    }
    
    public static String multiplyBase(String[] num, int base) {
    	long decimal = 1;
    	
    	for(int i=0; i < num.length; i++)
    		decimal *= Integer.parseInt(toBase(num[i],base,10));
    		
    	
    	return toBase(""+decimal,10,base);  	
    }
    
    public static String subtractBase(String num1, String num2, int base) {   	
    	return toBase(""+(Integer.parseInt(toBase(num1,base,10)) - Integer.parseInt(toBase(num2,base,10))),10,base);  	
    }
    
    public static String divideBase(String num1, String num2, int base) {   	
    	return toBase(""+(Integer.parseInt(toBase(num1,base,10)) / Integer.parseInt(toBase(num2,base,10))),10,base);  	
    }
}