package game;
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
		view = new TitleScreenView();
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
		return screen;
	}

	public void changeView(GameMode mode) {
		switch(mode) {
		case INSTRUCTIONS:
			this.view.setVisible(false);
			this.view = new InstructionsView();
			break;
		case CLAPPERRAIL:
			this.view.setVisible(false);
			this.view = new ClapperRailView();
		case REDKNOT:
			this.view.setVisible(false);
			this.view = new RedKnotView();
		}
	}
}
