package game;

//ClapperRail Game

public class Platform extends GameObject {
	
	private static final int PLATFORM_VX = 0;
	private static final int PLATFORM_VY = 0;
	
	private static final int PLATFORM_WIDTH = 292;
	private static final int PLATFORM_WIDTH_CORRECTION = 0; //image must be messed up, this is tempoary
	private static final int PLATFORM_HEIGHT = 80;
	
	
	/*
	 * So, we do a 50/100 roll to see if something will spawn,
	 * then we do a 33/100 roll to see if that is food,
	 * if it is not food, we then do a 8/100 roll to see if it is a question,
	 * then if it is not a question, it is a material. 
	 */
 	private static final int SPAWN_TOTAL = 100; // out of this.
	private static final int SPAWN_CHANCE = 50; // out of 100
	private static final int SPAWN_CHANCE_FOOD = 33; // out of 100 
	private static final int SPAWN_CHANCE_QUESTION = 8;
	// if its not food, it is material.
	
	public static final int PLATFORM_HEIGHT_INCREASE = 5;
	public static final int platform_height_limit = ClapperRailGameState.GROUND+50;
	
	private boolean hasObject = false;
	private Food f;
	private Material m;
	private ClapperQuestion q;
	
	public Platform(int x, int y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		f = null;
		m = null;
		q=null;
	}
	
	public int getWidth() {
		return PLATFORM_WIDTH;
	}
	
	public int getHeight() {
		return PLATFORM_HEIGHT;
	}
	
	/**@author Derek 
	 * @param pos
	 * @param vy
	 * @return
	 *
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
	
	/**@author Jake 
	 * @param pos
	 * @return
	 * Checks if the Y of the given Position is within 1 pixel of the platform. Also checks if the X of the given
	 * Position is within the width of the platform.
	 */
	public boolean touchPlatform(Position pos) {
		if(pos.getY() == this.getPosition().getY() || pos.getY() == this.getPosition().getY()+1) {
			if((pos.getX() >= this.getPosition().getX()) 
					&& (pos.getX() <= (this.getPosition().getX() + (this.getWidth()-PLATFORM_WIDTH_CORRECTION)))) {
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see game.GameObject#move()
	 * -Increments the height of the platform by 
	 */
	@Override
	public void move() {
		if(m!=null)
			m.move();
		if(f!=null)
			f.move();
		if(q!=null)
			q.move();
		Position p = this.getPosition();
		int newY = p.getY()+PLATFORM_HEIGHT_INCREASE;
		
		if(newY > platform_height_limit) {
			resetPosition();
			return;
		}
		
		this.setPosition(new Position(p.getX(),newY));
	}
	
	
	/**@author Derek 
	 * @param 
	 *
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
		int x = (int)(Math.random()*(Platform.SPAWN_TOTAL));
		if(x < Platform.SPAWN_CHANCE){
			int y = (int)(Math.random()*Platform.SPAWN_TOTAL);
			if(y < Platform.SPAWN_CHANCE_FOOD){
				addFood();
			}else{
				int z = (int) (Math.random()*Platform.SPAWN_TOTAL);
				if(z < Platform.SPAWN_CHANCE_QUESTION){
					addQuestion();
				}else{
					addMaterial();
				}
			}
		}
		
		
		
	}
	
	
	public Food getFood() {
		return f;
	}


	/**@author Derek 
	 * @param 
	 * Adds Food to the platform
	 */
	public Food addFood() {
//		f = new Food(this.getPosition().getX()+(Food.CRAB_SIZE/2),this.getPosition().getY()-Food.CRAB_SIZE,0);
		/*
		 * NOTE: the only reason setting the y position like this works, is because food and materials are 
		 * only added when the platform is at 0.
		 */
		if(this.getHasObject()==false) {
			f = new Food(this.getPosition().getX()+(Food.CRAB_SIZE/2),-1*(Food.CRAB_SIZE),0);
			System.out.println(f.getPosition());
			this.setHasObject(true);
		}
		return f;
	}
	public Material getMaterial(){
		return m;
	}
	
	/**@author Derek 
	 * @param 
	 * Adds a Material to the platform
	 */
	public Material addMaterial(){
//		m = new Material(this.getPosition().getX()+(Material.MAT_SIZE),this.getPosition().getY()-(Material.MAT_SIZE));
		/*
		 * NOTE: the only reason setting the y position like this works, is because food and materials are 
		 * only added when the platform is at 0.
		 */
		if(this.getHasObject()==false) {
			m = new Material(this.getPosition().getX()+(Material.MAT_SIZE/2),-1*(Material.MAT_SIZE));
			this.setHasObject(true);
		}
		return m;
	}
	public ClapperQuestion getQuestion(){
		return q;
	}
	/**@author Derek 
	 * @param 
	 * Adds a ClapperQuestion to the platform
	 */
	public ClapperQuestion addQuestion(){
		if(this.getHasObject()==false) {
			q = new ClapperQuestion(this.getPosition().getX()+(ClapperQuestion.BLOCK_SIZE/2),-1*(ClapperQuestion.BLOCK_SIZE));
			this.setHasObject(true);
		}
		return q;
	}
	
	/**@author Miguel
	 * -sets the q(question) property to null
	 */
	public void removeQuestion(){
		q=null;
	}

	/**@author Miguel
	 *  -sets the m(material) property to null
	 */
	public void removeMaterial(){
		m=null;
	}
	
	/**@author Miguel
	 * -sets the f(food) property to null
	 */
	public void removeFood() {
		f = null;
	}
	
	/**@author Miguel
	 * @return
	 * -checks the m,f,q properties and if one of them is not null returns true
	 */
	public boolean getHasObject() {
		boolean isItemOnPlatform = false;
		if(this.m!=null|this.f!=null|this.q!=null) {
			isItemOnPlatform = true;
		}
		this.hasObject = isItemOnPlatform;
		return hasObject;
	}
	
	/**@author Miguel
	 * @param b
	 * -sets the hasObject boolean to the inputted b boolean
	 */
	public void setHasObject(boolean b) {
		hasObject = b;
	}

}
