/**
 * @(#)DiminishingCircle.java
 *
 *
 * @author 
 * @version 1.00 2011/1/25
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class DiminishingCircle {

    public static void main(String args[]) throws IOException
    {
    	Scanner input = new Scanner(new File("diminishingCircle2.in"));    	
    	//PrintStream writer = new PrintStream(new FileOutputStream(new File("diminishingCircle.out")));
    	
    	int iterations = input.nextInt();
    	while(iterations-- > 0)
    	{
    		input.nextLine();
    		long N = input.nextLong(), K = input.nextLong();
    		long index = 1;
    		for(int i = 0; i < N-1; i++)
    			index+= K + i + 1;
    		System.out.println(index%(N-1));
    	}
    }
    public static long fact(long N)
    {
    	if(N == 1)
    		return 1;
    	else return N*fact(N-1);
    }
}