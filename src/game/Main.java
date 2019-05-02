package game;

public class Main {
	
	private static final int TICKRATE = 10;
	
	public static void main(String[] args){
		System.setProperty("sun.java2d.opengl", "True"); //MAGICALLY MAKES THINGS GREAT
		Controller game_controller = new Controller();
		game_controller.changeView(GameMode.TITLESCREEN);
		game_controller.start(TICKRATE); 
		//TODO: Modify the speeds of game objects such that the tickrate of 15ms, is playable.
		

		
		
		//Testing randomized clouds (WORKS- Miguel)	
//		for(int i=0;i<10;i++) {
//			Cloud temp = Cloud.spawnCloud(500, 500, 0);
//			System.out.println(temp.getPosition().getX()+","+temp.getPosition().getY());
//			temp.move();
//			System.out.println(temp.getPosition().getX()+","+temp.getPosition().getY());
//			
//		}
	}

}
