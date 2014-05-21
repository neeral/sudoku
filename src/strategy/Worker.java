package strategy;

import java.util.Collection;

import game.SudokuException;

class Worker implements Runnable {
	private Collection<Strategy> strategies;
	private int i;
	private Popper p;

	Worker(int i, Collection<Strategy> strategies, Popper p) {
		this.i = i;
		this.strategies = strategies;
		this.p = p;
	}

	@Override
	public void run() {
		try {
//			System.out.println(Thread.currentThread().getName() + " [Worker] starting for " + i);
			for (Strategy s : strategies)
				s.resolve(i);
			
			p.completed(i);
			
//			System.out.println(Thread.currentThread().getName() + " [Worker] finished for " + i);
			
		} catch (SudokuException e) {
			System.out.println("An error occurred trying to resolve dependents of cell " + i);
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Worker[" + i + "]";
	}

}
