package strategy;

import game.Numbers;
import game.Numbers.TaskType;
import game.SudokuException;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import board.BoardAccessor;
import board.BoardManager;

public class StrategyManager {
	private BoardAccessor board;
	private List<Integer> waitingQueue;
	private Pop pop;
	private Strategy rows;
	private Strategy squares;
	private List<Strategy> strategies;
	private List<Strategy> counting;
	private ExecutorService ex;
	
	public StrategyManager(BoardManager board) {
		this.board = board;
		this.waitingQueue = new LinkedList<Integer>();
		this.pop = new Pop();
		this.rows = new EliminatingRowsAndColumnsStrategy(board, pop);
		this.squares = new EliminatingSquaresStrategy(board, pop);
		this.strategies = new LinkedList<Strategy>();
		this.strategies.add(rows);
		this.strategies.add(squares);
		this.counting = new LinkedList<Strategy>();
		this.counting.add(new CountingRowsStrategy(board, pop));
		this.counting.add(new CountingColumnsStrategy(board, pop));
		this.counting.add(new CountingSquaresStrategy(board, pop));
		this.ex = Executors.newSingleThreadExecutor(); //Executors.newFixedThreadPool(10); 
	}
	
	public void solve() throws SudokuException, InterruptedException {
		// begin with GivenCells
		for (int i=0; i<Numbers.BOARD_SIZE; i++)
			if (board.getCell(i).isFilled())
				submitTask(TaskType.DETERMINISTIC, i);
		// also fire off for all the 9 numbers as a start
		for (int i=1; i<=Numbers.BOARD_LENGTH; i++)
			submitTask(TaskType.COUNTING, i);
		// keep polling until all cells are filled in or we can't resolve any more squares
		while (!board.isCompletelyFilledIn() && !waitingQueue.isEmpty()) {
			System.out.println("board.isCompletelyFilledIn()=" + board.isCompletelyFilledIn() + "\twaitingQueue.size()=" + waitingQueue.size() + "\n" + board.printToString());
			ex.awaitTermination(1000, TimeUnit.MILLISECONDS);
		}
		ex.shutdown();
	}
	
	private void submitTask(TaskType type, int i) {
		synchronized (waitingQueue) {
			waitingQueue.add(Numbers.BOARD_SIZE * type.ordinal() + i);
		}
		if (TaskType.DETERMINISTIC.equals(type)) ex.submit(new Worker(i, strategies, pop, TaskType.DETERMINISTIC));
		else if (TaskType.COUNTING.equals(type)) ex.submit(new Worker(i, counting, pop, TaskType.COUNTING));
	}
	
	private void completeTask(TaskType type, int i) {
		synchronized (waitingQueue) {
			waitingQueue.remove(Integer.valueOf(Numbers.BOARD_SIZE * type.ordinal() + i));
		}
	}
	
	class Pop implements Popper {
		@Override
		public void add(TaskType type, int i) {
			System.out.println(Thread.currentThread().getName() + " [Popper] adding " + type + "/" + i + " onto the queue");
			
			if (board.isCompletelyFilledIn()) {
				ex.shutdownNow();
			} else {
				submitTask(type, i);
			}
		}

		@Override
		public void completed(TaskType type, int i) {
			completeTask(type, i);
		}
	}
}
