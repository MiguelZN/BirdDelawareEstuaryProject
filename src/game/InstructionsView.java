package game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: InstructionsView
 * -contains the drawing methods/images for the Instructions mode
 */
public class InstructionsView extends GameView{
	ImageIcon titleImage;
	BufferedImage[] previewImages;
	JButton nextButton;
	JButton backtoMenuButton;
	JLabel titlelogolabel;
	
	public ImageIcon getTitleImage() {
		return titleImage;
	}
	public BufferedImage[] getPreviewImages() {
		return previewImages;
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
		this.backtoMenuButton = new JButton("Back-to-Menu");
		this.backtoMenuButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        c.changeView(GameMode.TITLESCREEN);
		    }
		});
//		this.nextButton.setSize(75, 30);
//		this.backtoMenuButton.setSize(75,30);
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		ImageIcon titleiconLogo = new ImageIcon("resources/images/instructions.png");
		this.titlelogolabel = new JLabel(titleiconLogo);
		this.titlelogolabel.setSize(200, 50);
		
		this.add(titlelogolabel, BorderLayout.PAGE_START);
		this.add(nextButton, BorderLayout.EAST);
		this.add(backtoMenuButton, BorderLayout.PAGE_END);
		this.titlelogolabel.setVisible(true);
		System.out.println("TITLELOGO");
	}
	
	
}
