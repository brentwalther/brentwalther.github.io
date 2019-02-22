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

public class Diamond {
    public static void main(String args[]) {
    	Scanner in = new Scanner(System.in);
    	
    	System.out.println("What shall be the width:");
    		int width = Integer.parseInt(in.next());
    		
    	System.out.println(d((width - 1)/2,(width%2==0?2:1),true));
    }
    
    public static String d(int a, int b, boolean c) {    	
    	return (b>0?(s(" ",a)+s("*",b)+"\n"+(a>0&&c?d(--a,b+2,c):d(++a,b-2,false))):"");
    }
    public static String s(String r, int i) {
    	return (i==0?"":(i>1?r+s(r,--i):r));
    }
}