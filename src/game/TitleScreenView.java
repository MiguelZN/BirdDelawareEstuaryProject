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
public class TitleScreenView extends WindowView{
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

}
