package cells;

import game.UnsetSudokuException;

import java.util.Set;

public interface Cell {
	boolean isFilled();
	Set<Integer> getPossibilities();
	int getValue() throws UnsetSudokuException;
	void eliminateChoice(int i);
}
