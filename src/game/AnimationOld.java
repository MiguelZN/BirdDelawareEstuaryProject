package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * I rewrote this class to work with the generalized way that we load in images.
 * Raw Sprite PNGs will be named: "sprite-NUMOFFRAMES-name.png", and now we can always load them.
 * 
 * Now any sprite png we add to our redknot folder, can be loaded and stored as an Animation.
 */



//Handles the frame animations (Spritesheets/SpriteAnimations)
public class AnimationOld {
	BufferedImage spritesheet;
	String sprite_sheet_path;
	int num_rows, num_columns, num_frames, frame_index, current_tick, threshold, fps;
	int current_row, current_column;
	Size size;

	int imgsrcx1, imgsrcx2, imgsrcy1, imgsrcy2;

	public AnimationOld(String sprite_sheet_path, Size s, int num_rows, int num_columns, int num_frames, int fps) {
		this.sprite_sheet_path = sprite_sheet_path;
		this.num_rows = num_rows;
		this.num_columns = num_columns;
		this.num_frames = num_frames;
		this.fps = fps; // GAME_SPEED
		this.frame_index = 0; // starts at the first frame

		this.size = s; // Keeps track of image size

		this.spritesheet = null;

		try {
			this.spritesheet = ImageIO.read(new File(this.sprite_sheet_path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Explanation:
		/*
		 * Lets say we want the game to run at 60 fps == 1000/60 == 16.66 ms updates.
		 * 
		 * We want our sprite animation to update to the next image frame every second:
		 * Thus we do 60fps/1fps (1 frame per second) = 60 tick multiplier 1000ms/60fps
		 * = 16.66ms. 16.66ms * 60 tick multipler = 1000ms threshold.
		 * 
		 * Thus every
		 */

//		int tick_multiplier = this.fps/this.num_frames;
//		int oneSecondMS = 1000; //1 second == 1000ms
//		this.threshold = (oneSecondMS/this.fps)*tick_multiplier;

		// Gives us the threshold of when to increment the frame index
		this.threshold = this.fps / this.num_frames;

		// Starts at the top left image of the spritesheet
		this.current_row = 0;
		this.current_column = 0;

		this.updateImgSrcXY();

	}

//	public void loadSpriteSheet(String sprite_sheet_path, int num_rows, int num_columns) {
//		BufferedImage[][] spritesheet;
//		
//		for(int r=0;r<num_rows;r++) {
//			for(int c=0;c<num_columns;c++) {
//				BufferedImage test_image = null;
//				try {
//					test_image = ImageIO.read(new File("resources/images/redknot/background1.png"));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	public AnimationOld() {
		;
	}

	public void updateImgSrcXY() {

		if (this.frame_index < this.num_columns) {
			this.imgsrcx1 = this.frame_index * this.size.getWidth();
			this.imgsrcx2 = this.imgsrcx1 + this.size.getWidth();
			this.imgsrcy1 = this.current_row * this.size.getHeight();
			this.imgsrcy2 = this.imgsrcy1 + this.size.getHeight();

			//System.out.printf("X1:%d, Y1:%d, X2:%d, Y2:%d, INDEX:%d\n", this.imgsrcx1, this.imgsrcy1, this.imgsrcx2,
				//	this.imgsrcy2, this.frame_index);
		}
	}

//	public void drawImage1(Graphics g, Position p, Size s) {
//		//dx1,dxy1 means top left of image ON the java window -> dx2, dy2 bottom right ON java window
//		//sx1, sxy1 means top left ON spritesheet -> sx2, sy2 bottom right
//		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer)
//
//	}

	// Call this method where-ever ticks update the game
	public void update() {
		/*
		 * System.out.println("UPDATING TICK"); System.out.println(this.current_tick);
		 * System.out.println(this.threshold);
		 */
		
		System.out.println(current_tick);

		if (this.frame_index >= this.num_columns) {
			this.frame_index = -1;
		}

		if (this.current_tick >= this.threshold) {
			this.current_tick = 0;
			this.frame_index++;
			
			this.updateImgSrcXY();

		}

		this.current_tick++;

		this.current_column = this.frame_index;
	}

	public void changeRow(int row) {
		this.current_row = row;
	}

	public void changeColumn(int column) {
		this.current_column = column;
		this.frame_index = column;
	}

	public void drawImageExact(Graphics g, Position p, Size draw_size) {
		// dx1,dxy1 means top left of image ON the java window -> dx2, dy2 bottom right
		// ON java window
		// sx1, sxy1 means top left ON spritesheet -> sx2, sy2 bottom right
		// g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer)
		int destx1 = p.getX() - (draw_size.getWidth() / 2);
		int destx2 = p.getX() + (draw_size.getWidth() / 2);
		int desty1 = p.getY() - (draw_size.getHeight() / 2);
		int desty2 = p.getY() + (draw_size.getHeight() / 2);

		g.drawImage(this.spritesheet, destx1, desty1, destx2, desty2, this.imgsrcx1, this.imgsrcy1, this.imgsrcx2,
				this.imgsrcy2, null, null);
		g.setColor(Color.RED);

		this.update();
	}
	
	public void drawImageTopLeft(Graphics g, Position p, Size draw_size) {
		// dx1,dxy1 means top left of image ON the java window -> dx2, dy2 bottom right
		// ON java window
		// sx1, sxy1 means top left ON spritesheet -> sx2, sy2 bottom right
		// g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer)
		int destx1 = p.getX();
		int destx2 = p.getX() + draw_size.getWidth();
		int desty1 = p.getY();
		int desty2 = p.getY() + draw_size.getHeight();

		g.drawImage(this.spritesheet, destx1, desty1, destx2, desty2, this.imgsrcx1, this.imgsrcy1, this.imgsrcx2,
				this.imgsrcy2, null, null);
		g.setColor(Color.RED);

		this.update();
	}

//	public void drawImage(Graphics g) {
//		// dx1,dxy1 means top left of image ON the java window -> dx2, dy2 bottom right
//		// ON java window
//		// sx1, sxy1 means top left ON spritesheet -> sx2, sy2 bottom right
//		BufferedImage test_image = null;
//		try {
//			test_image = ImageIO.read(new File("resources/images/redknot/background1.png"));
//			g.drawImage(test_image, 10, 10, 500, 50, 0, 0, 300, 500, null, null);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
