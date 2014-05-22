package strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.Numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import board.BoardManager;
import cells.Cell;

public class CountingSquarestrategyTest {
	private BoardManager boardManager;
	private Strategy strategy;
	private static final int VALUE = 7;
	private Map<Integer, Integer> startingBoard;
	
	@Before
	public void setUp() throws Exception {
		startingBoard = new HashMap<Integer, Integer>();
		startingBoard.put(35, 2);
		boardManager = new BoardManager();
		boardManager.setUpBoard(startingBoard);
		strategy = new CountingSquaresStrategy(boardManager, PopperStub.getSingleton());
	}

	@Test
	public final void test() throws Exception {
		Integer[] square = { 34, 42, 43, 44, 51, 52, 53 };
		for (int i : square)
			boardManager.eliminateChoice(i, VALUE, PopperStub.getSingleton());
		
		strategy.resolve(VALUE);
		
		for (int i = 0; i < Numbers.BOARD_SIZE; i++) {
			final Cell cell = boardManager.getCell(i);
			if (i == 33) { // check cell itself
				assertTrue(cell.isFilled());
				assertEquals(VALUE, cell.getValue());
			} else if (startingBoard.keySet().contains(i)) {
				assertTrue(cell.isFilled());
				assertEquals(startingBoard.get(i), Integer.valueOf(cell.getValue()));
			} else if (Arrays.asList(square).contains(i)) {
				assertFalse("Cell " + i + " has value " , cell.isFilled());
				assertEquals("Cell " + i, 8, cell.getPossibilities().size());			
			} else {
				assertFalse("Cell " + i + " has value " , cell.isFilled());
				assertEquals("Cell " + i, 9, cell.getPossibilities().size());
			}
		}
		
	}
}
