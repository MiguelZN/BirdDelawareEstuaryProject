package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.BorderLayout;

import javax.swing.JButton;

/*Class: TitleScreenView
 * -class that acts as the View of the TitleScreen 
 * -contains methods and control over the drawing of the TitleScreen
 */
public class TitleScreenView extends GameView {
	
	JButton RedKnot;
	JButton ClapperRail;
	JButton Instructions;
	
	public TitleScreenView() {
		this.RedKnot = new JButton("RED KNOT");
		this.ClapperRail = new JButton("CLAPPER RAIL");
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.add(RedKnot, BorderLayout.WEST);
		this.add(ClapperRail, BorderLayout.EAST);
		//this.add(Instructions, BorderLayout.NORTH);
	}
}
