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
	private RedKnotGameState state;
	private BufferedImage backgroundimage;
	private BufferedImage forest1;
	private HashMap<RedKnotAsset,BufferedImage> objectMap;
	private HashMap<String,RedKnotAsset> fnameMap;
	private int forestx = 600;
	private int foresty = 300;
	private int xpos = 5;	


	public RedKnotView(Controller controller,RedKnotGameState state){
		super(controller,state);
		controller.getScreen().setSize(1000, 500);
		objectMap = new HashMap<>();
		fnameMap = new HashMap<>();
		
		
		try {
			loadAllImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		scrollImage(g);
		g.setColor(Color.RED);
		Bird RL = this.controller.getRedKnotGS().getRL();
		g.fillOval(RL.getPosition().getX(),RL.getPosition().getY(),RL.getSize(),RL.getSize());
		g.drawImage(objectMap.get(RedKnotAsset.FOREST1), forestx, foresty, 400, 250, null, this);
		
		forestx += this.controller.getRedKnotGS().getRL().getVelocity().getxSpeed();
		forestx %=1000;
		
		
	}
	
	
	/*
	 * Boring method requires human effort.
	 * Put all files that will be loaded in both this method, and in the
	 * red knot asset enum.
	 */
	private void fnameMapCreate(){
		fnameMap.put("background1.png", RedKnotAsset.BACKGROUND);
		fnameMap.put("forest2.png", RedKnotAsset.FOREST1);
	}
	
	/*
	 * This method loads all images that we will ever use in this view, and puts them
	 * into a hashmap as the values, each with a key that we know, and will use when drawing images frmo objects.
	 */
	public void loadAllImages() throws IOException{
		fnameMapCreate();
		File[] files = new File(System.getProperty("user.dir") + "\\resources\\images\\redknot").listFiles();
		for(File f : files){
			objectMap.put(fnameMap.get(f.getName()), loadImage(f));
		}
	}
	private BufferedImage loadImage(File f){
		BufferedImage output=null;
		try{
			output = ImageIO.read(f);
		}catch (IOException e){
			e.printStackTrace();
		}
		return output;
	}

	public void scrollImage(Graphics g){
		xpos = (xpos % 1000)+5;
		g.drawImage(objectMap.get(RedKnotAsset.BACKGROUND), xpos*-1, -5, 1005, 505, null, this);
		g.drawImage(objectMap.get(RedKnotAsset.BACKGROUND), (xpos*-1)+1000, -5, 1005, 505, null, null);
	}

	
	
}