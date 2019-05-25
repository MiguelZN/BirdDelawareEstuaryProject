package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*Class: TitleScreenView
 * -class that acts as the View of the TitleScreen 
 * -contains methods and control over the drawing of the TitleScreen
 */
public class TitleScreenView extends WindowView{
	JPanel hold_content;
	JButton RedKnot;
	JButton ClapperRail;
	JButton Instructions;
	JLabel Title_Logo;
	
	
	public TitleScreenView() {
		super();
		
		//Loads up the images associated with the TitleScreen
		try {
			loadAllImages("/resources/images/titlescreen");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Initializes JComponents
		this.hold_content = new JPanel(); 
		this.RedKnot = new JButton("RED KNOT");
		this.ClapperRail = new JButton("CLAPPER RAIL");
		this.Instructions = new JButton("INSTRUCTIONS");
		this.Title_Logo = new JLabel();

		//Sets the ratio and size of the JComponents
		double width_ratio = .75;
		double height_ratio = .2;
		int content_width = (int)(GameScreen.PLAY_SCREEN_WIDTH*width_ratio);
		int content_height = (int)(GameScreen.PLAY_SCREEN_HEIGHT*height_ratio);
		Dimension c_dim = new Dimension(content_width,content_height);

		//Organizes the buttons/JComponents on the TitleScreen
		int row_gap = 20; //20px spacing between JComponents
		BorderLayout hold_layout = new BorderLayout();
		hold_layout.setVgap(row_gap);
		this.hold_content.setLayout(hold_layout);
		this.hold_content.add(this.RedKnot, BorderLayout.NORTH);
		this.hold_content.add(this.ClapperRail, BorderLayout.CENTER);
		this.RedKnot.setPreferredSize(c_dim);
		this.ClapperRail.setPreferredSize(c_dim);
		
		//Sets the dimensions and places the Delaware Estuary Logo 
		double height_extension_ratio = 1.5;
		Dimension title_dim = new Dimension((int)c_dim.getWidth()/2,(int)(c_dim.getHeight()*height_extension_ratio));
		BufferedImage title_logo_BF = (BufferedImage)objectMap.get(TitleAsset.TITLELOGO);
		this.Title_Logo.setIcon(this.createScaledImageIcon(title_logo_BF, (int)title_dim.getWidth(), (int)title_dim.getHeight()));
		this.Title_Logo.setPreferredSize(title_dim);
		
		this.add(Title_Logo);
		this.add(this.hold_content);
		
		//Hides the instructions button (not needed since we have the tutorials)
		this.Instructions.setVisible(false);
		
		//Sets the background a light blueish color
		Color light_blue_color = new Color(216,231,255);
		Color title_screen_bc = light_blue_color;
		this.setBackground(title_screen_bc);
		
	}


	/* (non-Javadoc)
	 * @see game.WindowView#fnameMapCreate()
	 */
	public void fnameMapCreate() {
		System.out.println("ADDED");
		fnameMap.put("deestuary_actuallogo.png", TitleAsset.TITLELOGO);

	}
	
	/**@author Miguel
	 * @param bf (BufferedImage)
	 * @param width
	 * @param height
	 * @return ImageIcon
	 * -Returns a new scaled ImageIcon that was converted from a BufferedImage
	 */
	public ImageIcon createScaledImageIcon(BufferedImage bf, int width, int height) {
		Image scaled_image = bf.getScaledInstance(width, height,
		        Image.SCALE_SMOOTH);
		ImageIcon new_image_icon = new ImageIcon(scaled_image);
		return new_image_icon;
	}
	
}
