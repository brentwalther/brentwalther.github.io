/**
 * @(#)NFactorful.java
 *
 *
 * @author Brent Walther
 * @version 1.00 2011/1/29
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class NFactorful {

    public static void main(String args[]) throws IOException
    {
    	Scanner input = new Scanner(new File("NFactorful.in"));
    	PrintStream writer = new PrintStream(new FileOutputStream(new File("nfac.out")));
    	int[] primeList = primeList((int)Math.ceil(Math.sqrt(Math.pow(10,7))));
    	int runTimes = input.nextInt();
    	for(int r = 0; r < runTimes; r++)
    	{
    		input.nextLine();
    		int start = input.nextInt(), current = start;
    		int end = input.nextInt();
    		int needFactors = input.nextInt();
    		int nFactors = 0;
    		while(current <= end)
    		{
    			int primeIndex = 0, factors = 0;
    			while(true)
    			{
    				int testP = primeList[primeIndex];
    				if(testP > current)
    					break;
    				for(int i = 1; i*testP<=current; i++)
    					if(i*testP == current)
    					{
    						factors++;
    					}
    				primeIndex++;
    				if(factors > needFactors)
    					break;
    			}
    			if(factors == needFactors)
    			{
    				System.out.println("."+current);
    				nFactors++;
    				
    			}
    			current++;
    		}
    	writer.println(nFactors);
    	}
    }
    public static int[] primeList(int max)
    {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	int[] ret;
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
    			list.add(i);
    	Iterator e = list.iterator();
    	ret = new int[list.size()];
    	int index = 0;
    	while(e.hasNext())
    		ret[index++] = ((Integer)(e.next())).intValue();
    	return ret;
    }
    public static boolean isPrime(long n) {
		boolean prime = true;
		for (long i = 3; i <= Math.sqrt(n); i += 2)
			if (n % i == 0) {
				prime = false;
				break;
			}
		if (( n%2 !=0 && prime && n > 2) || n == 2) {
			return true;
		} else {
			return false;
		}
	}    
}