package board;

import static org.junit.Assert.*;

import game.SudokuException;
import game.SudokuIndexException;

import org.junit.Before;
import org.junit.Test;

import cells.Cell;
import cells.CellFactory;

public class BoardTest {
	private Board board;
	private Cell cell6;
	private CellFactory cellFactory;

	@Before
	public void setUp() {
		board = new Board();
		cellFactory = new CellFactory();
		board.setupEmptyBoard(cellFactory);
		cell6 = cellFactory.createFilledInCell(6);
	}
	
	@Test
	public final void testCheckRowTrue() throws Exception {
		board.setCell(0, cell6);
		board.setCell(1, cellFactory.createFilledInCell(1));
		board.setCell(2, cellFactory.createFilledInCell(2));
		board.setCell(3, cellFactory.createFilledInCell(3));
		board.setCell(4, cellFactory.createFilledInCell(4));
		board.setCell(5, cellFactory.createFilledInCell(5));
		board.setCell(6, cellFactory.createFilledInCell(7));
		board.setCell(7, cellFactory.createFilledInCell(8));
		board.setCell(8, cellFactory.createFilledInCell(9));
		assertTrue(board.checkRow(0));
	}

	@Test
	public final void testCheckRowFalse() throws Exception {
		board.setCell(0, cell6);
		board.setCell(1, cellFactory.createFilledInCell(2));
		board.setCell(2, cellFactory.createFilledInCell(2));
		board.setCell(3, cellFactory.createFilledInCell(3));
		board.setCell(4, cellFactory.createFilledInCell(4));
		board.setCell(5, cellFactory.createFilledInCell(5));
		board.setCell(6, cellFactory.createFilledInCell(7));
		board.setCell(7, cellFactory.createFilledInCell(8));
		board.setCell(8, cellFactory.createFilledInCell(9));
		assertTrue(!board.checkRow(0));
	}
	
	public final void testCheckRowFalseEmpty() throws Exception {
		board.setCell(0, cell6);
		board.setCell(2, cellFactory.createFilledInCell(2));
		board.setCell(3, cellFactory.createFilledInCell(3));
		board.setCell(4, cellFactory.createFilledInCell(4));
		board.setCell(5, cellFactory.createFilledInCell(5));
		board.setCell(6, cellFactory.createFilledInCell(7));
		board.setCell(7, cellFactory.createFilledInCell(8));
		board.setCell(8, cellFactory.createFilledInCell(9));
		assertFalse(board.checkRow(0));
	}

	@Test
	public final void testCheckColumnTrue() throws Exception {
		board.setCell(0, cell6);
		board.setCell(9, cellFactory.createFilledInCell(1));
		board.setCell(18, cellFactory.createFilledInCell(2));
		board.setCell(27, cellFactory.createFilledInCell(3));
		board.setCell(36, cellFactory.createFilledInCell(4));
		board.setCell(45, cellFactory.createFilledInCell(5));
		board.setCell(54, cellFactory.createFilledInCell(7));
		board.setCell(63, cellFactory.createFilledInCell(8));
		board.setCell(72, cellFactory.createFilledInCell(9));
		assertTrue(board.checkColumn(0));
	}

	@Test
	public final void testCheckColumnFalse() throws Exception {
		board.setCell(0, cell6);
		board.setCell(9, cell6);
		board.setCell(18, cellFactory.createFilledInCell(2));
		board.setCell(27, cellFactory.createFilledInCell(3));
		board.setCell(36, cellFactory.createFilledInCell(4));
		board.setCell(45, cellFactory.createFilledInCell(5));
		board.setCell(54, cellFactory.createFilledInCell(7));
		board.setCell(63, cellFactory.createFilledInCell(8));
		board.setCell(72, cellFactory.createFilledInCell(9));
		assertTrue(!board.checkColumn(0));
	}
	
	public final void testCheckColumnFalseEmpty() throws Exception {
		board.setCell(0, cell6);
		board.setCell(18, cellFactory.createFilledInCell(2));
		board.setCell(27, cellFactory.createFilledInCell(3));
		board.setCell(36, cellFactory.createFilledInCell(4));
		board.setCell(45, cellFactory.createFilledInCell(5));
		board.setCell(54, cellFactory.createFilledInCell(7));
		board.setCell(63, cellFactory.createFilledInCell(8));
		board.setCell(72, cellFactory.createFilledInCell(9));
		assertFalse(board.checkColumn(0));
	}

	@Test
	public final void testCheckSquareTrue() throws Exception {
		board.setCell(33, cell6);
		board.setCell(34, cellFactory.createFilledInCell(1));
		board.setCell(35, cellFactory.createFilledInCell(2));
		board.setCell(42, cellFactory.createFilledInCell(3));
		board.setCell(43, cellFactory.createFilledInCell(4));
		board.setCell(44, cellFactory.createFilledInCell(5));
		board.setCell(51, cellFactory.createFilledInCell(7));
		board.setCell(52, cellFactory.createFilledInCell(8));
		board.setCell(53, cellFactory.createFilledInCell(9));
		assertTrue(board.checkSquare(2,1));
	}

	@Test
	public final void testCheckSquareFalse() throws Exception {
		board.setCell(33, cell6);
		board.setCell(34, cell6);
		board.setCell(35, cellFactory.createFilledInCell(2));
		board.setCell(42, cellFactory.createFilledInCell(3));
		board.setCell(43, cellFactory.createFilledInCell(4));
		board.setCell(44, cellFactory.createFilledInCell(5));
		board.setCell(51, cellFactory.createFilledInCell(7));
		board.setCell(52, cellFactory.createFilledInCell(8));
		board.setCell(53, cellFactory.createFilledInCell(9));
		assertTrue(!board.checkSquare(2,1));
	}
	
	public final void testCheckSquareFalseEmpty() throws Exception {
		board.setCell(33, cell6);
		board.setCell(35, cellFactory.createFilledInCell(2));
		board.setCell(42, cellFactory.createFilledInCell(3));
		board.setCell(43, cellFactory.createFilledInCell(4));
		board.setCell(44, cellFactory.createFilledInCell(5));
		board.setCell(51, cellFactory.createFilledInCell(7));
		board.setCell(52, cellFactory.createFilledInCell(8));
		board.setCell(53, cellFactory.createFilledInCell(9));
		assertTrue(!board.checkSquare(2,1));
	}
	
	@Test
	public final void testGetCellInt() throws Exception {
		board.setCell(4, cell6);
		assertTrue(board.getCell(4).getValue() == 6);
	}
	
	@Test
	public final void testGetCellEmpty() throws Exception {
		assertTrue(!board.getCell(3).isFilled());
	}
	
	@Test(expected = SudokuIndexException.class)
	public final void testGetCellIndexNegative() throws Exception {
		board.getCell(-1);
	}
	
	@Test(expected = SudokuIndexException.class)
	public final void testGetCellIndexOver() throws Exception {
		board.getCell(81);
	}

	@Test(expected = SudokuException.class)
	public final void testSetTwice() throws SudokuIndexException {
		board.setCell(4, cell6);
		board.setCell(4, cell6);
	}
}
