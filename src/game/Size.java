package game;

import java.io.Serializable;

/*Created by Miguel:
 * -A Size instance is included in each GameObject in order
 * to keep track of their width, and height properties (in order
 * to draw them onto the screen)
 */
public class Size implements Serializable{
	private int width,height;
	
	/*Contructor*/
	/**
	 * @param width
	 * @param height
	 */
	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/*Getters*/
	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	
	
}
