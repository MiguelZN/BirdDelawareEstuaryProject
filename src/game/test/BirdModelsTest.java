package game.test;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import game.Energy;
import game.FlockBird;
import game.GameObject;
import game.RedKnotGameState;
import game.Bird;
import game.BirdType;
import game.ClapperRailGameState;
import game.Controller;
import game.Material;

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
	
//	@Test
//	public void testCollectingBirds() {
//		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
//		redknotgamestate.collectBird();
//		redknotgamestate.collectBird();
//		assertEquals(redknotgamestate.countBirds(),2);
//	}
	
//	@Test
//	public void testCollectingBirds2() {
//		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
//		redknotgamestate.collectBird();
//		redknotgamestate.collectBird();
//		redknotgamestate.collectBird();
//		redknotgamestate.collectBird();
//		redknotgamestate.collectBird();
//		redknotgamestate.collectBird();
//		assertEquals(redknotgamestate.countBirds(),6);
//	}
	
//	@Test
//	public void testLosingBirds() {
//		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
//		redknotgamestate.collectBird();
//		redknotgamestate.collectBird();
//		redknotgamestate.collectBird();
//		redknotgamestate.lostBird();
//		redknotgamestate.lostBird();
//		assertEquals(redknotgamestate.countBirds(),1);
//	}
	
//	@Test
//	public void testLosingBirds2() {
//		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
//		redknotgamestate.collectBird();
//		redknotgamestate.lostBird();
//		assertEquals(redknotgamestate.countBirds(),0);
//	}
	
	@Test
	public void testLosingBirds3() {
		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
		redknotgamestate.lostBird();
		assertEquals(redknotgamestate.countBirds(),0);
	}
	
	
	//CLAPPERRAIL------------------
	@Test
	public void testCollectingMaterial() {
		ClapperRailGameState CLGS= new ClapperRailGameState(c);
		CLGS.collectMaterial();
		CLGS.collectMaterial();
		assertEquals(CLGS.countMaterials(),2);
	}
	
	@Test
	public void testCollectingMaterial2() {
		ClapperRailGameState CLGS= new ClapperRailGameState(c);
		CLGS.collectMaterial();
		CLGS.collectMaterial();
		CLGS.collectMaterial();
		CLGS.collectMaterial();
		CLGS.collectMaterial();
		CLGS.collectMaterial();
		assertEquals(CLGS.countMaterials(),6);
	}
	
	@Test
	public void testGetCL() {
		ClapperRailGameState clapperrailGS = new ClapperRailGameState(c);
		Bird cr = clapperrailGS.getCR();
		assertEquals(clapperrailGS.getCR(), cr);
	}
	
	@Test
	public void testGetMaterials() {
		ClapperRailGameState clapperrailGS = new ClapperRailGameState(c);
		ArrayList<Material> Materials = clapperrailGS.getMaterials();
		assertEquals(clapperrailGS.getMaterials(), Materials);
	}
	
	
	
	
}
