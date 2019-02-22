/*
 *Brent Walther
 *01.20.10
 *Description: Creates a diamond (much faster)
 */

import java.util.Scanner;
public class DiamondProgram {

    public static void main(String args[]) {
    	
    	
    	int max = new Scanner(System.in).nextInt();  
    		
    	double startTime = System.currentTimeMillis();  
    			
    	boolean bool = false;
    	int i = 1, b = max % 2 == 0 ? (int)(max / 2) : (int)(max / 2) +1;   
    		
    	while (i>0) {   		
    		if (i>max-2) 
    			bool = true;
    			
    		if (bool){
    			System.out.println(str(" ",b++)+""+str("*",i));
    			i-=2;
    		}  	
    						
    		else {
    			System.out.println(str(" ",b--)+""+str("*",i));
    			i+=2;
    		}
    	}
    	
    	double endTime = System.currentTimeMillis();    	
    	System.out.println("Program took: "+ (endTime - startTime) +"ms");
    }
    
    public static String str(String string, int iter) {
    	String result = "";
    	for(int i = 0; i<iter; i++) {
    		result = result+string;
    	}
    	return result;
    }
    
    
}