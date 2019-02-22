/**
 * @(#)PegGame.java
 *
 *
 * @author 
 * @version 1.00 2011/1/10
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.awt.Point;

public class PegGame {

    public static void main(String args[]) throws IOException
    {    	
    	Scanner input = new Scanner(new File("pegGame.in"));
    	PrintStream writer = new PrintStream(new FileOutputStream(new File("pegGame.out")));
    	
    	int dataSets = input.nextInt();
    	for(int m = 0; m < dataSets; m++)
    	{
    		int rows = input.nextInt(), columns = input.nextInt(), target = input.nextInt(), numberOfMissingPegs = input.nextInt();
    		Point[] missingPegs = new Point[numberOfMissingPegs];
    		
    		for(int i = 0; i < missingPegs.length; i++)
    		{
    			missingPegs[i] = new Point(input.nextInt(), input.nextInt());
    		}
    		System.out.println(determineDrop(rows,columns,target,missingPegs));
    	}
    }
    public static double determineDrop(int r, int c, int t, Point[] mp)
    {
    	double left, right;
    	if(r==1)
    	{
    		System.out.print(t+" ");
    		//if(t == 0 || t == c-1)
    			return 1;
    	//	else
    		//	return .5;
    	}
    	for(Point p: mp)
    		if(r-1 == p.y && t == p.x)
    			if(r%2==1)
    			return 1;
    	if(r%2==1)
    	{
    		if(t==0)
    			return .5*determineDrop(r-1,c,t,mp);
    		else if(t == c-1)
    			return .5*determineDrop(r-1,c,c-1,mp);
    		else
    		{
    			left = determineDrop(r-1,c,t-1,mp);
    			right = determineDrop(r-1,c,t,mp);
    			return ((left+right)/2);
    		}
    	}
    	else 
    	{
    		if(t==0)
    			return 1*determineDrop(r-1,c,t,mp);
    		else if(t == c-1)
    			return 1*determineDrop(r-1,c,c-1,mp);
    		else
    		{
    			left = determineDrop(r-1,c,t,mp);
    			right = determineDrop(r-1,c,t+1,mp);
    			return ((left+right)/2);
    		}
    	}
    }
}