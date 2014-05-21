package cells;

import static org.junit.Assert.*;

import java.util.Set;

import game.Numbers;
import game.SudokuException;
import game.UnsetSudokuException;

import org.junit.Before;
import org.junit.Test;

public class SpaceTest {
	private Cell c;

	@Before
	public void setUp() throws Exception {
		c = new Space();
	}

	@Test
	public final void testFilled() {
		assertTrue(!c.isFilled());
	}

	@Test
	public final void testGetPossibilities() {
		assertTrue(c.getPossibilities().equals(Numbers.getChoices()));
	}

	@Test(expected=UnsetSudokuException.class)
	public final void testGetValue() throws SudokuException {
		c.getValue();
	}

	@Test
	public final void testEliminateChoice0() {
		c.eliminateChoice(0);
		assertTrue(c.getPossibilities().equals(Numbers.getChoices()));
	}

	@Test
	public final void testEliminateChoice1() {
		c.eliminateChoice(1);
		Set<Integer> expectedChoices = Numbers.getChoices();
		expectedChoices.remove(1);
		assertTrue(c.getPossibilities().equals(expectedChoices));
	}
	
	@Test
	public final void testDetermination() throws SudokuException {
		c.eliminateChoice(1);
		c.eliminateChoice(2);
		c.eliminateChoice(3);
		c.eliminateChoice(4);
		c.eliminateChoice(5);
		c.eliminateChoice(6);
		c.eliminateChoice(7);
		c.eliminateChoice(8);
		Set<Integer> expectedChoices = Numbers.getChoices();
		expectedChoices.remove(1);
		expectedChoices.remove(2);
		expectedChoices.remove(3);
		expectedChoices.remove(4);
		expectedChoices.remove(5);
		expectedChoices.remove(6);
		expectedChoices.remove(7);
		expectedChoices.remove(8);
		assertTrue(c.getPossibilities().equals(expectedChoices));
		assertTrue(c.isFilled());
		assertTrue(c.getValue() == 9);
	}
	
	@Test(expected=RuntimeException.class)
	public final void testElimination() {
		c.eliminateChoice(1);
		c.eliminateChoice(2);
		c.eliminateChoice(3);
		c.eliminateChoice(4);
		c.eliminateChoice(5);
		c.eliminateChoice(6);
		c.eliminateChoice(7);
		c.eliminateChoice(8);
		c.eliminateChoice(9);
	}

}

