/**
 *Brent Walther
 *12.15.09
 *Desc: Class to generate player objects.
 */


public class Player {
	
	public int xPos, yPos, angle, xVelocity = 0, yVelocity = 0, shots = 0, power = 1000, shootDelay, bulletOption = 1, width, height, mass, hitpoints;
	public boolean moving = false, left = false, shooting = false;

    public Player(int x, int y, int w, int h, int ang) {
    	xPos = x;
    	yPos = y;
    	width = w;
    	height = h;
    	angle = ang;
    	mass = 250;
    	hitpoints = 100;
    }
    
    public void update(double gravity, double wind, Wall walls[], int max) {    	
    	boolean collides = false;
    	
    	yPos -= (int)yVelocity/100;
    	xPos += xVelocity;
    	
    	for(int r = 0; walls[r] != null && r < max - 1; r++) {
    		if (walls[r].playerCollision(this)) {				
    			xVelocity = 0;
    			yVelocity = 0;
    			collides = true;
    		}
    	}
    	
    	if (!collides) {		
    		if (yPos < 475) {	
    			yVelocity -= gravity;
    		}
    		else {
    			yPos = 475;
    			yVelocity = 0;
    		}
    	}    	
    }
    
    public boolean hit(Ball ball) {
    	if (ball.xPos > xPos - width && ball.xPos < xPos)
    	if (ball.yPos > yPos - height && ball.yPos < yPos)	{
    		hitpoints -= 5;
    		ball.xPos = 1000;
    		ball.yPos = 1000;
    	}
    	return hitpoints <= 0;
    }
    
    public int getDelay(int option) {
    	switch(option) {
    		case 1: 
    			return 100;
    		default:
    			return 1000;
    	}
    }
}