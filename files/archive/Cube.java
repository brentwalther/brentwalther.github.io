/**
 * @(#)Cube.java
 *
 *
 * @Brent Walther 
 * @version 1.00 2010/9/15
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class Cube extends JFrame implements KeyListener {

	Painter p;
	Container pane;
	
	int zoom, deltaX, deltaY;
	
    public Cube() {    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(600,600);
		  	
    	pane = getContentPane();
    	p = new Painter();
    	addKeyListener(this);
    	    	
    	pane.add(p);
    }
    
    public static void main(String args[]) {
    	Cube window = new Cube();
    }
    
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
    	System.out.println("Key pressed: "+e);
    	p.repaint();
    }
    public void keyReleased(KeyEvent e) {
    }    
}
class Painter extends JPanel {
	double angle;
	int zoom, deltaX, deltaY;
	
	public Painter() {
		super();
		zoom = 30;
		deltaX
	}
	public void paint(Graphics g) {
		length = (int)(Math.cos((180 - angle)/180*Math.PI) * 100);
		height = (int)(Math.sin((180 - angle)/180*Math.PI) * 100);
		
		g.setColor(new Color(255,255,255));
		g.fillRect(0,0,600,600);
		
		g.setColor(new Color(0,0,0));
		//left line
		g.drawLine(100, 100 + height, 100, 200 + height );
		//center line
		g.drawLine(200 + length,200,200 + length,300);
		
		g.drawLine(100, 100 + height, 200 + length, 200);
		g.drawLine(100, 200 + height, 200 + length, 300);
		
		g.drawLine(200 + length, 200, 200, 200 - height);
		g.drawLine(200 + length, 300, 200, 300 - height);
		
		g.drawLine(100, 100 + height, 100 - length, 100);
		g.drawLine(100 - length, 100, 200, 200 - height);
		
		//right line
		g.drawLine(200, 200 - height, 200, 300 - height);
		//g.drawLine(200,200,(int)(200+Math.cos(angle/180*Math.PI)*100),(int)(200-Math.sin(angle/180*Math.PI)*100));
		//g.drawOval(90,90,220,220);
		if(angle == 90)
			angle = 0;
		else
			angle++;
		
		try {
			Thread.sleep(20);
		}
		catch (Exception e) {
		}
    }
}