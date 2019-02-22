/**
 * @(#)FirstOrLast.java
 *
 *
 * @author 
 * @version 1.00 2011/1/15
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

public class FirstOrLast {

    public static void main(String args[]) throws IOException
    {
    	Scanner input = new Scanner(new File("firstOrLast.in"));
    	PrintStream writer = new PrintStream(new FileOutputStream(new File("firstOrLast.out")));
    	
    	int dataSets = input.nextInt();
    	input.nextLine();
    	for(int i = 0; i < dataSets; i++)
    	{
    		int risks = input.nextInt()-1;
    		int pairs = input.nextInt();
    		int[] overtake = new int[pairs], normal = new int[pairs], choices = new int[risks--], orignial = new int[pairs*2];
    		for(int p = 0; p < pairs; p++)
    		{
    			int first = input.nextInt(), sec = input.nextInt();
    			overtake[p] = first;
    			normal[p] = sec;
    			original[2*p] = first;
    			original[2*p+1] = sec;
    		}
    		Arrays.sort(overtake);
    		Arrays.sort(normal);
    		
    		for(int s = 0; s < overtake.length; s++)
    		{
    			int np = unsortedPairs.get(overtake[s]);
    			int n = 0; 
    			while(n < normal.length)
    				if(normal[n++] == np)
    				{
    					n--;
    					break;
    				}
    			if(s<n)
    				choices[risks--] = s;
    			if(risks == -1)
    				break;
    		}
    		
    	}
    }
}