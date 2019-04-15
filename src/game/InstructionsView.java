package game;

import java.awt.BorderLayout;
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
	
	public InstructionsView() {
		this.nextButton = new JButton("next");
		this.backtoMenuButton = new JButton("Back-to-Menu");
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.add(nextButton, BorderLayout.PAGE_START);
		this.add(backtoMenuButton, BorderLayout.EAST);
		
		ImageIcon iconLogo = new ImageIcon("Images/YourCompanyLogo.png");
		JLabel l = new JLabel(iconLogo);
	}
	
	
}
