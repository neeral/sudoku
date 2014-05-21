package strategy;

import cells.Cell;
import game.SudokuException;
import game.UnsetSudokuException;
import board.BoardAccessor;
import board.BoardManager;

/**
 * Does a 3x3 square only. Given a filled-in cell, it goes through all other cells in its
 * 3x3 square and eliminates that choice from them. 
 */
class EliminatingSquaresStrategy extends AbstractStrategy {

	EliminatingSquaresStrategy(BoardAccessor board, Popper popper) {
		super(board, popper);
	}

	@Override
	public void resolveAux(int p) throws SudokuException {
		final int x = BoardManager.getX(p);
		final int y = BoardManager.getY(p);
		int value;
		try {
			value = cell.getValue();
		} catch (UnsetSudokuException e) {
			if (!cell.isFilled())
				return;
			else throw new SudokuException(e);
		}

		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				int index = 27*(y/3) + 9*j + 3*(x/3) + i;
				Cell sqCell = getCell(index);
				if (!sqCell.isFilled()) {
					sqCell.eliminateChoice(value);
				
					if (sqCell.isFilled())
						getPopper().add(index);
				}
			}	
		}
	}

}
