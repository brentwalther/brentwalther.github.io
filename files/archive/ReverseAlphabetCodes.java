/*
 *Brent Walther
 *12.09.09
 *Desc: code for classname challenge on cstutoringcenter.com
 */

import java.util.Scanner;

public class ReverseAlphabetCodes {

    public static void main(String args[]) {
    	Scanner input = new Scanner(System.in);
    	String theString = input.next();
    	
    	char currentChar;
    	int sum = 0, add;
    	
    	for(int i = 0; i < theString.length(); i++) {
    		currentChar = theString.charAt(i);
    		add = 27 - (currentChar - 96);
    		System.out.println(add);
    		sum += add;
    	}
    	System.out.println(sum);
    }
    
    
}