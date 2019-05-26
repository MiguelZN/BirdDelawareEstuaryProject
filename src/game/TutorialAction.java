package game;

public class TutorialAction {
	Position position;
	Size size;

	public TutorialAction(Thread f, Position p, Size s) {
		this.position = p;
		this.size = s;
		f.start();
	}
	

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Size getSize() {
		return size;
	}

	
	
	
	
	
	
}
