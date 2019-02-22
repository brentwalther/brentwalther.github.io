/*
 *Brent Walther
 *11.18.09
 *Desc: Practicing method creation
 */

import java.util.Scanner;
public class MethodPractice {

    public static void main(String args[]) 
    {
    	Scanner input = new Scanner(System.in);  
    			
    	double num1 = 5, num2 = 12, perimeter;
    	
    	perimeter = perimeter(num1, num2);
    	
    	System.out.println("The perimeter to the triangle is: "+perimeter);
    }
    
    public static double findHypotenuse(double n1, double n2)
    {
    	return Math.sqrt(n1*n1+n2*n2);
    }
    
    public static double perimeter(double s1, double s2)
    {
    	return s1 + s2 + findHypotenuse(s1, s2);
    }
    
    
}