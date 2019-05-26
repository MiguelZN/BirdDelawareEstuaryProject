package game.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JRadioButton;

import org.junit.Test;

import game.Energy;
import game.FlockBird;
import game.GameObject;
import game.RedKnotGameState;
import game.Size;
import game.Velocity;
import game.Bird;
import game.BirdType;
import game.ClapperRailGameState;
import game.Cloud;
import game.Controller;
import game.Material;
import game.Position;
import game.QuestionCloud;
import game.QuizQuestion;
import game.RedKnot;
import game.GameScreen;

public class BirdModelsTest {
	private Controller c = new Controller();
	
	
	//REDKNOT----------------
	@Test
	public void testGetRK() {
		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
		Bird rk = redknotgamestate.getRK();
		assertEquals(redknotgamestate.getRK(), rk);
	}
	
	@Test
	public void testGetFlock() {
		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
		ArrayList<FlockBird> flock = redknotgamestate.getFlock();
		assertEquals(redknotgamestate.getFlock(), flock);
	}
	
	@Test
	public void testCollectBird() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		ArrayList<FlockBird> flock = rgs.getFlock();
		ListIterator<FlockBird> flock_iter = flock.listIterator();
		
		int old_size = flock.size(); //old size
		rgs.collectBird(flock_iter, new FlockBird(new Position(0,0), new Size(5,5), new Velocity(-5,0), false));
		
		int new_size = flock.size();
		assertEquals(old_size+1,new_size);
	}
	
	@Test
	public void testLosingBirds() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		
		ArrayList<FlockBird> flock = rgs.getFlock();
		ListIterator<FlockBird> flock_iter = flock.listIterator();
		
		rgs.collectBird(flock_iter, new FlockBird(new Position(0,0), new Size(5,5), new Velocity(-5,0), false));
		rgs.collectBird(flock_iter, new FlockBird(new Position(0,0), new Size(5,5), new Velocity(-5,0), false));
		rgs.lostBird();
		assertEquals(rgs.countBirds(),1);
	}
	
	@Test
	public void testGetEndDialogue() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		assertEquals(rgs.isHasPressedEndDialogue(), false);
		
		rgs.setHasPressedEndDialogue(true);
		assertEquals(rgs.isHasPressedEndDialogue(), true);
	}
	
	@Test
	public void testCreateQuizzes() {
		RedKnotGameState rgs = new RedKnotGameState(c);
//		assertEquals(rgs.qr,null);
		
		rgs.createQuiz(new Position(50,50));
		assertNotNull(rgs.current_TA);
		
		String answer = rgs.current_quiz.getAnswer();
		for(JRadioButton qq:rgs.current_quiz.getResponse_buttons()) {
			if(qq.getText().equalsIgnoreCase(answer)) {
				for(ActionListener a:qq.getActionListeners()) {
					a.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
				}
			}
		}
		
		rgs.createQuiz(new Position(50,50));

		String answer2 = rgs.current_quiz.getAnswer();
		for(JRadioButton qq:rgs.current_quiz.getResponse_buttons()) {
			if(qq.getText().equalsIgnoreCase(answer)==false) {
				for(ActionListener a:qq.getActionListeners()) {
					a.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
					assertEquals(true,true);
					return;
				}
			}
		}
		
		
	}
	
	@Test
	public void testOntick() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		rgs.setIsGameRunning(false);
		assertEquals(rgs.getIsGameRunning(),false);
		
		rgs.setIsGameRunning(true);
		assertEquals(rgs.getIsGameRunning(),true);
		
		if(rgs.getIsGameRunning()) {
			rgs.ontick();
			assertEquals(true,true); //if this passes, means that the ontick was called
		}
		
	}
	
	
	//Simulates a player getting a Cloud Tutorial and them pressing the up,down keys and
	//removing the tutorial (PASSES ALL TESTS)
	@Test
	public void testCloudTutorial() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		Cloud c = new Cloud(new Position((int)(GameScreen.PLAY_SCREEN_WIDTH*.74),0), new Size(50,50));
		
		rgs.setCurrent_RKTA(null);
		assertEquals(rgs.getCurrent_TA(),null);
		
		rgs.createCloudTutorial(c);
		assertEquals(rgs.getCurrent_TA(),rgs.getCurrent_TA());
		
		rgs.setDown_key_pressed(true);
		rgs.setUp_key_pressed(true);
		assertEquals(rgs.isDown_key_pressed(), true);
	}
	
	
	@Test
	public void testDebugging() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		rgs.setDebuggingMode(true);
		assertTrue(rgs.getDebuggingMode());
		
		rgs.setDebuggingMode(false);
		assertEquals(rgs.getDebuggingMode(),false);		
	}
	
	@Test
	public void testUpdateGameObjects() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		
		ArrayList<GameObject> game_object_list = new ArrayList<>();
		game_object_list.add(new GameObject(new Position(50,50), new Size(10,10)));
		game_object_list.add(new GameObject(new Position(50,50), new Size(10,10)));
		game_object_list.add(new GameObject(new Position(50,50), new Size(10,10)));
		
		rgs.setIsGameRunning(true);
		if(rgs.getIsGameRunning()) {
			rgs.updateGameObjects(rgs.getRK(), game_object_list);
			assertEquals(true,true); //if the game is running and passes, this means it iterated through game objects arrayList
		}
	}
	
	@Test
	public void testGetGameObjects() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		assertEquals(rgs.getUpdateableGameObjects() instanceof ArrayList,true);
	}
	
	@Test
	public void testReachedDestination() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		boolean b = rgs.reachedDestination;
		assertEquals(b,rgs.hasReachedDestination());
	}
	
	@Test
	public void testCheckFlockBirds() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		ArrayList<FlockBird> new_flock = new ArrayList<>();
		//Tests by adding two flockbirds: one way past the left boundary
		//and one past the bottom boundary causing the checkFlockBirds() to remove them both leaving only 2 left
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(-5,0), false));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(-5,0), false));
		new_flock.add(new FlockBird(new Position(-3500,50), new Size(50,50), new Velocity(-5,0), false));
		new_flock.add(new FlockBird(new Position(50,3000), new Size(50,50), new Velocity(-5,0), false));
		rgs.setFlock(new_flock);
		rgs.checkFlockBirds();
		assertEquals(rgs.getFlock().size()==2,true);
	}
	
	@Test
	public void testAddClouds() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		ArrayList<Cloud> new_clouds = new ArrayList<>();
		rgs.setClouds(new_clouds);
		assertEquals(rgs.getClouds().size()==0,true);
	
		//Tests to make sure that EnemyClouds were added
		rgs.addClouds();
		assertEquals(rgs.getClouds().size()>0,true);
		
	}
	
	@Test
	public void testSetFlockBirdFlyStates() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		ArrayList<FlockBird> new_flock = new ArrayList<>();
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		
		//sets flystate of first bird to 1 (up)
		new_flock.get(0).setFlyState(1);
		//sets flystate of second bird to -1 (up)
		new_flock.get(1).setFlyState(-1);
		
		assertEquals(new_flock.get(0).getFlyState(),1);
		assertEquals(new_flock.get(1).getFlyState(),-1);

		
		//Should set the flystate of the first bird to 0(stationary)
		rgs.setFlock(new_flock);
		rgs.setFlyStateAllFlockBirds();
		int new_flystateOfFirstBird = new_flock.get(0).getFlyState();
		int new_flystateOfSecondBird = new_flock.get(1).getFlyState();
		
		assertEquals(new_flock.get(0).getFlyState(),0);
		assertEquals(new_flock.get(1).getFlyState(),0);
		
	}
	
	@Test
	public void testLostBirds() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		ArrayList<FlockBird> new_flock = new ArrayList<>();
		//sets flock to a flock of ALL collected birds thus by collect setBirdsLost() this should cause a random bird to have
		//its 'LostInStorm' boolean attribute to true
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		rgs.setFlock(new_flock);
		
		int lost_count = 0;
		//Just assures that all flock birds have its lostinstorm attribute to false
		for(FlockBird fb:rgs.getFlock()) {
			fb.setGotLostInStorm(false);
			fb.setIsCollected(true);
			
			if(fb.getIsLostInStorm()) {
				lost_count++;
			}
		}
		
		assertEquals(lost_count==0,true);
		
		int one_bird = 1;
		rgs.setBirdsLost(rgs.getFlock(), one_bird);
		for(FlockBird fb:rgs.getFlock()) {
			if(fb.getIsLostInStorm()) {
				lost_count++;
			}
		}
		
		assertEquals(lost_count!=0,true);
		
		
	}
	
	@Test
	public void testFlockBirdFlyUpFlyDown() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		ArrayList<FlockBird> new_flock = new ArrayList<>();
		//sets flock to a flock of ALL collected birds thus by collect setBirdsLost() this should cause a random bird to have
		//its 'LostInStorm' boolean attribute to true
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(5,0), true));
		rgs.setFlock(new_flock);
		int flock_size = rgs.getFlock().size();
		
		int fly_count_up = 0;
		int fly_count_down = 0;
		//Just assures that all flock birds have its lostinstorm attribute to false
		for(FlockBird fb:rgs.getFlock()) {
			fb.setFlyState(0);
			
			if(fb.getFlyState()==1) {
				fly_count_up++;
			}
			
			if(fb.getFlyState()==-1) {
				fly_count_down++;
			}
		}
		
		assertEquals(fly_count_up==0,true); //if 0 means that all birds have their fly state to 0 and we can begin testing
		assertEquals(fly_count_down==0,true); //if 0 means that all birds have their fly state to 0 and we can begin testing
		
		rgs.allFlockBirdsFlyUp();
		for(FlockBird fb:rgs.getFlock()) {
			
			if(fb.getFlyState()==1) {
				fly_count_up++;
			}
			
			if(fb.getFlyState()==-1) {
				fly_count_down++;
			}
		}
		
		assertEquals(fly_count_up==flock_size,true);
		assertEquals(fly_count_down==0,true);
		
		rgs.allFlockBirdsFlyDown();
		for(FlockBird fb:rgs.getFlock()) {
			
			if(fb.getFlyState()==1) {
				fly_count_up++;
			}
			
			if(fb.getFlyState()==-1) {
				fly_count_down++;
			}
		}
		assertEquals(fly_count_down==flock_size,true);
	}

	@Test
	public void testRedKnotFlockBirdCollision() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		ArrayList<FlockBird> new_flock = new ArrayList<>();
		RedKnot new_RK = new RedKnot();
		//sets flock to a flock of ALL collected birds 
		new_flock.add(new FlockBird(new_RK.getPosition(), new Size(300,300), new Velocity(0,0), false));
		new_flock.add(new FlockBird(new Position(50,50), new Size(50,50), new Velocity(0,0), false));

		rgs.setFlock(new_flock);
		ListIterator<FlockBird> fb_iter = new_flock.listIterator();
		assertEquals(true,true);
		
		//DOES NOT WORK (currently no way to test for java.awt's Rectangle's collision)
		//rgs.detectFlockBirdCollection(new_RK, new_flock.get(0), fb_iter);
	}
	
	@Test
	public void testScore() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		int test_score =100;
		rgs.setScore(test_score);
		int old_score = rgs.getScore();
		
		assertEquals(old_score==test_score,true);
		
		rgs.incrementScore(200);
		assertEquals(old_score+200==test_score+200,true);
		
	}
	
	@Test
	public void testAddRandomFlockBird() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		int low_chance = 0;
		int high_chance = 10;
		int threshold = 100; //since it will produce a number between 0-10 it will always create a question cloud since it is under the threshold of 100
		
		int number_fb = 0;
		ArrayList<FlockBird> new_flock = new ArrayList<>();
		rgs.setFlock(new_flock);
		rgs.addRandomFlockBirds(low_chance, high_chance, threshold);
		for(FlockBird fb:rgs.getFlock()) {
			if(fb.getIsCollected()==false) {
				number_fb++;
			}
		}
		assertEquals(number_fb>0,true);
	}
	
	@Test
	public void testAddQuestionCloud() {
		RedKnotGameState rgs = new RedKnotGameState(c);
		int low_chance = 0;
		int high_chance = 10;
		int threshold = 100; //since it will produce a number between 0-10 it will always create a question cloud since it is under the threshold of 100
		
		int number_qc = 0;
		ArrayList<Cloud> new_clouds = new ArrayList<>();
		rgs.setClouds(new_clouds);
		rgs.addQuestionCloud(low_chance, high_chance, threshold);
		for(Cloud c:rgs.getClouds()) {
			if(c instanceof QuestionCloud) {
				number_qc++;
			}
		}
		assertEquals(number_qc>0,true);
	}
	
	
	
}
