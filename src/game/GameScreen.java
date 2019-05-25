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
	
	//TitleScreen, Screen adjustments:
	public static final int SCREEN_BORDER_PX = 4;
	public static final int TITLE_BAR_HEIGHT = 26;
	
	//Sets the Play_screen_width/height to the total size of the user's screen dimensions
	public static int PLAY_SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int PLAY_SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();//600;

	//Sets the ClapperRail size to the Play_screen_width/height
	public static int CR_SCREEN_WIDTH = PLAY_SCREEN_WIDTH;
	public static int CR_SCREEN_HEIGHT = PLAY_SCREEN_HEIGHT;//(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
	
	//Sets the default Frame-Per-Seconds to 100 to have smoother gameplay
	public static final int GAME_FPS = 100;
	
	
	/*Contructor: 
	 * -takes in two integers for width and height in order to set the size of the JFrame window
	 */
	/**
	 * @param width
	 * @param height
	 */
	public GameScreen(int width, int height) {
		
		//Sets the JFrame properties
		this.setResizable(false); 
		Dimension d  = Toolkit.getDefaultToolkit().getScreenSize();
		this.width = (int) d.getWidth();
		this.height = (int) d.getHeight();
		PLAY_SCREEN_WIDTH=this.width;
		PLAY_SCREEN_HEIGHT=this.height;
		CR_SCREEN_WIDTH = this.width;
		CR_SCREEN_HEIGHT = this.height;
		
		//Sets JFrame operations
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		
	}
	
	/**@author Miguel
	 *-When called, this redraws the JFrame, Background, etc
	 */
	public void redraw() {
		repaint();
	}
	

	/**@author Miguel
	 *-Sets the size of the screen to Play_screen_width/height when called
	 */
	public void setPlaySize() {
		this.setPreferredSize(new Dimension(PLAY_SCREEN_WIDTH, PLAY_SCREEN_HEIGHT));
		this.pack();
	}
	
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
	
}
