package strategy;

import game.Numbers;
import game.SudokuException;
import board.BoardCountingAccessor;

/**
 * Does columns only. Given a value number, it goes through all the columns and checks
 * if any have only one position for it.
 */
class CountingColumnsStrategy extends AbstractCountingStrategy {

	CountingColumnsStrategy(BoardCountingAccessor board, Popper popper) {
		super(board, popper);
	}

	@Override
	void resolveAux(int value) throws SudokuException {
		for (int x = 0; x < Numbers.BOARD_LENGTH; x++) {
			if (getBoard().isPendingCountsForColumn(x, value)) {
				for (int y = 0; y < Numbers.BOARD_LENGTH; y++) {
					if (getCell(x, y).getPossibilities().contains(value)) {
						setValue(x, y, value);
					}
				}
			}
		}
	}

}
