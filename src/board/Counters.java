package board;

public class Counters {
	// arrays to hold the count for how many cells in that row/column/square can take that value
	// first index = value
	// second index = row/column/square number 
	private Counter[][] rows;
	private Counter[][] columns;
	private Counter[][][] squares;
	
	static final int MAX_CHOICES = 9;
	static final int PENDING = 1;
	static final int COMPLETE = 0;
	
	Counters() {
		rows = new Counter[9][9];
		columns = new Counter[9][9];
		for (int i=0; i<rows.length; i++)
			for (int j=0; j<rows[i].length; j++) {
				rows[i][j] = new Counter();
				columns[i][j] = new Counter();
			}
		
		squares = new Counter[3][3][9];
		for (int i=0; i<squares.length; i++)
			for (int j=0; j<squares[i].length; j++)
				for (int k=0; k<squares[i][j].length; k++)
					squares[i][j][k] = new Counter();
	}
	
	boolean eliminateChoice(int i, int value) {
		final int x = BoardManager.getX(i);
		final int y = BoardManager.getY(i);
		
		rows[y][value - 1].decrement();
		columns[x][value - 1].decrement();
		squares[x/3][y/3][value - 1].decrement();
		
		// should only be "== 1" meaning only one cell can hold that value but I'm using "<=" to be safer
		return rows[y][value - 1].isPending() || columns[x][value - 1].isPending() || squares[x/3][y/3][value - 1].isPending();
	}
	
	// only for use by construction phase
	final void setChoice(int i, int value) {
		final int x = BoardManager.getX(i);
		final int y = BoardManager.getY(i);
		
		for (int j=0; j<rows[y].length; j++) rows[y][j].decrement();
		for (int j=0; j<columns[x].length; j++) columns[x][j].decrement();
		for (int j=0; j<squares[x/3][y/3].length; j++) squares[x/3][y/3][j].decrement();
		
		rows[y][value - 1].setComplete();
		columns[x][value - 1].setComplete();
		squares[x/3][y/3][value - 1].setComplete();
		// what about notifications?? could this be coded in a higher manager??
	}
	
	void setComplete(int i, int value) {
		final int x = BoardManager.getX(i);
		final int y = BoardManager.getY(i);
		
		rows[y][value - 1].setComplete();
		columns[x][value - 1].setComplete();
		squares[x/3][y/3][value - 1].setComplete();
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
		boolean isPending(){
			return value == PENDING;
		}
		void setComplete() {
			value = COMPLETE;
		}
		@Override
		public String toString() {
			return "c"+ value;
		}
	}

	boolean isPendingCountForRow(int i, int value) {
		return rows[i][value - 1].isPending();	
	}

	boolean isPendingCountForColumn(int j, int value) {
		return columns[j][value - 1].isPending();
	}

	boolean isPendingCountForSquare(int i, int j, int value) {
		return squares[i][j][value - 1].isPending();
	}

	
}
