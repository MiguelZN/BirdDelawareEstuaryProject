package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

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
//	private int forestx = 600;
//	private int foresty = 300;
//	private int xpos = 5;	
//	
	//X,Y are the top left, (first two parameters), Width, Height are next two parameters
	//private Rectangle rect = new Rectangle(0,0,50,50);
//	
//	private Rectangle rect = new Rectangle(0,0,50,50);
//	private Rectangle rect2 = new Rectangle(30,30,50,50);
//	
	
	public RedKnotView(Controller controller){
		super(controller);
		controller.getScreen().setPlaySize();
		
		
		
		
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
		Bird RL = this.controller.getRedKnotGS().getRK();
		birdMovement(RL);
		g.fillOval(RL.getPosition().getX(),RL.getPosition().getY(),RL.getSize(),RL.getSize());
//		g.drawImage((Image) objectMap.get(RedKnotAsset.FOREST1), forestx, foresty, 400, 250, null, this);
		//System.out.println(objectMap.get(RedKnotAsset.FOREST1));
		
//		forestx -= this.controller.getRedKnotGS().getRK().getVelocity().getXSpeed();
//		forestx %=1000;
		
		drawClouds(g);
		System.out.println(this.controller.getRedKnotGS().getClouds().size());
		
		//System.out.println("INTERSECTS?:"+rect.intersects(rect2));
		

		
	}
	public void birdMovement(Bird b) {
		int x = b.getFlyState();
		switch(x) {
		case 1:b.FlyUp();break;
		case -1:b.FlyDown();break;
		}
	}
	
	//Takes the Clouds ArrayList and draws individual clouds
	public void drawClouds(Graphics g) {
		ArrayList<Cloud> clouds = this.getController().getRedKnotGS().getClouds();
		for(Cloud c:clouds) {
			c.move();
			Position current_pos = c.getPosition();
			g.drawImage((Image) objectMap.get(RedKnotAsset.CLOUD), current_pos.getX(), current_pos.getY(), c.getWidth(),c.getHeight(),null, this);
			
			//Testing Collision for Clouds and RedKnot (Works -Miguel)
			if(Utility.GameObjectCollision(this.controller.getRedKnotGS().getRK(), c)) {
				System.out.println("COLLSIION!");
			}
		}
		
	}
	
	//Moves the background 
	public void scrollImage(Graphics g, Object background1, Object background2){
		int new_background_x = (this.controller.getRedKnotGS().getBackgroundX() % 1000)+this.controller.getRedKnotGS().getRK().getVelocity().getXSpeed();
		this.controller.getRedKnotGS().setBackgroundX(new_background_x);
		g.drawImage((Image) objectMap.get(background1), new_background_x*-1, -5, 1005, 505, null, this);
		g.drawImage((Image) objectMap.get(background2), (new_background_x*-1)+1000, -5, 1005, 505, null, null);
	}

	@Override
	public void fnameMapCreate() {
		fnameMap.put("background1.png", RedKnotAsset.BACKGROUND);
		fnameMap.put("forest2.png", RedKnotAsset.FOREST1);
		fnameMap.put("cloud.png",RedKnotAsset.CLOUD);
		fnameMap.put("southamericabackground.jpeg", RedKnotAsset.SABACKGROUND);
	}
	
//	public BufferedImage loadImage(File f) {
//		BufferedImage output=null;
//		try{
//			output = ImageIO.read(f);
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		return output;
//	}

	
	
}