package strategy;

import game.Numbers;
import game.SudokuException;
import board.BoardCountingAccessor;

/**
 * Does rows only. Given a value number, it goes through all the rows and checks
 * if any have only one position for it.
 */
class CountingRowsStrategy extends AbstractCountingStrategy {

	CountingRowsStrategy(BoardCountingAccessor board, Popper popper) {
		super(board, popper);
	}

	@Override
	void resolveAux(int value) throws SudokuException {
		for (int y = 0; y < Numbers.BOARD_LENGTH; y++) { // iterate through all the rows
			if (getBoard().isPendingCountsForRow(y, value)) { // check if any counts = 1
				for (int x = 0; x < Numbers.BOARD_LENGTH; x++) { // iterate through cells in that row
					if (getCell(x, y).getPossibilities().contains(value)) { // find cell which can take that value
						setValue(x, y, value); // sets cell's value and count as complete
					}
				}
			}
		}
	}

}
