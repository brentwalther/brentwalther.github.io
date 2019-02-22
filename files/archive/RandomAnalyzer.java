/**
 * Brent Walther
 */
 import *;

public class RandomAnalyzer {

    public static void main(String args[]) {
    	double sum = 0, counter = 0;
    	while(true) {
    		sum += Math.random();
	   		counter++;
	   		System.out.println(sum/counter);
    	}
    }
}