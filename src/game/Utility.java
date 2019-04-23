package game;

//Static Helper Methods
public class Utility {
	
	//returns a random int from [offset, number]
	public static int getRandomInt(int number, int offset) {
		int rand_range = number-offset;
		return ((int)Math.ceil(Math.random()*rand_range))+offset; 
	}

}
