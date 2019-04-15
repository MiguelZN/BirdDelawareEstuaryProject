package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/*Class: GameView
 * -abstract superClass which embodies the View of MVC
 * -RedKnot, ClapperRail Views extend this class
 * -contains methods and all the images required to draw onto the screen
 */
public abstract class GameView extends JPanel{
	
	private BufferedImage gameImage;
	private BufferedImage miniMap;
	private BufferedImage birdImage;
	private BufferedImage foodImage;
	private BufferedImage nestImage;
	protected Controller controller;
	
	public void paintComponent(Graphics g) {
		
	}
	
	public GameView(Controller c) {
		super();
		controller=c;
	}
	
	public void updateView() {
		
	}
	public void setController(Controller c){
		controller=c;
	}
	private BufferedImage createImage() {
		gameImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
		
		return gameImage;
	}
	public Controller getController(){
		return controller;
	}

}
