package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
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




/*
 * TODO:
 * 	Update ClapperRailView to be up to speed with redknot view,
 *	I have modified redknot view with recent commits and added generalization to the
 *	process that we will use to load images, and draw them to the screen from the model. - Derek
 */
public class ClapperRailView extends GameView{
	private int xpos = 5;
	
	public ClapperRailView(Controller controller) {
		super(controller);
		
		try {
			loadAllImages("/resources/images/clapperrail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		controller.getScreen().setPlaySize();
		
		Container current_layout = controller.getScreen().getContentPane();
	}
	
	
	public void paintComponent(Graphics g) {
		scrollImage(g);
		g.setColor(new Color(224,160, 42));
		Position p = this.controller.getClapperRailGS().getCR().getPosition();
		g.fillOval(p.getX(),p.getY(),controller.getClapperRailGS().getCR().getSize(),controller.getClapperRailGS().getCR().getSize());
		this.setVisible(true);
		
	}

	public void scrollImage(Graphics g){
		xpos = (xpos % 1000)+5;
//		BufferedImage backgroundimage = (BufferedImage) objectMap.get(ClapperRailAsset.BACKGROUND);
		g.drawImage((Image)objectMap.get(ClapperRailAsset.BACKGROUND), xpos*-1, -5, 1005, 505, null, this);
		g.drawImage((Image)objectMap.get(ClapperRailAsset.BACKGROUND), (xpos*-1)+1000, -5, 1005, 505, null, null);
	}

	@Override
	public void fnameMapCreate() {
		fnameMap.put("swamp_background.jpg", ClapperRailAsset.BACKGROUND);
		
	}

}
