package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Controller
 * -class that acts as the Controller of our MVC model
 * -controls updates between state and the view 
 */
public class Controller implements KeyListener {
	private GameView view;
	private GameScreen screen;
	private GameMode current_mode;
	
	//All gamestates:
	private RedKnotGameState RedKnotGS;
	private ClapperRailGameState ClapperRailGS;
	private TitleScreenModel TitleGS;
	private InstructionsModel InstructionsGS;
	
	public Controller() {
		this.screen = new GameScreen(GameScreen.PLAY_SCREEN_WIDTH, GameScreen.PLAY_SCREEN_HEIGHT);
		this.RedKnotGS = new RedKnotGameState(this);
		this.ClapperRailGS = new ClapperRailGameState(this);
		this.InstructionsGS = new InstructionsModel(this);
		this.screen.addKeyListener(this);
		view = new TitleScreenView(this);
		
	}
	
	// Another possibility of a way to do our main loop.
	// with my testing, however, this does not remove stuttering.
	/*
	public void start(int tickdelay){
		ActionListener task = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater( () -> screen.redraw() );
			}
		};
		Timer t = new Timer(tickdelay,task);
		t.start();
		t.restart();
	}*/
	
	/*
	 * TODO:
	 * Split this into two updates, updating the model.
	 * And, updating the screen. (screen.redraw())
	 * And make the model update on a different thread.
	 * 
	 */
	public void start(int tickdelay) {
		long bef,aft;
		bef=System.currentTimeMillis();
		while(loop()){
			aft = System.currentTimeMillis();
			//System.out.println("MILLISECONDS:"+(aft-bef));
			try {
				Thread.sleep(tickdelay-(aft-bef));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bef = System.currentTimeMillis();
		}
	}
	
	
	/*
	 * Calls the Current Models Update Method
	 */
	public void updateModel(){
		if(current_mode==GameMode.CLAPPERRAIL){
			ClapperRailGS.ontick();
		}else if(current_mode==GameMode.REDKNOT){
			RedKnotGS.ontick();
		}else if(current_mode==GameMode.INSTRUCTIONS){
			
		}else if(current_mode==GameMode.TITLESCREEN){
			
		}
	}
	/*
	 * Main loop for the game
	 * Everything that happens once per tick goes in here.
	 * 
	 * We will update the model once per tick, and then the view will draw it. 
	 * 
	 * ANYTHING THAT UPDATES THE MODEL MUUST HAPPEN WHEN updateModel() is called. 
	 * NOT INSIDE PAINTCOMPONENT.
	 * 
	 */
	public boolean loop(){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				updateModel();
			}
		});
		t.start();
		SwingUtilities.invokeLater( () -> screen.redraw() );
		return true;
	}
	

	public GameMode getCurrent_mode() {
		return current_mode;
	}

	public RedKnotGameState getRedKnotGS() {
		return RedKnotGS;
	}

	public ClapperRailGameState getClapperRailGS() {
		return ClapperRailGS;
	}

	public TitleScreenModel getTitleGS() {
		return TitleGS;
	}

	public InstructionsModel getInstructionsGS() {
		return InstructionsGS;
	}

	public GameView getView() {
		return view;
	}

	public GameScreen getScreen() {
		return this.screen;
	}

	public void changeView(GameMode mode) {
		switch(mode) {
		case INSTRUCTIONS:
			this.view.setVisible(false);
//			this.view = new InstructionsView(this,this.InstructionsGS);
			this.view = new InstructionsView(this);
			this.screen.add(this.view);
			this.current_mode = GameMode.INSTRUCTIONS;
			break;
		case CLAPPERRAIL:
			this.view.setVisible(false);
//			this.view = new ClapperRailView(this,this.ClapperRailGS);
			this.view = new ClapperRailView(this);
			this.screen.add(this.view);
			this.current_mode = GameMode.CLAPPERRAIL;
			break;
		case REDKNOT:
			this.view.setVisible(false);
//			this.view = new RedKnotView(this,this.RedKnotGS);
			this.view = new RedKnotView(this);
			this.screen.add(this.view);
			this.current_mode = GameMode.REDKNOT;
			break;
		case TITLESCREEN:
			this.view.setVisible(false);
//			this.view = new TitleScreenView(this,this.TitleGS);
			this.view = new TitleScreenView(this);
			this.screen.add(this.view);
			this.current_mode = GameMode.TITLESCREEN;
			break;
			
		}
		
		this.screen.revalidate();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE){
			changeView(GameMode.TITLESCREEN);
		}
		//System.out.println(key);
		//System.out.println(this.ClapperRailGS.getCR().getPosition().getX());
		
		if(key==KeyEvent.VK_ESCAPE) {
			changeView(GameMode.TITLESCREEN);
		}
		
		if(this.current_mode==GameMode.CLAPPERRAIL) {
			System.out.println("CURRENT MODE IS CLAPPERRAIL");
			switch(key){
			case KeyEvent.VK_RIGHT :
				this.ClapperRailGS.getCR().move();
				this.ClapperRailGS.moveBackground();
				this.ClapperRailGS.checkRightBounds(getScreen().PLAY_SCREEN_WIDTH);
				break;
			case KeyEvent.VK_LEFT : this.ClapperRailGS.getCR().moveLeft();break;
			case KeyEvent.VK_SPACE : this.getClapperRailGS().getCR().jump(); break;
			}
		}
		else if(this.current_mode==GameMode.REDKNOT) {
			System.out.println("CURRENT MODE IS REDKNOT");
			switch(key){
			case KeyEvent.VK_UP : this.RedKnotGS.getRK().newFlyUp();;break;
			//change these to be setUp and setDown
			case KeyEvent.VK_DOWN : this.RedKnotGS.getRK().newFlyDown();break;
			case KeyEvent.VK_S: this.RedKnotGS.switchDebugMode();break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(this.current_mode==GameMode.REDKNOT) {
			Bird b = RedKnotGS.getRK();
			switch(key) {
			case KeyEvent.VK_UP:b.setFlyState((b.getFlyState() == 1 ? 0 : b.getFlyState()));break;
			case KeyEvent.VK_DOWN:b.setFlyState((b.getFlyState() == -1 ? 0 : b.getFlyState()));break;
			}
		}
		// TODO Auto-generated method stub
		
	}
	
}
