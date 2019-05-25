package game;

import java.awt.image.BufferedImage;


//Essentially, an animation is just an array of images, the size of that array, and the current index.
public class Animation {
	
	private BufferedImage[] images;
	private int frameCount;
	private int tick;
	private int frameIndex;//current frame index

	/*
	 * Constructor, given an Object o which will be the whole sprite image, we will then store that
	 * in our array.
	 */
	/**
	 * @param o
	 * @param frameCount
	 */
	public Animation(Object o, int frameCount){
		BufferedImage sprite=null;
		frameIndex=0;
		tick=0;
		this.frameCount=frameCount;
		if(o instanceof BufferedImage){
			sprite = (BufferedImage)o;
		}
		int subWidth = sprite.getWidth()/frameCount;
		images = new BufferedImage[frameCount];
		for(int i = 0; i < frameCount; i++){
			images[i] = sprite.getSubimage(subWidth*i, 0, subWidth, sprite.getHeight());
		}
	}
	/*
	 * This returns the current image of our animation.
	 * We track the amount of times this method is called with 'tick'
	 * If our desired FPS is 60, and the framecount of this animation is 6,
	 * then we would like to increment to the next frame of the animation every 
	 * 10 times this is called.
	 */
	/**
	 * @return
	 */
	public BufferedImage currImage(){
		tick++;
		if(GameScreen.GAME_FPS/frameCount==tick){
			incrementIndex();
			tick=0;
		}
		return images[frameIndex];
	}
	
	//Given a frameIndex, this returns the Animation image corresponding to it (FOR FLOCKBIRD)
	/**
	 * @param frameIndex
	 * @return
	 */
	public BufferedImage currImage(int frameIndex) {
		System.out.println(frameCount);
		return images[frameIndex];
	}

	/**
	 * -Increments the current frameIndex
	 */
	public void incrementIndex(){
		frameIndex=(frameIndex+1)%frameCount;
	}
	
	/**
	 * @return this.frameCount
	 */
	public int getFrameCount() {
		return this.frameCount;
	}
	
}
