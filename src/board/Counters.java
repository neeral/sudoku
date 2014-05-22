package board;

import java.util.Arrays;

public class Counters {
	// arrays to hold the count for how many cells in that row/column/square can take that value
	// first index = value
	// second index = row/column/square number 
	private int[][] rows;
	private int[][] columns;
	private int[][][] squares;
	
	static final int MAX_CHOICES = 9;
	static final int PENDING = 1;
	static final int COMPLETE = 0;
	
	Counters() {
		rows = new int[9][9];
		for (int[] row : rows) Arrays.fill(row, 9);
		
		columns = new int[9][9];
		for (int[] col : columns) Arrays.fill(col, 9);
		
		squares = new int[3][3][9];
		for (int[][] row : squares) for (int[] col : row) Arrays.fill(col, 9);
	}
	
	boolean eliminateChoice(int i, int value) {
		final int x = BoardManager.getX(i);
		final int y = BoardManager.getY(i);
		
		rows[y][value - 1]--;
		columns[x][value - 1]--;
		squares[x/3][y/3][value - 1]--;
		
		// should only be "== 1" meaning only one cell can hold that value but I'm using "<=" to be safer
		return rows[y][value - 1] <= PENDING || columns[x][value - 1] <= PENDING || squares[x/3][y/3][value - 1] <= PENDING;
	}
	
	// only for use by construction phase
	final void setChoice(int i, int value) {
		final int x = BoardManager.getX(i);
		final int y = BoardManager.getY(i);
		
		for (int j=0; j<rows[y].length; j++) rows[y][j]--;
		for (int j=0; j<columns[x].length; j++) columns[x][j]--;
		for (int j=0; j<squares[x/3][y/3].length; j++) squares[x/3][y/3][j]--;
		
		rows[y][value - 1] = PENDING;
		columns[x][value - 1] = PENDING;
		squares[x/3][y/3][value - 1] = PENDING;
		// what about notifications?? could this be coded in a higher manager??
	}
	
	void setComplete(int i, int value) {
		final int x = BoardManager.getX(i);
		final int y = BoardManager.getY(i);
		
		rows[y][value - 1] = COMPLETE;
		columns[x][value - 1] = COMPLETE;
		squares[x/3][y/3][value - 1] = COMPLETE;
	}
	
	private static class Counter {
		private int value;
		Counter() {
			value = MAX_CHOICES;
		}
		int decrement() {
			if (value > PENDING) value--;
			return value;
		}
	}

	int getCountForRow(int i, int value) {
		return rows[i][value - 1];		
	}

	int getCountForColumn(int j, int value) {
		return columns[j][value - 1];
	}

	int getCountForSquare(int i, int j, int value) {
		return squares[i][j][value - 1];
	}
	
	
}
