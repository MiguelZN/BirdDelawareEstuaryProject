package game;

public class TutorialAction {
	Position position;
	Size size;
	Animation anim;
	
//	public TutorialAction(Thread f) {
//		f.start();
//	}
//	
//	public TutorialAction(Runnable f) {
//		f.run();
//	}
//	
//	public TutorialAction(Thread f, Position p) {
//		this.position = p;
//		f.start();
//	}
	
	public TutorialAction(Thread f, Position p, Size s) {
		this.position = p;
		this.size = s;
		f.start();
	}
	
	public TutorialAction(Thread f, Position p, Size s, Animation anim) {
		this.position = p;
		this.size = s;
		this.anim =anim;
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

	public void setSize(Size size) {
		this.size = size;
	}
	
	
	
	
	
	
}
