package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

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
		
		this.screen = new GameScreen(500, 500);
		this.RedKnotGS = new RedKnotGameState(this);
		this.ClapperRailGS = new ClapperRailGameState(this);
		this.InstructionsGS = new InstructionsModel(this);
		this.screen.addKeyListener(this);
		view = new TitleScreenView(this,TitleGS);
		
	}
	
	public void start(int tickdelay) {
		while(loop()){
			try {
				Thread.sleep(tickdelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * Main loop for the game
	 * Everything that happens once per tick goes in here.
	 */
	public boolean loop(){
		getScreen().redraw();
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
			this.view = new InstructionsView(this,this.InstructionsGS);
			this.screen.add(this.view);
			this.current_mode = GameMode.INSTRUCTIONS;
			break;
		case CLAPPERRAIL:
			this.view.setVisible(false);
			this.view = new ClapperRailView(this,this.ClapperRailGS);
			this.screen.add(this.view);
			this.current_mode = GameMode.CLAPPERRAIL;
			break;
		case REDKNOT:
			this.view.setVisible(false);
			this.view = new RedKnotView(this,this.RedKnotGS);
			this.screen.add(this.view);
			this.current_mode = GameMode.REDKNOT;
			break;
		case TITLESCREEN:
			this.view.setVisible(false);
			this.view = new TitleScreenView(this,this.TitleGS);
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
		System.out.println(key);
		System.out.println(this.ClapperRailGS.getCR().getPosition().getX());
		
		if(key==KeyEvent.VK_ESCAPE) {
			changeView(GameMode.TITLESCREEN);
			this.screen.setSize(500,500);
		}
		
		if(this.current_mode==GameMode.CLAPPERRAIL) {
			System.out.println("CURRENT MODE IS CLAPPERRAIL");
			switch(key){
			case KeyEvent.VK_RIGHT : this.ClapperRailGS.getCR().move();break;
			case KeyEvent.VK_LEFT : this.ClapperRailGS.getCR().moveLeft();break;
			case KeyEvent.VK_SPACE : this.getClapperRailGS().getCR().jump(); break;
			}
		}
		else if(this.current_mode==GameMode.REDKNOT) {
			System.out.println("CURRENT MODE IS REDKNOT");
			switch(key){
			case KeyEvent.VK_UP : this.RedKnotGS.getRL().FlyUp();;break;
			case KeyEvent.VK_DOWN : this.RedKnotGS.getRL().FlyDown();break;
			
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
