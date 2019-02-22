import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Point;


public class Level {	
		
	private Scanner levelInput;
	public Level(String file) throws IOException
	{
		levelInput = new Scanner(new File(file));
	}
	
	
	final int CELL_H = 20;
	final int CELL_W = 20;
	final int WIDTH = 28;	
	final int HEIGHT = 31;	
	private int[][] board = new int[HEIGHT][WIDTH];
	
	public void populateLevel(int num)
	{
		String currentLine = "";
		
		for(int i = 0; i < HEIGHT * (num - 1); i++)
			currentLine = levelInput.nextLine();
		
		for(int i = 0; i < HEIGHT; i++) {
			currentLine = levelInput.nextLine();
			for(int o = 0; o < WIDTH; o++)
				board[i][o] = currentLine.charAt(o) - 48;
		}
			
	}
	
	/*
	 * Type definition:
	 * 0 = null zone 			(no moving here)
	 * 1 = open zone 			(can move here, no piece)
	 * 2 = dot zone 			(can move here, regular dot here)
	 * 3 = big dot zone 		(can move here, big dot here)
	 * 4 = ghost spawn zone		(no moving, ghost spawn)
	 * 5 = pacman spawn zone	(can move here, pacman spawn)
	 * 6 = one way gate zone	(can move out, not in)
	 */
	public int getType(Point point) 
	{
		return board[point.y][point.x];
	}
	
	public void setType(Point point, int t) 
	{
		// will set to new type as long as the type is not 'null zone'
		if (board[point.y][point.x] != 0)
			board[point.y][point.x] = t;
	}
	public Point findType(int query) {
		for(int i = 0; i < HEIGHT * WIDTH; i++) 
			if(board[i / HEIGHT][i % WIDTH] == query)
				return new Point(i % WIDTH, i / HEIGHT);
		return new Point(0,0);
	}
	
	public Point putInMap(Point q) {
		if(q.x < 0 || q.x >= WIDTH - 1)
			q.x = Math.abs(q.x - (WIDTH - 1));
		if(q.y < 0 || q.y >= HEIGHT - 1)
			q.y = Math.abs(q.y - (HEIGHT - 1));
		return q;
	}
}
