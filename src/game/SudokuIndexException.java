package game;

@SuppressWarnings("serial")
public class SudokuIndexException extends SudokuException {
	private static final String message = "Invalid index was requested: ";
	public SudokuIndexException(int i) {
		super(message + i);
	}
}
