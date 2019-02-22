/*
 *Brent Walther
 *10.09.09
 *Description: prompts user for fahrenhiet input and converst to celsius
 */

import java.util.*;
public class fahrenheitToCelsius {

    public static void main(String args[]) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Please input fahrenheit value to convert to celsius:");
    		double fahrenheit = input.nextInt();
    	
    	System.out.println(fahrenheit+ " degrees fahrenheit converts to "+((fahrenheit-32)*5/9)+" degrees celsius");
    }
    
    
}