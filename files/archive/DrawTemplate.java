/**
 * @(#)DrawTemplate.java
 *
 *
 * @author 
 * @version 1.00 2010/10/19
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class DrawTemplate extends JFrame{
	
	Painter p;
	Container pane;
	
	public DrawTemplate() {    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		//Size of the window: (x, y)
		setSize(600,600);
		//Set if the window can be resized
		setResizable(false);
		  	
    	pane = getContentPane();
    	p = new Painter();
    	
    	pane.add(p);
    }
    
    class Painter extends JPanel {
		public void paint(Graphics g) {
			/*
			 *DRAW STUFF HERE
			 */
			 
			//This block pauses the program for a specified number of milliseconds
			//Uncomment it if you want to use it.
			/*
			try {
				Thread.sleep(10);
			}
			catch (Exception e) {
			}
			*/
			repaint();
		}
    }
    
    //Do not put anything in the main method
    public static void main(String args[]) {
    	WanderingLines window = new WanderingLines();
    }
}