/*
 *Brent Walther
 *12.07.09
 *Desc: Ball object class. Creates new ball objects that are shot
 *Main class: Shooter.java
 */


public class Ball {
	
	public int xPos, yPos, mass;
	double xVelocity, yVelocity;
	
	public Ball(int a, int b, double c, double d) {
		xPos = a;
		yPos = b;
		xVelocity = c;
		yVelocity = d;
		mass = 5;
	}
	
	public void newPosition(double gravity, double wind, Wall walls[], int max, Player p1, Player p2) {
		if (yPos <= 475) {
    		yPos -= (int)yVelocity/100;
    		xPos += (int)xVelocity/100;    		
    		
    		yVelocity -= gravity;
    		xVelocity += wind;
    	}
    	
    	for(int r = 0; walls[r] != null && r < max - 1; r++) {
    		if(xPos <= walls[r].xPos2 && xPos >= walls[r].xPos1 && yPos <= walls[r].yPos2 && yPos >= walls[r].yPos1 || walls[r].yPos1 == walls[r].yPos2 || walls[r].xPos1 == walls[r].xPos2) 
    			if (walls[r].ballCollision(this)) {				
    				xVelocity = 0;
    				yVelocity = 0;
    			}
		}
	}
}