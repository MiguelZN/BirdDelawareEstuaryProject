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
	
	private boolean isGameRunning;
	
	
	protected boolean left_key_pressed;
	protected boolean right_key_pressed;
	protected boolean down_key_pressed;
	protected boolean up_key_pressed;
	
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
		this.isGameRunning = true;
		
		//ArrowKeys
		this.left_key_pressed = false;
		this.up_key_pressed = false;
		this.down_key_pressed = false;
		this.right_key_pressed = false;
	}
	
	/**
	 * @param x
	 */
	public abstract void updateScore(int x); //receives the updated score from the controller which received it from the GameState
	/**
	 * @param gameObjects
	 */
	public abstract void update(ArrayList<GameObject> gameObjects);
	
	/**
	 * @return
	 */
//	private BufferedImage createImage() {
//		gameImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
//		
//		return gameImage;
//	}
	
	public void updateIsGameRunning(boolean gr) {
		this.isGameRunning = gr;
	}
	
	public boolean getIsGameRunning() {
		return this.isGameRunning;
	}
	
	
	//Moves the background 
	/**
	 * @param g
	 * @param background1
	 * @param background2
	 */
	public abstract void scrollImage(Graphics g, Object background1, Object background2);

	
	
	public boolean isLeft_key_pressed() {
		return left_key_pressed;
	}

	public boolean isRight_key_pressed() {
		return right_key_pressed;
	}

	public boolean isDown_key_pressed() {
		return down_key_pressed;
	}

	public boolean isUp_key_pressed() {
		return up_key_pressed;
	}

	/*ArrowKeys*/
	public void updateUpKey(boolean b) {
		this.up_key_pressed = b;
	}
	
	public void updateDownKey(boolean b) {
		this.down_key_pressed = b;
	}
	
	public void updateRightKey(boolean b) {
		this.right_key_pressed = b;
	}
	
	public void updateLeftKey(boolean b) {
		this.left_key_pressed = b;
	}
}
