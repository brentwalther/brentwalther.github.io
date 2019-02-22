/*
 *Brent Walther
 *11.18.09
 *Desc: Takes the sum of the roots up untill input 'x' and then takes the root of that sum.
 */
import java.util.Scanner;
public class SumOfRoots {

    public static void main(String args[]) {
    	Scanner input = new Scanner(System.in);
    	
    	int iterations;
    	double sum = 0;
    	
    	System.out.println("Please input the max number:");
    		iterations = input.nextInt();
    		
    	for(int i = 1; i <= iterations; i++) {
    		sum += Math.sqrt(i);
    	}
    	System.out.println(Math.sqrt(sum));
    		
    }
    
    
}