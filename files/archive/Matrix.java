/*
 *Brent Walther
 *01.28.10
 *Desc: Creates a matrix effect.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Matrix {

	// drawing Pane
	JFrame frame;
	Container pane;
	Painter p;


	// defines the number of character objects and creates them
	int characters = 1000;
    Letter character[] = new Letter[characters];
    int charIterator = 0;
    
    char authChars[] = { 
    	'0','1','2','3','4','5', // These characters are the ones the program randomly
    	'6','7','8','9','A','B', // chooses from when a new falling character is
    	'C','D','E','F','G','H', // created.
    	'I','J','K','L','M','N',
    	'O','P','Q','R','S','T',
    	'U','V','W','X','Y','©'
    };

    double tempTime = 0;

	// main method to start the panel
	public static void main(String[] args) {
        Matrix mainObject = new Matrix();
    }

    public Matrix() {
    	frame = new JFrame("Matrix");

    	pane = frame.getContentPane();
    	pane.setLayout(null);

    	p = new Painter();
    	p.setSize(1024,768);
    	p.setLocation(0,0);

    	pane.add(p);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024,768);
		frame.setVisible(true);
    }

    public class Painter extends JPanel {

    	public void paintComponent(Graphics g) {
    		character[charIterator] = new Letter((int)(Math.random() * 1024),0,(int)(Math.random()*12+4),authChars[(int)(Math.random() * authChars.length)]);
    		if (charIterator < characters-1)
    			charIterator++;
    		else
    			charIterator = 0;

    		erase(g);
    		drawObjects(g);

    		try {
    			Thread.sleep(80);
    		} catch (InterruptedException e) {
    			System.out.println(e);
    		}
    		repaint();

    	}

    }

    public void drawObjects(Graphics g) {
    	for(int i = 0; character[i] != null && i < characters - 1; i++) {
    		g.setColor(new Color(0,255,0));
    		character[i].newPosition();

    		for(int p=0; p < 15; p++) {
    			g.setColor(character[i].getColor());
    			g.drawString(""+character[i].character, character[i].xPos, character[i].yPos - 11*p);
    		}
    	}
    	g.setColor(new Color(255,255,255));
    	g.drawString("FPS: "+(int)(1000/(System.currentTimeMillis() - tempTime)), 1, 10);
    	tempTime = System.currentTimeMillis();

    }
    public void erase(Graphics g) {
    	g.setColor(new Color(0,0,0));
    	g.fillRect(0,0,1024,768);
    }

}