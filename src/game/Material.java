package game;

public class Material extends GameObject {
	static final int MAT_SIZE = 160;
	static final int RADIUS = 80;
	
	public Material(int x, int y) {
		super(x,y,MAT_SIZE,MAT_SIZE);
	}
	
	public boolean touchMaterial(Position p) {
		if((p.getX() >= this.getPosition().getX()-RADIUS)&&(p.getX() <= this.getPosition().getX()+RADIUS) &&
				(p.getY() >= this.getPosition().getY()-RADIUS)&&(p.getY() <= this.getPosition().getY()+RADIUS)) {
			return true;
		}
		return false;
	}
}
