package game;

import java.awt.Color;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


/*
 * TODO: Take anything that updates the model, and move it out of the view.
 */


/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: RedKnotView
 * -class that acts as the View of the RedKnot GameMode
 * -contains methods and control over the drawing of the RedKnot minigame
 */
public class RedKnotView extends GameView {
	
	//Private enum for just the RedKnotView 
	private enum RKBackgrounds {
		SA("SA"), //south america (jungly background)
		OCEAN("OCEAN"),
		COAST("COAST");

		private String asset_key = null;

		private RKBackgrounds(String s){
			asset_key=s;
		}
	}
	
	/*
	 *  Clouds 
	 *  Bird
	 *  Score
	 */
	private ArrayList<Cloud> clouds;
	private RedKnot redKnot;
	private ArrayList<FlockBird> flock;
	private MiniMap map;
	private int score;
	private boolean debug_mode;

	
	//New Scroll Image (Scrolls two background images independently in comparison to before where two were dependent on the same
	//variables)
	int backgroundx1 = 0;
	int backgroundx2 = GameScreen.PLAY_SCREEN_WIDTH;
	RedKnotAsset current_background1;
	RedKnotAsset current_background2;
	//Keeps track of the backgrounds
	private LinkedList<RedKnotAsset> backgrounds;
	
	//MAP:
	private FlatteningPathIterator iter;
	private Path2D.Double map_curve;
	private ArrayList<Point> points;
	private RKBackgrounds previous; //keeps track of the previous background 
	
	//GameTimer:
	GameTimer Timer;
	int time_allocated;
	
	//Drawing Score Variables:
	static final String SCORE_TEXT = "Score: ";
	static final String FINAL_SCORE_TEXT = "Total Score:";
	static final String AMOUNT_OF_BIRDS_TEXT = "x";
	static final int SCORE_FONT_SIZE = (int)((40/1000.0)*GameScreen.PLAY_SCREEN_WIDTH);
	static final int FINAL_SCORE_FONT_SIZE = 30;
	
	
	//Tutorial 
	RKTutorialAction current_TA;
	
	JOptionPane end_game_window;
	
	//End Game
	boolean reachedDestination;
	private final int amountOfNests = 6; //[1,6]
	int amount_of_end_nests = 0;
	ArrayList<RedKnotNest> redknot_nests;
	private final int NEST_MIN_WIDTH = 50;
	private final int NEST_MAX_WIDTH = 150;
	private final int NEST_MIN_HEIGHT = 50;
	private final int NEST_MAX_HEIGHT = 100;
	private final int NEST_CHANCE_LOW = 0;
	private final int NEST_CHANCE_MAX = 1000;
	private final int NEST_THRESHOLD = 200;
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public RedKnotView(){
		super();
		
		/*Initializes loading of images*/
		try {
			loadAllImages("/resources/images/redknot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Initializing redknot, clouds, flock, score, map, etc)*/
		score=0;
		redKnot= new RedKnot();
		clouds = new ArrayList<>();
		flock = new ArrayList<>();
		this.reachedDestination = false;
		this.redknot_nests = new ArrayList<>();
		
		//GAMETIME:
		this.time_allocated = 0; //Starts at 0, because 0 time has gone by yet
		/*GameTimer*/
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				//Only updates time if the game is running
				if(getIsGameRunning()) {
					time_allocated++;
				}
			}
				//System.out.println("TIMER");
		};
		
		//Initializes the GameTimer and sets it to how often to update the time_allocated variable
		Timer = new GameTimer(GameTimer.ONE_SECOND,task);
			
		
		

		
		
		
		//Map: (Curve works for any map size, used ratios to determine curve points etc)
		Size map_size = new Size(RedKnotGameState.MAP_SIZE,RedKnotGameState.MAP_SIZE);
		map = new MiniMap(new Position(GameScreen.PLAY_SCREEN_WIDTH-map_size.getWidth()-MiniMap.LEFT_MARGIN,GameScreen.PLAY_SCREEN_HEIGHT-map_size.getHeight()-MiniMap.BOTTOM_MARGIN), map_size);
		this.map_curve = new Path2D.Double();
		createMapPoints();
		
		/*Initializes Background*/
		this.previous = RKBackgrounds.SA; //starst the background in the water
		this.backgrounds = new LinkedList<>();
		this.backgrounds.add(RedKnotAsset.SABACKGROUND);
		this.backgrounds.add(RedKnotAsset.COAST);
		this.backgrounds.add(RedKnotAsset.OCEAN);

	}
	
	

	/*
	 * NOTE: 
	 * NEVER CHANGE THE MODEL IN HERE EVER. ONLY DRAW WHAT THE MODEL IS.
	 */
	/* (non-Javadoc)
	 * @see game.GameView#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//MAP:
		drawMapBackgroundClouds(g); //draws the map (map image, map curve, bird position), background, and the clouds
		/*Draws the score, flock birds, redknot*/
		drawScore(g);
		if(this.reachedDestination==false) {
			drawFlockBirds(g);
			drawBird(g);
			g.setColor(Color.BLUE);
			Utility.drawHitBoxPoint(g, this.redKnot.hitBox, this.debug_mode);	
			drawAmountOfRedKnots(g);
			drawTutorialAction(g, this.current_TA);
		}
		
	}
	
	/**@author Miguel
	 * @param g
	 * @param TA
	 * -Allows us to draw different actions 
	 */
	public void drawTutorialAction(Graphics g,RKTutorialAction TA) {
		if(TA!=null) {
			Position p = TA.getPosition();
			Size s = TA.getSize();
			RedKnotAsset rka = TA.RKA;
			System.out.println("CURRENT_REDKNOT:"+rka);
			
			if(objectMap.get(TA.RKA) instanceof Animation) {
				Animation anim = (Animation)objectMap.get(TA.RKA);
				g.drawImage(anim.currImage(),p.getX(),p.getY(),s.getWidth(),s.getHeight(),null);
			}
			else {
				BufferedImage anim = (BufferedImage)objectMap.get(TA.RKA);
				g.drawImage(anim,p.getX(),p.getY(),s.getWidth(),s.getHeight(),null);
			}
		}
		
	}
	
	/**@author Miguel
	 * @param g
	 * @return void
	 * -Draws the map, the curves, and clouds
	 * -Deals with drawing the migrating bird, testing if the bird is over water, and transitioning the backgrounds
	 */
	public void drawMapBackgroundClouds(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.ORANGE);
//		System.out.println(map_size.getWidth());
//		System.out.println(map_size.getHeight());
//		System.out.println("X1Y1:"+x1+','+y1);
//		System.out.println("X3Y3:"+x3+','+y3);
//		System.out.println("X2Y2:"+x2+","+y2);
		
		
		//If the points on the curve, were successfully created..
		if(points.size()>0) {
			//System.out.println("ENTERING");
			//System.out.println(points.size());
			g.setColor(Color.RED);
			//Using time as index to get the curve point and draw where the bird is currently
			
			
			//Used to tell how far the bird is on the curve in respect to how much time has gone by
			double time_ratio = (double)this.time_allocated/(RedKnotGameState.MAX_GAME_TIME/GameTimer.ONE_SECOND);
			
			int time_index = (int)(time_ratio*points.size()); //gets the index in reference to how much time has gone by
			
			Position p;
			
			//Checks if the Bird Reached its destination
			if(this.reachedDestination) {
				p = new Position((int)points.get(points.size()-1).getX(), (int)points.get(points.size()-1).getY());
			}
			else {
				this.reachedDestination = false;
				p = new Position((int)points.get(time_index%points.size()).getX(), (int)points.get(time_index%points.size()).getY());
			}
			
			
			
			//Position on the curve (the position is based on where it is on the 
			//JFrame window Not relative to the actual map itself)
			int point_x = p.getX();
			int point_y = p.getY();
			
			//System.out.println("POINT ON CURVE POS:"+p);
			
			//Where the bird is relative to the ingame map size
			int relative_x = point_x - map.getPosition().getX();
			int relative_y = point_y - map.getPosition().getY();
			int ingame_mapwidth = map.hitBox.width;
			int ingame_mapheight = map.hitBox.height;
			int halfheight_ingamemap = ingame_mapheight/2;
			
			Position relative_pos = new Position(relative_x,relative_y);
			
			//System.out.println("REL:"+relative_pos);
			
			double ratio_x = (relative_x/(double)map.hitBox.width);
			double ratio_y = (relative_y/(double)map.hitBox.height);
			//System.out.println(map.hitBox.width);
			//System.out.println("Ratiox, ratioy:"+ratio_x+","+ratio_y);
			
			BufferedImage map_image = (BufferedImage)objectMap.get(RedKnotAsset.MAP);
			Position absolute_pos = new Position((int)((map_image.getWidth()*ratio_x)+1),(int)(map_image.getHeight()*ratio_y));
			//System.out.println(map_image.getRGB(499,499));
			//System.out.println(relative_pos);
			//System.out.println(absolute_pos);
			//checkPixel(map_image, absolute_pos);
			//System.out.println("B:"+checkPixel(map_image,absolute_pos,"B"));
			
			
			//Checking if bird is 'over' water and land on the map:
			int green_rgb = checkPixel(map_image,absolute_pos,"g");
			int blue_rgb = checkPixel(map_image, absolute_pos, "b");
			
			RKBackgrounds current = null;
			
			//Checks for a blue pixel (Water)
			if(blue_rgb>green_rgb) {
				//scrollImage(g, RedKnotAsset.BACKGROUND, RedKnotAsset.BACKGROUND);
				current = RKBackgrounds.OCEAN;
				
			}
			//if the bird is on the bottom half of the map (-> draw jungle background)
			else if(green_rgb> blue_rgb && relative_y>halfheight_ingamemap) {
				current = RKBackgrounds.SA;
			}
			//if the bird is on the top half of the map (-> draw coastal background)
			else if(green_rgb> blue_rgb && relative_y<halfheight_ingamemap) {
				current = RKBackgrounds.COAST;

			}
			else{
				System.out.println("ERROR-BACKGROUND");
				//System.exit(0);
			}
			
			//System.out.println(this.previous);
			//System.out.println(current);
			
			
			//Handles the Background Transitions
			//Checks what the previous background was (background1) and
			//checks what the current background is (background2)
			if(this.reachedDestination==false) {
				if(this.previous==RKBackgrounds.SA && current == RKBackgrounds.SA) {
					//scrollImage(g, RedKnotAsset.SABACKGROUND, RedKnotAsset.SABACKGROUND);
					this.newScrollImage1(g, RedKnotAsset.SABACKGROUND);//previous
					this.newScrollImage2(g,RedKnotAsset.SABACKGROUND);//current
				}
				else if(this.previous==RKBackgrounds.SA && current == RKBackgrounds.COAST) {
					//scrollImage(g, RedKnotAsset.SABACKGROUND, RedKnotAsset.SABACKGROUND);
					this.newScrollImage1(g, RedKnotAsset.SABACKGROUND);//previous
					this.newScrollImage2(g,RedKnotAsset.COAST);//current
				}
				else if(this.previous==RKBackgrounds.OCEAN && current == RKBackgrounds.OCEAN) {
					//scrollImage(g, RedKnotAsset.BACKGROUND, RedKnotAsset.BACKGROUND);
					this.newScrollImage1(g,RedKnotAsset.OCEAN);//previous
					this.newScrollImage2(g,RedKnotAsset.OCEAN);//current
				}
				else if(this.previous==RKBackgrounds.SA && current == RKBackgrounds.OCEAN) {
					//scrollImage(g, RedKnotAsset.SABACKGROUND, RedKnotAsset.BACKGROUND);
					this.newScrollImage1(g,RedKnotAsset.SABACKGROUND); //previous
					this.newScrollImage2(g,RedKnotAsset.OCEAN);//current
				}
				else if(this.previous==RKBackgrounds.OCEAN && current == RKBackgrounds.SA) {
					//scrollImage(g, RedKnotAsset.BACKGROUND, RedKnotAsset.SABACKGROUND);
					this.newScrollImage1(g,RedKnotAsset.OCEAN); //previous
					this.newScrollImage2(g,RedKnotAsset.SABACKGROUND);//current
				}
				else if(this.previous==RKBackgrounds.OCEAN && current == RKBackgrounds.COAST) {
					//scrollImage(g, RedKnotAsset.BACKGROUND, RedKnotAsset.SABACKGROUND);
					this.newScrollImage1(g,RedKnotAsset.OCEAN); //previous
					this.newScrollImage2(g,RedKnotAsset.COAST); //current
				}
				else if(this.previous==RKBackgrounds.COAST && current == RKBackgrounds.OCEAN) {
					//scrollImage(g, RedKnotAsset.BACKGROUND, RedKnotAsset.SABACKGROUND);
					this.newScrollImage1(g,RedKnotAsset.COAST); //previous
					this.newScrollImage2(g,RedKnotAsset.OCEAN); //current
				}
				else if(this.previous==RKBackgrounds.COAST && current == RKBackgrounds.SA) {
					//scrollImage(g, RedKnotAsset.BACKGROUND, RedKnotAsset.SABACKGROUND);
					this.newScrollImage1(g,RedKnotAsset.COAST); //previous
					this.newScrollImage2(g,RedKnotAsset.SABACKGROUND); //current
				}
				else if(this.previous==RKBackgrounds.COAST && current == RKBackgrounds.COAST) {
					//scrollImage(g, RedKnotAsset.BACKGROUND, RedKnotAsset.SABACKGROUND);
					this.newScrollImage1(g,RedKnotAsset.COAST); //previous
					this.newScrollImage2(g,RedKnotAsset.COAST); //current
				}
			}
			else if(this.reachedDestination){
				
				//Adds the nests
				if(this.redknot_nests.isEmpty()) {
					this.redknot_nests= this.addNests(this.flock); //sets the redknot_nests property to the newly created list
				}
				
				g.drawImage((Image)objectMap.get(RedKnotAsset.DELAWAREBAY),0,0,GameScreen.PLAY_SCREEN_WIDTH,GameScreen.PLAY_SCREEN_HEIGHT,null);
				this.drawNests(g, this.redknot_nests);
			}
			
			this.previous = current;
			
			if(this.reachedDestination==false) {
				drawClouds(g); //draws the clouds
				drawMap(g);
				g2d.draw(map_curve);
				g.fillOval(p.getX(), p.getY(), 5, 5);
			}
			
			
		}
		
	}
	
	/**@author Miguel
	 * @param g
	 * @param rkn
	 * -Takes in an ArrayList of RedKnotNests and draws them onto the screen
	 * based on their size property, position property
	 */
	public void drawNests(Graphics g, ArrayList<RedKnotNest> rkn) {
		for(RedKnotNest nest:rkn) {
			BufferedImage bf = null;
			String nest_path = "resources/images/redknot/redknotnest"+nest.getNestId()+".png";
					
			try{
				bf = ImageIO.read(new File(nest_path));
			}catch (IOException e){
				e.printStackTrace();
			}
			g.drawImage(bf, nest.getPosition().getX(),nest.getPosition().getY(),nest.getSize().getWidth(),nest.getSize().getHeight(),null);
		}
	}
	
	
	/**@author Miguel
	 * @param flock_list
	 * @return
	 * -Adds the end-game nests to the background
	 * -The more Redknots the player collected, the more chance of having an end-game nest
	 */
	public ArrayList<RedKnotNest> addNests(ArrayList<FlockBird> flock_list) {
		ArrayList<RedKnotNest> rkn = new ArrayList<>();
		
		for(FlockBird fb:flock_list) {
			int random_x = Utility.randRangeInt(0, GameScreen.PLAY_SCREEN_WIDTH);
			int random_y = Utility.randRangeInt((int)(GameScreen.PLAY_SCREEN_HEIGHT*.6), (int)(GameScreen.PLAY_SCREEN_HEIGHT*.9));
			Position p = new Position(random_x, random_y);
			
			int random_width = Utility.randRangeInt(NEST_MIN_WIDTH, NEST_MAX_WIDTH);
			int random_height = Utility.randRangeInt(NEST_MIN_HEIGHT, NEST_MAX_HEIGHT);
			Size s = new Size(random_width,random_height);
			
			int chance = Utility.randRangeInt(this.NEST_CHANCE_LOW, this.NEST_CHANCE_MAX);
			int threshold = this.NEST_THRESHOLD;
			
			if(chance<threshold) {
				rkn.add(new RedKnotNest(p,s));
			}
			
		}
		
		return rkn;
	}
	

	/**@author Miguel
	 * @param img
	 * @param p
	 * @param s
	 * @return an integer representing an r,g,b, or a value
	 * -Checks the current pixel of a point from the map curve
	 * (in order to tell if the red knot is over a blue or green pixel)
	 */
	public int checkPixel(BufferedImage img, Position p, String s) {
		int rgb_value = img.getRGB(p.getX(), p.getY());
		int one_byte = 8;
		int a = (rgb_value>>one_byte*3) & 0xff; //Bitmasks to get alpha
		int r = (rgb_value>>one_byte*2) & 0xff; //Bitmasks to get red
		int g =  (rgb_value>>one_byte) & 0xff; //Bitmasks to get green
		int b = (rgb_value) & 0xff; //Bitmasks to get blue
		
		//System.out.printf("RGBA:(%d,%d,%d,%d)\n",r,g,b,a);
		int high_blue_value = 200;
		int high_green_value = 200;
		
		switch(s.toLowerCase()) {
		case "r":
			return r;
		case "g":
			return g;
		case "b":
			
			return b;
		case "a":
			
			return a;
			
		default:
			System.out.println("Error, wrong string input: input either 'r', 'g', 'b', or 'a'");
			return -1;
		}
		
		

		
	}

	
	public void createMapPoints() {
		Size map_size = new Size(map.hitBox.width,map.hitBox.height);
		//Places the Redknot's starting migration point (SOUTH AMERICA) on the mini-map
		int x1 = (int) ((315d/500d)*map_size.getWidth())+map.getPosition().getX(); //gives the width ratio of first x1
		int y1 = (int) ((415d/500d)*map_size.getHeight())+map.getPosition().getY(); //gives height ratio
				
		//Places the Redknot's mid-migration point (DELAWARE BAY-migrates through it) on the mini-map
		int x2 = (int) ((325d/500d)*map_size.getWidth())+map.getPosition().getX(); //gives the width ratio of first x1
		int y2 = (int) ((130d/500d)*map_size.getHeight())+map.getPosition().getY(); //gives height ratio
		
		//Places the Redknot's finishing migration point (NORTHERN CANADA-NESTING LOCATION) on the mini-map
		int x3 = (int) ((200d/500d)*map_size.getWidth())+map.getPosition().getX();
		int y3 = (int) ((35d/500d)*map_size.getHeight())+map.getPosition().getY();
		
		map_curve.moveTo(x1, y1);
		map_curve.curveTo(x1, y1, x2, y2, x3, y3);
		
        float[] coords=new float[6];
        this.points = new ArrayList<>();
		this.iter=new FlatteningPathIterator(map_curve.getPathIterator(new AffineTransform()), 0.001);
        while (!this.iter.isDone()) {
            this.iter.currentSegment(coords);
            int x=(int)coords[0];
            int y=(int)coords[1];
            this.points.add(new Point(x,y));
            this.iter.next();
        }
        //System.out.println(points.size());
	}
	
	/* (non-Javadoc)
	 * @see game.GameView#update(java.util.ArrayList)
	 */
	public void update(ArrayList<GameObject> gameObjects){
		clouds = new ArrayList<>();
		redKnot = (RedKnot)gameObjects.get(0);
		flock = new ArrayList<>();
		//gameObjects.remove(0);
		for(GameObject go : gameObjects){
			if(go instanceof Cloud){
				clouds.add((Cloud)go);
			}
			else if(go instanceof FlockBird) {
				//System.out.println("ADDED FLOCK BIRD");
				flock.add((FlockBird)go);
			}
		}

	}
	
	/**@author Miguel
	 * @param debug_mode
	 * @return void
	 * -Takes in a boolean and sets the 'debug_mode' property 
	 */
	public void updateDebugging(boolean debug_mode) {
		this.debug_mode = debug_mode;
	}
	

	/**@author Miguel
	 * @param g
	 * -Draws the bird with the associated red knot sprite sheet
	 */
	public void drawBird(Graphics g){
		Animation birdAnim;
		if(redKnot.getColliding()){
			birdAnim = (Animation) objectMap.get(RedKnotAsset.MAINBIRDHURT);
		}else{
			birdAnim = (Animation) objectMap.get(RedKnotAsset.MAINBIRD);
		}
		g.drawImage(birdAnim.currImage(),redKnot.getPosition().getX(),redKnot.getPosition().getY(),redKnot.getSize().getWidth(),redKnot.getSize().getHeight(),null,this);
	}
	
	/**@author Miguel
	 * @param g
	 * -Draws the GameScore to the Top Right of the Screen
	 */
	public void drawScore(Graphics g){
		g.setColor(Color.BLACK);
		
		if(this.reachedDestination) {
			g.setFont(new Font("Serif",Font.PLAIN,this.FINAL_SCORE_FONT_SIZE));
			FontMetrics fm = g.getFontMetrics();
			//System.out.println(fm.getFont());
			
			//The String being drawn
			String toDrawString = this.FINAL_SCORE_TEXT + this.score+", RedKnots:"+this.checkAmountOfOwnedRedKnots()+", Nests:"+this.redknot_nests.size();
			int string_width = fm.stringWidth(toDrawString);
			BufferedImage final_banner = (BufferedImage)objectMap.get(RedKnotAsset.REDKNOTBANNER);
			g.drawImage(final_banner, (GameScreen.PLAY_SCREEN_WIDTH-final_banner.getWidth())-((GameScreen.PLAY_SCREEN_WIDTH-final_banner.getWidth())/2),0,final_banner.getWidth(),final_banner.getHeight(),null);
			g.drawString(toDrawString, (GameScreen.PLAY_SCREEN_WIDTH/2)-(string_width/2)-GameScreen.SCREEN_BORDER_PX, 0-this.FINAL_SCORE_FONT_SIZE+final_banner.getHeight());
		}
		else {
			g.setFont(new Font("Serif",Font.PLAIN,this.SCORE_FONT_SIZE));
			FontMetrics fm = g.getFontMetrics();
			//System.out.println(fm.getFont());
			
			//The String being drawn
			String toDrawString = this.SCORE_TEXT + this.score;
			int string_width = fm.stringWidth(toDrawString);
			
			g.drawString(toDrawString, GameScreen.PLAY_SCREEN_WIDTH-string_width-GameScreen.SCREEN_BORDER_PX, 0+this.SCORE_FONT_SIZE);
		}
	}
	
	/**@author Miguel
	 * @return (int) AmountOfRedknots
	 * -returns the amount of redknots that are owned by the player
	 */
	public int checkAmountOfOwnedRedKnots() {
		int amount_of_redknots =0;
		
		for(FlockBird FB:this.flock) {
			if(FB.getIsCollected()) {
				amount_of_redknots++;
			}
		}
		
		return amount_of_redknots;
	}
	
	/**@author Miguel
	 * @param g
	 */
	public void drawAmountOfRedKnots(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN,this.SCORE_FONT_SIZE));
		FontMetrics fm = g.getFontMetrics();
		//System.out.println(fm.getFont());
		
		//The String being drawn
		int amount_of_birds = this.checkAmountOfOwnedRedKnots();
		String toDrawString = this.AMOUNT_OF_BIRDS_TEXT + amount_of_birds;
		int string_width = fm.stringWidth(toDrawString);
		
		int x_offset = (int)((10/1000.0)*GameScreen.PLAY_SCREEN_WIDTH);
		int rk_xpos = 0+x_offset;
		
		int amount_xpos = rk_xpos + this.SCORE_FONT_SIZE+GameScreen.SCREEN_BORDER_PX;
		
		BufferedImage static_redknot_img = (BufferedImage)objectMap.get(RedKnotAsset.STATICREDKNOT);
		g.drawImage(static_redknot_img,rk_xpos,0,this.SCORE_FONT_SIZE,this.SCORE_FONT_SIZE,null);
		g.drawString(toDrawString, amount_xpos, 0+this.SCORE_FONT_SIZE);
	}
	


	/**@author Miguel
	 * @param FB
	 * @param g
	 * -Takes a single FlockBird(FB) and draws it onto the screen
	 */
	public void drawFlockBird(FlockBird FB, Graphics g) {
		Animation FlockBirdAnim = (Animation) objectMap.get(RedKnotAsset.FLOCKBIRD);
		g.drawImage(FlockBirdAnim.currImage(FB.frameIndex),FB.getPosition().getX(),FB.getPosition().getY(),FB.getSize().getWidth(),FB.getSize().getHeight(),null,this);
		FB.updateCurrImage();
	}
	
	/**@author Miguel
	 * @param g
	 * -Iterates through the 'flock' List of FlockBirds and draws them onto the screen
	 */
	public void drawFlockBirds(Graphics g) {
		for(FlockBird FB: flock) {
			drawFlockBird(FB, g);
			Utility.drawHitBoxPoint(g, FB.hitBox, this.debug_mode);
			if(Utility.GameObjectCollision(this.redKnot, FB) && this.debug_mode) {
				System.out.println("COLLISION!");
			}
		}
	}

	
	/**@author Miguel
	 * @param g
	 * -Iterates through the 'clouds' List of Clouds and draws them onto the screen
	 */
	public void drawClouds(Graphics g) {
		for(Cloud c:clouds){
			drawEnemyCloud(c,g);
			drawQuestionCloud(c,g);
			Utility.drawHitBoxPoint(g, c.hitBox, this.debug_mode);
			if(Utility.GameObjectCollision(this.redKnot, c) && this.debug_mode) {
				System.out.println("COLLISION!");
			}
		}
		
		
	}
	/**@author Miguel
	 * @param c
	 * @param g
	 * -Takes in an individual Cloud instance and draws it onto the screen
	 */
	public void drawEnemyCloud(Cloud c, Graphics g){
		if(c instanceof EnemyCloud) {
			Position current_pos = c.getPosition();
			g.drawImage((Image) objectMap.get(RedKnotAsset.ENEMYCLOUD), current_pos.getX(), current_pos.getY(), c.getWidth(),c.getHeight(),null, this);
		}
	}
	
	/**@author Miguel
	 * @param c
	 * @param g
	 * -Takes in an individual Cloud instance and draws it onto the screen
	 */
	public void drawQuestionCloud(Cloud c, Graphics g){
		if(c instanceof QuestionCloud) {
			Position current_pos = c.getPosition();
			g.drawImage((Image) objectMap.get(RedKnotAsset.QUESTIONCLOUD), current_pos.getX(), current_pos.getY(), c.getWidth(),c.getHeight(),null, this);
		}
	}
	
	
	/**@author Miguel
	 * @param g
	 * -Draws map image onto the screen (simply draws the map image, does not deal with the curve, bird migrating,etc)
	 */
	public void drawMap(Graphics g) {
		g.drawImage((Image) objectMap.get(RedKnotAsset.MAP), map.getPosition().getX(),map.getPosition().getY(),map.hitBox.width,map.hitBox.height,null);
	}
	

	
	/**@author Miguel
	 * @param g
	 * @param next_background
	 * -Scrolls the first background image from the right side to the left 
	 */
	public void newScrollImage1(Graphics g, RedKnotAsset next_background) {
		this.backgroundx1 = this.backgroundx1-redKnot.getVelocity().getXSpeed();//BACKGROUND_SPEED;
		if(this.current_background1==null) {
			this.current_background1 = this.backgrounds.get(0);
		}
		
		Image background_image = (Image)objectMap.get(this.current_background1);
		
		if(this.backgroundx1< -1*GameScreen.PLAY_SCREEN_WIDTH) {
			this.current_background1 = RedKnotAsset.OCEAN;
			this.backgroundx1 = GameScreen.PLAY_SCREEN_WIDTH;
			this.current_background1 = next_background;
		}
		
		int minorwidth = 20; //adds a little more width to the width of the background
		
		g.drawImage(background_image, this.backgroundx1, 0, GameScreen.PLAY_SCREEN_WIDTH+minorwidth, GameScreen.PLAY_SCREEN_HEIGHT, this);
	}
	
	/**@author Miguel
	 * @param g
	 * @param next_background
	 * -Scrolls the second background image from the right side to the left 
	 */
	public void newScrollImage2(Graphics g, RedKnotAsset next_background) {
		this.backgroundx2 = this.backgroundx2-redKnot.getVelocity().getXSpeed();//BACKGROUND_SPEED;
		if(this.current_background2==null) {
			this.current_background2 = this.backgrounds.get(1);
		}
		Image background_image = (Image)objectMap.get(this.current_background2);
		
		if(this.backgroundx2< -1*GameScreen.PLAY_SCREEN_WIDTH) {
			this.current_background2 = RedKnotAsset.OCEAN;
			this.backgroundx2 = GameScreen.PLAY_SCREEN_WIDTH;
			this.current_background2 = next_background;
		}
		int minorwidth = 20; //adds a little more width to the width of the background
		
		g.drawImage(background_image, this.backgroundx2, 0, GameScreen.PLAY_SCREEN_WIDTH+minorwidth, GameScreen.PLAY_SCREEN_HEIGHT, this);
	}
    

	/* (non-Javadoc)
	 * @see game.WindowView#fnameMapCreate()
	 */
	@Override
	public void fnameMapCreate() {
		fnameMap.put("background1.png", RedKnotAsset.BACKGROUND);
		fnameMap.put("forest2.png", RedKnotAsset.FOREST1);
		fnameMap.put("cloudnorain.png",RedKnotAsset.ENEMYCLOUD);
		fnameMap.put("SAbackground3.png", RedKnotAsset.SABACKGROUND);
		fnameMap.put("sprite-6-redknot2.png", RedKnotAsset.MAINBIRD);
		fnameMap.put("sprite-6-redknot2transparent.png", RedKnotAsset.FLOCKBIRD);
		//fnameMap.put("sprite-4-redknot2transparent.png", RedKnotAsset.FLOCKBIRD);
		fnameMap.put("sprite-6-redknot2redflash.png", RedKnotAsset.MAINBIRDHURT);
		fnameMap.put("NA_SA_MAP.png", RedKnotAsset.MAP);
		fnameMap.put("ocean.png",RedKnotAsset.OCEAN);
		fnameMap.put("coast2.png",RedKnotAsset.COAST);
		fnameMap.put("QuestionCloud.png",RedKnotAsset.QUESTIONCLOUD);
		fnameMap.put("redknotimage.png",RedKnotAsset.STATICREDKNOT);
		fnameMap.put("sprite-2-arrowkeyup.png", RedKnotAsset.UPARROWFLASH);
		fnameMap.put("sprite-2-arrowkeydown.png", RedKnotAsset.DOWNARROWFLASH);
		fnameMap.put("sprite-2-arrowkeyright.png", RedKnotAsset.RIGHTARROWFLASH);
		fnameMap.put("sprite-2-arrowkeyleft.png", RedKnotAsset.LEFTARROWFLASH);
		fnameMap.put("redknotinstructions.png", RedKnotAsset.RKGOALS);
		fnameMap.put("delawarebaybackground2.png", RedKnotAsset.DELAWAREBAY);
		fnameMap.put("redknot_banner.png",RedKnotAsset.REDKNOTBANNER);
	}



	/* (non-Javadoc)
	 * @see game.GameView#updateScore(int)
	 */
	@Override
	public void updateScore(int x) {
		this.score = x;
//		System.out.println("VIEW IS UPDATING SCORE");
//		System.out.println(this.score);
	}

	
	public void updateHasReachedDestination(boolean b) {
		this.reachedDestination = b;
	}



	@Override
	public void scrollImage(Graphics g, Object background1, Object background2) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateTutorialAction(RKTutorialAction TA) {
		this.current_TA = TA;
	}

	
}