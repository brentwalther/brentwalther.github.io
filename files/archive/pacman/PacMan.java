import java.awt.Point;

public class PacMan extends Movable{
	private int lives;
	private int score;
	
	public PacMan(Point point) {
		super(point);
		lives = 3;
		score = 0;
	}
	
	public void updatePosition(Level b) {
		super.updatePosition(b);
		if(b.getType(new Point(location())) == 2)
		{
			alterScore(10);
			b.setType(new Point(location()), 1);
		}
		if(b.getType(new Point(location())) == 3)
		{
			alterScore(50);
			b.setType(new Point(location()), 1);
		}
		
	}
	
	public int alterLives(int alter) {
		lives += alter;
		return lives;
	}
	public int alterScore(int alter) {
		score += alter;
		return score;
	}
}
