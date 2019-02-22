/*
 *Brent Walther
 *1.8.2011
 *Desc:
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class DoubleSquares {
	
    public static void main(String[] args) throws IOException
    {
        int[] squares = new int[46341];
        int i = 0;
        while(i<squares.length)
        	squares[i] = i*i++;
        
        
        Scanner input = new Scanner(new File("doubleSquares.in"));
    	PrintStream writer = new PrintStream(new FileOutputStream(new File("doubleSquares.out")));
    	
    	int dataSets = input.nextInt();
    	for(int m = 0; m < dataSets; m++)
    	{
    		input.nextLine();
    		int candidate = input.nextInt();
    		int startPoint = (int)(Math.sqrt(candidate));
    		int matches = 0;
    		while(squares[startPoint] >= (int)(Math.sqrt(candidate)))
    		{
    			i = 0;
    			while(startPoint >= i)
    				if(squares[startPoint]+squares[i++]==candidate)
    				{
    					matches++;
    				}
    			startPoint--;
    			if(startPoint < 0)
    				break;
    		}
    		writer.println(matches);
    	}
    }
}
