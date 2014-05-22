package strategy;

import game.Numbers.TaskType;

// used for notifications
public interface Popper {
	void add(TaskType type, int i);			// request addition of a new entry for processing
	void completed(TaskType type, int i);	// signal completion of processing an entry
}
