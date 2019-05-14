package game;

public class QuestionCloud extends Cloud{

	public QuestionCloud(Position p, Size s) {
		super(p, s);
	}
	
	public QuestionCloud(Cloud c) {
		super(c.getPosition(), new Size(c.hitBox.width,c.hitBox.height));
	}

	

}
