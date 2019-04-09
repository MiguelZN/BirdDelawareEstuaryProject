package game.test;
import static org.junit.Assert.*;

import org.junit.Test;

import game.Energy;

public class EnergyTest {

	@Test
	public void testRestoreFull() {
		Energy e = new Energy();
		e.setEnergy(0);
		e.restoreFull();
		assertEquals(e.getEnergy(),100);
	}

	@Test
	public void testRestoreDefault() {
		Energy e = new Energy();
		e.setEnergy(30);
		e.setRestoreRate(40);
		e.restoreDefault();
		assertEquals(e.getEnergy(),70);
		}

	@Test
	public void testDepleteFull() {
		Energy e = new Energy();
		e.setEnergy(30);
		e.depleteFull();
		assertEquals(e.getEnergy(),0);
	}

	@Test
	public void testDepleteDefault() {
		Energy e = new Energy();
		e.setEnergy(70);
		e.setDepletionRate(30);
		e.depleteDefault();
		assertEquals(e.getEnergy(),40);
	}

}
