/*
 *Brent Walther
 *03.02.10
 *Desc: Java UIL Regionals 2005 Problem 2
 */
import java.io.*;
import java.util.Scanner;

public class Brain {

    public static void main(String args[]) throws IOException {
    	Scanner input = new Scanner(new File("./reg_judgedata_05/brain.in"));
    	
    	int sets = Integer.parseInt(input.nextLine());
    	String first = "", second = "";
    	
    	for(int x = 0; x < sets; x++) {
    		first = input.nextLine();
    		second = input.nextLine();
    		for(int i = 0; i < first.length(); i++)
    			if (first.charAt(i) == second.charAt(i))
    				System.out.print(first.charAt(i));
    			else
    				System.out.print("*");
    		System.out.println();
    	}
    }
    
    
}