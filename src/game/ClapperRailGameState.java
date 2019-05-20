package game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;
import java.util.stream.Collectors;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: ClapperRailGameState
 * -class that acts as the Model of the ClapperRail GameMode
 * -keeps track of the data for the ClapperRail mini-game
 */
public class ClapperRailGameState extends GameState {
	private ClapperRail CR;
	private ArrayList<Platform> platforms;
	private Flood flood;
	// The Ground Level of the game (temporary)
	static int GROUND = 974;
	static final String ENERGY_TEXT = "Health: ";
	static final String SCORE_TEXT = "Score: ";
	static final String MATERIALS_TEXT = "x ";
	static final int ENERGY_FONT_SIZE = 40;
	static final int MATERIAL_FONT_SIZE = 40;
	static final int SCORE_FONT_SIZE = 40;
	private static final int SPAWN_CHANCE = 5;
	int BackgroundX = 5;
	
	/*
	 * if its the start of the game, show the tutorial.
	 */
	private boolean start = true;
	
	private boolean waitingOnQuestion = false;
	
	//194 is a very important magic number!
	//the jump height is 300, the ground position of the bird is 494, 
	//494-300=194. That is what this stems from. Becuase if you are at the bottom of the screen jumping, we want 
	//the screen to not move, but any higher, and we want it to move.
	private static final int MOVE_SCREEN_HEIGHT = 568;
	

	// GAME_TIME: (NOTE: ALL TIMING IS DONE IN MILLISECONDS)
	// EX: GameTimer.ONE_SECOND == 1000 for 1000 milliseconds as
	// this is what the Java.util.Timer takes in
	
	
	private Platform current_platform =null;

	/**
	 * @param controller
	 */
	public ClapperRailGameState(Controller controller) {
		super(controller);
		Dimension d  = Toolkit.getDefaultToolkit().getScreenSize();
		GROUND=((int)d.getHeight())-106;
		this.CR = new ClapperRail();
		this.platforms = new ArrayList<>();
		this.flood = new Flood(0,1180);

		
		this.addPlatforms();
		//this.addFood();
		//this.addMaterials();

	}


	/**
	 * @return
	 */
	public ClapperRail getCR() {
		return CR;
	}


	
	
	
	/*
	 * Every object on the screen, (the bird, and the platforms)
	 * must move down at a constant rate to simulate you rising.
	 * 
	 * So, whenever the bird is above some point on the screen, we will shift
	 * every platform, and the bird, some constant amount, until the bird is below
	 * that arbitrary point on the screen.
	 * 
	 * So, when you are above that point, it will feel as if you are forever going up.
	 */
	
	public void objectShift(){
		for(Platform p : platforms){
			p.move();
		}
		flood.move();
		CR.move(0, 5);
		CR.setScore(CR.getScore()+CR.getScoreIncrease());
		controller.moveClapperBackground(5);
	}
	
	@Override
	public void ontick() {
		System.out.println(CR.getPosition());
		if(waitingOnQuestion){
			return;
		}
		if(start){
			tutorialUpdate();
			return;
		}
		CR.ontick(platforms);

		
		if(CR.getPosition().getY() < ClapperRailGameState.MOVE_SCREEN_HEIGHT){
			objectShift();
		}
		handleLeftRightMovement();
		moveBackground();
		if (this.getIsGameRunning()) {
			checkOnPlatform2();
			checkFood();
			checkMaterials();
			checkFlood();
			checkQuestions();
			if(this.CR.getEnergy() <= 0) {
				this.CR.gameOver = true;
			}
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

	
	public void tutorialUpdate(){
		controller.setCRTutMode(true);
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
		output.add(flood);
		// this.addPlatforms();
		output.addAll(platforms);
//		output.addAll(food);
//		output.addAll(materials);
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
				//System.out.println(this.current_platform.getPosition());
				
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

		}
	}
	public void checkQuestions(){
		Collection<Platform> filtered = platforms.stream().filter(p -> p.getQuestion()!=null).collect(Collectors.toList());
		Iterator<Platform> plat_it = filtered.iterator();
		while(plat_it.hasNext()){
			Platform pl = plat_it.next();
			ClapperQuestion q = pl.getQuestion();
			
			if(q.touchObject(this.CR.getPosition(),ClapperQuestion.RADIUS)){
				pl.removeQuestion();
				invokeRandomQuestion();
			}
		}
	}
	public void setWaitingOnQuestion(boolean b){
		this.waitingOnQuestion=b;
	}
	public boolean getWaitingOnQuestion(){
		return this.waitingOnQuestion;
	}
	/*
	 * TODO: will call a method in controller, and get data back from it.
	 */
	public void invokeRandomQuestion(){
		controller.setClapperNotMoving();
		setWaitingOnQuestion(true);
		boolean correct = controller.randomClapperQuestion();
		if(correct){
			CR.setMaterialCount(CR.getMaterialCount()+10);
		}
		setWaitingOnQuestion(false);
	}
	
	public void checkFood() {
		Collection<Platform> filtered = platforms.stream().filter(p -> p.getFood()!=null).collect(Collectors.toList());
		Iterator<Platform> plat_it = filtered.iterator();
		while(plat_it.hasNext()){
			Platform pl = plat_it.next();
			Food f = pl.getFood();
			
			if(f.touchObject(this.CR.getPosition(),Food.RADIUS)) {
				pl.removeFood();
				this.CR.gainEnergy();
			}
			
		}
	}
	
	public void checkMaterials() {
		Collection<Platform> filtered = platforms.stream().filter(p -> p.getMaterial()!=null).collect(Collectors.toList());
		Iterator<Platform> plat_it = filtered.iterator();
		
		while(plat_it.hasNext()){
			Platform pl = plat_it.next();
			Material m = pl.getMaterial();
			
			if(m.touchObject(this.CR.getPosition(),Material.RADIUS)) {
				pl.removeMaterial();
				this.CR.setMaterialCount(this.CR.getMaterialCount()+1);
			}
			
		}
	}
	
	public void checkFlood() {
		if(CR.getPosition().getY() >= ClapperRailGameState.GROUND-5){
			if(flood.getPosition().getY() <= 1080){
				CR.setEnergy(CR.getEnergy()-ClapperRail.ENERGY_LOSS);
			}
			if(flood.getPosition().getY() > 880){
				flood.increaseFlood();
			}
		}	
		
	}
	public void setStart(boolean b){
		start=b;
	}

	public boolean getStart(){
		return this.start;
	}
	public void addPlatforms() {
			this.platforms.add(new Platform(1620, 0));
			this.platforms.add(new Platform(180, 768));
			this.platforms.add(new Platform(540, 576));
			this.platforms.add(new Platform(900,384));
			this.platforms.add(new Platform(1260,192));
	}
	/*
	public void addObjects() {
		Random ran = new Random();
		for(Platform p:platforms) {
			int n = ran.nextInt(SPAWN_CHANCE); //generates random number. 
			if(!p.getHasObject()&&(n==1)) { //spawn food if number matches
				int j = ran.nextInt(3);
				if(j==0) {
					Material newM = p.addMaterial();
					this.materials.add(newM);
					objectsMap.put(newM, p);
				} else {
					Food newF = p.addFood();
					this.food.add(newF);
					objectsMap.put(newF, p);
				}
				p.setHasObject(true);
			}
		}
	}*/
}
