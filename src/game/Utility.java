package game;

import java.awt.Graphics;
import java.util.List;

/*Created by Miguel:
 * -The Utility class is used to hold static helper methods 
 * -Holds miscellaneous methods such as a random int generator, GameObjectCollision
 * detection, drawHitBox for debugging, etc.
 */
public class Utility {
	
	
	/*Created by Miguel:
	 * -Takes in two integers: a min, and max
	 * -Returns a random int from the [inputted min, inputted max]
	 */
	/**
	 * @author Miguel
	 * @param min_num
	 * @param max_num
	 * @return
	 */
	public static int randRangeInt(int min_num,int max_num){
		return (int)(Math.random()*((max_num - min_num) + 1)+min_num);
	}
	
	/*Created by Miguel:
	 * -Takes in two GameObject instances
	 * -Returns either true or false if the two GameObjects intersect (collision occurs)
	 */
	/**
	 * @author Miguel
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean GameObjectCollision(GameObject o1, GameObject o2) {
		return o1.hitBox.intersects(o2.hitBox);
	}
	
	/*Created by Miguel:
	 * -Takes in three arguments: A Graphics instace, HitBox instance,and
	 * a boolean value
	 * -If the 'debug' boolean is true, this allows the Graphics instance
	 * to draw a rectangle based on the HitBox's properties
	 */
	/**
	 * @author Miguel
	 * @param g
	 * @param hb
	 * @param debug
	 */
	public static void drawHitBox(Graphics g, HitBox hb, boolean debug) {
		if(debug) {
			g.drawRect(hb.x, hb.y, hb.width, hb.height);
		}
	}
	
	/*Created by Miguel:
	 * -Takes in three arguments: A Graphics instace, HitBox instance,and
	 * a boolean value
	 * -If the 'debug' boolean is true, this allows the Graphics instance
	 * to draw a rectangle based on the HitBox's properties
	 * -Draws a circle at the HitBox's position
	 */
	/**
	 * @author Miguel
	 * @param g
	 * @param hb
	 * @param debug
	 */
	public static void drawHitBoxPoint(Graphics g, HitBox hb, boolean debug) {
		if(debug) {
			g.drawRect(hb.x, hb.y, hb.width, hb.height);
			int circle_size= 10;
			g.fillOval(hb.getPosition().getX(), hb.getPosition().getY(), circle_size,circle_size);
		}
	}
	
	/**@author Miguel
	 * @param list
	 * @param amount
	 * -Utility function which takes an a list, and integer 'amount'
	 * and checks and pulls the object at a random index and removes it
	 */
	public void removeElements(List<Object> list, int amount) {
		for(int i=0;i<amount;i++) {
			int random_index = Utility.randRangeInt(0, list.size());
			list.remove(random_index);
		}
	}
	
	


}
