package board;

import static org.junit.Assert.*;
import game.Numbers;

import org.junit.Before;
import org.junit.Test;

public class CountersTest {
	private static final int COLUMN_INDEX = 0;
	private static final int SQ_COLUMN_INDEX = 0;
	private static final int ROW_INDEX = 5;
	private static final int SQ_ROW_INDEX = 1;
	private static final int CELL_INDEX = 45;
	private static final int VALUE = 7;
	private Counters c;
	 
	@Before
	public void setUp() throws Exception {
		c = new Counters();
	}

	@Test
	public final void testCounters() {
		for (int i = 0; i < Numbers.BOARD_LENGTH; i++) {
			for (int value = 1; value < Counters.MAX_CHOICES; value++) {
				assertFalse(c.isPendingCountForColumn(i, value));
				assertFalse(c.isPendingCountForRow(i, value));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int value = 1; value < Counters.MAX_CHOICES; value++) {
					assertFalse(c.isPendingCountForSquare(i, j, value));
				}
			}
		}
	}

	@Test
	public final void testEliminateChoice() {
		for (int i=0; i<7; i++) { // repeat 7 times
			assertFalse(c.eliminateChoice(CELL_INDEX, VALUE));
			assertFalse(c.isPendingCountForColumn(COLUMN_INDEX, VALUE));
			assertFalse(c.isPendingCountForRow(ROW_INDEX, VALUE));
			assertFalse(c.isPendingCountForSquare(SQ_COLUMN_INDEX, SQ_ROW_INDEX, VALUE));
		}
		
		assertTrue(c.eliminateChoice(CELL_INDEX, VALUE));
		assertTrue(c.isPendingCountForRow(ROW_INDEX, VALUE));
		assertTrue(c.isPendingCountForColumn(COLUMN_INDEX, VALUE));
		assertTrue(c.isPendingCountForSquare(SQ_COLUMN_INDEX, SQ_ROW_INDEX, VALUE));
		
		// try to reduce it even after it is in PENDING state
		assertTrue(c.eliminateChoice(CELL_INDEX, VALUE));
		assertTrue(c.isPendingCountForRow(ROW_INDEX, VALUE));
		assertTrue(c.isPendingCountForColumn(COLUMN_INDEX, VALUE));
		assertTrue(c.isPendingCountForSquare(SQ_COLUMN_INDEX, SQ_ROW_INDEX, VALUE));
	}

	@Test
	public final void testSetChoice() {
		c.setChoice(CELL_INDEX, VALUE);
		assertFalse(c.isPendingCountForColumn(COLUMN_INDEX, VALUE));
		assertFalse(c.isPendingCountForRow(ROW_INDEX, VALUE));
		assertFalse(c.isPendingCountForSquare(SQ_COLUMN_INDEX, SQ_ROW_INDEX, VALUE));
	}

	@Test
	public final void testSetChoiceOnOtherValues() {
		c.setChoice(CELL_INDEX, VALUE);
		// spot check one other counter value to see if its count has been decremented
		for (int i=0; i<6; i++) { // repeat 6 times (one less than in testEliminateChoice()
			assertFalse(c.eliminateChoice(CELL_INDEX, 5));
			assertFalse(c.isPendingCountForColumn(COLUMN_INDEX, 5));
			assertFalse(c.isPendingCountForRow(ROW_INDEX, 5));
			assertFalse(c.isPendingCountForSquare(SQ_COLUMN_INDEX, SQ_ROW_INDEX, 5));
		}
		assertTrue(c.eliminateChoice(CELL_INDEX, 5));
		assertTrue(c.isPendingCountForRow(ROW_INDEX, 5));
		assertTrue(c.isPendingCountForColumn(COLUMN_INDEX, 5));
		assertTrue(c.isPendingCountForSquare(SQ_COLUMN_INDEX, SQ_ROW_INDEX, 5));
	}
	
	
	@Test
	public final void testSetComplete() {
		for (int i=0; i<8; i++) // repeat 8 times
			c.eliminateChoice(CELL_INDEX, VALUE);
		
		assertTrue(c.isPendingCountForRow(ROW_INDEX, VALUE));
		assertTrue(c.isPendingCountForColumn(COLUMN_INDEX, VALUE));
		assertTrue(c.isPendingCountForSquare(SQ_COLUMN_INDEX, SQ_ROW_INDEX, VALUE));
		
		c.setComplete(CELL_INDEX, VALUE);
		
		assertFalse(c.isPendingCountForColumn(COLUMN_INDEX, VALUE));
		assertFalse(c.isPendingCountForRow(ROW_INDEX, VALUE));
		assertFalse(c.isPendingCountForSquare(SQ_COLUMN_INDEX, SQ_ROW_INDEX, VALUE));
	}

}
