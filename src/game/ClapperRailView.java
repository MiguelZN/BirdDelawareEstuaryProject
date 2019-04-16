package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/*Class: ClapperRailView
 * -class that acts as the View of the ClapperRail GameMode
 * -contains methods and control over the drawing of the ClapperRail minigame
 */
public class ClapperRailView extends GameView{
	private BufferedImage backgroundimage;
	private int xpos = 5;
	
	public ClapperRailView(Controller controller) {
		super(controller);

		controller.getScreen().setSize(1000, 500);
		
		Container current_layout = controller.getScreen().getContentPane();
	}
	
	public void paintComponent(Graphics g) {
		try {
			//System.out.println(spritesheet_name);
			backgroundimage = ImageIO.read(new File("resources/images/background2.jpg"));
			//System.out.println(spritesheet_name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		scrollImage(g);
		g.setColor(new Color(224,160, 42));
		Position p = this.controller.getClapperRailGS().getCR().getPosition();
		g.fillOval(p.getX(),p.getY(),controller.getClapperRailGS().getCR().getSize(),controller.getClapperRailGS().getCR().getSize());
		this.setVisible(true);
		
	}

	public void scrollImage(Graphics g){
		xpos = (xpos % 1000)+5;
		g.drawImage(backgroundimage, xpos*-1, -5, 1005, 505, null, this);
		g.drawImage(backgroundimage, (xpos*-1)+1000, -5, 1005, 505, null, null);
	}

}
