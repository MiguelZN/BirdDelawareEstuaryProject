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
	private ClapperRail CR;
	private ArrayList<GameObject> Materials;
	
	
	public ClapperRailGameState(Controller controller){
		super(controller);
		this.CR = new ClapperRail();
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

	@Override
	public ArrayList<GameObject> getUpdateableGameObjects() {
		ArrayList<GameObject> output = new ArrayList<>();
		output.add(CR);
		return output;
	}
	
	
}
