package game;

//ClapperRail Game

public class Platform extends GameObject {
	
	private static final int PLATFORM_VX = 0;
	private static final int PLATFORM_VY = 0;
	
	private static final int PLATFORM_WIDTH = 146;
	private static final int PLATFORM_WIDTH_CORRECTION = 0; //image must be messed up, this is tempoary
	private static final int PLATFORM_HEIGHT = 40;
	
	
 	private static final int SPAWN_TOTAL = 100; // out of this.
	private static final int SPAWN_CHANCE = 50; // out of 100
	private static final int SPAWN_CHANCE_FOOD = 33; // out of 100 
	// if its not food, it is material.
	
	private boolean hasObject = false;
	private Food f;
	private Material m;
	
	public Platform(int x, int y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		f = null;
		m = null;
	}
	
	public int getWidth() {
		return PLATFORM_WIDTH;
	}
	
	public int getHeight() {
		return PLATFORM_HEIGHT;
	}
	/* 
	 * the only collision we want is the collision during falling,
	 * and we would like to check if the next tick's movement will bring it into collision
	 * with the platform.
	 */
	public boolean willTouchPlatform(Position pos, int vy){
		if(pos.getY()+vy >= this.getPosition().getY() && pos.getY() <= this.getPosition().getY()) {
			if((pos.getX() >= this.getPosition().getX()) 
					&& (pos.getX() <= (this.getPosition().getX() + (this.getWidth()-PLATFORM_WIDTH_CORRECTION)))) {
				return true;
			}
		}
		return false;
	}
	public boolean touchPlatform(Position pos) {
		if(pos.getY() == this.getPosition().getY() || pos.getY() == this.getPosition().getY()+1) {
			if((pos.getX() >= this.getPosition().getX()) 
					&& (pos.getX() <= (this.getPosition().getX() + (this.getWidth()-PLATFORM_WIDTH_CORRECTION)))) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void move() {
		if(m!=null)
			m.move();
		if(f!=null)
			f.move();
		Position p = this.getPosition();
		int newY = p.getY()+5;
		if(newY > ClapperRailGameState.GROUND+50) {
			resetPosition();
			return;
		}
		
		this.setPosition(new Position(p.getX(),newY));
	}
	
	
	/*
	 * Resets the position of the platform,
	 * and also controls the random chance for materials 
	 * and food to be added.
	 * 
	 */
	public void resetPosition(){
		removeFood();
		removeMaterial();
		int newY = 0;
		int newX = (int)(Math.random()*(GameScreen.CR_SCREEN_WIDTH - this.getWidth()));
//		int newX = this.getPosition().getX();
		this.setPosition(new Position(newX,newY));
		System.out.println();
		int x = (int)(Math.random()*(Platform.SPAWN_TOTAL));
		if(x < Platform.SPAWN_CHANCE){
			int y = (int)(Math.random()*Platform.SPAWN_TOTAL);
			if(y < Platform.SPAWN_CHANCE_FOOD){
				addFood();
			}else{
				addMaterial();
			}
		}
		
		
		
	}
	
	
	public Food getFood() {
		return f;
	}


	public Food addFood() {
//		f = new Food(this.getPosition().getX()+(Food.CRAB_SIZE/2),this.getPosition().getY()-Food.CRAB_SIZE,0);
		/*
		 * NOTE: the only reason setting the y position like this works, is because food and materials are 
		 * only added when the platform is at 0.
		 */
		f = new Food(this.getPosition().getX()+(Food.CRAB_SIZE/2),-1*(Food.CRAB_SIZE),0);
		System.out.println(f.getPosition());
		return f;
	}
	public Material getMaterial(){
		return m;
	}
	public Material addMaterial(){
//		m = new Material(this.getPosition().getX()+(Material.MAT_SIZE),this.getPosition().getY()-(Material.MAT_SIZE));
		/*
		 * NOTE: the only reason setting the y position like this works, is because food and materials are 
		 * only added when the platform is at 0.
		 */
		m = new Material(this.getPosition().getX()+(Material.MAT_SIZE/2),-1*(Material.MAT_SIZE));
		return m;
	}

	public void removeMaterial(){
		m=null;
	}
	
	public void removeFood() {
		f = null;
	}
	
	public boolean getHasObject() {
		return hasObject;
	}
	
	public void setHasObject(boolean b) {
		hasObject = b;
	}

}
