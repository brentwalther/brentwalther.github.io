/*
 *Brent Walther
 *11.18.09
 *Description: Draws a Mr. Potato Head according to options input by user.
 */


import java.awt.*;
import javax.swing.*;


class MainPanel extends JPanel {
	

	
	//------------------- OPTIONS --------------------------------	
		// Asks for glasses (yes/no)
		boolean glasses = (0 == JOptionPane.showConfirmDialog(null, "Would you like glasses?", "Glasses?", JOptionPane.YES_NO_OPTION));
		
		// Asks for shoes (yes/no)
		boolean shoes = (0 == JOptionPane.showConfirmDialog(null, "Would you like shoes?", "Shoes?", JOptionPane.YES_NO_OPTION));
		
		// Asks for choice of eye color
		String eyeColorChoices[] = { "Blue", "Green", "Red", "Purple", "Cyan", "Yellow" };
		int eyeColorChoice = JOptionPane.showOptionDialog(null, "What color eyes would you like?", "Eye Color", 1, 1, null, eyeColorChoices, eyeColorChoices[1]);
		
		// Asks for happiness rating
		String happy = JOptionPane.showInputDialog(null, "Rate Mr Potato Man's happiness on a scale of 1-10");
		int happiness = Integer.parseInt(happy);
		
		// Gives choice for  headpeice
		String headpieceChoices[] = { "Hat", "Beanie", "Hair" };
		int headPiece = JOptionPane.showOptionDialog(null, "What headpiece would you like?", "Head Piece", 1, 1, null, headpieceChoices, headpieceChoices[1]);
	//-------------------------------------------------------------
	
	public void paintComponent(Graphics g)
	{
		// Takes input from 'showOptionDialog' and converts it to the respective color
		Color eyeColor = null; 
		switch (eyeColorChoice) {
			case 0: eyeColor =  new Color(0,0,255);
				break;
			case 1: eyeColor = new Color(0,255,0);
				break;
			case 2: eyeColor = new Color(255,0,0);
				break;
			case 3: eyeColor = new Color(255,0,255);
				break;
			case 4: eyeColor = new Color(0,255,255);
				break;
			case 5: eyeColor = new Color(255,255,0);
		}
		
		// Draws main piece 
		g.setColor(getRandomColor());
		g.fillOval(250,100,300,400);
		
		//Draws each individual part
		drawGlasses(g, glasses);
		drawShoes(g, shoes);
		drawEyes(g, eyeColor);
		drawSmile(g, happiness);
		drawHeadpiece(g, headPiece);
	}
	
	public void drawEyes(Graphics g, Color color)
	{
		g.setColor(new Color(255,255,255));
		g.fillOval(300,220,75,35); // draws the white part of the eyes
		g.fillOval(425,220,75,35);
		
		g.setColor(color); // draws the iris (according to user input)
		g.fillOval(320,220,35,35);
		g.fillOval(445,220,35,35);
		
		g.setColor(new Color(0,0,0)); // draws the pupils
		g.fillOval(330,230,15,15);
		g.fillOval(455,230,15,15);
	}
	
	public void drawGlasses(Graphics g, boolean draw)
	{
		if (draw) {
			g.setColor(new Color(0,0,0)); // Draws glasses
			g.drawOval(300,200,75,75);
			g.drawOval(425,200,75,75);
			g.drawLine(375,237,425,237);
		}
	}
	
	public void drawShoes(Graphics g, boolean draw)
	{
		if (draw) {
			g.setColor(getRandomColor());
			g.fillArc(250,475,150,100,0,180); // Draws shoes in a random color
			g.fillArc(400,475,150,100,0,180);
		}
	}
	
	public void drawHeadpiece(Graphics g, int choice)
	{
		g.setColor(getRandomColor());
		switch (choice) {
			case 0:
				g.fillArc(240,70,320,220,0,180); // Draws a hat
				g.fillRect(550,150,100,30);
				break;
				
			case 1:
				g.fillArc(250,100,300,200,0,180); // Draws a beanie
				break;
				
			case 2:
				int xPoints[] = { 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525 };
				int yPoints[] = { 190, 80, 130, 50, 90, 20, 90, 50, 130, 80, 190 };
				g.fillPolygon(xPoints, yPoints, 11); // Draws hair
		}
	}
	
	public void drawSmile(Graphics g, int happiness)
	{
		
		switch (happiness) {
			case 10:
			case 9:
			case 8:
				g.setColor(getRandomColor()); // Chooses a random color
				g.fillArc(300,225,200,150,180,180); // Draws smile
				
				g.setColor(new Color(0,0,0));
				g.drawArc(300,225,200,150,180,180);
			
				g.setColor(getRandomColor()); // Draws bowtie
				int xPoints[] = { 350, 450, 450, 350 };
				int yPoints[] = { 400, 450, 400, 450 };
				g.fillPolygon(xPoints, yPoints, 4);
				break;
				
			case 7:
			case 6:
			case 5:
				g.setColor(getRandomColor()); // Chooses a random color
				g.fillArc(300,225,200,150,180,180); // Draws smile
				
				g.setColor(new Color(0,0,0));
				g.drawArc(300,225,200,150,180,180);
				break;
				
			default:
				g.setColor(getRandomColor()); // Chooses a random color
				g.fillArc(300,300,200,150,180,-180); // Draws frown 
				
				g.setColor(new Color(0,0,0));
				g.fillArc(300,300,200,150,180,-180);
		}
	}
	
	public static Color getRandomColor() // Method to generate a random color to use 
	{
		return new Color((int)(Math.random()*255 + 1),(int)(Math.random()*255 + 1),(int)(Math.random()*255 + 1));
	}	
}









public class DrawPotatoHead extends JFrame{

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