package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

/*Class: ClapperRailView
 * -class that acts as the View of the ClapperRail GameMode
 * -contains methods and control over the drawing of the ClapperRail minigame
 */
public class ClapperRailView extends GameView {
	ClapperRailGameState model;
	JButton jump;
	
	public ClapperRailView(Controller c) {
		super(c);
		BorderLayout layout = new BorderLayout();
		this.jump=new JButton("JUMP");
		this.setLayout(layout);
		this.add(jump, BorderLayout.WEST);
		c.getScreen().setSize(1000, 500);
		
		this.model = new ClapperRailGameState();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(224,160, 42));
		Position p = model.getCR().getPosition();
		g.fillOval(p.getX(),p.getY(),model.getCR().getSize(),model.getCR().getSize());
		
	}
}
