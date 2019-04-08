/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*Class: GameView
 * -abstract superClass which embodies the View of MVC
 * -RedKnot, ClapperRail Views extend this class
 * -contains methods and all the images required to draw onto the screen
 */
public abstract class GameView {
	
	private BufferedImage gameImage;
	private BufferedImage miniMap;
	private BufferedImage birdImage;
	private BufferedImage foodImage;
	private BufferedImage nestImage;
	
	public void paint(Graphics g) {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public GameView() {
		
	}
	
	public void updateView() {
		
	}
	
	private BufferedImage createImage() {
		gameImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
		
		return gameImage;
	}

}
