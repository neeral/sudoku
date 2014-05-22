package board;

import strategy.Popper;
import game.SudokuIndexException;
import cells.Cell;

public interface BoardAccessor {
	Cell getCell(int i) throws SudokuIndexException;
	Cell getCell(int x, int y) throws SudokuIndexException;
	String printToString();
	boolean isCompletelyFilledIn();
	void eliminateChoice(int i, int value, Popper pop) throws SudokuIndexException;
}
