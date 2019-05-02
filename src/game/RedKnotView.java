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
	private Bird redKnot;
//	private ArrayList<Bird> flock; to be used later when we add the flock
	private int score;
	
	int background_x = 5;
	
	
	private static final long serialVersionUID = 1L;
	public RedKnotView(){
		super();
		score=0;
		redKnot= new Bird();
		clouds = new ArrayList<>();
		//Charizard Sprite animation using the Redknot's position to draw it
//		test_anim = new Animation("resources/images/redknot/redknotspritesheet2.png", new Size(200,100),1,6,15,60);
		
		
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
//		Utility.drawHitBoxPoint(g, RK.hitBox, this.controller.getRedKnotGS().debug_mode);	
	}
	
	public void update(ArrayList<GameObject> gameObjects){
		clouds = new ArrayList<>();
		redKnot = (Bird)gameObjects.get(0);
		gameObjects.remove(0);
		for(GameObject go : gameObjects){
			if(go instanceof Cloud){
				clouds.add((Cloud)go);
			}
		}
	}
	public void setScore(int x){
		this.score=x;
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
		String toDrawString = RedKnotGameState.SCORE_TEXT + score;
		int string_width = fm.stringWidth(toDrawString);
		
		g.drawString(toDrawString, GameScreen.PLAY_SCREEN_WIDTH-string_width-GameScreen.SCREEN_BORDER_PX, 0+RedKnotGameState.SCORE_FONT_SIZE);
	}
	
	//Takes the Clouds ArrayList and draws individual clouds
	public void drawClouds(Graphics g) {
		
		
		for(int i = 0; i < clouds.size();i++){
			drawCloud(clouds.get(i),g);
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
		background_x = (this.background_x % GameScreen.PLAY_SCREEN_WIDTH)+BACKGROUND_SPEED;
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
	}
	
}