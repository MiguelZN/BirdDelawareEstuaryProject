package game;

public class TutorialAction {
	public TutorialAction(Thread f) {
		f.start();
	}
	
	public TutorialAction(Runnable f) {
		f.run();
	}
	
	
}
