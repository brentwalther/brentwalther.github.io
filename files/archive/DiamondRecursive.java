/**
 * @(#)Diamond.java
 *
 *
 * @Brent Walther 
 * @version 1.00 2010/11/4
 *
 * @Two liner diamond program. 
 * @The user inputs a number, and the program draws a diamond of the width
 */
import java.util.Scanner;

public class DiamondRecursive {
    public static void main(String args[]) {
    	Scanner in = new Scanner(System.in);
    	
    	System.out.println("What shall be the width:");
    		int width = Integer.parseInt(in.next());
    		
    	System.out.println(d((width - 1)/2,(width%2==0?2:1),true,-1,-1));
    }
    
    public static String d(int spaces, int stars, boolean c, int space, int star) {
    	String ret = "";
    	if(star >= 0) {
    		if(star == 0)
    			return "";
    		else
    			return "*"+d(stars,spaces,c,space,--star);
    	}
    	if(space>=0) {
    		if(space == 0)
    			return "";
    		else
    			return " "+d(stars,spaces,c,--space,star);
    	}
    	if(stars > 0) {
    		ret += d(spaces,stars,c,spaces,star) + d(stars,spaces,c,space,stars) + "\n";
    		if(spaces > 0 && c)
    			ret += d(--spaces,stars+2,c,star,space);
    		else
    			ret += d(++spaces,stars-2,false,star,space);
    	}
    	return ret;
    }
    
    
}