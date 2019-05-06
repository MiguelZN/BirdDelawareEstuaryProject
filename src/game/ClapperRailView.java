package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
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
	private int score;
	int BackgroundX;
	ClapperRail CL;
	
	public ClapperRailView() {
		super();
		CL=new ClapperRail();
		this.score = 0;
		this.BackgroundX = 5;
		
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
		Position p = CL.getPosition();
		g.fillOval(p.getX(),p.getY(),CL.getSize().getWidth(),CL.getSize().getWidth());
		drawEnergy(g);

		this.setVisible(true);
		
	}

	@Override
	public void fnameMapCreate() {
		fnameMap.put("swamp_background.jpg", ClapperRailAsset.BACKGROUND);
		fnameMap.put("energy_icon.png", ClapperRailAsset.ENERGY);
		
	}


	@Override
	public void scrollImage(Graphics g, Object background1, Object background2) {
		g.drawImage((Image) objectMap.get(background1), BackgroundX*-1, 0, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, null, this);
		g.drawImage((Image) objectMap.get(background2), (BackgroundX*-1)+GameScreen.PLAY_SCREEN_WIDTH, 0, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, null, null);
	}


	@Override
	public void update(ArrayList<GameObject> gameObjects) {
		this.CL = (ClapperRail)gameObjects.get(0);
		
	}
	
	public void update(int BackgroundX) {
		this.BackgroundX = BackgroundX;
	}


	public void drawEnergy(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN,ClapperRailGameState.ENERGY_FONT_SIZE));
		FontMetrics fm = g.getFontMetrics();
		//System.out.println(fm.getFont());
		
		//The String being drawn
		String toDrawString = ClapperRailGameState.ENERGY_TEXT;
		int string_width = fm.stringWidth(toDrawString);
		
		g.drawString(toDrawString, GameScreen.SCREEN_BORDER_PX, 0+ClapperRailGameState.ENERGY_FONT_SIZE);
		g.drawImage((Image) objectMap.get(ClapperRailAsset.ENERGY), 0+string_width, 0, 50, 50,null,this);
	}

	@Override
	public void updateScore(int x) {
		this.score = x;
	}

}
