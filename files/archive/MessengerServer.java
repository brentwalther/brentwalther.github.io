/**
 * @(#)MessengerServer.java
 *
 *
 * @author 
 * @version 1.00 2010/10/5
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Enumeration;

public class MessengerServer extends Thread {
	
	int port;
	ServerSocket sock;
	Hashtable clientMap = new Hashtable();
	
    public MessengerServer(int port) {
    	this.port = port;
    	try {
    		sock = new ServerSocket(port);
    	}
    	catch (Exception e) {
    		System.out.println("Error: "+e);
    		System.out.println("Was not able to start server socket!");
    		System.exit(0);
    	}
    }
    public void run() {
    	System.out.println("Socket is running. Listening on port: "+port);
    	System.out.println("Clients can connect to this address: "+sock.getLocalSocketAddress());
    	while(true) 
    		try 
    		{
    			new Client(sock.accept()).start();
    		}
    		catch(IOException e)
    		{
    			System.out.println("Attempted client connection failed. "+e);
    		}
    }
    public static void main(String args[]) {
    	java.util.Scanner read = new java.util.Scanner(System.in);
    	System.out.println("Enter port number to use:");
    	int port = Integer.parseInt(read.next());
    	new MessengerServer(port).start();
    }
    
    public class Client extends Thread {
    	Socket client;
    	PrintWriter out;
    	BufferedReader in;
    	
    	long clientID;
    	String nick;
    	
    	public Client(Socket client) {
    		this.client = client;
    		System.out.println("Client connected from address: "+client.getRemoteSocketAddress());
    	}
    	public void run() {
    		try 
    		{
	    		clientID = (long)(System.currentTimeMillis()*Math.random());
	    		
	    		out = new PrintWriter(client.getOutputStream(), true);
	    		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    		
	    		//Waits until the Client specifies a nickname
	    		String temp = in.readLine();
	    		while(temp.indexOf("NICK:") == -1)
	    			temp = in.readLine();
	    		nick = temp.replaceAll("NICK:","");
	    		
	    		//Sends the nick list to the client
	    		out.println(clientMap);
	    		Enumeration enu = clientMap.elements();
	    		while(enu.hasMoreElements())
					((Client)(enu.nextElement())).out.println("NEW:"+clientID+"="+this);
	    		
	    		//Adds the new client to the nick list
    			clientMap.put(new Long(clientID), this);
    			
	    		while(true)
	    		{
	    			String toParse = in.readLine();
	    			if(toParse.equals("QUIT"))
	    				break;
	    			else
	    			{
	    				long id = Long.parseLong(toParse.substring(0,toParse.indexOf(":")).trim());
	    				((Client)clientMap.get(id)).out.println(id+toParse.substring(toParse.indexOf(":")));
	    			}
	    		}
    			out.close();
    			in.close();
    			client.close();	
	    	}
	    	catch (Exception e) 
	    	{
	    		e.printStackTrace();
	    	}
			finally 
			{
				clientMap.remove(clientID);			
			}
    	}
    	public String toString()
    	{
    		return nick;
    	}
    }
}