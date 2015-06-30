package junit;

import static org.junit.Assert.*;

import org.junit.Test;
import core.User;
import searches.Goal;
public class GoalTest {
	RowCounter counter = new RowCounter();
	@Test
	public void setGoalTest() {
		searches.GoalShow test = new searches.GoalShow();
		int initial = counter.countRow("goal");
		test.setGoal(75, "2015-06-26", 8);
		int newcount = counter.countRow("goal");
		assertEquals("The row count must be increased by 1", initial + 1, newcount);
	}
	@Test
	public void getGoalBeanTest(){
		searches.GoalShow test = new searches.GoalShow();
		User user = User.fromIdUser(8);		
		Goal goal = test.getGoalBean(user);
		//statistics.Weight is tested in WeightTest
		statistics.Weight weight = new statistics.Weight();
		double currentweight = weight.getWeight(8);
		assertEquals("goalweight must be 75", 75.0, goal.getGoalweight(),0);
		assertEquals("goal date must be 2015-06-26", "2015-06-26", goal.getGoaldate());
		assertEquals("currentweight must be "  + currentweight, currentweight, goal.getCurrentWeight(),0);
	}
}
