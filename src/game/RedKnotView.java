package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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

	public RedKnotView(Controller controller){
		super(controller);
		controller.getScreen().setSize(1000, 500);
	}
	
	public void paintComponent(Graphics g) {
		//BufferedImage backgroundicon = ImageIO.read(new File("resources/images/background1.png"));
		try {
			//System.out.println(spritesheet_name);
			backgroundimage = ImageIO.read(new File("resources/images/background1.png"));
			forest1 = ImageIO.read(new File("resources/images/forest1.png"));
			//System.out.println(spritesheet_name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.drawImage(backgroundimage, -5, -5, 1050, 550, null, this);
		g.setColor(Color.RED);
		Bird RL = this.controller.getRedKnotGS().getRL();
		g.fillOval(RL.getPosition().getX(),RL.getPosition().getY(),RL.getSize(),RL.getSize());
		
		g.drawImage(forest1, forestx, foresty, 400, 250, null, this);
		
		if(forestx+400<0) {
			forestx = 1000;
		}
		else {
			forestx -= this.controller.getRedKnotGS().getRL().getVelocity().getxSpeed();
		}
		
		
	}
	
	
}