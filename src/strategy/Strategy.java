package strategy;

import game.SudokuException;

public interface Strategy {
	/**
	 * Main Method.
	 * @param i position of cell
	 * @throws SudokuException 
	 */
	void resolve(int i) throws SudokuException;
}
