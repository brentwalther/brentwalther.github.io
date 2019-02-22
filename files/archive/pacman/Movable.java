import java.awt.Point;

public abstract class Movable {
	
	private int xPos = 0;
	private int yPos = 0;
	
	public Movable(Point point) {
		xPos = point.x;
		yPos = point.y;
	}
	/* Direction code definition:
	 * 0 = left
	 * 1 = up
	 * 2 = right
	 * 3 = down
	 */
	private int direction = 5;
	
	public boolean setDirection(int set) {
		boolean isSame = direction == set;
		if (!isSame)
			direction = set;
		return isSame;
	}
	public int getDirection() {
		return direction;
	}
	
	public Point location() {
		return new Point(xPos, yPos);
	}
	
	public void updatePosition(Level b) {
		try {
				switch (direction) {
					case 0: 
						moveLeft(b, true);
						break;
					case 1:
						moveUp(b, true);
						break;
					case 2:
						moveRight(b, true);
						break;
					case 3:
						moveDown(b, true);
				}
		}
		catch (BoundaryException e) {
		}
		
	}
	
	public boolean moveLeft(Level board, boolean move) throws BoundaryException {
		int newPos = board.getType(board.putInMap(new Point(xPos - 1, yPos)));
		if (newPos == 0 || newPos == 6) {
			if (move)
				throw new BoundaryException();
			return false;
		}
		else 
			if(move)
				xPos--;
		return true;
	}
	
	public boolean moveUp(Level board, boolean move) throws BoundaryException {
		int newPos = board.getType(board.putInMap(new Point(xPos, yPos - 1)));
		if (newPos == 0) {
			if (move)
				throw new BoundaryException();
			return false;
		}
		else
			if (move)
				yPos--;
		return true;
	}
	
	public boolean moveRight(Level board, boolean move) throws BoundaryException {
		int newPos = board.getType(board.putInMap(new Point(xPos + 1, yPos)));
		if (newPos == 0 || newPos == 6) {
			if (move)
				throw new BoundaryException();
			return false;
		}
		else 
			if (move)
				xPos++;
		return true;
	}
	
	public boolean moveDown(Level board, boolean move) throws BoundaryException {
		int newPos = board.getType(board.putInMap(new Point(xPos, yPos + 1)));
		if (newPos == 0 || newPos == 6) {
			if (move)
				throw new BoundaryException();
			return false;
		}
		else
			if (move)
				yPos++;
		return true;
	}
}
