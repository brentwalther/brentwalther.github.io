/*
 *Brent Walther
 *10.07.09
 *Description: takes an amount and shows the amount with interest compunded annually at 10%
 */

import java.util.*;
public class interestCompund {

    public static void main(String args[]) {
    	Scanner input = new Scanner(System.in);
    	int initAmount = input.nextInt();
    	final double RATE = .1;
    		
    	for(int i=1;i<60;i++) {
    		initAmount += initAmount * RATE;
    	}
    	System.out.println(initAmount);
    }
    
    
}