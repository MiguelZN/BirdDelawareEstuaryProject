package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.Instructions = new JButton("INSTRUCTIONS");
		RedKnot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Controller c;
				if((c=getController())!= null)
					c.changeView(GameMode.REDKNOT);
			}
		});
		ClapperRail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				Controller c;
				if((c=getController())!= null){
					c.changeView(GameMode.CLAPPERRAIL);
					System.out.println("trying to change to the clapperrail view");
				}
			}
		});
		Instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Controller c;
				if((c=getController())!= null)
					c.changeView(GameMode.INSTRUCTIONS);
			}
		});
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.add(RedKnot, BorderLayout.WEST);
		this.add(ClapperRail, BorderLayout.EAST);
		this.add(Instructions, BorderLayout.PAGE_START);
	}
}
