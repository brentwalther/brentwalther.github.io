/*
 *Brent Walther
 *02.09.10
 *Desc: CSCI UIL Regionals 2005 - Problem 10
 */

import java.util.Scanner;
import java.io.*;

public class Word {

    public static void main(String args[]) throws IOException {
    	Scanner input = new Scanner(new File("./Regionals/2005/reg_judgedata_05/word.in"));
    	
    	int sets = input.nextInt();
    	String o = input.nextLine(), firstWord = "", madeWord = "";
    	
    	for(int i = 0; i < sets; i++) {
    		madeWord = "";
    		firstWord = "";
    		o = input.nextLine().toLowerCase();
    		
    		for(int p = 0;p < o.length(); p++) 
    			if (Character.isLetter(o.charAt(p)))
    				firstWord += o.charAt(p);
    			else break;
    			
    		madeWord += o.charAt(0);
    		
    		for(int x=0;x < o.length(); x++) {
    			if((int)(o.charAt(x)) == 32)
    				madeWord += o.charAt(x+1);
    		}
    		System.out.println(firstWord+" "+madeWord);
    		if(firstWord.equals(madeWord))
    			System.out.println("YES");
    		else
    			System.out.println("NO");
    		
    	}
    }
    
    
}