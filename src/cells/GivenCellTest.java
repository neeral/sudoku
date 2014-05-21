package cells;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GivenCellTest {
	private GivenCell c;
	@Before
	public void setUp() throws Exception {
		c = new GivenCell(1);
	}

	@Test(expected=RuntimeException.class)
	public final void testGivenCellSetTo0() {
		c = new GivenCell(0);
	}
	
	@Test(expected=RuntimeException.class)
	public final void testGivenCellSetTo10() {
		c = new GivenCell(10);
	}
	
	@Test(expected=RuntimeException.class)
	public final void testGivenCellSetTo24() {
		c = new GivenCell(24);
	}

	@Test
	public final void testFilled() {
		assertTrue(c.isFilled());
	}

	@Test
	public final void testGetPossibilities() {
		assertTrue(c.getPossibilities().isEmpty());
	}

	@Test
	public final void testGetValue() {
		assertTrue(c.getValue() == 1);
	}

	@Test
	public final void testEliminateChoice() {
		c.eliminateChoice(1);
		assertTrue(c.getValue() == 1);
	}

}
