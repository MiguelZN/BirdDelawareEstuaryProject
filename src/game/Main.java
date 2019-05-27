package game;

public class Main {
	
	static final int TICKRATE = 10;
	
	public static void main(String[] args){
//		System.setProperty("sun.java2d.opengl", "True"); //MAGICALLY MAKES THINGS GREAT
		Controller game_controller = new Controller();
		game_controller.start(TICKRATE); 

	}

}
