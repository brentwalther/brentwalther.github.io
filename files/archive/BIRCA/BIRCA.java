/**
 * @(#)BIRCA.java
 *
 * BIRCA Applet application
 *
 * @Brent Walther
 * @version 1.00 2010/12/15
 */
 
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class BIRCA implements ActionListener{
	
	JFrame application;
	JPanel panel;
	Container content;
	
	JTextArea output;
	JScrollPane scroller;
	
	JTextField input;
	InputInterpreter inputParser;
	
	NavTree nav;
	Channel active;
	
	
	//ArrayList<Server> serverList;
	Server serv;
	
	public BIRCA() {
		application = new JFrame("bIRCA");
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.pack();
		application.setVisible(true);
		application.setJMenuBar(createMenuBar());
		
		content = application.getContentPane();

        /*Create a scrolled text area.*/
        output = new JTextArea(5, 30);
        output.setEditable(false);
        output.setLineWrap(true);
        scroller = new JScrollPane(output);
        
        /*The Channel/Server Navigation*/        
        //nav = new NavTree("bIRCA");
        
        /*The input box*/
        input = new JTextField();
        inputParser = new InputInterpreter(this);
        input.addActionListener(inputParser);
                

        /*Add the text area to the content pane.  */      
        //content.add(nav, BorderLayout.WEST);
        content.add(scroller, BorderLayout.CENTER);
        content.add(input, BorderLayout.SOUTH);
		application.setSize(800,600);
		
		//serverList = new ArrayList<Server>();
		active = null;
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu actions = new JMenu("Actions");
		JMenuItem newServer = new JMenuItem("New Server");
		newServer.addActionListener(this);
			
		actions.add(newServer);
		
		menuBar.add(actions);
		
		return menuBar;
	}
	
	public void update() {
		output.setText("");
		Enumeration lines = active.messageBuffer.elements();
		while(lines.hasMoreElements())
			output.append(lines.nextElement()+"\n");
	}
	
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());
		if(source.getText().equalsIgnoreCase("New Server")) {				
			String host = JOptionPane.showInputDialog(null, "Enter the host address:");
			if(!host.equals("")){
				Server newServ = new Server(host, 6667, this);
				//serverList.add(newServ);	
				//nav.add(newServ.getTreeNode());
				active = newServ.channelList.get(0);
				serv = newServ;
			}			
		}	
	}
	
	public static void main(String args[]) {
		BIRCA application = new BIRCA();	
	}
}