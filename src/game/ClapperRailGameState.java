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
	
	//The Ground Level of the game (temporary)
	static int GROUND = GameScreen.PLAY_SCREEN_HEIGHT-(int)(GameScreen.PLAY_SCREEN_HEIGHT*.25);
	static final String ENERGY_TEXT = "Energy: ";
	static final int ENERGY_FONT_SIZE = 40;
	int BackgroundX = 5;
	
	
	/**
	 * @param controller
	 */
	public ClapperRailGameState(Controller controller){
		super(controller);
		this.CR = new ClapperRail();
		this.Materials = new ArrayList<>();
		
	}
	
	/**
	 * 
	 */
	public void collectMaterial() {
		this.Materials.add(new GameObject(0,0,0,0));
	}
	
	/**
	 * @return
	 */
	public int countMaterials() {
		return this.Materials.size();
	}

	/**
	 * @return
	 */
	public ClapperRail getCR() {
		return CR;
	}

	/**
	 * @return
	 */
	public ArrayList<GameObject> getMaterials() {
		return Materials;
	}
	
	
	/* (non-Javadoc)
	 * @see game.GameState#ontick()
	 */
	@Override
	public void ontick() {
		moveBackground();
	}

	/* (non-Javadoc)
	 * @see game.GameState#addGameObject(game.GameObject)
	 */
	@Override
	public void addGameObject(GameObject o) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see game.GameState#getBackgroundX()
	 */
	public int getBackgroundX() {
		return this.BackgroundX;
	}
	
	/**
	 * 
	 */
	public void moveBackground() {
		this.setBackgroundX((this.BackgroundX % 1000)+this.getCR().getVelocity().getXSpeed());
	}
	
	/**
	 * @param right_most_x
	 */
	public void checkRightBounds(int right_most_x) {
		if(this.getCR().getPosition().getX()>right_most_x) {
			this.getCR().setPosition(new Position(0,this.getCR().getPosition().getY()));
		}
	}

	/* (non-Javadoc)
	 * @see game.GameState#getUpdateableGameObjects()
	 */
	@Override
	public ArrayList<GameObject> getUpdateableGameObjects() {
		ArrayList<GameObject> output = new ArrayList<>();
		output.add(CR);
		return output;
	}
	
	
}
