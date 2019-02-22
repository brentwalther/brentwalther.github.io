/*
 *Brent Walther
 *11.17.09
 *Desc: Adds factorials 1-x and prints the sum
 */

import java.util.Scanner;
public class AddFactorial {

    public static void main(String args[]) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	int max;
    	long sum = 0, tempSum = 1;
    	
    	System.out.println("Please input the max number factorial to iterate to:");
    		max = input.nextInt();
    	
    	for(int i = 1; i <= max; i++) {		// loops through each number and adds factorial to sum
    		for(int t = 1; t <= i; t++){	// loops through the iterator to find it's factorial
    			tempSum *= t;
    		}
    		
    		sum += tempSum;
    		tempSum = 1;
    	}
    	
    	System.out.println("Sum is: "+sum); // Prints result
    }
    
    
}