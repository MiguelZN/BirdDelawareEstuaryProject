package game.test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Energy;
import game.RedKnotGameState;
import game.ClapperRailGameState;
import game.Controller;

public class BirdModelsTest {
	private Controller c = new Controller();
	
	@Test
	public void testCollectingBirds() {
		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
		redknotgamestate.collectBird();
		redknotgamestate.collectBird();
		assertEquals(redknotgamestate.countBirds(),2);
	}
	
	@Test
	public void testCollectingBirds2() {
		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
		redknotgamestate.collectBird();
		redknotgamestate.collectBird();
		redknotgamestate.collectBird();
		redknotgamestate.collectBird();
		redknotgamestate.collectBird();
		redknotgamestate.collectBird();
		assertEquals(redknotgamestate.countBirds(),6);
	}
	
	@Test
	public void testLosingBirds() {
		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
		redknotgamestate.collectBird();
		redknotgamestate.collectBird();
		redknotgamestate.collectBird();
		redknotgamestate.lostBird();
		redknotgamestate.lostBird();
		assertEquals(redknotgamestate.countBirds(),1);
	}
	
	@Test
	public void testLosingBirds2() {
		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
		redknotgamestate.collectBird();
		redknotgamestate.lostBird();
		assertEquals(redknotgamestate.countBirds(),0);
	}
	
	@Test
	public void testLosingBirds3() {
		RedKnotGameState redknotgamestate = new RedKnotGameState(c);
		redknotgamestate.lostBird();
		assertEquals(redknotgamestate.countBirds(),0);
	}
	
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
	
	
}
