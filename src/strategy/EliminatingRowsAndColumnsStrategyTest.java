package strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import game.Numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import board.BoardManager;

public class EliminatingRowsAndColumnsStrategyTest {
	private BoardManager boardManager;
	private Strategy strategy;
	private static final int VALUE = 7;

	@Before
	public void setUp() throws Exception {
		Map<Integer, Integer> startingBoard = new HashMap<Integer, Integer>();
		startingBoard.put(39, VALUE);
		boardManager = new BoardManager();
		boardManager.setUpBoard(startingBoard);

		strategy = new EliminatingRowsAndColumnsStrategy(boardManager, PopperStub.getSingleton());
	}

	@Test
	public final void test() throws Exception {
		strategy.resolve(39);

		Integer[] cols = { 3, 12, 21, 30, 48, 57, 66, 75 };
		Integer[] rows = { 36, 37, 38, 40, 41, 42, 43, 44 };

		for (int i = 0; i < Numbers.BOARD_SIZE; i++) {
			final Set<Integer> possibilities = boardManager.getCell(i).getPossibilities();
			if (i == 39) { // check cell itself
				assertTrue(boardManager.getCell(39).isFilled());
				assertFalse("Filled in cell should not have the value left", possibilities.contains(VALUE));
			} else if (Arrays.asList(cols).contains(i)
					|| Arrays.asList(rows).contains(i)) {
				assertFalse("At cell " + i, possibilities.contains(VALUE));
				assertEquals("At cell " + i, 8, possibilities.size());
			} else {
				assertTrue("At cell " + i, possibilities.contains(VALUE));
				assertEquals("No numbers should have been eliminated for cell "	+ i, 9, possibilities.size());
			}
		}
	}

}
