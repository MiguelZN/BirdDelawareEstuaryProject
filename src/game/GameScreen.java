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
	
	public static final int PLAY_SCREEN_CONSTRAINT_W = 1920;
	public static final int PLAY_SCREEN_CONSTRAINT_H = 1080;//600;
	

//	public static int PLAY_SCREEN_WIDTH = 1000;
//	public static int PLAY_SCREEN_HEIGHT = 600;//600;

	public static int PLAY_SCREEN_WIDTH = 1920;
	public static int PLAY_SCREEN_HEIGHT = 1080;//600;

	
	public static int CR_SCREEN_WIDTH = 1920;
	public static int CR_SCREEN_HEIGHT = 1080;//(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
	
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
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		this.PLAY_SCREEN_WIDTH = (int) screenSize.getWidth();
//		this.PLAY_SCREEN_HEIGHT = (int) screenSize.getHeight();
//		this.width = this.PLAY_SCREEN_WIDTH;
//		this.height = this.PLAY_SCREEN_HEIGHT;
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setFocusable(true);
//		
////		System.out.println(((120.0/1000.0)*GameScreen.PLAY_SCREEN_WIDTH));
////		System.exit(0);
//		
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		Dimension d  = Toolkit.getDefaultToolkit().getScreenSize();
		this.width = (int) d.getWidth();
		this.height = (int) d.getHeight();
		PLAY_SCREEN_WIDTH=this.width;
		PLAY_SCREEN_HEIGHT=this.height;
		CR_SCREEN_WIDTH = this.width;
		CR_SCREEN_HEIGHT = this.height;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		
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
