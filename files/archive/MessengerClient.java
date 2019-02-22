/**
 * @(#)MessengerClient.java
 *
 *
 * @author 
 * @version 1.00 2010/10/5
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MessengerClient extends Thread implements ActionListener {
	
	BufferedReader in;
	PrintWriter out;
	String name;
    Socket sock;
    
    ArrayList<String> friends;
    
    JList list;
    DefaultListModel listModel;
	
	public MessengerClient() throws IOException
	{
		
    	BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Please enter the host address:");
    	String host = read.readLine();
    	System.out.println("Please enter the port:");
    	int port = Integer.parseInt(read.readLine());
    	System.out.println("Please enter your name:");
    	name = read.readLine();
    	try {
    		sock= new Socket(host, port);
    		out = new PrintWriter(sock.getOutputStream(), true);
	    	in = new BufferedReader(new InputStreamReader(sock.getInputStream()));	    	
    	}
    	catch (IOException e) {
    		System.out.println("Could not connect: "+e);
    		System.exit(0);
    	}
    	createGUI();
	}
	
	public void run()
	{
		friends = new ArrayList<String>();
		try
		{
			out.println("NICK:"+name);
			String temp[] = in.readLine().replaceAll("[{}]","").split(",");			
			for(int i = 0; i < temp.length; i++)
				if(!temp[i].equals(""))
					friends.add(temp[i]);
			
			Iterator<String> i = friends.iterator();
			while(i.hasNext())
			{
				String c = i.next();
				listModel.addElement(c.substring(c.indexOf("=")+1));
			}
				
			while(true)
			{
				if(!in.ready())
					continue;
				String toParse = in.readLine();
				System.out.println(toParse);
				if(toParse.startsWith("NEW:"))
				{
					friends.add(toParse.substring(4));
					listModel.addElement(toParse.substring(toParse.indexOf("=")+1));
				}
				else
				{
					if(Conversation.findConversation(Long.parseLong(toParse.substring(0,toParse.indexOf(":")).trim()))!=null)
						System.out.print("found the recipient");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void createGUI()
	{
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(150,300);
		frame.setResizable(true);
		frame.setLayout(new BorderLayout());

        //Create the list and put it in a scroll pane.
        listModel = new DefaultListModel();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        
        JButton button = new JButton("Chat");
        button.addActionListener(this);
        
        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

	}
    public static void main(String args[]) throws IOException
    {
    	new MessengerClient().start();
    }
    public void actionPerformed(ActionEvent e)
    {
    	String str = (String)friends.get(list.getSelectedIndex());
    	System.out.println("Chose: "+str);
    	long id = Long.parseLong(str.substring(0,str.indexOf("=")).trim());
    	str = str.substring(str.indexOf("=")+1);
    	new Conversation(str, id, this);
    	
    }
}