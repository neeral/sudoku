package game;

import java.util.Map;

import strategy.StrategyManager;

import board.BoardManager;

public class Game {

	/**
	 * @param args
	 * @throws SudokuException 
	 * @throws InterruptedException because using Executors and many threads
	 */
	public static void main(String[] args) throws SudokuException, InterruptedException {
		SudokuInput i = new SudokuInputImpl();
		Map<Integer, Integer> startingBoard = i.read();
		BoardManager boardManager = new BoardManager();
		boardManager.setUpBoard(startingBoard);
		StrategyManager strategyManager = new StrategyManager(boardManager);
		System.out.println("At start\n" + boardManager.printToString());
		
		long start = System.currentTimeMillis();
		strategyManager.solve();
		long end = System.currentTimeMillis();
		
		System.out.println("\nAt end\n" + boardManager.printToString());
		System.out.println("The Sudoku solver took " + (end - start) + "ms ... but is it finished? " + boardManager.checkConstraints());
	}

}
