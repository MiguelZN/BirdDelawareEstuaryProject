package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

/*Class: TitleScreenView
 * -class that acts as the View of the TitleScreen 
 * -contains methods and control over the drawing of the TitleScreen
 */
public class TitleScreenView extends GameView{
	JButton RedKnot;
	JButton ClapperRail;
	JButton Instructions;
	
	/*TODO: fix later
	 * This isn't mvc but I'm leaving it for now.
	 */
	/**
	 * 
	 */
	public TitleScreenView() {
		super();
		this.RedKnot = new JButton("RED KNOT");
		this.ClapperRail = new JButton("CLAPPER RAIL");
		this.Instructions = new JButton("INSTRUCTIONS");
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.add(ClapperRail, BorderLayout.EAST);
		this.add(Instructions, BorderLayout.PAGE_START);
		this.add(RedKnot, BorderLayout.WEST);
	}

	/* (non-Javadoc)
	 * @see game.GameView#fnameMapCreate()
	 */
	@Override
	public void fnameMapCreate() {
		//Title currently does not have any images
		
	}
	
	/* (non-Javadoc)
	 * @see game.GameView#loadImage(java.io.File)
	 */
	public Object loadImage(File f) {
		return null;
	}


	/* (non-Javadoc)
	 * @see game.GameView#scrollImage(java.awt.Graphics, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void scrollImage(Graphics g, Object background1, Object background2) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see game.GameView#update(java.util.ArrayList)
	 */
	@Override
	public void update(ArrayList<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		
	}


	//maybe we need to split our views that aren't games from the gameview, but
	// if it is only for the purpose of this single method that they don't share, id be fine just leaving this
	// here since it changes nothing.


	/* (non-Javadoc)
	 * @see game.GameView#updateScore(int)
	 */
	@Override
	public void updateScore(int x) {
		// TODO Auto-generated method stub
		
	}
}
