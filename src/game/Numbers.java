package game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Numbers  {

	public static final Set<Integer> getChoices() {
		return new HashSet<Integer>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
	}
	public enum TaskType { DETERMINISTIC, PREDICTIVE, COUNTING }
	public static final int BOARD_SIZE = 81;
	public static final int BOARD_LENGTH = 9;
}
