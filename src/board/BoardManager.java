package board;

import game.SudokuException;
import game.SudokuIndexException;
import game.UnsetSudokuException;

import java.util.Map;

import cells.Cell;
import cells.CellFactory;

public class BoardManager implements BoardAccessor {
	public static final int BOARD_SIZE = 81;
	private Board board;
	private CellFactory cellFactory = new CellFactory();
	
	public boolean isCompletelyFilledIn() {
		try {
			for (int i = 0; i < BOARD_SIZE; i++)
				if (!board.getCell(i).isFilled())
					return false;
		} catch (SudokuIndexException e) {
			// empty as this should never happen
		}
		return true;
	}

	public boolean checkConstraints() {
		for (int i=0; i<9; i++) 
			if (!board.checkColumn(i) || !board.checkRow(i) || !board.checkSquare(i / 3, i % 3))
				return false;
		return true;
	}
	
	public Cell getCell(int x, int y) throws SudokuIndexException {
		return board.getCell(9*y + x);
	}
	
	public Cell getCell(int i) throws SudokuIndexException {
		return board.getCell(i);
	}
	
	public void setUpBoard(Map<Integer, Integer> presetBoard) throws SudokuException {
		board = new Board();
		board.setupEmptyBoard(cellFactory);
		for (int i : presetBoard.keySet())
			if (presetBoard.containsKey(i)) {
				try {
					board.setCell(i, cellFactory.createFilledInCell(presetBoard.get(i)));
				} catch (SudokuIndexException e) {
					throw new SudokuException("Unable to prepare board. Bad entry was [" + i + "," + presetBoard.get(i) + "]", e);
				}
			}
			
	}
	
	public static int getX(int i) {
		return i % 9;
	}
	
	public static int getY(int i) {
		return i / 9;
	}
	
	public static int getI(int x, int y) {
		return 9*y + x;
	}

	@Override
	public String printToString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < BOARD_SIZE; i++) {
			Cell cell;
			try {
				cell = getCell(i);
				if (cell.isFilled()) sb.append(cell.getValue());
				sb.append(",");
				if (i % 9 == 8) sb.append ("\n");
			} catch (SudokuIndexException | UnsetSudokuException e) {
				System.err.println("There was an error while trying to print the Sudoku board.");
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
