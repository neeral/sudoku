package strategy;

import game.SudokuIndexException;
import board.BoardCountingAccessor;
import board.BoardManager;

abstract class AbstractCountingStrategy extends AbstractStrategy {

	private BoardCountingAccessor board;

	AbstractCountingStrategy(BoardCountingAccessor board, Popper popper) {
		super(board, popper);
		this.board = board;
	}

	BoardCountingAccessor getBoard() {
		return board;
	}
	
	void setValue(int x, int y, int value) throws SudokuIndexException {
		board.setChoice(BoardManager.getI(x, y), value, getPopper());
	}

}
