package board;

import game.SudokuIndexException;
import strategy.Popper;

public interface BoardCountingAccessor extends BoardAccessor {
	boolean isPendingCountsForRow(int i, int value);
	void setChoice(int i, int value, Popper pop) throws SudokuIndexException;
	boolean isPendingCountsForColumn(int j, int value);
	boolean isPendingCountsForSquare(int i, int j, int value);	
}
