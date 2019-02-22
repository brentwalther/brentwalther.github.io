/*
 *Brent Walther
 *12.6.2009
 *Desc: A ball bounces around the coordinate map according to initial angle and speed
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PingPong implements KeyListener {
        
	JFrame frame;
	Container pane;
    
    int xPos = 250, yPos = 250, speed = 800, paddleSize = 60, p1Paddle = paddleSize / 2, p2Paddle = paddleSize / 2, p1Score = 0, p2Score = 0;
    double angle = 37;
    double xVelocity = speed*Math.cos(angle * Math.PI / 180), yVelocity = speed*Math.sin(angle * Math.PI / 180);
	
	boolean p1Moving, p2Moving, p1Up, p2Up;
	Painter p;
	
	public static void main(String[] args) {
        PingPong mainObject = new PingPong();
    }
        
    public PingPong() {
    	frame = new JFrame("PingPong");
    	frame.addKeyListener(this);
    	
    	pane = frame.getContentPane();
    	pane.setLayout(null);
    	    	
    	p = new Painter();
    	p.setSize(500,500);
    	p.setLocation(0,0);
    	
    	pane.add(p);
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(516,530);
		frame.setVisible(true);
	
    }
    
    public class Painter extends JPanel {
    	
    	public void paintComponent(Graphics g) {
    		
    		erase(g);
    		
    		if (p1Moving) {
    			if (p1Up)
    				p1Paddle += 5;
    			else
    				p1Paddle -= 5;
    		}
    		if (p2Moving) {
    			if (p2Up)
    				p2Paddle += 5;
    			else
    				p2Paddle -= 5;
    		}
 
    		if (yPos >= 500) {
   				yPos = 500;
   				yVelocity *= -1;    		
    		}
    		else if (yPos <= 0) {
    			yPos = 0;
    			yVelocity *= -1;
    		}
    		else if (xPos >= 495) {
    			if(yPos > p2Paddle-paddleSize/2 && yPos < p2Paddle+paddleSize/2) {
   					xPos = 495;
   					xVelocity *= -1; 
    			}
    			else {
    				p1Score++;
    				xPos = 250;
    				yPos = 250;
    				angle = 122;
    				xVelocity = speed*Math.cos(angle * Math.PI / 180);
    				yVelocity = speed*Math.sin(angle * Math.PI / 180); 				
    			}   		
    		}
    		else if (xPos <= 5) {
    			if(yPos > p1Paddle-paddleSize/2 && yPos < p1Paddle+paddleSize/2) {
   					xPos = 5;
   					xVelocity *= -1; 
    			}
    			else {
    				p2Score++;
    				xPos = 250;
    				yPos = 250;
    				angle = 58;
    				xVelocity = speed*Math.cos(angle * Math.PI / 180);
    				yVelocity = speed*Math.sin(angle * Math.PI / 180); 				
    			}
    		}
    		
    		yPos -= (int)yVelocity/100;
    		xPos += (int)xVelocity/100; 
    			
    		//drawStats(g);
    		drawObject(g, xPos, yPos, 10);
    		drawPaddles(g, p1Paddle, p2Paddle, paddleSize);
    		drawScore(g, p1Score, p2Score);
    				
    		try {
    			Thread.sleep(1);
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
    
    public void drawPaddles(Graphics g, int p1, int p2, int size) {
    	g.setColor(new Color(255,255,255));
    	g.fillRect(0, p1-size/2, 5, size);
    	g.fillRect(495, p2-size/2, 5, size);
    }
    
    public void drawScore(Graphics g, int s1, int s2) {
    	g.setColor(new Color(255,255,255));
    	g.drawString("Score: "+s1, 10, 10);
    	g.drawString("Score: "+s2, 400, 10);
    }   
   
	public void keyPressed(KeyEvent evt) {
    	int keyCode = evt.getKeyCode();
    	switch(keyCode) {
    		case 38:
    			p2Moving = true;
    			p2Up = false;
    			break;
    		case 40:
    			p2Moving = true;
    			p2Up = true;
    			break;
    		case 87:
    			p1Moving = true;
    			p1Up = false;
    			break;
    		case 83:
    			p1Moving = true;
    			p1Up = true;
    			break;
			
    			
    	}
	}
	public void keyReleased(KeyEvent evt) {
		int keyCode = evt.getKeyCode();
    	switch(keyCode) {
    		case 38:
    		case 40:
    			p2Moving = false;
    			break;
    		case 87:
    		case 83:
    			p1Moving = false;
    			break;
			
    			
    	}
	}
  	public void keyTyped(KeyEvent evt)    {    }
}