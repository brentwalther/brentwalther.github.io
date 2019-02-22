/*
 *Brent Walther
 *11.17.09
 *Desc: User input's 'x' and then it calculates 2^x and then adds the digits of that numbers
 */

import java.util.Scanner;
public class AddBitDigits {

    public static void main(String args[]) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	int exponent, sum = 0;
    	long calculated;
    	String parseCalc = "";
    	
    	System.out.println("Please input the exponent:");
    		exponent = input.nextInt();
    		
    	calculated = (long)Math.pow(2,exponent);
    	parseCalc = ""+calculated;
    	
    	for(int i = 0; i < parseCalc.length(); i++) {
    		sum += parseCalc.charAt(i) - 48;
    	}
    	
    	System.out.println("Sum is: "+sum);
		
    }
    
    
}