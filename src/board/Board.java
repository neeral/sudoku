package board;

import java.util.Set;

import cells.Cell;
import cells.CellFactory;

import game.Numbers;
import game.SudokuIndexException;
import game.UnsetSudokuException;

class Board {
	
	private Cell[] cells = new Cell[Numbers.BOARD_SIZE];
	boolean checkColumn(int columnNo) {
		final Set<Integer> choices = Numbers.getChoices();
		try {
			for (int i=0; i<9; i++)
				if (!choices.remove(cells[columnNo + 9*i].getValue()))
					return false;	// means number was already removed
			return choices.isEmpty();
		} catch (UnsetSudokuException e) {
			return false;
		}
	}
	boolean checkRow(int rowNo) {
		final Set<Integer> choices = Numbers.getChoices();
		try {
			for (int i=0; i<9; i++)
				if (!choices.remove(cells[9*rowNo + i].getValue()))
					return false;	// means number was already removed
			return choices.isEmpty();
		} catch (UnsetSudokuException e) {
			return false;
		}
	}
	boolean checkSquare(int x, int y) {
		final Set<Integer> choices = Numbers.getChoices();
		try {
			for (int i=0; i<3; i++)
				for (int j=0; j<3; j++)
					if (!choices.remove(cells[27*y + 9*j + 3*x + i].getValue()))
						return false;
			return choices.isEmpty();
		} catch (UnsetSudokuException e) {
			return false;
		}
	}
	public Cell getCell(int i) throws SudokuIndexException {
		if (validIndex(i))
			return cells[i];
		else
			throw new SudokuIndexException(i);
	}
	void setCell(int i, Cell cell) throws SudokuIndexException {
		if (!validIndex(i) || cells[i].isFilled())
			throw new SudokuIndexException(i);
		else
			cells[i] = cell;
	}
	private boolean validIndex(int i) {
		return i>=0 && i<Numbers.BOARD_SIZE;
	}
	void setupEmptyBoard(CellFactory cellFactory) { // initialisation
		for (int i=0; i<Numbers.BOARD_SIZE; i++)
			cells[i] = cellFactory.createEmptyCell();
	}
}
