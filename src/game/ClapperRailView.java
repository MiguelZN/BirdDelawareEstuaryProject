package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

/*Class: ClapperRailView
 * -class that acts as the View of the ClapperRail GameMode
 * -contains methods and control over the drawing of the ClapperRail minigame
 */
public class ClapperRailView extends GameView{
	//JButton jump;
	
	public ClapperRailView(Controller controller) {
		super(controller);
		//BorderLayout layout = new BorderLayout();
		//this.jump=new JButton("JUMP");
		//this.setLayout(layout);
		//this.add(jump, BorderLayout.WEST);
		controller.getScreen().setSize(1000, 500);
		
		//this.jump.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
		//		controller.getClapperRailGS().getCR().jump();
		//	}
		//});
		
		Container current_layout = controller.getScreen().getContentPane();
	}
	
	public void paintComponent(Graphics g) {
		System.out.println("PAINT");
		g.setColor(new Color(224,160, 42));
		Position p = this.controller.getClapperRailGS().getCR().getPosition();
		g.fillOval(p.getX(),p.getY(),controller.getClapperRailGS().getCR().getSize(),controller.getClapperRailGS().getCR().getSize());
		this.setVisible(true);
		
	}

}
