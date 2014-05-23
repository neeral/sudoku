package cells;

import java.util.Set;

import game.Numbers;
import game.UnsetSudokuException;

class Space implements Cell {
	Set<Integer> choices = Numbers.getChoices();

	public boolean isFilled() {
		return choices.size() == 1;
	}

	@Override
	public Set<Integer> getPossibilities() {
		return choices;
	}

	@Override
	public int getValue() throws UnsetSudokuException {
		if (!isFilled()) throw new UnsetSudokuException();
		return choices.toArray(new Integer[1])[0];		
	}

	@Override
	public boolean eliminateChoice(int i) {
		final boolean b = choices.remove(i);
		if (choices.isEmpty()) throw new RuntimeException("Space is empty.");
		return b;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o instanceof Space) {
			Space other = (Space) o;
			return other.choices.equals(choices);
		} else
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return 31*choices.hashCode() + super.hashCode();
	}

	@Override
	public String toString() {
		return "s" + choices;
	}

}
