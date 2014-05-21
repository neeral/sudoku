package board;

import game.SudokuIndexException;
import cells.Cell;

public interface BoardAccessor {
	Cell getCell(int i) throws SudokuIndexException;
	Cell getCell(int x, int y) throws SudokuIndexException;
	String printToString();
	public boolean isCompletelyFilledIn();
}
