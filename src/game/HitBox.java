package game;

import java.awt.Rectangle;

//Helps keep track of the width and height of GameObjects to make it more organized
public class HitBox extends Rectangle{
	
	HitBox(int x, int y, int width, int height){
		super(x,y,width,height);
	}
	
	HitBox(Position p, int width, int height){
		super(p.getX(),p.getY(),width,height);
	}
	
	public void changeWidth(int new_width) {
		this.width = new_width;
	}
	
	public void changeHeight(int new_height) {
		this.height = new_height;
	}
	
	public void setSize(int new_width, int new_height) {
		this.width = new_width;
		this.height = new_height;
	}
	
	
}
