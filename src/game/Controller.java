package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*Authors: Miguel Zavala, Derek Baum, Matt Benvenuto, Jake Wise
 * 
 */

/*Class: Controller
 * -class that acts as the Controller of our MVC model
 * -controls updates between state and the view 
 */
public class Controller {
	private GameState state;
	private GameView view;
	private GameScreen screen;
	
	public Controller() {
		view = new TitleScreenView(this);
		this.screen = new GameScreen(500, 500);
		
	}
	
	public void start() {
		
	}
	
	
	
	public GameState getState() {
		return state;
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
			this.view = new InstructionsView(this);
			this.screen.add(this.view);
			break;
		case CLAPPERRAIL:
			this.view.setVisible(false);
			this.view = new ClapperRailView(this);
			this.screen.add(this.view);
			break;
		case REDKNOT:
			this.view.setVisible(false);
			this.view = new RedKnotView(this);
			this.screen.add(this.view);
			break;
		case TITLESCREEN:
			this.view.setVisible(false);
			this.view = new TitleScreenView(this);
			this.screen.add(this.view);
			break;
			
		}
		
		this.screen.revalidate();
	}
	
	public void changeState(GameMode mode) {
		switch(mode) {
		case CLAPPERRAIL:
			this.state = new ClapperRailGameState();
			break;
		case REDKNOT:
			this.state = new RedKnotGameState();
			break;
		}
	}
	
}
