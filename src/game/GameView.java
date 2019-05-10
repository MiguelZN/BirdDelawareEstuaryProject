package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*Class: GameView
 * -abstract superClass which embodies the View of MVC
 * -RedKnot, ClapperRail Views extend this class
 * -contains methods and all the images required to draw onto the screen
 */
public abstract class GameView extends WindowView{
	/**
	 * 
	 */
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		
	}
	
	/**
	 * 
	 */
	public GameView() {
	}
	
	/**
	 * @param x
	 */
	public abstract void updateScore(int x); //receives the updated score from the controller which received it from the GameState
	/**
	 * @param gameObjects
	 */
	public abstract void update(ArrayList<GameObject> gameObjects);
	
	/**@author Miguel
	 * @param No parameters
	 * @return Nothing
	 * 
	 * -Draws any 'Game Over' features such as the final score,
	 * the bird reaching its destination, etc
	 */
	public abstract void drawEndGame();
	
	/**
	 * @return
	 */
//	private BufferedImage createImage() {
//		gameImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
//		
//		return gameImage;
//	}
	

	
	
	//Moves the background 
	/**
	 * @param g
	 * @param background1
	 * @param background2
	 */
	public abstract void scrollImage(Graphics g, Object background1, Object background2);

}
