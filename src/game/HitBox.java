package game;

import java.awt.Rectangle;

//Helps keep track of the width and height of GameObjects to make it more organized
public class HitBox extends Rectangle{
	int width,height;
	Position p;
	
	
	HitBox(int x, int y, int width, int height){
//		int topleftx = x - (width/2);
//		int toplefty = y - (height/2);
		super(x - (width/2), y - (height/2),width,height); //Sets the Rectangle position and width/height
		this.p  =new Position(x,y);
		this.width = width;
		this.height = height;
	}
	
	HitBox(Position p, int width, int height){
		super(p.getX()-(width/2),p.getY()-(height/2),width,height);
		//super(p.getX(), p.getY(), width, height);
		this.p = p;
		this.width = width;
		this.height = height;
	}
	

	public Position getPosition() {
		return this.p;
	}
	
	public void setPosition(Position p) {
		this.p = p;
		this.updateRectangleHitBox();
	}
	
	public void updateRectangleHitBox() {
		this.setLocation(p.getX()-(width/2),p.getY()-(height/2));
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
