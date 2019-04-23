package game;

//Static Helper Methods
public class Utility {
	
	//returns a random int from the [inputted min, inputted max]
	public static int randRangeInt(int min_num,int max_num){
		return (int)(Math.random()*((max_num - min_num) + 1)+min_num);
	}

}
