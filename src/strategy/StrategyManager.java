package strategy;

import game.SudokuException;

import java.util.Collections;
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
	private ExecutorService ex;
	
	public StrategyManager(BoardAccessor board) {
		this.board = board;
		this.waitingQueue = new LinkedList<Integer>();
		this.pop = new Pop();
		this.rows = new EliminatingRowsAndColumnsStrategy(board, pop);
		this.squares = new EliminatingSquaresStrategy(board, pop);
		this.strategies = new LinkedList<Strategy>();
		this.strategies.add(rows);
		this.strategies.add(squares);
		this.ex = Executors.newSingleThreadExecutor(); //Executors.newFixedThreadPool(10); 
	}
	
	public void solve() throws SudokuException, InterruptedException {
		// begin with GivenCells
		for (int i=0; i<BoardManager.BOARD_SIZE; i++)
			if (board.getCell(i).isFilled())
				submitTask(i);
		// keep polling until all cells are filled in or we can't resolve any more squares
		while (!board.isCompletelyFilledIn() && !waitingQueue.isEmpty()) {
			System.out.println("board.isCompletelyFilledIn()=" + board.isCompletelyFilledIn() + "\twaitingQueue.size()=" + waitingQueue.size());
			ex.awaitTermination(1000, TimeUnit.MILLISECONDS);
		}
		ex.shutdown();
	}
	
	private void submitTask(int i) {
		synchronized (waitingQueue) {
			waitingQueue.add(i);
		}
		ex.submit(new Worker(i, strategies, pop));
	}
	
	private void completeTask(int i) {
		synchronized (waitingQueue) {
			waitingQueue.remove(Integer.valueOf(i));
		}
	}
	
	class Pop implements Popper {
		@Override
		public void add(int i) {
			System.out.println(Thread.currentThread().getName() + " [Popper] adding cell " + i + " onto the queue");
			
			if (board.isCompletelyFilledIn()) {
				ex.shutdownNow();
			} else {
				submitTask(i);
			}
		}

		@Override
		public void completed(int i) {
			completeTask(i);
		}
	}
}
