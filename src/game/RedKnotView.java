package game;

import java.awt.Color;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TimerTask;


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
	private final int BACKGROUND_SPEED = 5;
	private ArrayList<Cloud> clouds;
	private RedKnot redKnot;
	private ArrayList<FlockBird> flock;
	private MiniMap map;
	private int score;
	private boolean debug_mode;
	
	
	int background_x = 0;
	
	//New Scroll Image
	int backgroundx1 = 0;
	int backgroundx2 = GameScreen.PLAY_SCREEN_WIDTH;
	RedKnotAsset current_background1;
	RedKnotAsset current_background2;
	
	//MAP:
	private FlatteningPathIterator iter;
	private Path2D.Double map_curve;
	private ArrayList<Point> points;
	private RKBackgrounds previous; //keeps track of the previous background 
	
	GameTimer Timer;
	int time_allocated;
	
	private LinkedList<RedKnotAsset> backgrounds;
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public RedKnotView(){
		super();
		
		this.time_allocated = 0; //Starts at 0, because 0 time has gone by yet
		/*GameTimer*/
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				time_allocated++;
			}
				//System.out.println("TIMER");
		};
		
		Timer = new GameTimer(GameTimer.ONE_SECOND,task);
			
		
		
		//Test QuestionBox (for quiz)
		Thread question_thread = new Thread( new Runnable() {
			@Override
			public void run() {
				ArrayList<String> responses = new ArrayList<>();
				responses.add("South America");
				responses.add("North America");
				responses.add("Europe");
				responses.add("Asia");
				responses.add("Mars");
				QuestionWindow qw = new QuestionWindow(new Position(GameScreen.PLAY_SCREEN_HEIGHT,0), new Size(300,200),"Where does a redknot begin migrating from?", responses);
			}
			
		});
		question_thread.run();
		
		/*Initializing redknot, clouds, flock, score, map, etc)*/
		score=0;
		redKnot= new RedKnot();
		clouds = new ArrayList<>();
		flock = new ArrayList<>();
		
		//Map: (Curve works for any map size, used ratios to determine curve points etc)
		Size map_size = new Size(150,150);
		map = new MiniMap(new Position(GameScreen.PLAY_SCREEN_WIDTH-map_size.getWidth()-MiniMap.LEFT_MARGIN,GameScreen.PLAY_SCREEN_HEIGHT-map_size.getHeight()-MiniMap.BOTTOM_MARGIN), map_size);
		this.map_curve = new Path2D.Double();
		createMapPoints();
		
		/*Initializes Background*/
		this.previous = RKBackgrounds.SA; //starst the background in the water
		this.backgrounds = new LinkedList<>();
		this.backgrounds.add(RedKnotAsset.SABACKGROUND);
		this.backgrounds.add(RedKnotAsset.COAST);
		this.backgrounds.add(RedKnotAsset.OCEAN);
		
	
		/*Initializes loading of images*/
		try {
			loadAllImages("/resources/images/redknot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		drawFlockBirds(g);
		drawBird(g);
		g.setColor(Color.BLUE);
		Utility.drawHitBoxPoint(g, this.redKnot.hitBox, this.debug_mode);	
		
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
			System.out.println("ENTERING");
			System.out.println(points.size());
			g.setColor(Color.RED);
			//Using time as index to get the curve point and draw where the bird is currently
			
			//Used to tell how far the bird is on the curve in respect to how much time has gone by
			double time_ratio = (double)this.time_allocated/(RedKnotGameState.MAX_GAME_TIME/GameTimer.ONE_SECOND);
			
			int time_index = (int)(time_ratio*points.size()); //gets the index in reference to how much time has gone by
			
			Position p = new Position((int)points.get(time_index%points.size()).getX(), (int)points.get(time_index%points.size()).getY());
			
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
			System.out.println("B:"+checkPixel(map_image,absolute_pos,"B"));
			
			
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
				System.exit(0);
			}
			
			System.out.println(this.previous);
			System.out.println(current);
			
			
			//Handles the Background Transitions
			//Checks what the previous background was (background1) and
			//checks what the current background is (background2)
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
			
			this.previous = current;
			
			drawClouds(g); //draws the clouds
			drawMap(g);
			g2d.draw(map_curve);
			g.fillOval(p.getX(), p.getY(), 5, 5);
			
			
		}
		
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
		
		System.out.printf("RGBA:(%d,%d,%d,%d)\n",r,g,b,a);
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
		int x1 = (int) ((320d/500d)*map_size.getWidth())+map.getPosition().getX(); //gives the width ratio of first x1
		int y1 = (int) ((420d/500d)*map_size.getHeight())+map.getPosition().getY(); //gives height ratio
		int x3 = (int) ((260d/500d)*map_size.getWidth())+map.getPosition().getX();
		int y3 = (int) ((140d/500d)*map_size.getHeight())+map.getPosition().getY();
		
		
		int x2 = (x1-x3)+x3+(int)(x1*0.0125);
		int y2 = (y3-y1)+y3+(int)(y1*.1);
		
		map_curve.moveTo(x1, y1);
		map_curve.curveTo(x1, y1, x2, y2, x3, y3);
		
        float[] coords=new float[6];
        this.points = new ArrayList<>();
		this.iter=new FlatteningPathIterator(map_curve.getPathIterator(new AffineTransform()), 0.01);
        while (!this.iter.isDone()) {
            this.iter.currentSegment(coords);
            int x=(int)coords[0];
            int y=(int)coords[1];
            this.points.add(new Point(x,y));
            this.iter.next();
        }
        System.out.println(points.size());
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
		Animation birdAnim = (Animation) objectMap.get(RedKnotAsset.MAINBIRD);
		g.drawImage(birdAnim.currImage(),redKnot.getPosition().getX(),redKnot.getPosition().getY(),redKnot.getSize().getWidth(),redKnot.getSize().getHeight(),null,this);
	}
	
	/**@author Miguel
	 * @param g
	 */
	public void drawScore(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN,RedKnotGameState.SCORE_FONT_SIZE));
		FontMetrics fm = g.getFontMetrics();
		//System.out.println(fm.getFont());
		
		//The String being drawn
		String toDrawString = RedKnotGameState.SCORE_TEXT + this.score;
		int string_width = fm.stringWidth(toDrawString);
		
		g.drawString(toDrawString, GameScreen.PLAY_SCREEN_WIDTH-string_width-GameScreen.SCREEN_BORDER_PX, 0+RedKnotGameState.SCORE_FONT_SIZE);
	}
	


	/**@author Miguel
	 * @param FB
	 * @param g
	 * -Takes FlockBird(FB) and draws it onto the screen
	 */
	public void drawFlockBird(FlockBird FB, Graphics g) {
		Animation FlockBirdAnim = (Animation) objectMap.get(RedKnotAsset.MAINBIRD);
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
			drawCloud(c,g);
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
	public void drawCloud(Cloud c, Graphics g){
		Position current_pos = c.getPosition();
		g.drawImage((Image) objectMap.get(RedKnotAsset.CLOUD), current_pos.getX(), current_pos.getY(), c.getWidth(),c.getHeight(),null, this);
	}
	
	
	/**@author Miguel
	 * @param g
	 * -Draws map image onto the screen (simply draws the map image, does not deal with the curve, bird migrating,etc)
	 */
	public void drawMap(Graphics g) {
		g.drawImage((Image) objectMap.get(RedKnotAsset.MAP), map.getPosition().getX(),map.getPosition().getY(),map.hitBox.width,map.hitBox.height,null);
	}
	

	
//	public void scrollImage(Graphics g, Object background1, Object background2){
////		this.background_x = (this.background_x % GameScreen.PLAY_SCREEN_WIDTH)-redKnot.getVelocity().getXSpeed();//BACKGROUND_SPEED;
////		System.out.println(this.background_x%GameScreen.PLAY_SCREEN_WIDTH);
////		int background1_x1 = this.background_x;
////		int background1_y1 = 0;
////		Image background1_image = (Image)objectMap.get(background1);
////		
////		int background2_x1 = (background_x)+GameScreen.PLAY_SCREEN_WIDTH;
////		int background2_y1 = 0;
////		Image background2_image = (Image)objectMap.get(background2);
//		
//		
//		this.background_x = this.background_x-redKnot.getVelocity().getXSpeed();//BACKGROUND_SPEED;
//		System.out.println(this.background_x%GameScreen.PLAY_SCREEN_WIDTH);
//		int background1_x1 = this.background_x;
//		int background1_y1 = 0;
//		Image background1_image = (Image)objectMap.get(background1);
//		
//		int background2_x1 = (background_x)+GameScreen.PLAY_SCREEN_WIDTH-5;
//		int background2_y1 = 0;
//		Image background2_image = (Image)objectMap.get(background2);
//		
//		int background3_x1 = (background_x)+GameScreen.PLAY_SCREEN_WIDTH*2-5;
//		int background3_y1 = 0;
//		Image background3_image = (Image)objectMap.get(background2);
//		
//		if(this.background_x<=GameScreen.PLAY_SCREEN_WIDTH*2*-1) {
//			this.background_x = 0;
//			this.removeFirstBackground();
//			
//			int random = Utility.randRangeInt(0, 1);
//			switch(random) {
//			case 0:
//				this.addBackground(RedKnotAsset.OCEAN);
//				break;
//			
//			case 1:
//				this.addBackground(RedKnotAsset.SABACKGROUND);
//				break;
//			default:
//				System.out.println("NOT WORKING");
//				break;
//		}
//		}
//		
//		background1_image = (Image)objectMap.get(this.backgrounds.getFirst());
//		background2_image = (Image)objectMap.get(this.backgrounds.get(1));
//		//background3_image = (Image)objectMap.get(this.backgrounds.getLast());
//		
//		System.out.println(background1_x1);
//		System.out.println(background2_x1);
//		g.drawImage(background1_image, background1_x1, background1_y1, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, this);
//		g.drawImage(background2_image, background2_x1, background2_y1, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, this);
//		
//		
//		//g.drawImage(background3_image, background3_x1, background2_y1, GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT, this);
//	}
	
	
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
		fnameMap.put("cloudnorain.png",RedKnotAsset.CLOUD);
		fnameMap.put("SAbackground3.png", RedKnotAsset.SABACKGROUND);
		fnameMap.put("sprite-6-redknot.png", RedKnotAsset.MAINBIRD);
		fnameMap.put("sprite-6-flockbird.png", RedKnotAsset.FLOCKBIRD);
		fnameMap.put("NA_SA_MAP.png", RedKnotAsset.MAP);
		fnameMap.put("ocean.png",RedKnotAsset.OCEAN);
		fnameMap.put("coast2.png",RedKnotAsset.COAST);
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



	@Override
	public void drawEndGame() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void scrollImage(Graphics g, Object background1, Object background2) {
		// TODO Auto-generated method stub
		
	}

	
}