/*
 *Brent Walther
 *11.06.09
 *Desc: Effect Number One
 */

import java.awt.*;
import javax.swing.*;


class MainPanel extends JPanel {
	
	public void paintComponent(Graphics g)
	{
		boolean right = false, vertOrHor = true, down = true;
		int xPos = 50, yPos = 50;	
		
		while(true)
		{
			g.fillRect(0,0,850,650);
			
			if (vertOrHor) {
				if (down) {
					if (xPos <= 250)
						xPos++;
					else {
						down = false;
						vertOrHor = false;
					}
				}
				else {
					if (xPos >= 50)
						xPos--;
					else {
						down = true;
						vertOrHor = true;
					}
				}
			}
			else {
				if (right) {
					if (yPos <= 250)
						yPos++;
					else {
						right = false;
						vertOrHor = true;
					}
				}
				else {
					if (yPos >= 50)
						yPos--;
					else {
						right = true;
						vertOrHor = false;
					}
				}
			}
			g.fillRect(xPos,yPos,5,5);
   			try {
   				Thread.sleep(4000);
   				} 
   			catch (InterruptedException e) {
   				return;
   				}

		}
	}

}









public class effectOne extends JFrame{

  public static void main(String[] args) throws InterruptedException {
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
    frame.setDefaultCloseOperation(JFram