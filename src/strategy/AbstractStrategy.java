package strategy;

import cells.Cell;
import game.SudokuException;
import game.SudokuIndexException;
import board.BoardAccessor;

abstract class AbstractStrategy implements Strategy {
	private BoardAccessor board;
	private Popper popper;
	protected Cell cell;

	/**
	 * Constructor
	 * @param board access to the cells on the board
	 * @param popper used to notify any cells that have been resolved 
	 */
	AbstractStrategy(BoardAccessor board, Popper popper) {
		this.board = board;
		this.popper = popper;
	}
	
	Cell getCell(int x, int y) throws SudokuIndexException {
		return board.getCell(x, y);
	}
	
	Cell getCell(int i) throws SudokuIndexException {
		return board.getCell(i);
	}
	
	Popper getPopper() {
		return popper;
	}

	@Override
	public void resolve(int i) throws SudokuException {
		cell = getCell(i);
		resolveAux(i);
	}
	
	abstract void resolveAux(int i) throws SudokuException;
	
	// there are two types of strategise: deterministic (safe) and predictive (require backtracking)
}
