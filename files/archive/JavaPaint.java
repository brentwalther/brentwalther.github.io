/**
 * @(#)JavaPaint.java
 *
 *
 * @Brent Walther 
 * @version 1.00 2010/9/21
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class JavaPaint extends JFrame implements MouseListener, MouseMotionListener{

	Painter p;
	Colors c;
	Sizes s;
	Color current;
	Color[] pallete;
	Container content;
	boolean drawing;
	int lastX, lastY, lastImageW, lastImageH, width;
	double lastClick;
	Image lastImage;
    public JavaPaint(String x) { 
    	super(x);
    	setLayout(new BorderLayout());
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500,500);
		//setJMenuBar(createMenuBar());
		    	
    	p = new Painter();
		p.setPreferredSize(new Dimension(500,500));
		p.addMouseListener(this);
		p.addMouseMotionListener(this);
		c = new Colors();
		c.setPreferredSize(new Dimension(500,100));
		c.addMouseListener(this);
		s = new Sizes();
		s.addMouseListener(this);
		//pallete = c.getColors();
    	
    	content = getContentPane();
    	content.add(p, BorderLayout.CENTER);
    	content.add(c, BorderLayout.SOUTH);
    	content.add(s, BorderLayout.NORTH);
    	
    	width = 1;
    } // end JavaPaint()
    
    public static void main(String args[]) {
    	JavaPaint window = new JavaPaint("Java Paint");
    }
    
    class Painter extends JPanel {		
		public Painter() {
			
		} //end Painter()
		
		public void paint(Graphics g) {
			int width = getSize().width;
			int height = getSize().height;
			//Reset the screen
			g.setColor(Color.WHITE);
			g.fillRect(0,0,width,height);
   	 	} //end paint()
    } //end Painter.class
    
    class Colors extends JPanel {
    	public Colors() {	
			Color[] p = {
				Color.BLACK,
				Color.DARK_GRAY,
				Color.GRAY,
				Color.LIGHT_GRAY,
				Color.BLUE,
				Color.CYAN,
				new Color(0,100,0),
				Color.GREEN,
				new Color(205,16,118),
				Color.MAGENTA,
				new Color(255,182,193),
				new Color(104,34,139),
				Color.ORANGE,
				Color.RED,
				Color.WHITE,
				new Color(255,222,173),
				new Color(107,66,38),
				Color.YELLOW }; //end pallete
				pallete = p;
    	}
    	/*public Color[] getColors() {
    		return pallete;
    	}*/
    	public void paint(Graphics g) {
    		for(int i = 0; i < pallete.length; i++) {
    			g.setColor(pallete[i]);
    			g.fillRect(i/2*50,i%2*50,50,50);
    			g.setColor(Color.BLACK);
    			g.drawRect(i/2*50,i%2*50,50,50);
    		}
    	} //end paint()
    } //end Colors.class
    class Sizes extends JPanel {
    	public Sizes() {
    	}
    	public void paint(Graphics g) {
    		for(int i = 1; i < 10; i+=2) {
    			g.fillOval(((i-3)/2)*10+(10-i),10-i,i,i);
    		}
    	} //end paint()
    } //end Sizes.class
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
    	if(e.getComponent() instanceof Painter) 
    		drawing = false;	
    	lastImage = p.createImage(p.getSize().width, p.getSize().height);
        lastImageW = p.getSize().width;
        lastImageH = p.getSize().height;
    }
    public void mousePressed(MouseEvent e) {
    	if(e.getComponent() instanceof Painter) {
    		drawing = true; 
    		lastX = e.getX();
    		lastY = e.getY();  		
    	}
    	if(e.getComponent() instanceof Colors) {
    		int index = e.getX()/50*2+e.getY()/50;
    		if(index < pallete.length) {
    			if(System.currentTimeMillis() - lastClick < 500)
    				pallete[index] = JColorChooser.showDialog(null, "Choose New Color", pallete[index]);
    			current = pallete[index];
    			lastClick = System.currentTimeMillis();
    		}
    		//p.repaint();
    		c.repaint();
    	}
    	if(e.getComponent() instanceof Sizes) {
    		width = e.getX()/10+1;
    	}
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) {
    	if(drawing) {
    		Graphics g = e.getComponent().getGraphics();
    		g.setColor(current);
    		if(width == 1) {
    			g.drawLine(lastX, lastY, e.getX(), e.getY());
    		}
    		else {
			    drawPoint(g, lastX, lastY, e.getX(), e.getY(), current);
    		}
    		lastX = e.getX();
    		lastY = e.getY();
    	} 
    }
    public void mouseMoved(MouseEvent e) {}
    
    public void drawPoint(Graphics g, int x0, int y0, int x1, int y1, Color rgb ) {
    	boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
    	int tmp;
    	if (steep) {
    		tmp = x0;
    		x0 = y0;
    		y0 = tmp;
    		tmp = x1;
    		x1 = y1;
    		y1 = tmp;
    	}
    	if (x0 > x1) {
    		tmp = x0;
    		x1 = x1;
    		x1 = tmp;
    		tmp = y0;
    		y0 = y1;
    		y1 = tmp;
    	}
    	int deltax = x1 - x0;
    	int deltay = Math.abs(y1 - y0);
    	int error = deltax / 2;
    	int ystep;
    	int y = y0;
    	if (y0 < y1)
    		ystep = 1;
    	else
    		ystep = -1;
    	for (int x = x0; x <= x1; x++) {
    		g.setColor(rgb);
    		if (steep)
    			g.fillOval(y, x, width, width);
    		else
    			g.fillOval(x, y, width, width);
    		error -= deltay;
    		if (error < 0) {
    			y += ystep;
    			error += deltax;
    		}
    	}
	}
} //end JavaPaint.class