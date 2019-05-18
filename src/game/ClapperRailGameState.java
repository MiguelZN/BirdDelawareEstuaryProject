package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

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
	private ArrayList<Platform> platforms;

	// The Ground Level of the game (temporary)
	static int GROUND = GameScreen.CR_SCREEN_HEIGHT - (int) (GameScreen.CR_SCREEN_HEIGHT * .3);
	static final String ENERGY_TEXT = "Energy: ";
	static final int ENERGY_FONT_SIZE = 40;
	int BackgroundX = 5;

	// GAME_TIME: (NOTE: ALL TIMING IS DONE IN MILLISECONDS)
	// EX: GameTimer.ONE_SECOND == 1000 for 1000 milliseconds as
	// this is what the Java.util.Timer takes in
	static final int MAX_GAME_TIME = GameTimer.ONE_SECOND * 30; // 15 seconds (temporary)
	private GameTimer game_timer;
	private int current_time;
	
	
	private Platform current_platform =null;

	/**
	 * @param controller
	 */
	public ClapperRailGameState(Controller controller) {
		super(controller);
		this.CR = new ClapperRail();
		this.Materials = new ArrayList<>();
		this.platforms = new ArrayList<>();

		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				if (getIsGameRunning()) {
					// System.out.println("TIMER");
					current_time += GameTimer.ONE_SECOND;
					//System.out.println("GAMETIME RAN:" + current_time + " milliseconds");

					if (current_time >= MAX_GAME_TIME) {
						// System.exit(0);
						setIsGameRunning(false);
					}
				}
			}
		};

		// the game timer runs every second and updates the counter 'current_time'
		this.game_timer = new GameTimer(GameTimer.ONE_SECOND, task);
		this.addPlatforms();

	}

	/**
	 * 
	 */
	public void collectMaterial() {
		this.Materials.add(new GameObject(0, 0, 0, 0));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.GameState#ontick()
	 */
	@Override
	public void ontick() {
		CR.ontick();
		handleLeftRightMovement();
		moveBackground();
		if (this.getIsGameRunning()) {
			checkOnPlatform2();
		}
	}
	
	public void handleLeftRightMovement(){
		int x = CR.getLeftRightState();
		switch(x){
		case 1 : CR.moveRight();break;
		case -1 : CR.moveLeft();break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.GameState#addGameObject(game.GameObject)
	 */
	@Override
	public void addGameObject(GameObject o) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.GameState#getBackgroundX()
	 */
	public int getBackgroundX() {
		return this.BackgroundX;
	}

	/**
	 * 
	 */
	public void moveBackground() {
		this.setBackgroundX((this.BackgroundX % 1000) + this.getCR().getVelocity().getXSpeed());
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see game.GameState#getUpdateableGameObjects()
	 */
	@Override
	public ArrayList<GameObject> getUpdateableGameObjects() {
		ArrayList<GameObject> output = new ArrayList<>();
		output.add(CR);
		// this.addPlatforms();
		output.addAll(platforms);
		/*
		 * for(Platform p:platforms) { checkOnPlatform(p); }
		 */

		return output;
	}

	public void checkOnPlatform() {
		this.addPlatforms();
		
		System.out.println(platforms.size());

		Iterator<Platform> plat_it = platforms.iterator();
		while (plat_it.hasNext()) {
			Platform p = plat_it.next();
			System.out.println(this.current_platform.getPosition());

			if (this.CR.getIsFalling()) {
				if (p.touchPlatform(this.CR.getPosition())) {
					this.CR.setIsFalling(false);
					this.CR.setOnPlatform(true);
				}
			} else if (this.CR.getOnPlatform()) {
				if (!p.touchPlatform(this.CR.getPosition())) {
					this.CR.setOnPlatform(false);
					this.CR.setIsFalling(true);
				}
			}
		}
	}
	
	public void checkOnPlatform2() {
		//System.out.println(platforms.size());

		for(Platform p:platforms) {
			if(this.current_platform!=null) {
				System.out.println(this.current_platform.getPosition());
				
			}
			
		
			//System.out.print(p.getPosition());
			
			if(p.touchPlatform(this.CR.getPosition())&&this.current_platform==null) {
				this.CR.setIsFalling(false);
				this.current_platform = p;
				break;
			}
			else if(!p.touchPlatform(this.CR.getPosition())&&this.current_platform!=p) {
				this.CR.setIsFalling(true);
				this.current_platform = null;
			}
			
//			if (this.CR.getIsFalling()) {
//			if (p.touchPlatform(this.CR.getPosition())) {
//				this.CR.setIsFalling(false);
//				this.CR.setOnPlatform(true);
//			}
//		} 
//		
//		else if (this.CR.getOnPlatform()) {
//			if (!p.touchPlatform(this.CR.getPosition())) {
//				this.CR.setOnPlatform(false);
//				this.CR.setIsFalling(true);
//			}
//		}

		}
	}

	public void addPlatforms() {
		if (platforms.size() < 2) {
			this.platforms.add(new Platform(-200, 200));
			this.platforms.add(new Platform(200, 200));
			this.platforms.add(new Platform(400, 200));
			this.platforms.add(new Platform(600,400));
			this.platforms.add(new Platform(800,200));
		}
	}
}
