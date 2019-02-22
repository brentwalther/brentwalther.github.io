/**
 * @(#)PrimeNumber.java
 *
 *
 * @Brent Walther
 * @version 1.00 2010/11/8
 */
import java.util.Scanner;

public class PrimeNumber {

    public static void main(String args[]) {
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Please input the maximum number:");
    	int max = Integer.parseInt(input.next());
    	
    	long start = System.currentTimeMillis();
    	boolean notPrime[] = new boolean[max];
    	
    	for(int i = 2; i < max; i+=2) {
    		if(notPrime[i])
    			continue;
    		for(int o = i+1; o < max; o++) {
    			if(notPrime[o])
    				continue;
    			if(o%i == 0)
    				notPrime[o] = true;
    		}
    	}
    	for(int i = 2; i < max; i++)
    		if(!notPrime[i])
    			System.out.println(i);
    	System.out.print((System.currentTimeMillis() - start)+"ms");
    }
    
    
}