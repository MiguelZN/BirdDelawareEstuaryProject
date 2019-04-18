package game;

public class Main {
	
	public static void main(String[] args){
		Controller game_controller = new Controller();
		game_controller.changeView(GameMode.TITLESCREEN);
		game_controller.start(100);
//		game_controller.changeView(GameMode.INSTRUCTIONS);
//		game_controller.changeView(GameMode.REDKNOT);
//		game_controller.changeView(GameMode.CLAPPERRAIL);
	}

}
