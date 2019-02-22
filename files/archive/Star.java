/**
 * @(#)Star.java
 *
 *
 * @author 
 * @version 1.00 2010/11/10
 */
import javax.swing.*;
import java.awt.*;

public class Star extends JFrame{

    public Star(String title) {
    	super(title);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setSize(600,650);
    	setResizable(false);
    	setVisible(true);
    	
    	getContentPane().add(new Painter());
    }
    class Painter extends JPanel {
    	public Painter() {
    	}
    	public void paintComponent(Graphics g) {
    		for(int i = 0; i <= 600; i+=4)
    		{
    			g.drawLine(i,300,300,Math.abs(300-i));
    			g.drawLine(i,300,300,600-Math.abs(300-i));
    		}
    	}
    }
    
    public static void main(String args[]) {
    	Star window = new Star("Star");
    }
}