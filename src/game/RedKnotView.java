package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: RedKnotView
 * -class that acts as the View of the RedKnot GameMode
 * -contains methods and control over the drawing of the RedKnot minigame
 */
public class RedKnotView extends GameView {
	RedKnotGameState model;
	
	public RedKnotView(Controller c){
		super(c);
		c.getScreen().setSize(1000, 500);
		this.model = new RedKnotGameState();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		Position p = model.getRL().getPosition();
		g.fillOval(p.getX(),p.getY(),model.getRL().getSize(),model.getRL().getSize());
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
	}
	
	
}