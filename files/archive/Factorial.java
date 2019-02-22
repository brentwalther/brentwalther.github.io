/*
 *Brent Walther
 *01.04.10
 *Desc: Calculates factorials up to input value
 */

import java.util.Scanner;
public class Factorial {

    public static void main(String args[]) {
    	
    	Scanner input = new Scanner(System.in); // initialize scanner
    	int max, lastIter = 1;
    	
    	System.out.println("Enter the maximum factorial to iterate to:");
    		max = input.nextInt(); // get maximum number
    	
    	
    	for(int i = 1; i <= max; i++) {
    		lastIter *= i; // takes the last iteration's product and muiltiplies it witht he next iteration
    		System.out.println(i+"! \t"+lastIter);
    	}
    	
    	//Prints lines
    	System.out.print("\n\n\n\n\n");
    	
    	for(int i = 1; i <= max; i++) { //loops through each line 
    		lastIter = 1; // Sets the product to one with each iteration
    		for(int x = 1; x <= i; x++) // finds the factorial of the current line
    			lastIter *= x; // loops through each number [1 - x] to find factorial of x
    		System.out.println(i+"! \t"+lastIter);
    	}
    }
    
    
}