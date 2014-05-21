package strategy;

// used for notifications
interface Popper {
	void add(int i);		// request addition of a new entry for processing
	void completed(int i);	// signal completion of processing an entry
}
