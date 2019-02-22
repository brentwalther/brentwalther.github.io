/**
 * @(#)Infection.java
 *
 *
 * @Brent Walther 
 * @version 1.1 2010/12/3
 */
import javax.swing.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Infection extends Applet implements MouseMotionListener
{
	private Population pop;
	Graphics buffer; 
	Image offscreen;
	Dimension dim;
	long lastFPSlog, frames, fps;
	int cursorX, cursorY;
	
    public void init()
    {
    	//super(title); // Create a new Frame with the sepcified title
    	
    	pop = new Population(10000);
    	
    	dim = getSize();
        setBackground(Color.black);
        offscreen = createImage(dim.width,dim.height);
        buffer = offscreen.getGraphics();
    }
    
    public void paint(Graphics g)
    {
    	buffer.clearRect(0,0,dim.width,dim.width);
    	
    	int inf = 0, hlth = 0;
    	for(int i = 0; i < pop.sizeOf(); i++)
    	{
    		if(pop.isInfected(i)) {
    			buffer.setColor(new Color(255,0,0));
    			inf++;
    		}
    		else
    		{
    			buffer.setColor(new Color(0,0,255));
    			hlth++;
    		}
    		buffer.fillOval((int)pop.locationOf(i).x,(int)pop.locationOf(i).y,4,4);
    	}
    	buffer.setColor(new Color(255,255,255));
    	buffer.drawString("Healthy: "+hlth+" Infected: "+inf,10,10);
    	buffer.drawString("FPS: "+fps, 525,10);
    	
    	frames++;
    	long time = System.currentTimeMillis();
    	if(time > lastFPSlog+1000)
    	{
    		fps=frames;
    		frames=0;
    		lastFPSlog = time;
    	}
    	
    	g.drawImage(offscreen,0,0,this);
    	pop.updateAll();
    	repaint();
    	new Color(46,139,87);
    }
    public void update(Graphics g) 
    {
    	paint(g);
    }
    
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
    	cursorX = e.getX();
    	cursorY = e.getY();
    	System.out.println(cursorX + " " + cursorY);
    }
    
    private class Population
    {
    	private boolean[] members;
    	private int map[][];
    	private long lastUpdate;
    	private double[] x, y, vx, vy;
    	
    	public Population(int size)
    	{
    		members = new boolean[size];
    		map = new int[601][601];
    		x = new double[size];
    		y = new double[size];
    		vx = new double[size];
    		vy = new double[size];
    		
    		for(int i = 0; i < members.length; i++)
    		{
    			x[i] = 1+Math.random()*598;
    			y[i] = 1+Math.random()*598;
    		}
    		members[(int)(Math.random()*size)] = true;
    	}
    	
    	/**
    	 * Method to determine the size of the population
    	 *
    	 * @return the amount of members in the population.
    	 */
    	public int sizeOf()
    	{
    		return members.length;
    	}
    	
    	/**
    	 * Tests to see if a member of the population is infected
    	 *
    	 * @param id
    	 *		Which member to look at
    	 * @return whether or not the member is affected
    	 */
    	 public boolean isInfected(int id)
    	 {
    	 	return members[id];
    	 }
    	 
    	 /**
    	  * Returns the location of the specified member represented in a 2D point
    	  *
    	  *	@param id
    	  *		Which member to get the location of
    	  * @return the location of the member
    	  */
    	  public Point locationOf(int id)
    	  {
    	  	return new Point((int)Math.round(x[id]),(int)Math.round(y[id]));
    	  }
    	  
    	  /**
    	   * This method updates all the members of the population.
    	   * Each member has their own walking velocity and location
    	   * so this method computer their new location based on
    	   * their velocity. The method also computes the change in
    	   * velocity for each of the members.
    	   */
    	  public void updateAll()
    	  {
    	  	if((System.currentTimeMillis()-lastUpdate)>40)
    	  	{
			for(int i = 0; i < members.length; i++)
			{
				double newX = x[i] + vx[i];
				double newY = y[i] + vy[i];
				if(newX < 0 || newX > 600)
				{
					newX = x[i];
					vx[i]=0;
				}
				if(newY < 0 || newY > 600)
				{
					newY = y[i];
					vy[i]=0;
				}
				
				if(members[i])
				{
					map[locationOf(i).x][locationOf(i).y] = -1;    	  			
				}
				x[i]= newX;
				y[i] = newY;
				if(members[i])
					map[locationOf(i).x][locationOf(i).y] = i;
				double cvx = .25-Math.random()/2, cvy = .25-Math.random()/2;
				double nvx = vx[i] + cvx, nvy = vy[i] + cvy;
				vx[i] = ((nvx > 2 || nvx < -2) ? vx[i]:nvx);
				vy[i] = ((nvy > 2 || nvy < -2) ? vy[i]:nvy);
				
				members[i] = nearInfected(locationOf(i));
			}
			lastUpdate = System.currentTimeMillis();
    	  	}
    	  }
    	  
    	  /**
    	   * This method checks to see if the current 
    	   * position is near an infected member of 
    	   * the population.
    	   * @param point
    	   *		current location
    	   * @returns whether or not an infected is nearby
    	   */
    	   public boolean nearInfected(Point point) 
    	   {
    	   	for(int x = -1; x < 2; x++)
    	  			for(int y = -1; y < 2; y++)
    	  				try {
    	  					if(map[x+point.x][y+point.y] > 0)
    	  						return true;
    	  				} catch (ArrayIndexOutOfBoundsException e) {}
    	  	return false;
    	   }
    }
}