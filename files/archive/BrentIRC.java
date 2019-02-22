/**
 * @(#)MessengerClient.java
 *
 *
 * @author 
 * @version 1.00 2010/10/5
 */
import java.util.Vector;
import java.util.Enumeration;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrentIRC extends Thread implements ActionListener {
	
	BufferedReader in;
	PrintWriter out;
	String name;
    Socket sock;
    
    String active;
    Vector<String> buffer;
    
    JTextArea output;
	JScrollPane scroller;
	JTextField input;
    
    JMenuBar menu;
    JMenu chans;
	
	public BrentIRC() throws IOException
	{
		
    	String host = JOptionPane.showInputDialog(null, "Please enter the host address:");
    	int port = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the port:"));
    	name = JOptionPane.showInputDialog(null, "Please enter your nickname:");
    	buffer = new Vector<String>();
    	
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
		try
		{
			out.println("NICK "+name);
        	out.println("USER BrentIRC BrentIRC BrentIRC :BrentIRC");
        	
			while(true)
			{
				if(!in.ready())
					continue;
				String data = in.readLine();
				String args[] = data.split(" ");
				String argz[] = splitRawData(data);
				System.out.println("(1)"+argz[0]+" (2)"+argz[1]+" (3)"+argz[2]+" (4)"+argz[3]);
				if(args[0].equalsIgnoreCase("PING"))
					out.println("PONG "+args[1]);
				else
				{
					buffer.add(data);
					clearOutputWindow();
					populateOutputWindow();
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

        /* Create a scrolled text area */
    	output = new JTextArea(5, 30);
    	output.setEditable(false);
    	output.setLineWrap(true);
    	output.setSize(500,400);
    	scroller = new JScrollPane(output);
    	
    	/* Create the input box */
    	input = new JTextField();
    	input.addActionListener(this);
    	input.setSize(500,100);
        
        /* Retrieve the default menu bar */
        menu = defaultMenuBar();
        active = "Server";
        
        /* Add all the componenets to the frame */
        frame.setJMenuBar(menu);
        frame.add(scroller, BorderLayout.CENTER);
        frame.add(input, BorderLayout.SOUTH);

	}
    public static void main(String args[]) throws IOException
    {
    	Thread client = new BrentIRC();
    	client.start();
    }
    public void actionPerformed(ActionEvent e)
    {
    	System.out.println(e);
    	if(e.getSource() instanceof JTextField) 
    	{
    		JTextField source = (JTextField)e.getSource();
    		String message = source.getText();
    		out.println(message);
    		source.setText("");
    	}
    	else if(e.getSource() instanceof JMenuItem)
    	{
    		JMenuItem source = (JMenuItem)e.getSource();
    		if(source.getText().equals("Add Channel"))
    		{
    			String newChan = JOptionPane.showInputDialog(null, "Enter the channel name:");
    			out.println("JOIN :"+newChan);
    			JMenuItem temp = new JMenuItem(newChan);
    			temp.addActionListener(this);
    			chans.add(temp,0);
    		}
    		else
    		{
    			active = source.getText();
    			clearOutputWindow();
    			populateOutputWindow();
    		}
    	}
    	
    }
    
    private void clearOutputWindow()
    {
    	output.setText("");
    }
    private void populateOutputWindow()
    {
    	Enumeration<String> en = buffer.elements();
    	while(en.hasMoreElements())
    	{
    		String temp = en.nextElement();
    		String args[] = splitRawData(temp);
    		if(args[2].equalsIgnoreCase(active))
    		{
    			output.append("< " + args[0].substring(0,args[0].indexOf("!")) + " >" + " " +args[1]+" "+ args[3] + "\n");
    		}
    		if(!args[2].contains("#") && active.equals("Server"))
    		{
    			output.append(temp+"\n");
    		}
    	}
    }
    
    /*
     * This method splits raw IRC data into arguments.
     * Argument 1, index 0 - address of the person who sent the data
     * Argument 2, index 1 - command (PRIVMSG, JOIN, PART, raw, etc)
     * Argument 3, index 2 - channel
     * Argument 4, index 3 - rest (the message)
     */
    
    private String[] splitRawData(String raw)
    {
    	raw = raw.substring(1); //Cut first ':' off
    	int cutoff = raw.indexOf(":");
    	String ret[] = raw.substring(0,cutoff).split(" ");
    	ret = new String() { ret, raw.substring(cutoff+1)
    	}
    	return ret;
    }
    
    private JMenuBar defaultMenuBar()
    {
    	JMenuBar menuBar = new JMenuBar();
		
		chans = new JMenu("Channels");
		JMenuItem op1 = new JMenuItem("Server");
		JMenuItem op2 = new JMenuItem("Add Channel");
		op1.addActionListener(this);
		op2.addActionListener(this);
		
		chans.add(op1);
		chans.addSeparator();
		chans.add(op2);
		
		menuBar.add(chans);
		
		return menuBar;
	}
}