/*
 *Brent Walther
 *03.02.10
 *Desc: Java UIL Regionals 2005 Problem 5
 */
import java.io.*;
import java.util.Scanner;

public class Digdog {

    public static void main(String args[]) throws IOException {
    	Scanner input = new Scanner(new File("./reg_judgedata_05/digdog.in"));
    	
    	int sets = Integer.parseInt(input.nextLine()), size = 0, digCount;
    	String[] yard = new String[20];
    	String output = "";
    	
    	for(int i = 0; i < sets; i++) {
    		size = Integer.parseInt(input.nextLine());
    		digCount = 0;
    		
    		for(int x = 0; x < size*2; x++)
    			yard[x] = input.nextLine();
    			
    		for(int x = 0; x < size*size; x++)
    				if(yard[x/size].charAt(x%size) != '.' && yard[x/size + size].charAt(x%size) != '.')
    					digCount++;
    		
    		output = (digCount > 0 ? "BAD" : "GOOD") + "DOG";
    		System.out.println(output);
    	}   	
    }    
}