/**
 * @(#)StudiousStudent.java
 *
 *
 * @author 
 * @version 1.00 2011/1/7
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class StudiousStudent {

    public static void main(String args[]) throws IOException
    {
    	Scanner input = new Scanner(new File("student.in"));
    	PrintStream writer = new PrintStream(new FileOutputStream(new File("student.out")));
    	
    	int dataSets = input.nextInt();
    	input.nextLine();
    	for(int i = 0; i < dataSets; i++)
    	{
    		int numberOfWords = input.nextInt();
    		
    		String temp = input.nextLine().trim();
    		Scanner tokenizer = new Scanner(temp);
    		
    		ArrayList<String> items = new ArrayList<String>();
    		while(tokenizer.hasNext())
    			items.add(tokenizer.next());
    			
    		String output = "";
    		while(!items.isEmpty())
    		{
    			int index = items.size()-1, lowest = 0;
    			String firstword = "";
    			while(index >= 0)
    			{    				
    				if(compare(items.get(index),items.get(lowest)) < 0)
    					lowest = index;
    				index--;
    			}
    			output += items.get(lowest);
    			items.remove(lowest);
    		}
    		writer.println(output);
    		
    	}
    	writer.close();
    }
    
    public static int compare(String a, String b)
    {
    	int limit = (a.length() > b.length()?a.length():b.length());
    	for(int i = 0; i <limit; i++)
    	{
    		if(i != a.length() && i != b.length())
    		{
    			if(a.charAt(i) != b.charAt(i))
    				return a.charAt(i)-b.charAt(i);
    		}
    		else
    			return b.length()-a.length();
    	}
    	return 0;
    }
    
    
}