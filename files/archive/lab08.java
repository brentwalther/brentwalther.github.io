/*
 *Brent Walther
 *11.06.09
 *Desc: Draws a computer monitor
 */

import java.awt.*;
import javax.swing.*;


class MainPanel extends JPanel {
	
	public void paintComponent(Graphics g)
		{
		  g.setColor(new Color(0,0,0));
		  g.fillRect(50,50,400,300); // Draw the intial monitor shape
		  g.fillArc(100,345,300,70,180,-180);
		  
		  g.setColor(new Color(150,150,150));
		  for(int i=1;i<5;i++) // Draw the buttons
		  	g.fillOval(180+25*i,325,10,10);
		  g.fillOval(360,320,20,20);
		  
		  g.setColor(new Color(0,255,0));
		  g.fillOval(350,330,5,5); // Draw the power button
		  
		  g.setColor(new Color(0,0,255));
		  g.fillRect(70,70,360,240);
		  
		  g.setColor(new Color(255,255,255));
		  g.drawString("WINDOWS HAS ENCOUNTERED AN ERROR",80,90);
		  g.drawString("BEGINNING PHYSICAL MEMORY DUMP",80,105);

	    }

			
}









public class lab08 extends JFrame{

  public static void main(String[] args) {
      //This is a necessary in order to create the frame window
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              GraphicsMain();
          }
      });
  }

  public static void GraphicsMain() {
	
    //Sets up the frame
    JFrame frame = new JFrame("Graphics");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout());

    //Display the window.
    frame.pack();
    
    //Adds the graphics panel and sets the size
    frame.setSize(new Dimension(850,650));
    frame.getContentPane().add(new MainPanel(), BorderLayout.CENTER);
    frame.setVisible(true);
  }
}