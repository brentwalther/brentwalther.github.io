/**
 * @(#)Notepad.java
 *
 *
 * @author Brent Walther
 * @version 1.00 2011/3/4
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Notepad extends JFrame {
	
	//Window that the text area is painted in
	TextWindow textArea;
	ScrollBar scroller;
	int leftMargin, rightMargin, topMargin;
	CustomPopup menu;
	String fname;
	
    public Notepad(String file) {
    	super();
    	setVisible(true);
    	setLayout(new BorderLayout());
    	
    	String title = null;
    	fname = file;
    	String txt = "";
    	if(file == null) {
    		title = "";
    	}
    	else {    	
    		try {
    			txt = fileGetContents(file);
    			Scanner sc = new Scanner(txt);
    			String temp = sc.nextLine();
    			if(temp.length() < 40) {
    				title = temp;
    				txt = txt.substring(temp.length()+1);
    			}    				
    			else {
    				title = file;
    			}
    		}
    		catch(IOException e) {
    		}
    	}
    	
    	ChangeListener listener = new ChangeListener();
    	topMargin = 40;
    	rightMargin = 40;
    	leftMargin = 50;
    	textArea = new TextWindow(txt, title);
    	scroller = new ScrollBar();
    	String[] opt = {"Open","Save","Edit Title"};
    	menu = new CustomPopup(opt);
    	
    	addKeyListener(listener);
    	getContentPane().addMouseListener(listener);
    	getContentPane().addMouseMotionListener(listener);
    	getContentPane().add(textArea,BorderLayout.CENTER);
    	getContentPane().add(scroller,BorderLayout.EAST);
    	setSize(800,600);
    }
    public String fileGetContents(String url) throws IOException {
    	String contents = "";
    	Scanner s = new Scanner(new File(url));
    	while(s.hasNextLine())
    		contents += s.nextLine() + "\n";
    	return contents;
    }
    public void writeFile(String fname) throws IOException {
    	PrintWriter writer = new PrintWriter(new FileWriter(new File(fname)));
    	writer.println(textArea.title);
    	writer.print(textArea.text);
    	writer.flush();
    }
    public static void main(String args[]) {
    	Notepad notes = new Notepad("test.txt");
    }
    
    class TextWindow extends JPanel {
    	String text, title;
    	FontMetrics dims;
    	int caretPos = 0;
    	public TextWindow() {
    		this("", "Untitled Note");
    	}
    	public TextWindow(String s) {
    		this(s,null);
    	}
    	public TextWindow(String s, String t) {
    		text = s;
    		if(t == null)
    			title = "Untitled Note";
    		else
    			title = t;
    		setTitle("Notepad: "+title);
    		caretPos = text.length();
    	}
    	public void paint(Graphics g) {
    		
    		//We need to find the FontMetrics of the font to get the dimensions to use in calculations;
    		dims = g.getFontMetrics();
    		
    		//g.setColor(new Color(255,255,125));
    		g.setColor(new Color(255,255,255));
    		g.fillRect(0,0,getWidth(),getHeight());
    		drawBackground(g);
    		drawVisibleText(g);
    		menu.paint(g);
    	}
    	public void drawBackground(Graphics g) {
    		//g.setColor(new Color(255,128,64));
    		g.setColor(new Color(100,100,255));
    		for(int i = 1; i < getHeight(); i+=dims.getHeight() + 3)
    			g.drawLine(0,topMargin+i,getWidth(),topMargin+i);
    			
    		//left margin line
    		g.drawLine(leftMargin,0,leftMargin,getHeight());
    		
    		//right margin line
    		g.drawLine(getWidth()-rightMargin,0,getWidth()-rightMargin,getHeight());
    	}
    	public void drawVisibleText(Graphics g) {
    		//The default text color for now will be black. Will update later;
    		g.setColor(Color.BLACK);
    		
    		//This is the line height that we will use in calculations. Add 2 to allow whitespace in between lines
    		int h = dims.getHeight() + 3;
    		int margins = 60;
    		
    		int visibleLines = (getHeight()-topMargin)/h+1;
    		
    		//Fill the lines with text
    		ArrayList<String> lines = new ArrayList<String>();
    		
    		int ch = 0;
    		String x = "";
    		while(ch < text.length()) {
    			while(ch < text.length() && dims.stringWidth(x) < getWidth()-leftMargin-rightMargin) {
    				x += ""+text.charAt(ch++);
    				if(text.charAt(ch-1) == 10) {
    					break;
    				}
    			}
    			lines.add(x);   				
    			x = "";
    		}	
    		String line[] = new String[lines.size()];
    		Iterator<String> iter = lines.iterator();
    		int index = 0;
    		while(iter.hasNext())
    			line[index++] = iter.next();
    			
    		int realLines = line.length;
    		int unseenLines = realLines - visibleLines + 1;
    		if(unseenLines < 0)
    			unseenLines = 0;
    		scroller.reportVisibleAndReal(visibleLines,realLines);
    		
    		//Draw the title
    		g.setFont(new Font("Serif", Font.BOLD, 24));
    		g.drawString(title, leftMargin + 2, topMargin);
    		
    		//Draw all the text lines
    		g.setFont(new Font("SansSerif", Font.PLAIN, 12));
    		for(double i = unseenLines*scroller.position, o = 0; o < visibleLines && i < line.length; i++, o++)
    			g.drawString(line[(int)i],leftMargin + 2,(int)((o+1)*h+topMargin));    
    						
    		//Find and draw the insertion caret
    		int lineNum = 0;
    		int charSum = 0;
    		String charsInFrontOf = "";
    		while(line[lineNum].length()+charSum < caretPos) {
    			charSum += line[lineNum++].length();
    		}
    		index = 0;
    		while(charSum != caretPos) {
    			charsInFrontOf += ""+line[lineNum].charAt(index++);
    			charSum++; 
    		}
    		g.drawLine(leftMargin+dims.stringWidth(charsInFrontOf),topMargin+lineNum*h,leftMargin+dims.stringWidth(charsInFrontOf),topMargin+(lineNum+1)*h);
    	}
    	public void insertText(String s) {
    		if(caretPos==text.length())
    			text += s;
    		else
    			text = text.substring(0,caretPos) + s + text.substring(caretPos);
    		caretPos++;
    	}
    	public void backspace() {
    		if(caretPos==text.length())
    			text = text.substring(0,text.length()-1);
    		else
    			text = text.substring(0,caretPos-1) + text.substring(caretPos);
    		caretPos--;
    	}
    }
    class CustomPopup {
    	boolean visible;
    	String[] options;
    	int x, y, w, h, mx, my;
    	public CustomPopup(String[] opts) {
    		options = opts;
    		visible = false;
    		x = 0;
    		y = 0;
    		h = opts.length*20;
    		w = 80;
    	}
    	public void setLocation(int x, int y) {
    		this.x = x;
    		this.y = y;
    		mx = x;
    		my = y;
    	}
    	public void mouseIsAt(int x, int y) {
    		mx = x;
    		my = y;
    	}
    	public void setVisible(boolean b) {
    		visible = b;
    	}
    	public boolean isVisible() {
    		return visible;
    	}
    	public boolean inBounds(int x, int y) {
    		return x > this.x && x < this.x+w && y > this.y && y < this.y+h;
    	}
    	public int itemClicked(int x, int y) {
    		return (y-this.y)/20;
    	}
    	public void paint(Graphics g) {
    		if(visible) {    			
    			g.setColor(new Color(255,255,255));
    			g.fillRect(x,y,w,h);
    			
    			g.setColor(new Color(100,100,255));
    			g.drawRect(x,y,w,h);
    			if(inBounds(mx,my))
    				g.fillRect(x,y+(my-y)/20*20,w,20);
    			
    			for(int i = 0; i < options.length; i++) {
    				g.drawLine(x,y+i*20,x+w,y+i*20);
    				g.drawString(options[i],x,y+(i+1)*20);
    			}
    		}
    	}
    }
    
    class ScrollBar extends JPanel {
    	double position, seen, unseen;
    	int visible, real, height, sArea;
    	public ScrollBar() {
    		this(0);
    	}
    	public ScrollBar(double x) {
    		position = x;
    	}
    	public void paint(Graphics g) {
    		g.setColor(new Color(255,255,255));
    		g.fillRect(0,0,10,getHeight());
    		
    		g.setColor(Color.BLACK);
    		g.drawRect(0,topMargin,10,getHeight()-topMargin);
    		g.fillRect(0,topMargin,10,10);
    		g.fillRect(0,getHeight()-10,10,10);
    		
    		//Fills the rectangle of the scroller. I had to think about these calculations for a while
    		sArea = getHeight()-topMargin-24;
    		height = (int)(sArea*seen);
    		g.fillRect(2,topMargin+12+(int)((sArea-height)*position),6,height);
    		
    		g.setColor(Color.WHITE);
    		g.drawLine(2,topMargin+7,5,topMargin+3);
    		g.drawLine(5,topMargin+3,8,topMargin+7);
    		g.drawLine(2,getHeight()-7,5,getHeight()-3);
    		g.drawLine(5,getHeight()-3,8,getHeight()-7);
    	}
    	public void update(int deltaY) {
    		double pc = deltaY / (double)(sArea-height);
    		position += pc;
    		if(position > 1)
    			position = 1;
    		if(position < 0)
    			position = 0;
    		repaint();
    		textArea.repaint();
    	}
    	public void reportVisibleAndReal(int v, int r) {
    		visible = v;
    		real = r;
    		//'seen' is the percentage of the text that is seen. If it is greater than 1, that means all the text is seen
    		seen = (double)v/r;
    		if(seen > 1)
    			seen = 1;
    		unseen = 1 - seen;
    		repaint();
    	}
    }
    
    class ChangeListener implements MouseListener, KeyListener, MouseMotionListener {
    	boolean scrolling;
    	int lastLocation;
    	public void keyPressed(KeyEvent e) {
    		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
    			textArea.backspace();
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT && textArea.caretPos != 0)
    			textArea.caretPos--;
    		else if (e.getKeyCode() == KeyEvent.VK_RIGHT && textArea.caretPos != textArea.text.length())
    			textArea.caretPos++;
    		else
    			textArea.insertText(""+e.getKeyChar());
    		textArea.repaint();
    	}
    	public void keyReleased(KeyEvent e) {
    	}
    	public void keyTyped(KeyEvent e) {
    	}
    	public void mouseClicked(MouseEvent e) {
    	}
    	public void mouseEntered(MouseEvent e) {
    	}
    	public void mouseExited(MouseEvent e) {
    	}
    	public void mousePressed(MouseEvent e) {
    		if(e.getButton() == 3) {
    			menu.x = e.getX();
    			menu.y = e.getY();
    			menu.setVisible(true);
    			textArea.repaint();
    		}
    		else if(menu.isVisible()) {
    			if(!menu.inBounds(e.getX(),e.getY())) {
    				menu.setVisible(false);
    				textArea.repaint();
    			}
    			else {
    				int item = menu.itemClicked(e.getX(),e.getY());
    				if(item == 0) {
    					JFileChooser jfc = new JFileChooser();
    					jfc.showDialog(null, "Choose File");
    					new Notepad(jfc.getSelectedFile().getAbsolutePath());
    				}
    				if(item == 1) {
    					if(textArea.title.equals("") || fname == null);
    						fname = JOptionPane.showInputDialog(null, "Save as:");
    					try {
    						writeFile(fname);
    					}
    					catch (IOException er) {
    						JOptionPane.showMessageDialog(null, "Could not save file." + er);
    					}
    				}
    				if(item == 2) {
    					String newTitle = JOptionPane.showInputDialog(null, "New Title:");
    					setTitle("Notepad: "+newTitle);
    					textArea.title = newTitle;
    				}
    			}
    		}
    		else if(e.getX() > textArea.getWidth()) {
    			scrolling = true;
    			lastLocation = e.getY();
    		}
    	}
    	public void mouseReleased(MouseEvent e) {
    		if(scrolling)
    			scrolling = false;
    	}
    	public void mouseDragged(MouseEvent e) {
    		if(scrolling) {		
    			scroller.update(e.getY()-lastLocation);
    			lastLocation = e.getY();
    		}
    	}
    	public void mouseMoved(MouseEvent e) {
    		if(menu.inBounds(e.getX(),e.getY())) {
    			menu.mouseIsAt(e.getX(),e.getY());
    			textArea.repaint();
    		}
    	}
    }
}