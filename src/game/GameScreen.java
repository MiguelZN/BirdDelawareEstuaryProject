package game;
import java.awt.Color;

import javax.swing.JFrame;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: GameScreen
 * -custom JFrame class that contains the game screen properties such as the width/height
 * of the window
 * 
 */
public class GameScreen extends JFrame{
	private int width;
	private int height;
	
	private static final int TITLE_SCREEN_WIDTH = 500;
	private static final int TITLE_SCREEN_HEIGHT = 500;
	
	private static final int PLAY_SCREEN_WIDTH = 1000;
	private static final int PLAY_SCREEN_HEIGHT = 500;
	
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static int getTITLE_SCREEN_WIDTH() {
		return TITLE_SCREEN_WIDTH;
	}

	public static int getTITLE_SCREEN_HEIGHT() {
		return TITLE_SCREEN_HEIGHT;
	}

	public static int getPLAY_SCREEN_WIDTH() {
		return PLAY_SCREEN_WIDTH;
	}

	public static int getPLAY_SCREEN_HEIGHT() {
		return PLAY_SCREEN_HEIGHT;
	}

	
	/*Contructor: 
	 * -takes in two integers for width and height in order to set the size of the JFrame window
	 */
	public GameScreen(int width, int height) {
		setSize(width, height);
		this.width = width;
		this.height = height;
		this.setBackground(Color.CYAN);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setFocusable(true);
		this.revalidate();
	}
	
	//When called, this redraws the JFrame, Background, etc
	public void redraw() {
		this.repaint();
	}
	
	public void setPlaySize() {
		this.setSize(PLAY_SCREEN_WIDTH, PLAY_SCREEN_HEIGHT);
	}
	
	public void setTitleSize() {
		this.setSize(TITLE_SCREEN_WIDTH, TITLE_SCREEN_HEIGHT);
	}
	
	
}
