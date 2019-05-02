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
import java.util.ArrayList;

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
//	private int xpos = 5;
	
	int background_x=5;
	Bird clapper;
	
	public ClapperRailView() {
		super();
		clapper=new Bird();
		
		try {
			loadAllImages("/resources/images/clapperrail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void paintComponent(Graphics g) {
		scrollImage(g, ClapperRailAsset.BACKGROUND, ClapperRailAsset.BACKGROUND);
		g.setColor(new Color(224,160, 42));
		Position p = clapper.getPosition();
		g.fillOval(p.getX(),p.getY(),clapper.getSize().getWidth(),clapper.getSize().getWidth());
		this.setVisible(true);
		
	}

	@Override
	public void fnameMapCreate() {
		fnameMap.put("swamp_background.jpg", ClapperRailAsset.BACKGROUND);
		
	}


	@Override
	public void scrollImage(Graphics g, Object background1, Object background2) {
		g.drawImage((Image) objectMap.get(background1), background_x*-1, -5, 1005, 505, null, this);
		g.drawImage((Image) objectMap.get(background2), (background_x*-1)+1000, -5, 1005, 505, null, null);
		
	}


	@Override
	public void update(ArrayList<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setScore(int x) {
		// TODO Auto-generated method stub
		
	}

}
