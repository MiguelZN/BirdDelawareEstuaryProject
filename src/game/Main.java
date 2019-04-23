package game;

public class Main {
	
	public static void main(String[] args){
		Controller game_controller = new Controller();
		game_controller.changeView(GameMode.TITLESCREEN);
		game_controller.start(100);
		

		
		
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
