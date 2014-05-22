package strategy;

import game.Numbers.TaskType;

final class PopperStub implements Popper {
	private static Popper singleton = null;
	
	private PopperStub() {}
	
	static Popper getSingleton() {
		if (singleton == null) singleton = new PopperStub();
		return singleton;
	}

	@Override
	public void add(TaskType type, int i) {
	}

	@Override
	public void completed(TaskType type, int i) {
	}

}
