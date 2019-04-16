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
	private BufferedImage backgroundimage2;
	private BufferedImage forest1;
	private int forestx = 600;
	private int foresty = 300;
	private int xpos = 5;

	public RedKnotView(Controller controller){
		super(controller);
		controller.getScreen().setSize(1000, 500);
	}
	
	public void paintComponent(Graphics g) {
		//BufferedImage backgroundicon = ImageIO.read(new File("resources/images/background1.png"));
		try {
			//System.out.println(spritesheet_name);
			backgroundimage = ImageIO.read(new File("resources/images/background1.png"));
			forest1 = ImageIO.read(new File("resources/images/forest2.png"));
			//System.out.println(spritesheet_name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scrollImage(g);
		g.setColor(Color.RED);
		Bird RL = this.controller.getRedKnotGS().getRL();
		g.fillOval(RL.getPosition().getX(),RL.getPosition().getY(),RL.getSize(),RL.getSize());
		
		g.drawImage(forest1, forestx, foresty, 400, 250, null, this);
		
		forestx += this.controller.getRedKnotGS().getRL().getVelocity().getxSpeed();
		forestx %=1000;
		
		
	}

	public void scrollImage(Graphics g){
		xpos = (xpos % 1000)+5;
		g.drawImage(backgroundimage, xpos*-1, -5, 1005, 505, null, this);
		g.drawImage(backgroundimage, (xpos*-1)+1000, -5, 1005, 505, null, null);
	}

	
	
}