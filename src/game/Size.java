package game;

/*Created by Miguel:
 * -A Size instance is included in each GameObject in order
 * to keep track of their width, and height properties (in order
 * to draw them onto the screen)
 */
public class Size {
	private int width,height;
	
	/*Contructor*/
	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/*Getters*/
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	
}
