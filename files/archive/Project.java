/*
Joshua Cabebe
ECS 2336.004

1) Program graphs 3-dimensional points given to it, and draws lines between each point to each other point.

2.) To reset or exit the program, open "Menu" and the respective buttons can be found there. 
Alternatively, you can press Alt+R to reset and Alt+X to exit the program. In the "Options" 
menu, it is possible to change the color of the lines drawn on screen, add points, and remove 
points. In adding points, a dialog box will show on screen asking for x, y, and z coordinates. 
It is recommended to input points which satisfy the following conditions: -300 < x < 300, 
-300 < y < 300, 16 < z < 48. In removing a point, a dialog box with a list of all points will 
show, simply select one and click the "Remove point" button.

3.) Using a simple algorithm, I translated 3-dimensional points onto their respective points if
 translated to a 2-dimensional plane. If possible, I would like to be able to move the 'camera' 
 from which the points are seen, and enable an option to choose the line color by specific value,
  a la sliders in a dialog box for RGB components.
*/

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class GamePanel extends JPanel
{
	public int count = 4;
	public Point[] obj = new Point[50];
	Image dbImage;
	Graphics dbg;
	Color lineColor = Color.red;

	public void paintComponent(Graphics g)
	{
		update(g);
	}

	public void update (Graphics g)
	{
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics ();
		}

		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);


		dbg.setColor (getForeground());
		paint (dbg);


		g.drawImage (dbImage, 0, 0, this);
	}

	public void paint (Graphics g)
	{
		g.setColor(lineColor);
		updateObjects(g);

	}

	public void updateObjects(Graphics g)
	{
		double[] x = new double[count];
		double[] y = new double[count];

		for(int i = 0; i < count; i++)
		{
			x[i] = (double)(300 - obj[i].x) * 16 / obj[i].z + obj[i].x;
			y[i] = (double)(300 - obj[i].y) * 16 / obj[i].z + obj[i].y;
		}

		for(int i = 0; i < count; i++)
		{
			for(int j = 0; j < count; j++)
			{
				g.drawLine((int)x[i], (int)y[i], (int)x[j], (int)y[j]);
			}
		}



	}
}

class Point
{
	public int x, y, z;
	public Point(int a, int b, int c)
	{
		x = a;
		y = b;
		z = c;
	}
}

public class Project
{

	JFrame frame;
	public GamePanel mainPanel;
	JMenuBar menuBar;
	JMenu menu, options, colorMenu;
	JMenuItem exitItem, resetItem, addItem, removeItem;

	MenuExitHandler exitListener;
	MenuResetHandler resetListener;
	GreenColorHandler greenListener;
	BlueColorHandler blueListener;
	RedColorHandler redListener;
	MenuAddHandler addListener;
	MenuRemoveHandler removeListener;



	public Project()
	{

		//The Frame and its elements
		frame = new JFrame("Project");
		mainPanel = new GamePanel();
		mainPanel.setFocusable(true);

		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		pane.add(mainPanel, BorderLayout.CENTER);



		menuBar = new JMenuBar();

		menu = new JMenu("Menu");
		menuBar.add(menu);


		exitItem = new JMenuItem("Exit");
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
		exitItem.getAccessibleContext().setAccessibleDescription("Exit the program");
		exitListener = new MenuExitHandler();
		exitItem.addActionListener(exitListener);

		resetItem = new JMenuItem("Reset");
		resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		resetItem.getAccessibleContext().setAccessibleDescription("Reset the points");
		resetListener = new MenuResetHandler();
		resetItem.addActionListener(resetListener);


		menu.add(exitItem);
		menu.add(resetItem);


		options = new JMenu("Options");
		menuBar.add(options);

		colorMenu = new JMenu("Color");
		JRadioButtonMenuItem greenItem, blueItem, redItem;
		greenItem = new JRadioButtonMenuItem("Green");
		greenItem.addActionListener(greenListener = new GreenColorHandler());
		blueItem = new JRadioButtonMenuItem("Blue");
		blueItem.addActionListener(blueListener = new BlueColorHandler());
		redItem = new JRadioButtonMenuItem("Red");
		redItem.addActionListener(redListener = new RedColorHandler());
		colorMenu.add(greenItem);
		colorMenu.add(blueItem);
		colorMenu.add(redItem);


		addItem = new JMenuItem("Add a point");
		addListener = new MenuAddHandler();
		addItem.addActionListener(addListener);

		removeItem = new JMenuItem("Remove a point");
		removeListener = new MenuRemoveHandler();
		removeItem.addActionListener(removeListener);

		options.add(colorMenu);
		options.add(addItem);
		options.add(removeItem);

		frame.setJMenuBar(menuBar);

		frame.setResizable(false);
		frame.setSize(600,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.obj[0] = new Point(200, -250, 20);
		mainPanel.obj[1] = new Point(-150, 100, 22);
		mainPanel.obj[2] = new Point(300, 100, 25);
		mainPanel.obj[3] = new Point(-250, -100, 16);




		Thread paintThread = new Thread(new Repainter());
		paintThread.run();

	}


	private class MenuExitHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	private class MenuResetHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mainPanel.obj[0] = new Point(200, -250, 20);
			mainPanel.obj[1] = new Point(-150, 100, 22);
			mainPanel.obj[2] = new Point(300, 100, 25);
			mainPanel.obj[3] = new Point(-250, -100, 16);
			mainPanel.lineColor = Color.red;
			mainPanel.count = 4;
		}
	}

	private class GreenColorHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mainPanel.lineColor = Color.green;
		}
	}

	private class BlueColorHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mainPanel.lineColor = Color.blue;
		}
	}

	private class RedColorHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mainPanel.lineColor = Color.red;
		}
	}

	private class MenuAddHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int x = Integer.parseInt(JOptionPane.showInputDialog("Input X Value for new point:"));
			int y = Integer.parseInt(JOptionPane.showInputDialog("Input Y Value for new point:"));
			int z = Integer.parseInt(JOptionPane.showInputDialog("Input Z Value for new point:"));
			mainPanel.obj[mainPanel.count] = new Point(x, y, z);
			mainPanel.count++;
		}
	}

	private class MenuRemoveHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new RemoveDialog(frame);
		}
	}

	private class RemoveDialog extends JDialog
	{
		private String[] points;
		JList pointList;
		public RemoveDialog(java.awt.Frame parent)
		{
			super(parent, true);
			setTitle("Remove Point");
			setLayout(new FlowLayout());

			final int num = mainPanel.count;
			points = new String[num];
			for(int i = 0; i < num; i++)
			{
				points[i] = "Point " + (i+1) + "(" + mainPanel.obj[i].x + "," + mainPanel.obj[i].y + "," + mainPanel.obj[i].z + ")";
			}

			pointList = new JList(points);
			add(pointList);
			JButton button = new JButton("Remove point");
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					mainPanel.count--;
					int removing = pointList.getSelectedIndex();
					for(int i = removing+1; i < num;i++)
					{
						mainPanel.obj[i-1].x = mainPanel.obj[i].x;
						mainPanel.obj[i-1].y = mainPanel.obj[i].y;
						mainPanel.obj[i-1].z = mainPanel.obj[i].z;
					}
					setVisible(false);
				}
			});
			add(button);

			setSize(200, 400);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}

	private class Repainter implements Runnable{
			public void run ()
			{
				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

				while (true)
				{

					frame.repaint();

					try
					{
						Thread.sleep (20);
					}
					catch (InterruptedException ex)
					{
					}

					Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				}
			}
	}



	public static void main(String args[])
	{
		Project something = new Project();
	}


}