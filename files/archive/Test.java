/**
 * @(#)Test.java
 *
 *
 * @author 
 * @version 1.00 2010/9/13
 */
import java.io.*;
import java.util.Scanner;

public class Test {

    public static void main(String args[]) throws IOException
    {    	
    	Xyl a1 = new Xyl(5);
    	Xyl a2 = new Xyl(11);
    	Xyl.L = 7;
    	System.out.println(a1.product() + " " + a2.product());
    }    
    
    /*public static void check(int x, int y) {
    	if(x <= 3000) {
    		if(y == 0) {
    			if(x%500 == 0) {
    				if(x%3 == 0)
    					check(x,10);
    				else {
    					System.out.println(x++);
    					check(x,0);
    				}
    			}
    			else
    				check(++x,0);
    		}
    		else {
    			System.out.println(x);
    			if(y > 1)
    				check(x,y-1);
    			else
    				check(++x,y-1);
    		}    				
    	}
    }*/
}