package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
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
	private int forestx = 600;
	private int foresty = 300;
	private int xpos = 5;	
	
	public RedKnotView(Controller controller){
		super(controller);
		controller.getScreen().setSize(1000, 500);
		
		
		try {
			loadAllImages("/resources/images/redknot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void paintComponent(Graphics g) {
		scrollImage(g);
		g.setColor(Color.RED);
		Bird RL = this.controller.getRedKnotGS().getRK();
		g.fillOval(RL.getPosition().getX(),RL.getPosition().getY(),RL.getSize(),RL.getSize());
		g.drawImage((Image) objectMap.get(RedKnotAsset.FOREST1), forestx, foresty, 400, 250, null, this);
		System.out.println(objectMap.get(RedKnotAsset.FOREST1));
		
		forestx -= this.controller.getRedKnotGS().getRK().getVelocity().getxSpeed();
		forestx %=1000;
	}
	
	public void scrollImage(Graphics g){
		xpos = (xpos % 1000)+5;
		g.drawImage((Image) objectMap.get(RedKnotAsset.BACKGROUND), xpos*-1, -5, 1005, 505, null, this);
		g.drawImage((Image) objectMap.get(RedKnotAsset.BACKGROUND), (xpos*-1)+1000, -5, 1005, 505, null, null);
	}

	@Override
	public void fnameMapCreate() {
		fnameMap.put("background1.png", RedKnotAsset.BACKGROUND);
		fnameMap.put("forest2.png", RedKnotAsset.FOREST1);
		fnameMap.put("cloud.png",RedKnotAsset.CLOUD);
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