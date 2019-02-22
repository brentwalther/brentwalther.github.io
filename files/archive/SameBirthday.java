/*
 *Brent Walther
 *11.16.09
 *Desc: Gets input 'x' people and finds out what the chances are that out of 'x' amount of people, two share the same birthday
 */

import java.util.Scanner;
public class SameBirthday {

    public static void main(String args[]) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	int people;
    	double chance = 0;
    	
    	System.out.println("How many people do you have?");
    		people = input.nextInt();
    	
    	for(int i = people - 1; i > 0; i--) 
    		chance += i;
    		
    	System.out.println(chance);
    	chance /= 365;
    	
    	System.out.println("Chance that two share the same birthday: "+chance);
    }
    
    
}