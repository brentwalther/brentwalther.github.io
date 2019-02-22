/**
 * @(#)Cluster.java
 *
 *
 * @author 
 * @version 1.00 2010/12/21
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;

public class Cluster 
{
	File fileName;
	public Cluster(String fileName)
	{
		this.fileName = new File(fileName);
	}
	
    public void findClusters() throws FileNotFoundException
    {
    	ArrayList<User> users = new ArrayList<User>();
    	
    	Scanner parser = new Scanner(fileName);
    	while(parser.hasNextLine())
    	{
    		String line = parser.nextLine(), firstUser, secondUser;
    		line = line.substring(line.indexOf("\t")+1);
    		firstUser = line.substring(0,line.indexOf("\t"));
    		secondUser = line.substring(line.indexOf("\t")+1);
    		
    		boolean shouldBeAdded = true;
    		Iterator i = users.iterator();
    		while(i.hasNext())
    		{
    			User current = (User)i.next();
    			if(current.email.equals(firstUser))
    			{
    				current.addInteraction(secondUser);
    				shouldBeAdded = false;
    			}
    		}
    		if(shouldBeAdded)
    		{
    			User newOne = new User(firstUser);
    			newOne.addInteraction(secondUser);
    			users.add(newOne);
    		}
    	}
    	Iterator i = users.iterator();
    	while(i.hasNext())
    	{
    		System.out.println(((User)i.next()).interactions);
    	}
    }
    class User
    {
    	String email;
    	ArrayList<String> interactions;
    	public User(String email)
    	{
    		this.email = email;
    		interactions = new ArrayList<String>();
    	}
    	public void addInteraction(String with)
    	{
    		interactions.add(with);
    	}
    }
	public static void main(String args[])
	{
		Cluster finder = new Cluster("clusterlog.in");
		try
		{
			finder.findClusters();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}