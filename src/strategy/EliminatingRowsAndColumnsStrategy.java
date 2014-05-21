package strategy;

import game.SudokuException;
import game.UnsetSudokuException;
import board.BoardAccessor;
import board.BoardManager;
import cells.Cell;
/**
 * Does rows and columns only. Given a filled-in cell, it goes through all other cells in its
 * rows and columns and eliminates that choice from them. 
 */
class EliminatingRowsAndColumnsStrategy extends AbstractStrategy {

	EliminatingRowsAndColumnsStrategy(BoardAccessor board, Popper popper) {
		super(board, popper);
	}

	@Override
	public void resolveAux(int i) throws SudokuException {
		final int x = BoardManager.getX(i);
		final int y = BoardManager.getY(i);
		int value;
		try {
			value = cell.getValue();
		} catch (UnsetSudokuException e) {
			if (!cell.isFilled())
				return;
			else throw new SudokuException(e);
		}

		for (int k=0; k<9; k++) {
			Cell xCell = getCell(x, k);
			if (!xCell.isFilled()) {
				xCell.eliminateChoice(value);
				if (xCell.isFilled())
					getPopper().add(BoardManager.getI(x, k));
			}
			Cell yCell = getCell(k, y);
			if (!yCell.isFilled()) {
				yCell.eliminateChoice(value);
				if (yCell.isFilled())
					getPopper().add(BoardManager.getI(k, y));
			}
		}
	}

}
