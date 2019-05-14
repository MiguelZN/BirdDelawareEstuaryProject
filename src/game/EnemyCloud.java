package game;

public class EnemyCloud extends Cloud{

	public EnemyCloud(Position p, Size s) {
		super(p, s);
	}
	
	public EnemyCloud(Cloud c) {
		super(c.getPosition(), new Size(c.hitBox.width, c.hitBox.height));
	}

}
