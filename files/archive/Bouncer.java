/*
 *Brent Walther
 *12.6.2009
 *Desc: A ball bounces around the coordinate map according to initial angle and speed
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bouncer {
        
	JFrame frame;
	Container pane;
    
    int xPos = 250, yPos = 250, speed = 1000;
    double angle =  22;
    double xVelocity = speed*Math.cos(angle * Math.PI / 180), yVelocity = speed*Math.sin(angle * Math.PI / 180);
	
	Painter p;
	
	public static void main(String[] args) {
        Bouncer mainObject = new Bouncer();
    }
        
    public Bouncer() {
    	frame = new JFrame("Bouncer");
    	
    	pane = frame.getContentPane();
    	pane.setLayout(null);
    	    	
    	p = new Painter();
    	p.setSize(500,500);
    	p.setLocation(0,0);
    	
    	pane.add(p);
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(530,535);
		frame.setVisible(true);
	
    }
    
    public class Painter extends JPanel {
    	
    	public void paintComponent(Graphics g) {
    		
    		erase(g);
 
    		if (yPos >= 500) {
   				yPos = 500;
   				yVelocity *= -1;    		
    		}
    		else if (yPos <= 0) {
    			yPos = 0;
    			yVelocity *= -1;
    		}
    		else if (xPos >= 500) {
   				xPos = 500;
   				xVelocity *= -1;    		
    		}
    		else if (xPos <= 0) {
    			xPos = 0;
    			xVelocity *= -1;
    		}
    		
    		yPos -= (int)yVelocity/100;
    		xPos += (int)xVelocity/100; 
    			
    		drawStats(g);
    		drawObject(g, xPos, yPos, 10);
    				
    		try {
    			Thread.sleep(20);
    		} catch (InterruptedException e) {
    			System.out.println(e);
    		}
    		repaint();
    		
    	}
    	
    }
    
    public void drawObject(Graphics g, int pow, int angle, int size) {
    	g.setColor(new Color(255,255,255));
    	g.fillOval(xPos - size/2, yPos - size/2, size, size);
    }
    
    public void erase(Graphics g) {
    	g.setColor(new Color(0,0,0));
    	g.fillRect(0,0,500,500);
    }
    
    public void drawStats(Graphics g) {
    	g.setColor(new Color(255,255,255));
    	g.drawString("Speed: "+speed, 5, 15);
    	g.drawString("X: "+xPos+" Y: "+yPos, 5, 25);
    }
   
}