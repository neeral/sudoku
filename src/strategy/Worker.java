package strategy;

import java.util.Collection;

import game.SudokuException;
import game.Numbers.TaskType;

class Worker implements Runnable {
	private Collection<Strategy> strategies;
	private int i;
	private TaskType type;
	private Popper p;

	Worker(int i, Collection<Strategy> strategies, Popper p, TaskType type) {
		this.i = i;
		this.strategies = strategies;
		this.p = p;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " [Worker] starting for " + type + "/" + i);
			for (Strategy s : strategies)
				s.resolve(i);
			
			p.completed(type, i);
			
//			System.out.println(Thread.currentThread().getName() + " [Worker] finished for " + type + "/" + i);
			
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
