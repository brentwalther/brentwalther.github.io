/**
 * @(#)Hangman.java
 *
 *
 * @author 
 * @version 1.00 2011/5/23
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Hangman extends JFrame{

	Container pane, buttonPane, textPane;
	JButton[] alphabet;
	
    public Hangman() {
    	super("Hangman");
    	
    	pane = getContentPane();
    	pane.setLayout(new GridLayout(0,1));
    	
    	buttonPane = new Container();
    	buttonPane.setLayout(new GridLayout(0,9));
    	alphabet = new JButton[26];
    	for(int i = 0; i < 26; i++) {
    		alphabet[i] = new JButton(""+(char)(i+65));
    		buttonPane.add(alphabet[i]);
    	}
    	pane.add(buttonPane);
    	
    	setSize(500,500);
    	setVisible(true);
    }
    
    public static void main(String args[]) {
    	Hangman window = new Hangman();
    }
}