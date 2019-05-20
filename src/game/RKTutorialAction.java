package game;

public class RKTutorialAction extends TutorialAction{
	RedKnotAsset RKA;
	
	public RKTutorialAction(Thread t, Position p, Size s, RedKnotAsset RKA) {
		super(t, p, s);
		this.RKA = RKA;
	}
	
	public RKTutorialAction(Runnable r, Position p, Size s, RedKnotAsset RKA) {
		super(r, p, s);
		this.RKA = RKA;
	}
	

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
//	
//	public RKTutorialAction(Thread f, Position p, Size s) {
//		this.position = p;
//		this.size = s;
//		f.start();
//	}
	
	
	
}
