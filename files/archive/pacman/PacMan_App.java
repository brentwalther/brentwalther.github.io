/*
 *Brent Walther
 *03.07.10
 *Desc: Main class for PacMan game project.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PacMan_App implements KeyListener {
	
	// Drawing Pane
	JFrame frame;
	Container pane;
	Painter p;
	
	
	// Game characteristics
	int menuStep = 0;	
	Level board;
	static PacMan pacman;
	static Ghost[] ghost = new Ghost[4];
	
	
	// Start the game
	public static void main(String[] args) {
			new PacMan_App();
	}
	
	
	// Object/Game Constructor
	public PacMan_App() {
		
		//Set up the frame to play on
		frame = new JFrame("Pacman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024,768);
		frame.setVisible(true);
		frame.addKeyListener(this);
		
    	pane = frame.getContentPane();
    	pane.setLayout(null);	
		
    	p = new Painter();
    	p.setSize(1024,768);
    	p.setLocation(0,0);
    	
    	//Populate the first level
		try {
			board = new Level("./level.txt");
		}
		catch (IOException e) {
			System.out.println("ERROR: No level file!");
			System.exit(0);
		}
		board.populateLevel(1);
		pacman = new PacMan(board.findType(5));
		for(int i = 0; i < 4; i++)
			ghost[i] = new Ghost(board.findType(4));
		
    	pane.add(p);    	
		
    	
    	
    }
	
	// methods that implement keyListener
    public void keyPressed(KeyEvent evt) {
    	int keyCode = evt.getKeyCode();
    	switch(keyCode) {   		
    		case 37: 
    			pacman.setDirection(0);
    			break;
    		case 39:
    			pacman.setDirection(2);
    			break;
    		case 38:
    			pacman.setDirection(1);
    			break;
    		case 40:
    			pacman.setDirection(3);
    			break;
    		default:
    			if(menuStep == 0)
    				menuStep = 1;
    			break;	
    	}
  	}
  	public void keyReleased(KeyEvent evt) {}
  	public void keyTyped(KeyEvent evt) {}
	
	public class Painter extends JPanel {
    	
    	public void paintComponent(Graphics g) throws NullPointerException {
    		// Erase by refilling background
    		g.setColor(new Color(0,0,0));
    		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
    		
    		// Update position of pacman and ghosts
    		pacman.updatePosition(board);
    		for(int i = 0; i < 4; i++)
    			ghost[i].updatePosition(board);
    		
    		// Render the frame
    		switch(menuStep) {
    			case 0:
    				mainMenu(g);
    				break;
    			case 1:    				
    				drawGame(g);
    			
    		}
    		
    		// Pause every frame
    		try {
    			Thread.sleep(150);
    		}
    		catch (InterruptedException e) {    			
    		}
    		repaint();    		
    	}
    	
    	public void mainMenu(Graphics g) {
    		g.setColor(new Color(255,255,255));
    		g.drawString("Press any key to begin...", frame.getWidth() / 2, frame.getHeight() / 2);
    		pacman = new PacMan(board.findType(5));
    	}
    	
    	public void drawGame(Graphics g) {    		
    		// Draw the map
    		for(int y = 0; y < board.HEIGHT; y++)
    			for(int x = 0; x < board.WIDTH; x++) {
    				switch(board.getType(new Point(x,y))) {    				
    					case 0:
    						g.setColor(new Color(77,109,243));
    						g.fillRect(x*board.CELL_W + 2, y*board.CELL_H + 2, board.CELL_W - 4, board.CELL_H - 4);
    						break;
    					case 2:
    						g.setColor(new Color(255,249,189));
    						g.fillRect(x*board.CELL_W + board.CELL_W / 2 - 2, y*board.CELL_H + board.CELL_H / 2 - 2, 4, 4);
    						break;
    					case 3:
    						g.setColor(new Color(255,249,189));
    						g.fillOval(x*board.CELL_W + board.CELL_W / 2 - 6, y*board.CELL_H + board.CELL_H / 2 - 6, 12, 12);
    						break;
    				}
    			}
    		
    		
    		//Draw Characters
    		g.setColor(new Color(255,255,0));
    		g.fillOval(pacman.location().x * board.CELL_W - 2, pacman.location().y * board.CELL_H - 2, board.CELL_W + 4, board.CELL_H + 4);
    		
    		g.setColor(new Color(255,0,0));
    		for(int i = 0; i < 4; i++)
    			g.fillRect(ghost[i].location().x * board.CELL_W - 2, ghost[i].location().y * board.CELL_H - 2, board.CELL_W + 4, board.CELL_H + 4);

    		
    		//Draw Score
    		g.setColor(new Color(255,255,255));
    		g.drawString("Score: "+pacman.alterScore(0), board.WIDTH * board.CELL_W + 10, 10);
    	}
    	
	}	

}
