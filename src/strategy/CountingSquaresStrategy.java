package strategy;

import game.SudokuException;
import board.BoardCountingAccessor;

class CountingSquaresStrategy extends AbstractCountingStrategy {

	public CountingSquaresStrategy(BoardCountingAccessor board, Popper popper) {
		super(board, popper);
	}

	@Override
	void resolveAux(int value) throws SudokuException {
		for (int i=0; i<3; i++) { // iterate through all the squares
			for (int j=0; j<3; j++) {
				if (getBoard().isPendingCountsForSquare(i, j, value)) { // check if any counts = 1
					for (int x=0; x<3; x++) {  // iterate through all cells in that square
						for (int y=0; y<3; y++) {
							if (getCell(i*3 + x, j*3 + y).getPossibilities().contains(value)) { // find cell which can take that value
								setValue(i*3 + x, j*3 + y, value); // sets cell's value and count as complete
							}
						}
					}
				}
			}
		}
	}

}
