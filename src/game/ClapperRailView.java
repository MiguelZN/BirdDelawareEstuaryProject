package game;
/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/*Class: ClapperRailView
 * -class that acts as the View of the ClapperRail GameMode
 * -contains methods and control over the drawing of the ClapperRail minigame
 */




/*
 * TODO:
 * 	Update ClapperRailView to be up to speed with redknot view,
 *	I have modified redknot view with recent commits and added generalization to the
 *	process that we will use to load images, and draw them to the screen from the model. - Derek
 */
public class ClapperRailView extends GameView{
	private int score;
	private ArrayList<Platform> platforms;
	private ArrayList<Food> food;
	private ArrayList<Material> materials;
	private ArrayList<ClapperQuestion> questionBlocks;
	private Flood flood;
	int backgroundY;
	ClapperRail CL;
	private boolean tutorialMode = false;
	private int tutorialImageNum = 1;
	int height_tracker = GameScreen.CR_SCREEN_HEIGHT;
	boolean firstScroll = true;
	/**
	 * 
	 */
	public ClapperRailView() {
		super();
		CL=new ClapperRail();
		platforms = new ArrayList<>();
		food = new ArrayList<>();
		materials = new ArrayList<>();
		flood = new Flood(0,0);
		questionBlocks = new ArrayList<>();
		this.score = 0;
		this.backgroundY = 0;
		this.setBackground(Color.RED);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(GameScreen.CR_SCREEN_WIDTH, GameScreen.CR_SCREEN_HEIGHT));
//		this.setBounds(this.getLocation().x,this.getLocation().y,GameScreen.CR_SCREEN_WIDTH,GameScreen.CR_SCREEN_HEIGHT);
		this.revalidate();
		
		
		
		try {
			loadAllImages("/resources/images/clapperrail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		height_tracker=GameScreen.CR_SCREEN_HEIGHT-10;
	}
	
	/* (non-Javadoc)
	 * @see game.GameView#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(firstScroll){
			scrollImage(g, ClapperRailAsset.BACKGROUND, ClapperRailAsset.BACKGROUND2);
		}else{
			scrollImage(g, ClapperRailAsset.BACKGROUND2, ClapperRailAsset.BACKGROUND2);
		}
		//this.setBackground(Color.RED);
		g.setColor(new Color(224,160, 42));
		drawBird(g);
		drawPlatforms(g);
		drawFlood(g);
		drawFood(g);
		drawMaterials(g);
		drawQuestionBlocks(g);
		drawEnergy(g);
		drawScore(g);
		drawMaterialCount(g);
		//this.setBackground(Color.RED);
		this.setOpaque(true);
		
		if(tutorialMode){
			drawTutorialImage(g);
		}
		
	}

	/* (non-Javadoc)
	 * @see game.GameView#fnameMapCreate()
	 */
	@Override
	public void fnameMapCreate() {
		fnameMap.put("swamp_background_ground.png", ClapperRailAsset.BACKGROUND);
		fnameMap.put("swamp_background_sky2.png", ClapperRailAsset.BACKGROUND2);
//		fnameMap.put("clapper-background-pattern.png", ClapperRailAsset.BACKGROUND);
		fnameMap.put("energy_icon.png", ClapperRailAsset.ENERGY);
		fnameMap.put("platform1.png", ClapperRailAsset.PLATFORM);
		fnameMap.put("crab.png",ClapperRailAsset.CRAB);
		fnameMap.put("stick.png",ClapperRailAsset.STICK);
		fnameMap.put("water_texture.jpg", ClapperRailAsset.WATER);
		fnameMap.put("tutorialimage1.png", ClapperRailAsset.TUTORIALIMAGE1);
		fnameMap.put("tutorialimage2.png", ClapperRailAsset.TUTORIALIMAGE2);
		fnameMap.put("tutorialimage3.png", ClapperRailAsset.TUTORIALIMAGE3);
		fnameMap.put("tutorialimage4.png", ClapperRailAsset.TUTORIALIMAGE4);
		fnameMap.put("tutorialimage5.png", ClapperRailAsset.TUTORIALIMAGE5);
		fnameMap.put("tutorialimage6.png", ClapperRailAsset.TUTORIALIMAGE6);
		fnameMap.put("tutorialimage7.png", ClapperRailAsset.TUTORIALIMAGE7);
		fnameMap.put("tutorialimage8.png", ClapperRailAsset.TUTORIALIMAGE8);
		fnameMap.put("tutorialimage9.png", ClapperRailAsset.TUTORIALIMAGE9);
		fnameMap.put("questionmark.png", ClapperRailAsset.QUESTIONBLOCK);
		fnameMap.put("clapper.png", ClapperRailAsset.CLAPPERBIRD);
	}


	
	public void drawBird(Graphics g){
		Position posn = CL.getPosition();
		int size = CL.getSize().getWidth();
		g.drawImage((Image)objectMap.get(ClapperRailAsset.CLAPPERBIRD), posn.getX(), posn.getY(),size,size,null,this);
	}
	
	/* (non-Javadoc)
	 * @see game.GameView#scrollImage(java.awt.Graphics, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void scrollImage(Graphics g, Object background1, Object background2) {
		g.drawImage((Image) objectMap.get(background1), 0, backgroundY, GameScreen.CR_SCREEN_WIDTH, GameScreen.CR_SCREEN_HEIGHT, null, this);
		g.drawImage((Image) objectMap.get(background2), 0, backgroundY-GameScreen.CR_SCREEN_HEIGHT, GameScreen.CR_SCREEN_WIDTH, GameScreen.CR_SCREEN_HEIGHT, null, null);
	}


	/* (non-Javadoc)
	 * @see game.GameView#update(java.util.ArrayList)
	 */
	@Override
	public void update(ArrayList<GameObject> gameObjects) {
		platforms = new ArrayList<>();
		food = new ArrayList<>();
		materials = new ArrayList<>();
		questionBlocks = new ArrayList<>();
		this.CL = (ClapperRail)gameObjects.get(0);
		this.flood = (Flood)gameObjects.get(1);
		for(GameObject go : gameObjects) {
			if(go instanceof Platform) {
				Platform pf = (Platform)go;
				platforms.add(pf);
				if(pf.getFood()!=null){
					food.add(pf.getFood());
				}else if(pf.getMaterial() !=null){
					materials.add(pf.getMaterial());
				}else if(pf.getQuestion() != null){
					questionBlocks.add(pf.getQuestion());
				}
			}
		}
		
	}
	public void drawTutorialImage(Graphics g){
		switch(tutorialImageNum){
			case 1: 
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE1), 100, 100, 400,228,null);
				break;
			case 2:
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE2), 100, 100, 400,228,null);
				break;
			case 3:
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE3), 100, 100, 400,228,null);
				break;
			case 4:
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE4), 100, 100, 400,228,null);
				break;
			case 5:
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE5), 100, 100, 400,228,null);
				break;
			case 6:
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE6), 100, 100, 400,228,null);
				break;
			case 7:
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE7), 100, 100, 400,228,null);
				break;
			case 8:
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE8), 100, 100, 400,228,null);
				break;
			case 9:
				g.drawImage((BufferedImage)objectMap.get(ClapperRailAsset.TUTORIALIMAGE9), 100, 100, 400,228,null);
				break;
		}
	}
	public void setTutImageNum(int x){
		tutorialImageNum = x;
	}
	public int getTutImageNum(){
		return this.tutorialImageNum;
	}
	public void drawScore(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN,ClapperRailGameState.SCORE_FONT_SIZE));
		FontMetrics fm = g.getFontMetrics();
		//System.out.println(fm.getFont());
		//The String being drawn
		String toDrawString = ClapperRailGameState.SCORE_TEXT + CL.getScore();
		int string_width = fm.stringWidth(toDrawString);
		
		g.drawString(toDrawString, GameScreen.CR_SCREEN_WIDTH/3+240, 0+ClapperRailGameState.SCORE_FONT_SIZE);
	}
	
	/**
	 * @param BackgroundX
	 */
	public void updateBackground(int backgroundY) {
		if(backgroundY > GameScreen.CR_SCREEN_HEIGHT){
			this.backgroundY=0;
			return;
		}
			
			
		if(this.height_tracker >= 0){
			this.height_tracker -= (backgroundY-this.backgroundY);
			System.out.println("height tracker is " + height_tracker);
		}else{
			firstScroll=false;
		}
		this.backgroundY = backgroundY;
	}
	public int getBackgroundY(){
		return this.backgroundY;
	}


	/**
	 * @param g
	 */
	public void drawEnergy(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN,ClapperRailGameState.ENERGY_FONT_SIZE));
		FontMetrics fm = g.getFontMetrics();
		//System.out.println(fm.getFont());
		
		//The String being drawn
		String toDrawString = ClapperRailGameState.ENERGY_TEXT + CL.getEnergy();
		int string_width = fm.stringWidth(toDrawString);
		
		g.drawString(toDrawString, GameScreen.SCREEN_BORDER_PX, 0+ClapperRailGameState.ENERGY_FONT_SIZE);
		/* hol up
		BufferedImage bi = (BufferedImage) objectMap.get(ClapperRailAsset.ENERGY);
		double mult = ((double)CL.getEnergy())/((double)ClapperRail.MAX_ENERGY);
		int height = (int)(((double)bi.getHeight())*mult);
		System.out.println("What is the " + mult);
		bi = bi.getSubimage(0, bi.getHeight()-height, bi.getWidth(),height);
		g.drawImage(bi, 0+string_width, height, bi.getWidth(),height,null,this);
		*/
		double mult = ((double)CL.getEnergy())/((double)ClapperRail.MAX_ENERGY);
		g.setColor(Color.RED);
		g.fillRect((GameScreen.CR_SCREEN_WIDTH/4)-270,15,(int)(mult*200.0), 25);
	}
	
	public void drawFlood(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawImage((Image)objectMap.get(ClapperRailAsset.WATER), flood.getPosition().getX(), flood.getPosition().getY(),GameScreen.CR_SCREEN_WIDTH,Flood.FLOOD_HEIGHT,null,this);
//		g.fillRect(flood.getPosition().getX(), flood.getPosition().getY(), GameScreen.CR_SCREEN_WIDTH, Flood.FLOOD_HEIGHT);
	}
	
	public void drawMaterialCount(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN,ClapperRailGameState.ENERGY_FONT_SIZE));
		FontMetrics fm = g.getFontMetrics();
		
		String toDrawString = ClapperRailGameState.MATERIALS_TEXT + this.CL.getMaterialCount();
		int string_width = fm.stringWidth(toDrawString);
		
		g.drawImage((Image) objectMap.get(ClapperRailAsset.STICK), (int)((1800d/1920d)*GameScreen.PLAY_SCREEN_WIDTH)-(string_width+50), 5, 50, 50, null,this);
		g.drawString(toDrawString, (int)((1800d/1920d)*GameScreen.PLAY_SCREEN_WIDTH)-(string_width), 0+ClapperRailGameState.ENERGY_FONT_SIZE);
	}
	
	public void drawPlatforms(Graphics g) {
		for(Platform p:platforms) {
			drawPlatform(p,g);
		}
	}
	
	public void drawPlatform(Platform p, Graphics g) {
		Position pos = p.getPosition();
		g.drawImage((Image) objectMap.get(ClapperRailAsset.PLATFORM), pos.getX(), pos.getY(), p.getWidth(), p.getHeight(),null,this);
		//System.out.println("DREW A PLATFORM");
	}
	
	public void drawFood(Graphics g) {
		for(Food f:food) {
			drawCrab(f,g);
		}
	}
	
	public void drawCrab(Food f, Graphics g) {
		Position pos = f.getPosition();
		g.drawImage((Image) objectMap.get(ClapperRailAsset.CRAB), pos.getX(),pos.getY(),Food.CRAB_SIZE,Food.CRAB_SIZE,null,this);
	}
	
	public void drawMaterials(Graphics g) {
		for(Material m:materials) {
			drawStick(m,g);
		}
	}
	
	public void drawQuestionBlocks(Graphics g){
		for(ClapperQuestion q : questionBlocks){
			drawQuestionBlock(q,g);
		}
	}
	public void drawQuestionBlock(ClapperQuestion cq, Graphics g){
		Position pos = cq.getPosition();
		g.drawImage((Image) objectMap.get(ClapperRailAsset.QUESTIONBLOCK), pos.getX(),pos.getY(),ClapperQuestion.BLOCK_SIZE,ClapperQuestion.BLOCK_SIZE,null,this);
	}
	
	public void drawStick(Material m, Graphics g) {
		Position pos = m.getPosition();
		g.drawImage((Image) objectMap.get(ClapperRailAsset.STICK),pos.getX(),pos.getY(),m.MAT_SIZE,m.MAT_SIZE,null,this);
	}
	public void setTutorialMode(boolean b){
		this.tutorialMode=b;
	}
	public boolean getTutorialMode(){
		return this.tutorialMode;
	}

	/* (non-Javadoc)
	 * @see game.GameView#updateScore(int)
	 */
	@Override
	public void updateScore(int x) {
		this.score = x;
	}


	@Override
	public void drawEndGame() {
		// TODO Auto-generated method stub
		
	}

}
