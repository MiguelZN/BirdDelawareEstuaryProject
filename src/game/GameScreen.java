package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
	
	public static final int SCREEN_BORDER_PX = 4;
	public static final int TITLE_BAR_HEIGHT = 26;
	
	public static final int TITLE_SCREEN_WIDTH = 500;
	public static final int TITLE_SCREEN_HEIGHT = 500;
	
	public static final int PLAY_SCREEN_CONSTRAINT_W = 1000;
	public static final int PLAY_SCREEN_CONSTRAINT_H = 500;//600;
	
	public static final int PLAY_SCREEN_WIDTH = 1000;
	public static final int PLAY_SCREEN_HEIGHT = 600;//600;
	
	public static final int CR_SCREEN_WIDTH = 600;
	public static final int CR_SCREEN_HEIGHT = 800;//(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
	
	public static final int GAME_FPS = 100;
	
	
	/* (non-Javadoc)
	 * @see java.awt.Component#getWidth()
	 */
	public int getWidth() {
		return width;
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#getHeight()
	 */
	public int getHeight() {
		return height;
	}
	
	/*Contructor: 
	 * -takes in two integers for width and height in order to set the size of the JFrame window
	 */
	/**
	 * @param width
	 * @param height
	 */
	public GameScreen(int width, int height) {
		this.setResizable(false);
//		this.setUndecorated(true);
		//setSize(width, height);
		this.width = width;
		this.height = height;
		this.setBackground(Color.CYAN);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setFocusable(true);
		
		
	}
	
	//When called, this redraws the JFrame, Background, etc
	/**
	 * 
	 */
	public void redraw() {
		repaint();
	}
	
	/**
	 * 
	 */
	public void setPlaySize() {
		this.setPreferredSize(new Dimension(PLAY_SCREEN_WIDTH, PLAY_SCREEN_HEIGHT));
		this.pack();
	}
	
	/**
	 * 
	 */
	public void setTitleSize() {
		this.setSize(TITLE_SCREEN_WIDTH, TITLE_SCREEN_HEIGHT);
	}
	
}
