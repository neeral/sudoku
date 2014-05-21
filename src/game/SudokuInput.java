package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

interface SudokuInput {
	Map<Integer, Integer> read() throws SudokuException;
}

class SudokuInputImpl implements SudokuInput {
	File file = new File("medium-sudoku-qu.csv");

	@Override
	public Map<Integer, Integer> read() throws SudokuException {
		Map<Integer, Integer> startingBoard = new HashMap<Integer, Integer>();
		try (BufferedReader r = new BufferedReader(new FileReader(file))) {
			for (int y=0; y<9; y++) {
				String[] line = r.readLine().split(",");
				for (int x=0; x<9 && x<line.length; x++) {
					if (!line[x].isEmpty()) {
						startingBoard.put(9*y + x, Integer.parseInt(line[x]));
					}
				}
			}
		} catch (IOException e) {
			throw new SudokuException("Cannot read file " + file.getAbsolutePath() +" with starting Sudoku.", e);
		}
		return startingBoard;
	}

}