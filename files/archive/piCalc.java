/*
 *Brent Walther
 *Date: 10.07.09
 *Description: Calculates pi to specified number of places
 */
import java.util.*;
public class piCalc {

    public static void main(String args[]) {
    	
    	Scanner input = new Scanner(System.in);
    	int numPlaces = input.nextInt();
    	
    	int remainder = 4, output;
    	final int DIVISOR = 7;    
    		
    	System.out.print("3.14");
    		
    	for(int i=1;i<numPlaces;i++) {
    		remainder = (remainder*10) % DIVISOR;
    		output = (int)(remainder*10/DIVISOR);
    		System.out.print(output);  
    	}
    }
    
    
}