package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {
	//Final constants in milliseconds
	final static int ONE_SECOND = 1000;
	final static int ONE_MINUTE = ONE_SECOND*60;
	
	//Performs a 'task' or function after the given amount of 'delay' has
	//been passed. **delay is in milliseconds
	public GameTimer(){
		this.schedule(new TimerTask() {
			@Override
			public void run() {
				//System.out.println("TIMER");
			}
		}, 5000);
	}
	
	public GameTimer(TimerTask TK){
		this.schedule(TK, 5000);
	}
		
	//The timer is delayed by 'delay' then performs 
	//a task after every 'interval' time (in milliseconds)
	public GameTimer(int delay, int interval,TimerTask task){
		this.scheduleAtFixedRate(task, delay,interval);
		//System.out.println("TIMER");
	}
	
	//The timer is delayed by 'delay' then performs 
	//a task after every 'interval' time (in milliseconds)
	public GameTimer(int interval,TimerTask task){
		int delay = 0; //this means that immediately start the game timer
		this.scheduleAtFixedRate(task, delay,interval);
		//System.out.println("TIMER");
	}
}
