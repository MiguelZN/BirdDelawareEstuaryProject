package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
	private static final long serialVersionUID = 1L;
	public RedKnotView(Controller controller){
		super(controller);
		controller.getScreen().setPlaySize();
		
		//Charizard Sprite animation using the Redknot's position to draw it
//		test_anim = new Animation("resources/images/redknot/redknotspritesheet2.png", new Size(200,100),1,6,15,60);
		
		
		try {
			loadAllImages("/resources/images/redknot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void paintComponent(Graphics g) {
		scrollImage(g, RedKnotAsset.SABACKGROUND, RedKnotAsset.SABACKGROUND);
		g.setColor(Color.RED);
		Bird RK = this.controller.getRedKnotGS().getRK();
		birdMovement(RK);
		drawClouds(g);
		drawScore(g);
		drawBird(g,RK);
		g.setColor(Color.BLUE);
		Utility.drawHitBoxPoint(g, RK.hitBox, this.controller.getRedKnotGS().debug_mode);

		
	}
	public void birdMovement(Bird b) {
		int x = b.getFlyState();
		switch(x) {
		case 1:b.FlyUp();break;
		case -1:b.FlyDown();break;
		}
	}
	//draw our character, the bird.
	public void drawBird(Graphics g,Bird b){
		Animation birdAnim = (Animation) objectMap.get(RedKnotAsset.MAINBIRD);
		g.drawImage(birdAnim.currImage(),b.getPosition().getX(),b.getPosition().getY(),b.getSize().getWidth(),b.getSize().getHeight(),null,this);
	}
	
	public void drawScore(Graphics g){
		GameScreen screen = this.controller.getScreen();
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN,RedKnotGameState.SCORE_FONT_SIZE));
		FontMetrics fm = g.getFontMetrics();
		//System.out.println(fm.getFont());
		
		//The String being drawn
		String toDrawString = RedKnotGameState.SCORE_TEXT + controller.getRedKnotGS().getScore();
		int string_width = fm.stringWidth(toDrawString);
		
		g.drawString(toDrawString, screen.PLAY_SCREEN_WIDTH-string_width, 0+RedKnotGameState.SCORE_FONT_SIZE);
	}
	
	//Takes the Clouds ArrayList and draws individual clouds
	public void drawClouds(Graphics g) {
		ArrayList<Cloud> clouds = this.getController().getRedKnotGS().getClouds();
		for(Cloud c:clouds) {
			drawAndMoveCloud(c,g);
			
			Utility.drawHitBoxPoint(g, c.hitBox, this.controller.getRedKnotGS().debug_mode);
		}
	}
	
	//Draws the clouds based on point NOT TOP LEFT X,Y
	public void drawAndMoveCloud(Cloud c,Graphics g){
		c.move();
		Position current_pos = c.getPosition();
		g.drawImage((Image) objectMap.get(RedKnotAsset.CLOUD), current_pos.getX(), current_pos.getY(), c.getWidth(),c.getHeight(),null, this);
		//g.drawImage((Image) objectMap.get(RedKnotAsset.CLOUD), current_pos.getX()-(c.getWidth()/2), current_pos.getY()-(c.getHeight()/2), c.getWidth(),c.getHeight(),null, this);
		
		//Testing Collision for Clouds and RedKnot (Works -Miguel)
		if(Utility.GameObjectCollision(this.controller.getRedKnotGS().getRK(), c)) {
			System.out.println("COLLISION!");
		}
	}
	
	//Moves the background 
	public void scrollImage(Graphics g, Object background1, Object background2){
		int new_background_x = (this.controller.getRedKnotGS().getBackgroundX() % GameScreen.PLAY_SCREEN_WIDTH)+this.controller.getRedKnotGS().getRK().getVelocity().getXSpeed();
		this.controller.getRedKnotGS().setBackgroundX(new_background_x);
		g.drawImage((Image) objectMap.get(background1), new_background_x*-1, 0, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, null, this);
		g.drawImage((Image) objectMap.get(background2), (new_background_x*-1)+GameScreen.PLAY_SCREEN_WIDTH, 0, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, null, null);
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