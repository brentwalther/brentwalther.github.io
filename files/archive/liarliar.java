/**
 * @(#)liarliar.java
 *
 *
 * @author 
 * @version 1.00 2011/1/2
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class liarliar {

	static Accuser list[];
	
    public static void main(String args[]) throws IOException {
    	Scanner input = new Scanner(new File(args[0]));
    	int accusers = Integer.parseInt(input.nextLine());
    	
    	list = new Accuser[accusers];
    	
    	for(int i = 0; i < accusers; i++)
    	{
    		String accuser = input.next();
    		int accusations = input.nextInt();
    		input.nextLine();
    		Accuser a = new Accuser(accuser, accusations);
    		add(a);
    		for(int x = 0; x < accusations; x++)
    		{
    			a.tokens = a.tokens + input.nextLine() + ",";
    		}
    	}
    	System.out.println(list);
    	for(Accuser a : list)
    	{
    		a.initialize(list);
    	}
    	
    }
    
    public static void add(Accuser a)
    {
    	
    	int start = 0, end = list.length;
    	while(end - start > 1)
    	{
    		int middle = (end-start)/2;
    		if(list[middle] == null)
    			end = middle;
    		else if(list[middle].name.compareTo(a.name) < 0)
    			start = middle;
    		else
    			end = middle;
    	}
    	System.out.println("Inserting "+a+" at index "+start);
    	do
    	{
    		Accuser temp = list[start];
    		list[start] = a;
    		a = temp;
    		start++;
    	}while(list[start+1] != null);
    	list[start+1] = a;
    }
    
    static class Accuser
    {
    	private Accuser[] liars, list;
    	private int index;
    	final int accusations;
    	final String name;
    	boolean liar;
    	String tokens;
    	public Accuser(String name, int accusations)
    	{
    		liars = new Accuser[accusations];
    		index = 0;
    		this.accusations = accusations;
    		this.name = name;
    		liar = false;
    	}
    	public void initialize(Accuser[] list)
    	{
    		this.list = list;
    		String[] each = tokens.split(",");
    		for(String s : each)
    		{
    			if(!s.equals(""));
    				add(get(s));
    		}
    	}
    	public void add(Accuser a)
    	{
    		liars[index++] = a;
    		if(a.liar != !liar)
    			a.swtch();
    	}
    	public Accuser get(String s)
    	{
	    	int start = 0, end = list.length;
	    	while(end - start > 1)
	    	{
	    		int middle = (end-start)/2;
	    		if(list[middle].name.compareTo(s) < 0)
	    			start = middle;
	    		else if(list[middle].name.compareTo(s) > 0)
	    			end = middle;
	    		else
	    			return list[middle];
	    	}
	    	return null;    		
    	}
    	public void swtch()
    	{
    		liar = !liar;
    		for(Accuser a : liars)
    		{
    			if(a.liar != !liar)
    				a.swtch();
    		}
    	}
    }
    
    
}