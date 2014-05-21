package cells;

public class CellFactory {
	public Cell createFilledInCell(int value) {
		return new GivenCell(value);
	}
	public Cell createEmptyCell() {
		return new Space();
	}
}
