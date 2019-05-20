package game;

public class TutorialAction {
	Position position;
	Size size;
//	Animation anim;
	
	public TutorialAction(Thread f, Position p, Size s) {
		this.position = p;
		this.size = s;
		f.start();
	}
	
	public TutorialAction(Runnable r, Position p, Size s) {
		this.position = p;
		this.size = s;
		r.run();
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

	public void setSize(Size size) {
		this.size = size;
	}
	
	
	
	
	
	
}
