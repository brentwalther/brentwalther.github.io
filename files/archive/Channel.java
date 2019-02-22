/**
 * @(#)Conversation.java
 *
 *
 * @author 
 * @version 1.00 2010/12/17
 */
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class Channel extends JFrame implements ActionListener
{
	JTextArea output;
	JScrollPane scroller;
	JTextField input;
	
	String channelName;
	BrentIRC client;
	
	public Channel(String title, BrentIRC client)
	{
		super(title);
		this.client = client;
		channelName = title;
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		/*Create a scrolled text area.*/
    	output = new JTextArea(5, 30);
    	output.setEditable(false);
    	output.setLineWrap(true);
    	output.setSize(500,400);
    	scroller = new JScrollPane(output);
    	
    	input = new JTextField();
    	input.addActionListener(this);
    	input.setSize(500,100);
    	add(scroller, BorderLayout.CENTER);
    	add(input, BorderLayout.SOUTH);
    	pack();
	}
	public void actionPerformed(ActionEvent e) {
		client.out.println("PRIVMSG "+channelName+": "+input.getText());
		output.append(client.name+": "+input.getText()+"\n");
		input.setText("");
	}
/*	public static Conversation findConversation(long id)
	{
		Iterator i = convos.iterator();
		while(i.hasNext())
		{
			Conversation current = (Conversation)i.next();
			if(current.clientID == id)
				return current;
		}
		return null;
	}*/
}