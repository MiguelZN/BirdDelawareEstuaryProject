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
	protected HashMap<Object, Object> objectMap;
	protected HashMap<String, Object> fnameMap; //takes in a string and an Enum for the views
	
	public void paintComponent(Graphics g) {
		
	}
	
	public GameView(Controller c, GameState gs) {
		super();
		controller=c;
		state=gs;
		
		objectMap = new HashMap<>();
		fnameMap = new HashMap<>();
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
	public void loadAllImages(String relevent_res_path) throws IOException{
		fnameMapCreate(); //Creates the fileNameMap (places the image names of the images into a hashMap)
		File[] files = new File(System.getProperty("user.dir") + relevent_res_path).listFiles();
		
		for(File f : files){
			Object loaded_image = loadImage(f); //o is either a BufferedImage or ImageIcon
			
			//In the objectMap it places either a BufferedImage or ImageIcon at the fnameMap.get()
			objectMap.put(fnameMap.get(f.getName()), loaded_image);
		}
	}
	
	//Returns an object (so either a BufferedImage or ImageIcon)
	//public abstract Object loadImage(File f);
	public Object loadImage(File f) {
		Object output=null;
		try{
			output = ImageIO.read(f);
		}catch (IOException e){
			e.printStackTrace();
		}
		return output;
	}

}
