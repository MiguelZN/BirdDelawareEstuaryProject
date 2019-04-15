package game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: InstructionsView
 * -contains the drawing methods/images for the Instructions mode
 */
public class InstructionsView extends GameView{
	ImageIcon titleImage;
	ImageIcon redknot;
	ImageIcon clapperrail;
	JButton nextButton;
	JButton backtoMenuButton;
	JLabel titlelogolabel;
	JLabel currentInstructions;
	Controller controller;
	
	BirdType current_bird_info;
	
	
	public ImageIcon getTitleImage() {
		return titleImage;
	}
	
	public JButton getNextButton() {
		return nextButton;
	}
	public JButton getBacktoMenuButton() {
		return backtoMenuButton;
	}
	
	public InstructionsView(Controller c) {
		super(c);
		this.nextButton = new JButton("next");
		this.nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showcaseNextInstructions();
			}
		});
		
		this.backtoMenuButton = new JButton("Back-to-Menu");
		this.backtoMenuButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        c.changeView(GameMode.TITLESCREEN);
		    }
		});
		this.nextButton.setSize(75, 30);
		this.backtoMenuButton.setSize(75,30);
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		ImageIcon titleiconLogo = new ImageIcon("resources/images/instructions.png");
		this.titlelogolabel = new JLabel(titleiconLogo);
		this.titlelogolabel.setSize(200, 50);

		this.currentInstructions = new JLabel(clapperrail);
		this.currentInstructions.setSize(300, 300);
		this.currentInstructions.setBorder(new EmptyBorder(10,10,10,10));
		this.current_bird_info = BirdType.REDKNOT;
		
		
		this.add(titlelogolabel, BorderLayout.PAGE_START);
		this.add(this.nextButton, BorderLayout.EAST);
		this.add(this.currentInstructions, BorderLayout.WEST);
		this.add(backtoMenuButton, BorderLayout.PAGE_END);
		this.titlelogolabel.setVisible(true);
		System.out.println("TITLELOGO");
		
		showcaseNextInstructions();
	}
	
	public void showcaseNextInstructions() {
		if(this.current_bird_info==BirdType.REDKNOT) {
			this.current_bird_info = BirdType.CLAPPERRAIL;
		}
		else {
			this.current_bird_info = BirdType.REDKNOT;
		}
		
		switch(this.current_bird_info) {
		case REDKNOT:
			this.currentInstructions.setIcon(new ImageIcon("resources/images/instructionsredknot.png"));
			break;
		
		case CLAPPERRAIL:
			this.currentInstructions.setIcon(new ImageIcon("resources/images/clapperrailinstructions.png"));
			break;
		}
		
		this.currentInstructions.setVisible(true);
	}
	
	
}
