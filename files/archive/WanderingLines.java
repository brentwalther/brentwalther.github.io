/**
 * @(#)WanderingLines.java
 *
 *
 * @Brent Walther 
 * @version 1.00 2010/10/12
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class WanderingLines extends JFrame{

	Painter p;
	Container pane;
	
    public WanderingLines() {    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600,600);
		setResizable(false);
		  	
    	pane = getContentPane();
    	p = new Painter();
    	
    	pane.add(p);
    }
    
    public static void main(String args[]) {
    	WanderingLines window = new WanderingLines();
    }
    
	class Painter extends JPanel {
		int quantity = 50, length = 60;
		Piece lines[][] = new Piece[quantity][length];
		int index = 0;
		boolean started = false;
		public void paint(Graphics g) {
			if(!started) {
				for(int i = 0;i < lines.length; i++)
				{
					for(int o = 0; o < lines[i].length; o++)
					{					
						lines[i][o] = new Piece(-1,-1);
					}
					lines[i][0] = new Piece(Math.random()*500+50,Math.random()*500+50);
					lines[i][0].velX = (Math.random()*3+1) *(Math.random()<.5?(-1):1);
					lines[i][0].velY = (Math.random()*3+1) *(Math.random()<.5?(-1):1);
					lines[i][0].color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
				}
				started = true;
			}
			g.setColor(Color.BLACK);
			g.fillRect(0,0,600,600);
			
			//Draws each line
			for(int i = 0; i < lines.length; i++)
			{
				g.setColor(lines[i][0].color);
				Color current = lines[i][0].color;
				int o = index;
				
				//Draws the dots in the line
				do
				{	
					if(--o == -1)
						o=length-1;
					g.fillOval(lines[i][o].x,lines[i][o].y,4,4);
					current = new Color((int)(current.getRed()/1.05),(int)(current.getGreen()/1.05),(int)(current.getBlue()/1.05));
					g.setColor(current);
				} while(o != index);
				
				//increments the index of the 'starting dot' in order to correctly display the fading effect
				if(i==0)
					index = (index + 1 == length ? 0 : index + 1);
					
				//Calculate the new point 
				double accX = (300.0 - lines[i][(index - 1 == -1 ? length-1 : index - 1)].x) / 300, accY = (300.0-lines[i][(index - 1 == -1 ? length-1 : index - 1)].y) / 300;
				if(accX > .9)
					lines[i][0].velX += 1;
				if(accX < -.9)
					lines[i][0].velX -= 1;
				if(accY > .9)
					lines[i][0].velY += 1;
				if(accY < -.9)
					lines[i][0].velY -= 1;
				lines[i][0].velX = lines[i][0].velX + (Math.random()*Math.random()*(Math.random()<.5?(-1):1));
				lines[i][0].velY = lines[i][0].velY + (Math.random()*Math.random()*(Math.random()<.5?(-1):1));
				Piece newPoint = new Piece(lines[i][(index - 1 == -1 ? length-1 : index - 1)].x + lines[i][0].velX, lines[i][(index - 1 == -1 ? length-1 : index - 1)].y + lines[i][0].velY, lines[i][0].color);
				lines[i][index] = newPoint;
			}
			
			try {
				Thread.sleep(10);
			}
			catch (Exception e) {
			}
			repaint();
	    }
	}
	class Piece extends Point {
		double velX, velY;
		Color color;
		public Piece(int x, int y) {
			super(x,y);
			color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		}
		public Piece(double x, double y) {
			super((int)x,(int)y);
			color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		}
		public Piece(int x, int y, Color color) {
			super(x,y);
			this.color = color;
		}
		public Piece(double x, double y, Color color) {
			super((int)x,(int)y);
			this.color = color;
		}
	}
}