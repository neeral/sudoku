package cells;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellFactoryTest {
	private CellFactory cellFactory;
	
	@Before
	public void setUp() {
		cellFactory = new CellFactory();
	}

	@Test
	public final void testCreateFilledInCell() {
		final Cell actual = cellFactory.createFilledInCell(4);
		final Cell expected = new GivenCell(4);
		assertTrue(expected.equals(actual));
	}

	@Test
	public final void testCreateEmptyCell() {
		final Cell actual = cellFactory.createEmptyCell();
		final Cell expected = new Space();
		assertTrue(expected.equals(actual));
	}

}
