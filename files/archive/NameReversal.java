/*
 *Brent Walther
 *12.09.09
 *Desc: User inputs a name and program prints the name reversed.
 */

import java.util.Scanner;

public class NameReversal {

    public static void main(String args[]) {
    	
    	// Declare and initializew scanner object to recieve input
    	Scanner input = new Scanner(System.in);
    	
    	//decalre variable for storage of input
    	String name;
    	
    	//grabs input
    	System.out.println("Please input a name: ");
    		name = input.nextLine();
    	
    	//Prints string in reverse order
    	for(int i = name.length() - 1; i >= 0; i--) {
    		System.out.print(name.charAt(i));
    	}
    }
    
    
}