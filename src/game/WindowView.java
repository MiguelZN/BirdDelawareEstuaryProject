package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class WindowView extends JPanel{
	private static final long serialVersionUID = 1L;
	private BufferedImage gameImage;
	protected HashMap<Object, Object> objectMap;
	protected HashMap<String, Object> fnameMap; //takes in a string and an Enum for the views
	
	public WindowView() {
		objectMap = new HashMap<>();
		fnameMap = new HashMap<>();
	}
	
	
	
	/*
	 * Boring method requires human effort.
	 * Put all files that will be loaded in both this method, and in the
	 * red knot asset enum.
	 */
	/**
	 * 
	 */
	public abstract void fnameMapCreate();
	
	
	/*
	 * This method loads all images that we will ever use in this view, and puts them
	 * into a hashmap as the values, each with a key that we know, and will use when drawing images frmo objects.
	 */
	/**
	 * @author Derek Baum
	 * @param relevent_res_path
	 * @throws IOException
	 */
	public void loadAllImages(String relevent_res_path) throws IOException{
		fnameMapCreate(); //Creates the fileNameMap (places the image names of the images into a hashMap)
		File[] files = new File(System.getProperty("user.dir") + relevent_res_path).listFiles();
		
		for(File f : files){
			Object loaded_image = loadImage(f); //o is either a BufferedImage or ImageIcon
			System.out.println(f.getName());
			
			/*
			 * NOTE: the format of all sprite filenames will be:
			 * sprite-FRAMECOUNT-ANIMATIONNAME.png
			 */
			if(f.getName().startsWith("sprite-")){
				int frameCount = Integer.parseInt(f.getName().split("-")[1]);
				objectMap.put(fnameMap.get(f.getName()), new Animation(loaded_image,frameCount));
			}else{
				//In the objectMap it places either a BufferedImage or ImageIcon at the fnameMap.get()
				objectMap.put(fnameMap.get(f.getName()), loaded_image);
			}
			
		}
	}
	
	//Returns an object (so either a BufferedImage or ImageIcon)
	//public abstract Object loadImage(File f);
	/**@author Derek Baum
	 * @param f
	 * @return
	 */
	public Object loadImage(File f) {
		System.out.println("LOAD");
		Object output=null;
		try{
			output = ImageIO.read(f);
		}catch (IOException e){
			e.printStackTrace();
		}
		return output;
	}
}
