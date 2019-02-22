/*
 *Brent Walther
 *02.09.10
 *Desc: CSCI UIL Regionals 2005 - Problem 8
 */

import java.util.Scanner;
import java.io.*;

public class Juggle{

    public static void main(String args[]) throws IOException {
    	Scanner input = new Scanner(new File("./Regionals/2005/reg_judgedata_05/juggle.in"));
    	
    	int sets = input.nextInt(), zeros;
    	String set = input.nextLine();
    	int[] toss = new int[40];
    	boolean valid;
    	
    	for(int i = 0; i < sets; i++) {
    		set = input.nextLine();
    		valid = true;
    		
    		for (int r=0; r < toss.length; r++)
				toss[r] = -1;
    		
    		for(int p = 0; p < 40; p++) {
    			
    			if (p < set.length())
    				toss[p] = set.charAt(p) - 48;
    			zeros = 0;
    			for(int m = 0; m < p; m++){
    				if(--toss[m] == 0){
    					zeros++;
    				}
    				
    			}
    			if (zeros > 1) {
    				System.out.println("DROPPED "+(zeros-1)+" on step "+(p+1));
    				valid = false;
    				break;
    			}
    		}
    		if (valid)
    			System.out.println("VALID");
    	}
    }
    
}
