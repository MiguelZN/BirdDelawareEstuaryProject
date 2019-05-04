package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


/*
 * TODO: Take anything that updates the model, and move it out of the view.
 */


/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: RedKnotView
 * -class that acts as the View of the RedKnot GameMode
 * -contains methods and control over the drawing of the RedKnot minigame
 */
public class RedKnotView extends GameView {
	/**
	 * 
	 */
	
	/*
	 *  Clouds 
	 *  Bird
	 *  Score
	 * 
	 */
	private final int BACKGROUND_SPEED = 5;
	private ArrayList<Cloud> clouds;
	private RedKnot redKnot;
	private ArrayList<FlockBird> flock;
	private int score;
	private boolean debug_mode;
	
	int background_x = 5;
	
	
	private static final long serialVersionUID = 1L;
	public RedKnotView(){
		super();
		score=0;
		redKnot= new RedKnot();
		clouds = new ArrayList<>();
		flock = new ArrayList<>();
		
		try {
			loadAllImages("/resources/images/redknot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	/*
	 * NOTE: 
	 * NEVER CHANGE THE MODEL IN HERE EVER. ONLY DRAW WHAT THE MODEL IS.
	 */
	public void paintComponent(Graphics g) {
		scrollImage(g, RedKnotAsset.SABACKGROUND, RedKnotAsset.SABACKGROUND);
		g.setColor(Color.RED);
//		birdMovement(RK);
		drawClouds(g);
		drawScore(g);
		drawBird(g);
		g.setColor(Color.BLUE);
		drawFlockBirds(g);
		Utility.drawHitBoxPoint(g, this.redKnot.hitBox, this.debug_mode);	
	}
	
	public void update(ArrayList<GameObject> gameObjects){
		clouds = new ArrayList<>();
		redKnot = (RedKnot)gameObjects.get(0);
		flock = new ArrayList<>();
		//gameObjects.remove(0);
		for(GameObject go : gameObjects){
			if(go instanceof Cloud){
				clouds.add((Cloud)go);
			}
			else if(go instanceof FlockBird) {
				//System.out.println("ADDED FLOCK BIRD");
				flock.add((FlockBird)go);
			}
		}

	}
	
	public void updateDebugging(boolean debug_mode) {
		this.debug_mode = debug_mode;
	}
	
	//draw our character, the bird.
	public void drawBird(Graphics g){
		Animation birdAnim = (Animation) objectMap.get(RedKnotAsset.MAINBIRD);
		g.drawImage(birdAnim.currImage(),redKnot.getPosition().getX(),redKnot.getPosition().getY(),redKnot.getSize().getWidth(),redKnot.getSize().getHeight(),null,this);
	}
	
	public void drawScore(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN,RedKnotGameState.SCORE_FONT_SIZE));
		FontMetrics fm = g.getFontMetrics();
		//System.out.println(fm.getFont());
		
		//The String being drawn
		String toDrawString = RedKnotGameState.SCORE_TEXT + this.score;
		int string_width = fm.stringWidth(toDrawString);
		
		g.drawString(toDrawString, GameScreen.PLAY_SCREEN_WIDTH-string_width-GameScreen.SCREEN_BORDER_PX, 0+RedKnotGameState.SCORE_FONT_SIZE);
	}
	
	public void drawFlockBird(FlockBird FB, Graphics g) {
		Animation FlockBirdAnim = (Animation) objectMap.get(RedKnotAsset.MAINBIRD);
		g.drawImage(FlockBirdAnim.currImage(FB.frameIndex),FB.getPosition().getX(),FB.getPosition().getY(),FB.getSize().getWidth(),FB.getSize().getHeight(),null,this);
		FB.updateCurrImage();
	}
	
	public void drawFlockBirds(Graphics g) {
		for(FlockBird FB: flock) {
			drawFlockBird(FB, g);
			Utility.drawHitBoxPoint(g, FB.hitBox, this.debug_mode);
			if(Utility.GameObjectCollision(this.redKnot, FB) && this.debug_mode) {
				System.out.println("COLLISION!");
			}
		}
	}
	
	//Takes the Clouds ArrayList and draws individual clouds
	public void drawClouds(Graphics g) {
		
		
		for(Cloud c:clouds){
			drawCloud(c,g);
			Utility.drawHitBoxPoint(g, c.hitBox, this.debug_mode);
			if(Utility.GameObjectCollision(this.redKnot, c) && this.debug_mode) {
				System.out.println("COLLISION!");
			}
		}
		/*
		for(Cloud c:clouds) {
			drawCloud(c,g);	
			//the hitbox drawing needs to be restructured, if we want to still use it.
//			Utility.drawHitBoxPoint(g, c.hitBox, this.controller.getRedKnotGS().debug_mode);
		}*/
	}
	public void drawCloud(Cloud c, Graphics g){
		Position current_pos = c.getPosition();
		g.drawImage((Image) objectMap.get(RedKnotAsset.CLOUD), current_pos.getX(), current_pos.getY(), c.getWidth(),c.getHeight(),null, this);
	}
	
	/* collision comment: 
	 * 
	 * //Testing Collision for Clouds and RedKnot (Works -Miguel)
		if(Utility.GameObjectCollision(this.controller.getRedKnotGS().getRK(), c)) {
			System.out.println("COLLISION!");
		}
	 * 
	 * 
	 */
	
	//Moves the background 
	public void scrollImage(Graphics g, Object background1, Object background2){
		background_x = (this.background_x % GameScreen.PLAY_SCREEN_WIDTH)+redKnot.getVelocity().getXSpeed();//BACKGROUND_SPEED;
		g.drawImage((Image) objectMap.get(background1), background_x*-1, 0, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, null, this);
		g.drawImage((Image) objectMap.get(background2), (background_x*-1)+GameScreen.PLAY_SCREEN_WIDTH, 0, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, null, null);
	}
    
	@Override
	public void fnameMapCreate() {
		fnameMap.put("background1.png", RedKnotAsset.BACKGROUND);
		fnameMap.put("forest2.png", RedKnotAsset.FOREST1);
		fnameMap.put("cloudnorain.png",RedKnotAsset.CLOUD);
		fnameMap.put("southamericabackground.jpeg", RedKnotAsset.SABACKGROUND);
		fnameMap.put("sprite-6-redknot.png", RedKnotAsset.MAINBIRD);
		fnameMap.put("sprite-6-flockbird.png", RedKnotAsset.FLOCKBIRD);
	}



	@Override
	public void updateScore(int x) {
		this.score = x;
		System.out.println("VIEW IS UPDATING SCORE");
		System.out.println(this.score);
	}
	
}