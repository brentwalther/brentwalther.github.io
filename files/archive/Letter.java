/*
 *Brent Walther
 *02.05.10
 *Desc: Part of Matrix.java
 */

import java.awt.*;
public class Letter {
	
	public int xPos, yPos, size, green, blue, red, cgreen, cblue, cred;
	public char character;

    public Letter(int x, int y, int s, char c) {
    	xPos = x;
    	yPos = y;
    	size = s;
    	character = c;
    	green = (int)(Math.random()*255+1);
    	blue = (int)(Math.random()*255+1);
    	red = (int)(Math.random()*255+1);
    	cgreen = green;
    	cblue = blue;
    	cred = red;
    }
    public void newPosition() {
    	yPos += size+2;
    	cgreen = green;
    	cred = red;
    	cblue = blue;
    }
    public Color getColor() {    	
    	Color ret = new Color(cred,cgreen,cblue);
    	cgreen = (int)(cgreen/1.2);
    	cblue = (int)(cblue/1.2);
    	cred = (int)(cred/1.2);
    	return ret;
    }
    
    
}