package game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
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
	
	
	/**
	 * @return
	 */
	public ImageIcon getTitleImage() {
		return titleImage;
	}
	
	/**
	 * @return
	 */
	public JButton getNextButton() {
		return nextButton;
	}
	/**
	 * @return
	 */
	public JButton getBacktoMenuButton() {
		return backtoMenuButton;
	}
	/**
	 * 
	 */
	public InstructionsView() {
		super();
		
		try {
			loadAllImages("/resources/images/instructions");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.nextButton = new JButton("next");
		this.nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showcaseNextInstructions();
			}
		});
		
		this.backtoMenuButton = new JButton("Back-to-Menu");
		this.nextButton.setSize(75, 30);
		this.backtoMenuButton.setSize(75,30);
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		//File loaded_file = (File) fnameMap.get(InstructionsAsset.INSTRUCTIONS_LABEL);
		
		this.titlelogolabel = new JLabel((Icon) objectMap.get(InstructionsAsset.INSTRUCTIONS_LABEL));
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

	
	/**
	 * 
	 */
	public void showcaseNextInstructions() {
		if(this.current_bird_info==BirdType.REDKNOT) {
			this.current_bird_info = BirdType.CLAPPERRAIL;
		}
		else {
			this.current_bird_info = BirdType.REDKNOT;
		}
		
		switch(this.current_bird_info) {
		case REDKNOT:
			this.currentInstructions.setIcon((Icon) objectMap.get(InstructionsAsset.RK_INSTRUCTIONS));
			break;
		
		case CLAPPERRAIL:
			this.currentInstructions.setIcon((Icon) objectMap.get(InstructionsAsset.CL_INSTRUCTIONS));
			break;
		}
		
		this.currentInstructions.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see game.GameView#fnameMapCreate()
	 */
	@Override
	public void fnameMapCreate() {
		fnameMap.put("cl_instructions.png", InstructionsAsset.CL_INSTRUCTIONS);
		fnameMap.put("rk_instructions.png", InstructionsAsset.RK_INSTRUCTIONS);
		fnameMap.put("instructions_label.png", InstructionsAsset.INSTRUCTIONS_LABEL);	
	}
	
	/* (non-Javadoc)
	 * @see game.GameView#loadImage(java.io.File)
	 */
	@Override
	public ImageIcon loadImage(File f){
		ImageIcon output=null;
		String res_path = f.getPath();
		try{
			output = new ImageIcon(res_path);
		}catch (Exception e){
			e.printStackTrace();
		}
		return output;
	}

	//Not Needed for InstructionsView
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
