package game;

import java.awt.BorderLayout;

import javax.swing.JButton;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: RedKnotView
 * -class that acts as the View of the RedKnot GameMode
 * -contains methods and control over the drawing of the RedKnot minigame
 */
public class RedKnotView extends GameView {
	
	JButton jump;
	
	public RedKnotView(){
		BorderLayout layout = new BorderLayout();
		this.jump=new JButton("JUMP");
		this.setLayout(layout);
		this.add(jump, BorderLayout.WEST);
	}

}
