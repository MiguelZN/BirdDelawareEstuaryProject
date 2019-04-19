package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
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
	private GameState state;
	protected HashMap<Object, BufferedImage> objectMap;
	protected HashMap<String,Object> fnameMap;
	
	public void paintComponent(Graphics g) {
		
	}
	
	public GameView(Controller c, GameState gs) {
		super();
		controller=c;
		state=gs;
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
	
	/*
	 * Boring method requires human effort.
	 * Put all files that will be loaded in both this method, and in the
	 * red knot asset enum.
	 */
	public abstract void fnameMapCreate();
	
	
	/*
	 * This method loads all images that we will ever use in this view, and puts them
	 * into a hashmap as the values, each with a key that we know, and will use when drawing images frmo objects.
	 */
	public void loadAllImages(String res_path) throws IOException{
		fnameMapCreate();
		File[] files = new File(System.getProperty("user.dir") + res_path).listFiles();
		for(File f : files){
			objectMap.put(fnameMap.get(f.getName()), loadImage(f));
		}
	}
	private BufferedImage loadImage(File f){
		BufferedImage output=null;
		try{
			output = ImageIO.read(f);
		}catch (IOException e){
			e.printStackTrace();
		}
		return output;
	}

}
