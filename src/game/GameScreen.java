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
	
	
}
