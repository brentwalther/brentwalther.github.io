/*
 *Brent Walther
 *Date: 04.14.10
 *Desc: Goes through a String and parses operators within integers
 */

import java.util.Scanner;
public class AddEmUp {

    public static void main(String args[]) {
    	Scanner kb, parser;
    	
    	kb = new Scanner(System.in);
    	
    	System.out.print("Enter something like 8 + 133 + 1,345 + 137 : ");
    	String s = kb.nextLine(); // Takes the operater/integer input
    	
    	if(s.charAt(0) != '+' && s.charAt(0) != '-')
    		s = "+" + s;
    	    	
    	parser = new Scanner(s); // Creates a new scanner to parse through the plus signs
    	parser.useDelimiter("(\\s*[+-]\\s*)");
    	int sum = 0;
    	
    	while(parser.findInLine("\\s*\\+\\s*") != null) 
    		  sum += parser.nextInt(); // go through each plus sign and adds them   	
    			
    		
    	parser = new Scanner(s); //Creates a new scanner to parse through the minus signs
    	
    	
    	
    	while(parser.findInLine("\\s*-\\s*") != null)
    		  sum -= parser.nextInt(); //goes through all minus signs and subtracts them
    
    	System.out.println("Sum is: " + sum);
    }
    
    
}