package game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	board.BoardManagerTest.class,
	board.BoardTest.class,
	board.CountersTest.class,
	cells.CellFactoryTest.class,
	cells.GivenCellTest.class,
	cells.SpaceTest.class,
	strategy.EliminatingRowsAndColumnsStrategyTest.class,
	strategy.EliminatingSquaresStrategyTest.class,
	strategy.CountingSquarestrategyTest.class,
	strategy.CountingRowsStrategyTest.class,
	strategy.CountingColumnsStrategyTest.class
	})
public class AllTests {


}
