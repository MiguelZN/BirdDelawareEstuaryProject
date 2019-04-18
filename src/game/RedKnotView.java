package game;

import java.awt.Color;
import java.awt.Graphics;
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
	private BufferedImage backgroundimage;
	private BufferedImage forest1;
	private ArrayList<BufferedImage> screenObjects; // all objects that need to be drawn.
	private HashMap<RedKnotAsset,BufferedImage> objectMap;
	private HashMap<String,RedKnotAsset> fnameMap;
	private int forestx = 600;
	private int foresty = 300;
	private int xpos = 5;
	
	
	
	private String[] fnames={"resources/images/redknot/background1.png"};

	public RedKnotView(Controller controller){
		super(controller);
		controller.getScreen().setSize(1000, 500);
		screenObjects = new ArrayList<>();
		objectMap = new HashMap<>();
		fnameMap = new HashMap<>();
	}
	
	public void paintComponent(Graphics g) {
		loadAllImages();
		scrollImage(g);
		g.setColor(Color.RED);
		Bird RL = this.controller.getRedKnotGS().getRL();
		g.fillOval(RL.getPosition().getX(),RL.getPosition().getY(),RL.getSize(),RL.getSize());
		g.drawImage(forest1, forestx, foresty, 400, 250, null, this);
		
		forestx += this.controller.getRedKnotGS().getRL().getVelocity().getxSpeed();
		forestx %=1000;
		
		
	}
	
	/*
	 * This method loads all images that we will ever use in this view, and puts them
	 * into a hashmap as the values, each with a key that we know, and will use when drawing images frmo objects.
	 */
	public void loadAllImages(){
		try {
			//System.out.println(spritesheet_name);
			backgroundimage = ImageIO.read(new File("resources/images/redknot/background1.png"));
			forest1 = ImageIO.read(new File("resources/images/redknot/forest2.png"));
			
			//System.out.println(spritesheet_name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void scrollImage(Graphics g){
		xpos = (xpos % 1000)+5;
		g.drawImage(backgroundimage, xpos*-1, -5, 1005, 505, null, this);
		g.drawImage(backgroundimage, (xpos*-1)+1000, -5, 1005, 505, null, null);
	}

	
	
}