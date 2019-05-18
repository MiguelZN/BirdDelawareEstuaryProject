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
	
	/*TODO: fix later
	 * This isn't mvc but I'm leaving it for now.
	 */
	/**
	 * 
	 */
	public TitleScreenView() {
		super();
		
		try {
			loadAllImages("/resources/images/titlescreen");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.hold_content = new JPanel(); 
		this.RedKnot = new JButton("RED KNOT");
		this.ClapperRail = new JButton("CLAPPER RAIL");
		this.Instructions = new JButton("INSTRUCTIONS");
		this.Title_Logo = new JLabel();

		int content_width = (int)(GameScreen.PLAY_SCREEN_WIDTH*.75);
		int content_height = (int)(GameScreen.PLAY_SCREEN_HEIGHT*.2);
		Dimension c_dim = new Dimension(content_width,content_height);

		BorderLayout hold_layout = new BorderLayout();
		hold_layout.setVgap(20);
		this.hold_content.setLayout(hold_layout);
		//this.hold_content.setPreferredSize(c_dim);
		this.hold_content.add(this.RedKnot, BorderLayout.NORTH);
		this.hold_content.add(this.ClapperRail, BorderLayout.CENTER);
		this.hold_content.add(this.Instructions, BorderLayout.SOUTH);

		this.RedKnot.setPreferredSize(c_dim);
		this.Instructions.setPreferredSize(c_dim);
		this.ClapperRail.setPreferredSize(c_dim);
		
		Dimension title_dim = new Dimension((int)c_dim.getWidth(),(int)(c_dim.getHeight()*1.25));
		BufferedImage title_logo_BF = (BufferedImage)objectMap.get(TitleAsset.TITLELOGO);
		this.Title_Logo.setIcon(this.createScaledImageIcon(title_logo_BF, (int)title_dim.getWidth(), (int)title_dim.getHeight()));
		this.Title_Logo.setPreferredSize(title_dim);
		System.out.println("SDADSA");
		this.add(Title_Logo);
		this.add(this.hold_content);
		
		Color title_screen_bc = new Color(216, 231, 255);
		this.setBackground(title_screen_bc);
		
	}


	public void fnameMapCreate() {
		System.out.println("ADDED");
		fnameMap.put("deestuary.png", TitleAsset.TITLELOGO);

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
	
	/* (non-Javadoc)
	 * @see game.GameView#loadImage(java.io.File)
	 */
//	@Override
//	public ImageIcon loadImage(File f){
//		ImageIcon output=null;
//		String res_path = f.getPath();
//		try{
//			output = new ImageIcon(res_path);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		return output;
//	}
	
}
