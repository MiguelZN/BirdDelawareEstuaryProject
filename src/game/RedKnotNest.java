package game;

public class RedKnotNest extends GameObject{
	private final int AMOUNT_OF_DIFFERENT_NESTS = 6;
	private int nest_id;

	public RedKnotNest(Position p, Size s) {
		super(p, s);
		this.nest_id = Utility.randRangeInt(1, AMOUNT_OF_DIFFERENT_NESTS);
	}
	
	public int getNestId() {
		return this.nest_id;
	}

}
