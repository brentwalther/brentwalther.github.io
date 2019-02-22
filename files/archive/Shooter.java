/*
 *Brent Walther
 *12.3.2009
 *Desc: Shooter Game
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shooter implements KeyListener{

    // drawing Pane
	JFrame frame;
	Container pane;
	Painter p;


	// defines the number of ball objects and creates them
	int maxBalls = 1000;
    Ball ballGroup[] = new Ball[maxBalls];
    int ballIterator = 0;


    //defines the number of wall objects and creates them
    int maxWalls = 10;
    Wall wallGroup[] = new Wall[maxWalls];
    int wallIterator = 0;

    //defines players
    Player player1 = new Player(10, 470, 20, 30, 65);
    Player player2 = new Player(700, 470, 20, 30, 115);


    // used in frames per second calculation
    double systemTime;
    int frameDelay = 20;


    // used in the bullet physics
    int power = 1000;
    double wind =  0;
    final double GRAVITY = 9.8;


	// main method to start the panel
	public static void main(String[] args) {
        Shooter mainObject = new Shooter();
    }


    //constructor for the shooter game class
    public Shooter() {
    	frame = new JFrame("Choose angle and power with arrow keys then shoot");

    	pane = frame.getContentPane();
    	pane.setLayout(null);
    	frame.addKeyListener(this);

    	p = new Painter();
    	p.setSize(800,500);
    	p.setLocation(0,0);

    	pane.add(p);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,550);
		frame.setVisible(true);

		wallGroup[0] = new Wall(600,400,600,500);
		wallGroup[1] = new Wall(100,400,100,500);
		wallGroup[2] = new Wall(400,400,500,400);
		wallGroup[3] = new Wall(200,450,300,450);
    }


    // methods that implement keyListener
    public void keyPressed(KeyEvent evt) {
    	int keyCode = evt.getKeyCode();
    	switch(keyCode) {
    		case 17:
    			player1.yVelocity = 300;
    			break;
    		case 37:
    			player1.xVelocity = -2;
    			break;
    		case 39:
    			player1.xVelocity = 2;
    			break;
    		case 38:
    			player1.angle+=2;
    			break;
    		case 40:
    			player1.angle-=2;
    			break;
    		case 32:
    			player1.shooting = true;
    			break;
    		case 87:
    			player2.angle+=2;
    			break;
    		case 83:
    			player2.angle-=2;
    			break;
    		case 65:
    			player2.xVelocity = -2;
    			break;
    		case 68:
    			player2.xVelocity = 2;
    			break;
    		case 69:
    			player2.yVelocity = 300;
    			break;
    		case 81:
    			player2.shooting = true;

    	}
  	}
  	public void keyReleased(KeyEvent evt) {
  		int keyCode = evt.getKeyCode();
    	switch(keyCode) {
    		case 32:
    			player1.shooting = false;
    			break;
    		case 37:
    		case 39:
    			player1.xVelocity = 0;
    			break;
    		case 65:
    		case 68:
    			player2.xVelocity = 0;
    			break;
    		case 81:
    			player2.shooting = false;
    	}
  	}
  	public void keyTyped(KeyEvent evt)    {    }


    //main drawing panel that's added inside the main frame
    public class Painter extends JPanel {

    	public void paintComponent(Graphics g) {

    		createShots(player1);
    		createShots(player2);
    		//updateWind();

    		erase(g);
    		drawObjects(g);
    		drawPlayers(g);
    		drawStats(g);

    		try {
    			Thread.sleep(frameDelay);
    		} catch (InterruptedException e) {
    			System.out.println(e);
    		}
    		repaint();

    	}

    }

    public void drawObjects(Graphics g) {
    	g.setColor(new Color(255,0,0));

    	for(int i = 0; ballGroup[i] != null && i < maxBalls - 1; i++) {

    		ballGroup[i].newPosition(GRAVITY, wind, wallGroup, maxWalls, player1, player2);
    		g.fillOval(ballGroup[i].xPos - 2, ballGroup[i].yPos - 2, 4, 4);
    		if(player1.hit(ballGroup[i])) {
    			System.out.println("Player 2 Wins!");
    			System.exit(0);
    		}
    		if(player2.hit(ballGroup[i])) {
    			System.out.println("Player 1 Wins!");
    			System.exit(0);
    		}
    	}

    	for(int r = 0; wallGroup[r] != null && r < maxWalls - 1; r++) {

    		g.drawLine(wallGroup[r].xPos1, wallGroup[r].yPos1, wallGroup[r].xPos2, wallGroup[r].yPos2);
    	}
    }

    public void drawPlayers(Graphics g) {
    	g.setColor(new Color(255,255,255));

    	player1.update(GRAVITY, wind, wallGroup, maxWalls);
    	g.drawLine(player1.xPos, player1.yPos, (int)(30*Math.cos(player1.angle * Math.PI / 180) + player1.xPos), (int)(player1.yPos - 30*Math.sin(player1.angle * Math.PI / 180)));
    	g.drawRect(player1.xPos - player1.width / 2, player1.yPos - player1.height, player1.width, player1.height);

    	player2.update(GRAVITY, wind, wallGroup, maxWalls);
    	g.drawLine(player2.xPos, player2.yPos, (int)(30*Math.cos(player2.angle * Math.PI / 180) + player2.xPos), (int)(player2.yPos - 30*Math.sin(player2.angle * Math.PI / 180)));
    	g.drawRect(player2.xPos - player2.width / 2, player2.yPos - player2.height, player2.width, player2.height);
    }

    public void erase(Graphics g) {
    	g.setColor(new Color(0,0,0));
    	g.fillRect(0,0,800,500);
    }

    public void createShots(Player player) {
    	if (player.shooting && player.shootDelay == 0) {
    			player.shots++;
    			ballGroup[ballIterator] = new Ball(player.xPos, player.yPos, player.power*Math.cos(player.angle * Math.PI / 180), player.power*Math.sin(player.angle * Math.PI / 180));
    			if (ballIterator < maxBalls - 1)
    				ballIterator++;
    			else
    				ballIterator = 0;
    			player.shootDelay = player.getDelay(player.bulletOption);
    		}
    	else {
    		if(player.shootDelay > frameDelay)
    			player.shootDelay -= frameDelay;
    		else
    			player.shootDelay = 0;
    	}
    }

    public void drawStats(Graphics g) {
    	g.setColor(new Color(255,255,255));
    	//player 1 stats (right)
    	g.drawString("Power: "+player1.power, 5, 15);
    	g.drawString("Angle: "+player1.angle, 5, 25);
    	g.drawString("Shots: "+player1.shots, 5, 35);
    	g.drawString("Hitpoints: "+player1.hitpoints, 5, 45);

    	//central stats (for both players)
    	g.drawString("Wind: "+(int)wind, 350, 25);
    	g.drawString("FPS: "+ (int)(1000 / (System.currentTimeMillis() - systemTime)), 350, 15);
    	systemTime = System.currentTimeMillis();

    	//player 2 stats (left)
    	g.drawString("Power: "+player2.power, 700, 15);
    	g.drawString("Angle: "+player2.angle, 700, 25);
    	g.drawString("Shots: "+player2.shots, 700, 35);
    	g.drawString("Hitpoints: "+player2.hitpoints, 700, 45);
    }

    public void updateWind() {
    	if (wind > 10)
    		wind -= Math.random();
    	else if (wind < -10)
    		wind += Math.random();
    	else if (Math.random() > .5)
    		wind -= Math.random();
    	else
    		wind += Math.random();
    }

}

