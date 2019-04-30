package game;

import java.util.ArrayList;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: ClapperRailGameState
 * -class that acts as the Model of the ClapperRail GameMode
 * -keeps track of the data for the ClapperRail mini-game
 */
public class ClapperRailGameState extends GameState {
	private Bird CR;
	private ArrayList<GameObject> Materials;
	private final int XSTART = 100;
	private final int YSTART = 380;
	private final int VXSTART = 10;
	private final int VYSTART = 0;
	private final int SIZE = 80;
	
	
	public ClapperRailGameState(Controller controller){
		super(controller);
		this.CR = new Bird(new Position(XSTART,YSTART), new Size(SIZE,SIZE), new Velocity(VXSTART,VYSTART), BirdType.CLAPPERRAIL);
		this.Materials = new ArrayList<>();
		
	}
	
	public void collectMaterial() {
		this.Materials.add(new GameObject(0,0));
	}
	
	public int countMaterials() {
		return this.Materials.size();
	}

	public Bird getCR() {
		return CR;
	}

	public ArrayList<GameObject> getMaterials() {
		return Materials;
	}
	
	
	@Override
	public void ontick() {
		
	}

	@Override
	public void addGameObject(GameObject o) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveBackground() {
		this.setBackgroundX((this.getBackgroundX() % 1000)+this.getCR().getVelocity().getXSpeed());
	}
	
	public void checkRightBounds(int right_most_x) {
		if(this.getCR().getPosition().getX()>right_most_x) {
			this.getCR().setPosition(new Position(0,this.getCR().getPosition().getY()));
		}
	}
	
	
}
