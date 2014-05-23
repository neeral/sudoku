package cells;

import java.util.Collections;
import java.util.Set;

class GivenCell implements Cell{
	private int value;

	GivenCell(int value) {
		if (value < 1 || value > 9) throw new RuntimeException("Invalid preset");
		this.value = value;
	}

	@Override
	public boolean isFilled() {
		return true;
	}

	@Override
	public Set<Integer> getPossibilities() {
		return Collections.emptySet();
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public boolean eliminateChoice(int i) {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o instanceof GivenCell) {
			GivenCell other = (GivenCell) o;
			return other.value == value;
		} else
			return super.equals(o);
	}

	@Override
	public int hashCode() {
		return 31*value + super.hashCode();
	}

	@Override
	public String toString() {
		return "g[" + value + "]";
	}
	
}
