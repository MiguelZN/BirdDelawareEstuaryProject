package game;

public class RKTutorialAction extends TutorialAction{
	RedKnotAsset RKA;
	
	public RKTutorialAction(Thread t, Position p, Size s, RedKnotAsset RKA) {
		super(t, p, s);
		this.RKA = RKA;
	}
}
