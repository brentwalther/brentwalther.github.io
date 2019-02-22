import java.awt.Point;

public class Ghost extends Movable {
	
	public Ghost(Point point) {
		super(point);
	}
	
	public void updatePosition(Level b) {
		if(amountOfChoices(b) > 2) {
			setDirection((int)(Math.random() * 4));
		}
		else {
			while(true) {
				int tryThis = (int)(Math.random() * 4);
				if(tryThis != getDirection()) {
					setDirection(tryThis);
					break;
				}
			}
		}		
		super.updatePosition(b);
	}
	
	public int amountOfChoices(Level b) {
		int count = 0;
		try {
			if(moveLeft(b, false))
				count++;
			if(moveRight(b, false))
				count++;
			if(moveUp(b, false))
				count++;
			if(moveDown(b, false))
				count++;
		}
		catch (BoundaryException e) {			
		}
		return count;
	}

}
