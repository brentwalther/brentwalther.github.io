/**
 * @(#)Clock.java
 *
 *
 * @Brent Walther
 * @version 1.00 2010/9/16
 */


import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.TimeZone;

public class Clock extends JFrame implements ActionListener{

	Painter p;
	
    public Clock(String x) { 
    	super(x);   	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500,500);
		setJMenuBar(createMenuBar());
		    	
    	p = new Painter();
		p.setPreferredSize(new Dimension(500,500));
    	
    	getContentPane().add(p);
		pack();
    }
    public static void main(String args[]) {
    	Clock window = new Clock("Brent's Clock");
    }
    public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu colors = new JMenu("Colors");
		JMenuItem face = new JMenuItem("Face");
		JMenuItem ticks = new JMenuItem("Ticks");
		JMenuItem seconds = new JMenuItem("Second Hand");
		JMenuItem minutes = new JMenuItem("Minute Hand");
		JMenuItem hours = new JMenuItem("Hour Hand");
		JMenuItem background = new JMenuItem("Background");
		JMenu settings = new JMenu("Settings");
		JMenuItem zone = new JMenuItem("Time Zone");
		
		face.addActionListener(this);
		ticks.addActionListener(this);
		seconds.addActionListener(this);
		minutes.addActionListener(this);
		hours.addActionListener(this);
		background.addActionListener(this);
		zone.addActionListener(this);
			
		colors.add(face);
		colors.add(ticks);
		colors.add(seconds);
		colors.add(minutes);
		colors.add(hours);
		colors.add(background);
		
		settings.add(zone);
		
		menuBar.add(colors);
		menuBar.add(settings);
		
		return menuBar;
	}
   	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());
		if(p.getColor(source.getText()) != null) {
			Color newColor = JColorChooser.showDialog(Clock.this, "Choose Background Color", p.getColor(source.getText()));
			p.setColor(source.getText(), newColor);
		}
		else {
			String newZone = (String)JOptionPane.showInputDialog(Clock.this,"Select the timezone:", "Select the timezone:",JOptionPane.QUESTION_MESSAGE,null,TimeZone.getAvailableIDs(),p.zone.getID());
			if(newZone != null)
				p.zone = TimeZone.getTimeZone(newZone);
		}
	}
	class Painter extends JPanel {
		
		double minutes, hours, seconds;
		int width, height, margin, center;
		TimeZone zone;
		Dimension size;
		Color colors[] = new Color[6];
		
		public Painter() {
			zone = TimeZone.getDefault();
			for(int i = 0; i < colors.length; i++)
				colors[i] = new Color(255,255,255);
			setColor("face", new Color(0,0,0));
		}	
		public void paint(Graphics g) {
			size = getSize();
			width = size.width;
			height = size.height;
			margin = width / 10;
			center = width / 2;
			Calendar now = Calendar.getInstance(zone);
			//Find the degrees for the minutes and hours
			hours = 360 - ((now.get(Calendar.HOUR)+(double)now.get(Calendar.MINUTE)/60)*30 - 90);
			minutes = 360 - ((now.get(Calendar.MINUTE)+(double)now.get(Calendar.SECOND)/60)*6 - 90);
			seconds = 360 - (now.get(Calendar.SECOND)*6 - 90);
			//Reset the screen
			g.setColor(colors[5]);
			g.fillRect(0,0,width,height);
			
			g.setColor(colors[0]);
			g.fillOval(margin,margin,width-margin*2,width-margin*2);
			
			g.setColor(colors[1]);			
			g.drawString(now.get(Calendar.MONTH)+" / "+now.get(Calendar.DAY_OF_MONTH),50,50);
			int length = 0;
			for(double i = 450; i >= 90; i -= 6) {
				if(i%90 == 0) {
					length = (int)(.45*width-margin);
				}
				else if(i%30 == 0) {
					length = (int)(.475*width-margin);
				}
				else
					length = (int)(.4875*width-margin);
					
				g.drawLine((int)(center+cosine(i)*length),(int)(center-sine(i)*length),(int)(center+cosine(i)*((width-margin*2)/2)),(int)(center-sine(i)*(.5*width-margin)));
			}
			
			g.setColor(colors[3]);
			g.drawLine(center,center,(int)(center+cosine(minutes)*((width-margin*2)/2)*.85),(int)(center-sine(minutes)*((width-margin*2)/2)*.85));
			g.setColor(colors[4]);
			g.drawLine(center,center,(int)(center+cosine(hours)*((width-margin*2)/2)*.6),(int)(center-sine(hours)*((width-margin*2)/2)*.6));
			g.setColor(colors[2]);
			g.drawLine(center,center,(int)(center+cosine(seconds)*((width-margin*2)/2)*.875),(int)(center-sine(seconds)*((width-margin*2)/2)*.875));
				
			try {
				Thread.sleep(50);
			}
			catch (Exception e) {
			}
			repaint();
   	 	}   	 	
		public void setColor(String s, Color c) {
			String[] options = {"face","ticks","second hand","minute hand","hour hand", "background"};
			for(int i = 0; i < options.length; i++)
				if(s.equalsIgnoreCase(options[i]))
					colors[i] = c;	
		}	
		public Color getColor(String s) {
			String[] options = {"face","ticks","second hand","minute hand","hour hand", "background"};
			for(int i = 0; i < options.length; i++)
				if(s.equalsIgnoreCase(options[i]))
					return colors[i];
			return null;
		}
   	 	private double sine(double i) {
   	 		return Math.sin(i/180*Math.PI);
   	 	}
   	 	private double cosine(double i) {
   	 		return Math.cos(i/180*Math.PI);
   	 	}
	}	    
}