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

public class Conversation extends JFrame implements ActionListener
{
	JTextArea output;
	JScrollPane scroller;
	JTextField input;
	
	long clientID;
	MessengerClient list;
	
	static ArrayList<Conversation> convos = new ArrayList<Conversation>();
	public Conversation(String title, long id, MessengerClient client)
	{
		super(title);
		list = client;
		clientID = id;
		convos.add(this);
		
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
		list.out.println(clientID+": "+input.getText());
		output.append("ME: "+input.getText()+"\n");
		input.setText("");
	}
	public static Conversation findConversation(long id)
	{
		Iterator i = convos.iterator();
		while(i.hasNext())
		{
			Conversation current = (Conversation)i.next();
			if(current.clientID == id)
				return current;
		}
		return null;
	}
}