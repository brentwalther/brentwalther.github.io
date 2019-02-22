/*
 *Brent Walther
 *12.03.09
 *Description: Tester/Learner File on how to update a graphics window.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsUpdaterTester {
	JFrame frame;	
	Container pane;
	
	boolean right = true, vertOrHor = true, down = true, barDir = true;
	int xPos = 50, yPos = 50;
	int barPos = 50;
	
	Painter p;
	
	public static void main(String args[])
	{
		GraphicsUpdaterTester graphicsObject = new GraphicsUpdaterTester();
	}

    public GraphicsUpdaterTester() {
    	frame = new JFrame("Tester graphics object");
    	
    	pane = frame.getContentPane();
    	pane.setLayout(null);
    	
    	p = new Painter();
    	p.setSize(800,625);
    	p.setLocation(0,0);
    	
    	pane.add(p);
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,625);
		frame.setVisible(true);
    }
    public class Painter extends JPanel 
    {
    	public void paintComponent(Graphics g) 
    	{
    		g.setColor(Color.black);
    		g.fillRect(0,0,600,600);
    			    				
    		drawChoice(g, xPos, yPos);
    		drawBar(g, barPos, barDir);
    		
    		
    		/*************************************************/
			if (vertOrHor) {
				if (down) {
					if (yPos <= 250){
						yPos+=10;
						barPos+=10;
					}
					else {
						down = false;
						vertOrHor = false;
					}
				}
				else {
					if (yPos >= 50){
						yPos-=10;
						barPos-=10;
					}
					else {
						down = true;
						vertOrHor = false;
					}
				}
			}
			else {
				if (right) {
					if (xPos <= 250) {
						xPos+=10;
						barPos+=10;
					}
					else {
						right = false;
						vertOrHor = true;
						barDir = false;
					}
				}
				else {
					if (xPos >= 50) {
						xPos-=10;
						barPos-=10;
					}
					else {
						right = true;
						vertOrHor = true;
						barDir = true;
					}
				}
			}
			/***************************************************/
			
    		try {
   				Thread.sleep(25);
   				} 
   			catch (InterruptedException e) {
   				return;
   				}
    		
    		repaint();
    	}
    }
    public void drawBar(Graphics g, int pos, boolean right) 
    {
    	g.setColor(new Color(0,255,0));
    	g.fillRect(pos,350,25,10);

    }
    public void drawChoice(Graphics g, int x, int y)
    {
    	g.setColor(Color.red);
    	g.fillOval(xPos,yPos,25,25);   	
    }
    
}