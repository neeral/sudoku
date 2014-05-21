package board;

import static org.junit.Assert.assertTrue;
import game.SudokuException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class BoardManagerTest {
	private BoardManager boardManager;
	private Map<Integer, Integer> map;

	@Before
	public void setUp() throws SudokuException {
		boardManager = new BoardManager();
		map = new HashMap<Integer, Integer>();
	}

	@Test
	public final void testIsCompletelyFilledInIfEmpty() throws SudokuException {
		boardManager.setUpBoard(Collections.<Integer, Integer> emptyMap());
		assertTrue(!boardManager.isCompletelyFilledIn());
	}
	
	@Test
	public final void testIsCompletelyFilledInTrue() throws SudokuException {
		map.put(0,1);
		map.put(1,2);
		map.put(2,3);
		map.put(3,4);
		map.put(4,5);
		map.put(5,6);
		map.put(6,7);
		map.put(7,8);
		map.put(8,9);
		map.put(9,1);
		map.put(10,2);
		map.put(11,3);
		map.put(12,4);
		map.put(13,5);
		map.put(14,6);
		map.put(15,7);
		map.put(16,8);
		map.put(17,9);
		map.put(18,1);
		map.put(19,2);
		map.put(20,3);
		map.put(21,4);
		map.put(22,5);
		map.put(23,6);
		map.put(24,7);
		map.put(25,8);
		map.put(26,9);
		map.put(27,1);
		map.put(28,2);
		map.put(29,3);
		map.put(30,4);
		map.put(31,5);
		map.put(32,6);
		map.put(33,7);
		map.put(34,8);
		map.put(35,9);
		map.put(36,1);
		map.put(37,2);
		map.put(38,3);
		map.put(39,4);
		map.put(40,5);
		map.put(41,6);
		map.put(42,7);
		map.put(43,8);
		map.put(44,9);
		map.put(45,1);
		map.put(46,2);
		map.put(47,3);
		map.put(48,4);
		map.put(49,5);
		map.put(50,6);
		map.put(51,7);
		map.put(52,8);
		map.put(53,9);
		map.put(54,1);
		map.put(55,2);
		map.put(56,3);
		map.put(57,4);
		map.put(58,5);
		map.put(59,6);
		map.put(60,7);
		map.put(61,8);
		map.put(62,9);
		map.put(63,1);
		map.put(64,2);
		map.put(65,3);
		map.put(66,4);
		map.put(67,5);
		map.put(68,6);
		map.put(69,7);
		map.put(70,8);
		map.put(71,9);
		map.put(72,1);
		map.put(73,2);
		map.put(74,3);
		map.put(75,4);
		map.put(76,5);
		map.put(77,6);
		map.put(78,7);
		map.put(79,8);
		map.put(80,9);
		boardManager.setUpBoard(map);
		assertTrue(boardManager.isCompletelyFilledIn());
	}	
	
	@Test
	public final void testIsCompletelyFilledInWithOneRemaining() throws SudokuException {
		map.put(0,1);
		map.put(1,2);
		map.put(2,3);
		map.put(3,4);
		map.put(4,5);
		map.put(5,6);
		map.put(6,7);
		map.put(7,8);
		map.put(8,9);
		map.put(9,1);
		map.put(10,2);
		map.put(11,3);
		map.put(12,4);
		map.put(13,5);
		map.put(14,6);
		map.put(15,7);
		map.put(16,8);
		map.put(17,9);
		map.put(18,1);
		map.put(19,2);
		map.put(20,3);
		map.put(21,4);
		map.put(22,5);
		map.put(23,6);
		map.put(24,7);
		map.put(25,8);
		map.put(26,9);
		map.put(27,1);
		map.put(28,2);
		map.put(29,3);
		map.put(30,4);
		map.put(31,5);
		map.put(32,6);
		map.put(33,7);
		map.put(34,8);
		map.put(35,9);
		map.put(36,1);
		map.put(37,2);
		map.put(38,3);
		map.put(39,4);
		map.put(40,5);
		map.put(41,6);
		map.put(42,7);
		map.put(43,8);
		map.put(44,9);
		map.put(45,1);
		map.put(46,2);
		map.put(47,3);
		map.put(48,4);
		map.put(49,5);
		map.put(50,6);
		map.put(51,7);
		map.put(52,8);
		map.put(53,9);
		map.put(54,1);
		map.put(55,2);
		map.put(56,3);
		map.put(57,4);
		map.put(58,5);
		map.put(59,6);
		map.put(60,7);
		map.put(61,8);
		map.put(62,9);
		map.put(63,1);
		map.put(64,2);
		map.put(65,3);
		map.put(66,4);
		map.put(67,5);
		map.put(68,6);
		map.put(69,7);
		map.put(70,8);
		map.put(71,9);
		map.put(72,1);
		map.put(73,2);
		map.put(74,3);
		map.put(75,4);
		map.put(76,5);
		map.put(77,6);
		map.put(78,7);
		map.put(79,8);
		boardManager.setUpBoard(map);
		assertTrue(!boardManager.isCompletelyFilledIn());
	}	

	@Test
	public final void testCheckConstraintsTrue() throws SudokuException {
		map.put(0, 6);	map.put(1, 5);	map.put(2, 2);	map.put(3, 9);	map.put(4, 3);	map.put(5, 4);	map.put(6, 8);	map.put(7, 1);	map.put(8, 7);
		map.put(9, 8);	map.put(10, 9);	map.put(11, 7);	map.put(12, 1);	map.put(13, 5);	map.put(14, 6);	map.put(15, 2);	map.put(16, 4);	map.put(17, 3);
		map.put(18, 4);	map.put(19, 3);	map.put(20, 1);	map.put(21, 7);	map.put(22, 2);	map.put(23, 8);	map.put(24, 5);	map.put(25, 9);	map.put(26, 6);
		map.put(27, 1);	map.put(28, 7);	map.put(29, 5);	map.put(30, 6);	map.put(31, 9);	map.put(32, 3);	map.put(33, 4);	map.put(34, 2);	map.put(35, 8);
		map.put(36, 9);	map.put(37, 8);	map.put(38, 4);	map.put(39, 2);	map.put(40, 7);	map.put(41, 5);	map.put(42, 6);	map.put(43, 3);	map.put(44, 1);
		map.put(45, 3);	map.put(46, 2);	map.put(47, 6);	map.put(48, 4);	map.put(49, 8);	map.put(50, 1);	map.put(51, 9);	map.put(52, 7);	map.put(53, 5);
		map.put(54, 2);	map.put(55, 4);	map.put(56, 8);	map.put(57, 3);	map.put(58, 6);	map.put(59, 7);	map.put(60, 1);	map.put(61, 5);	map.put(62, 9);
		map.put(63, 5);	map.put(64, 1);	map.put(65, 3);	map.put(66, 8);	map.put(67, 4);	map.put(68, 9);	map.put(69, 7);	map.put(70, 6);	map.put(71, 2);
		map.put(72, 7);	map.put(73, 6);	map.put(74, 9);	map.put(75, 5);	map.put(76, 1);	map.put(77, 2);	map.put(78, 3);	map.put(79, 8);	map.put(80, 4);
		boardManager.setUpBoard(map);
		assertTrue(boardManager.checkConstraints());
	}
	
	@Test
	public final void testCheckConstraintsFalse() throws SudokuException {
		map.put(0,1);
		map.put(1,2);
		map.put(2,3);
		map.put(3,4);
		map.put(4,5);
		map.put(5,6);
		map.put(6,7);
		map.put(7,8);
		map.put(8,9);
		map.put(9,1);
		map.put(10,2);
		map.put(11,3);
		map.put(12,4);
		map.put(13,5);
		map.put(14,6);
		map.put(15,7);
		map.put(16,8);
		map.put(17,9);
		map.put(18,1);
		map.put(19,2);
		map.put(20,3);
		map.put(21,4);
		map.put(22,5);
		map.put(23,6);
		map.put(24,7);
		map.put(25,8);
		map.put(26,9);
		map.put(27,1);
		map.put(28,2);
		map.put(29,3);
		map.put(30,4);
		map.put(31,5);
		map.put(32,6);
		map.put(33,7);
		map.put(34,8);
		map.put(35,9);
		map.put(36,1);
		map.put(37,2);
		map.put(38,3);
		map.put(39,4);
		map.put(40,5);
		map.put(41,6);
		map.put(42,7);
		map.put(43,8);
		map.put(44,9);
		map.put(45,1);
		map.put(46,2);
		map.put(47,3);
		map.put(48,4);
		map.put(49,5);
		map.put(50,6);
		map.put(51,7);
		map.put(52,8);
		map.put(53,9);
		map.put(54,1);
		map.put(55,2);
		map.put(56,3);
		map.put(57,4);
		map.put(58,5);
		map.put(59,6);
		map.put(60,7);
		map.put(61,8);
		map.put(62,9);
		map.put(63,1);
		map.put(64,2);
		map.put(65,3);
		map.put(66,4);
		map.put(67,5);
		map.put(68,6);
		map.put(69,7);
		map.put(70,8);
		map.put(71,9);
		map.put(72,1);
		map.put(73,2);
		map.put(74,3);
		map.put(75,4);
		map.put(76,5);
		map.put(77,6);
		map.put(78,7);
		map.put(79,8);
		map.put(80,9);
		boardManager.setUpBoard(map);
		assertTrue(!boardManager.checkConstraints());
	}

	@Test
	public final void testGetCellIntInt() throws SudokuException {
		map.put(33,1);
		boardManager.setUpBoard(map);
		assertTrue(boardManager.getCell(6, 3).getValue() == 1);
		assertTrue(!boardManager.getCell(3, 6).isFilled());
		assertTrue(!boardManager.getCell(5, 3).isFilled());
		assertTrue(!boardManager.getCell(7, 5).isFilled());
	}

	@Test
	public final void testGetCellInt() throws SudokuException {
		map.put(40,1);
		boardManager.setUpBoard(map);
		assertTrue(boardManager.getCell(40).getValue() == 1);
		assertTrue(!boardManager.getCell(1).isFilled());
	}

	@Test
	public final void testSetUpBoard() throws SudokuException {
		boardManager.setUpBoard(Collections.<Integer, Integer> emptyMap());
		assertTrue(boardManager.getCell(56) != null);
	}

}
