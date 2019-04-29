package game;

import java.awt.Graphics;

//Static Helper Methods
public class Utility {
	
	//returns a random int from the [inputted min, inputted max]
	public static int randRangeInt(int min_num,int max_num){
		return (int)(Math.random()*((max_num - min_num) + 1)+min_num);
	}
	
	public static boolean GameObjectCollision(GameObject o1, GameObject o2) {
		return o1.hitBox.intersects(o2.hitBox);
	}
	
	public static void drawHitBox(Graphics g, HitBox hb, boolean debug) {
		if(debug) {
			g.drawRect(hb.x, hb.y, hb.width, hb.height);
		}
	}
	
	public static void drawHitBoxPoint(Graphics g, HitBox hb, boolean debug) {
		if(debug) {
			g.drawRect(hb.x, hb.y, hb.width, hb.height);
			g.fillOval(hb.getPosition().getX(), hb.getPosition().getY(), 10,10);
		}
	}


}
