/*
 *Brent Walther
 *09.26.09
 *Description: Finds prime numbers up to number x
 */
 import java.util.*;
public class primeFinder {
    
    public static void main(String[] args) {
    	
   		Scanner input = new Scanner(System.in);
   		
   		System.out.println("Input number of primes to count:");
   		    int maximum = input.nextInt();
   		    
   		boolean isPrime = true;
   		long sumOfPrime = 0, count = 0;
   		
   		for(int i=1; i <= maximum; i+=2) {
   			for(int b=2; b<i; b++) {
   				if (i%b == 0) isPrime = false;
   			}
   			if (isPrime) {
   				System.out.println(i);
   				sumOfPrime += i;
   				count++;
   			}
   			isPrime = true;
   		}
   		System.out.println("Sum of primes counted: "+sumOfPrime);
   		System.out.println("Number of primes counted: "+count);
    }
}
