package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Controller
 * -class that acts as the Controller of our MVC model
 * -controls updates between state and the view 
 */
public class Controller implements KeyListener {
	private WindowView view;
	private GameScreen screen;
	private Model current_state;
	
	

	// All gamestates:
	/*
	private RedKnotGameState RedKnotGS;
	private ClapperRailGameState ClapperRailGS;
	private TitleScreenModel TitleGS;
	private InstructionsModel InstructionsGS;
	*/
	/**
	 * 
	 */
	public Controller() {
		this.screen = new GameScreen(GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT);
		this.screen.addKeyListener(this);
		setUpTitleScreen();
	}

	
	
	/* This method will exist for each view, for safety purposes.
	 * 
	 * Sets our view to the titlescreenview, this methods purpose is to make sure we don't forget
	 * to set up the buttons when we switch to the title screen. Make sure
	 * that we use this method to switch to the titlescreen, and not just typing
	 * view = new titleScreenView();
	 */
	public void setUpTitleScreen(){
		view = new TitleScreenView();
		setUpTitleButtons();
		this.screen.add(view);
		this.screen.setPlaySize();
		//view.setBackground(Color.CYAN); //sets the background color of the TitleScreenView
		current_state = new TitleScreenModel(this);
	}
	
	/**
	 * -Sets up our IntructionsView
	 */
	public void setUpInstructions(){
		view = new InstructionsView();
		setUpInstructionsButton();
		this.screen.add(view);
		current_state = new InstructionsModel(this);
		
	}
	/**
	 * -Sets up our RedKnotView
	 */
	public void setUpRedKnotGame(){
		view=new RedKnotView();
		this.screen.add(view);
		this.screen.setPlaySize();
		current_state = new RedKnotGameState(this);
		
	}
	/**
	 * -Sets up our ClapperRailView
	 */
	public void setUpClapperRailGame(){
		this.screen.setSize(GameScreen.CR_SCREEN_WIDTH,GameScreen.CR_SCREEN_HEIGHT);
		view = new ClapperRailView();
		this.screen.add(view);
		current_state = new ClapperRailGameState(this);
	}
	
	//TODO: finish/fix this.
	//same thing as the below method, this doesn't work.
	/**
	 * 
	 */
	public void setUpInstructionsButton() {
		InstructionsView isv = null;
		if(view instanceof InstructionsView){
			isv = (InstructionsView)view;
		}else{
			System.out.println("You have accidentally called setUpInstructionsButton while the view was not on the instructions screen.");
			System.exit(0);
		}
		isv.backtoMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeView(GameMode.TITLESCREEN);
			}
		});
	}

	/* TODO: FIX this
	 * this method is to set up the title screen buttons
	 * in here by accessing them from the controller, so that we don't
	 * have to pass the controller to the view of the titlescreen.
	 * However, I didn't finish this, but I'm leaving it here. 
	 * 
	 */
	/**
	 * 
	 */
	public void setUpTitleButtons() {
		TitleScreenView tsv = null;
		if (view instanceof TitleScreenView) {
			tsv = (TitleScreenView) view;
		} else {
			System.out.println("You have accidentally called setUpTitleButtons while the view was not on the title screen.");
			System.exit(0);
		}

		tsv.RedKnot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button pressed");
				changeView(GameMode.REDKNOT);
			}
		});
		tsv.ClapperRail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeView(GameMode.CLAPPERRAIL);
			}
		});
		tsv.Instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeView(GameMode.INSTRUCTIONS);
			}
		});
		System.out.println("title buttons are set up");


		
		
	}

	// Another possibility of a way to do our main loop.
	// with my testing, however, this does not remove stuttering.
	/*
	 * public void start(int tickdelay){ ActionListener task = new
	 * ActionListener(){
	 * 
	 * @Override public void actionPerformed(ActionEvent arg0) {
	 * SwingUtilities.invokeLater( () -> screen.redraw() ); } }; Timer t = new
	 * Timer(tickdelay,task); t.start(); t.restart(); }
	 */

	/*
	 * TODO: Split this into two updates, updating the model. And, updating the
	 * screen. (screen.redraw()) And make the model update on a different
	 * thread.
	 * 
	 */
	/**
	 * @param tickdelay
	 */
	public void start(int tickdelay) {
		long bef, aft;
		bef = System.currentTimeMillis();
		while (loop()) {
			aft = System.currentTimeMillis();
			// System.out.println("MILLISECONDS:"+(aft-bef));
			try {
				Thread.sleep(tickdelay - (aft - bef));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bef = System.currentTimeMillis();
		}
	}

	
	/*
	 * Main loop for the game Everything that happens once per tick goes in
	 * here.
	 * 
	 * We will update the model once per tick, and then the view will draw it.
	 * 
	 * ANYTHING THAT UPDATES THE MODEL MUUST HAPPEN WHEN updateModel() is
	 * called. NOT INSIDE PAINTCOMPONENT.
	 * 
	 */
	public boolean loop() {
		SwingUtilities.invokeLater(() -> screen.redraw());
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(() -> updateModel());
			}
		});
		t.start();
		return true;
	}

	/**
	 * Calls the Current Models Update Method
	 * -This method should pass the updated data from the Model
	 * to the View
	 **/
	public void updateModel() {
		//Passes generic GameState updated data to all GameViews
		if(current_state instanceof GameState){
			GameState gs=(GameState)current_state;
			GameView gv = (GameView)view;
			gs.ontick();
			ArrayList<GameObject> gameObjects = gs.getUpdateableGameObjects();
			gv.update(gameObjects);
		}
		
		//Passes RedKnotGS updated data to the RedKnotView 
		if(current_state instanceof RedKnotGameState) {
			RedKnotGameState RK_GS = (RedKnotGameState)current_state;
			RedKnotView RK_V = (RedKnotView) view;
			//System.out.println("GS_SCORE:"+RK_GS.getScore());
			
			RK_V.updateIsGameRunning(RK_GS.getIsGameRunning());
			RK_V.updateScore(RK_GS.getScore());
		}
		
		//Passes ClapperRailGS updated data to the ClapperRailView 
			if(current_state instanceof ClapperRailGameState) {
				ClapperRailGameState CL_GS = (ClapperRailGameState)current_state;
				ClapperRailView CL_V = (ClapperRailView) view;
				
				CL_V.update(CL_GS.BackgroundX);
				if(CL_GS.getCR().gameOver)
					changeView(GameMode.TITLESCREEN);
				
//				System.out.println("GS_SCORE:"+CL_GS.getScore());
//				view.updateScore(CL_GS.getScore());
				
			}
					
			
	}

	/**@author Miguel
	 * @param mode
	 * -Allows the controller to change the current view to a different view (switching screens)
	 */
	public void changeView(GameMode mode) {
		switch (mode) {
		case INSTRUCTIONS:
			this.view.setVisible(false);
			setUpInstructions();
			break;
		case CLAPPERRAIL:
			this.view.setVisible(false);
			setUpClapperRailGame();
			break;
		case REDKNOT:
			this.view.setVisible(false);
			setUpRedKnotGame();
			break;
		case TITLESCREEN:
			this.view.setVisible(false);
			setUpTitleScreen();
			break;

		}

		this.screen.revalidate();
	}

	/*CURRENTLY NOT USED
	 *  (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/* 
	 * -Handles key presses for RedKnot and ClapperRail
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			changeView(GameMode.TITLESCREEN);
		}
		// System.out.println(key);
		// System.out.println(this.ClapperRailGS.getCR().getPosition().getX());

		if (key == KeyEvent.VK_ESCAPE) {
			changeView(GameMode.TITLESCREEN);
		}

		if (current_state instanceof ClapperRailGameState) {
			ClapperRailGameState ClapperRailGS = (ClapperRailGameState)current_state;
			
			switch (key) {
			case KeyEvent.VK_RIGHT:
				ClapperRailGS.getCR().setLeftRightState(1);
//				ClapperRailGS.checkRightBounds();
				break;
			case KeyEvent.VK_LEFT:
				ClapperRailGS.getCR().setLeftRightState(-1);
//				ClapperRailGS.checkLeftBounds();
				break;
			case KeyEvent.VK_SPACE:
				ClapperRailGS.getCR().jump();
				break;
			}
		} else if (current_state instanceof RedKnotGameState && view instanceof RedKnotView) {
			RedKnotGameState RedKnotGS = (RedKnotGameState) current_state;
			RedKnotView RedKnotV= (RedKnotView) view;
			RedKnot b = RedKnotGS.getRK();
			
		
			switch (key) {
			case KeyEvent.VK_UP:
				RedKnotGS.getRK().newFlyUp();
				RedKnotGS.allFlockBirdsFlyUp();
				break;
			// change these to be setUp and setDown
			case KeyEvent.VK_DOWN:
				RedKnotGS.getRK().newFlyDown();
				RedKnotGS.allFlockBirdsFlyDown();
				
				break;
			case KeyEvent.VK_S:
				RedKnotV.updateDebugging(RedKnotGS.updateDebugging());
		
				break;
			}
		}
	}

	/* 
	 * -Handles movement for RedKnot when pressing up and down arrow keys
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (current_state instanceof RedKnotGameState && view instanceof RedKnotView) {
			RedKnotGameState RedKnotGS = (RedKnotGameState) current_state;
			RedKnotView RedKnotV= (RedKnotView) view;
			RedKnot b = RedKnotGS.getRK();
			
			switch (key) {
			case KeyEvent.VK_UP:
				b.setFlyState((b.getFlyState() == 1 ? 0 : b.getFlyState()));
				RedKnotGS.setFlyStateAllFlockBirds();;
				break;
			case KeyEvent.VK_DOWN:
				b.setFlyState((b.getFlyState() == -1 ? 0 : b.getFlyState()));
				RedKnotGS.setFlyStateAllFlockBirds();;
				
				break;
			}
		}else if(current_state instanceof ClapperRailGameState && view instanceof ClapperRailView){
			ClapperRailGameState CGS = (ClapperRailGameState) current_state;
			ClapperRail CR = CGS.getCR();
			
			
			switch(key) { 
			case KeyEvent.VK_RIGHT:
				CR.setLeftRightState((CR.getLeftRightState() == 1 ? 0 : CR.getLeftRightState()));				
				break;
			case KeyEvent.VK_LEFT:
				CR.setLeftRightState((CR.getLeftRightState() == -1 ? 0 : CR.getLeftRightState()));
				break;
			}
			
		}
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return WindowView
	 */
	public WindowView getView() {
		return view;
	}

	/**
	 * @return GameScreen
	 */
	public GameScreen getScreen() {
		return this.screen;
	}

}
