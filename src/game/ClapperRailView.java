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
public class ClapperRailView extends GameView implements KeyListener{
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
		
		this.jump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.getCR().jump();
			}
		});
		
		Container current_layout = c.getScreen().getContentPane();
		current_layout.addKeyListener(this);
		c.getScreen().addKeyListener(this);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(224,160, 42));
		Position p = model.getCR().getPosition();
		g.fillOval(p.getX(),p.getY(),model.getCR().getSize(),model.getCR().getSize());
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_RIGHT : model.getCR().move();break;
		case KeyEvent.VK_LEFT : model.getCR().moveLeft();break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
