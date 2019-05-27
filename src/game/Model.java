package game;

import java.io.Serializable;

/*Created by Miguel:
 *-Model is the super class of GameState 
 *-Direct children classes include: TitleScreenModel, InstructionsModel, 
 *and GameState, 
 *-TitleScreenModel and InstructionsModel are Model classes and not GameState
 *classes because they are not mini-games and simply just different views
 *-made it abstract so that it cannot be initialized 
 */
public abstract class Model implements Serializable{
	protected Controller controller;
	
	public Model(Controller controller) {
		this.controller = controller;
	}

}
