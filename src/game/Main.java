package game;

public class Main {
	
	public static void main(String[] args){
		Controller game_controller = new Controller();
		game_controller.start();
		
		for(int i=0;i<5000;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			game_controller.getScreen().redraw();
		}
	}

}
