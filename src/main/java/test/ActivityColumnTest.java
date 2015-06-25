package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActivityColumnTest {
	searches.DetermineActivityColumn test = new searches.DetermineActivityColumn();

	@Test
	public void determineColumnTest() {
		assertEquals(92, test.determineActivityColumn(3));
		assertEquals(59, test.determineActivityColumn(7));
		assertEquals(81, test.determineActivityColumn(8));
		assertEquals(70, test.determineActivityColumn(6));
	}

	@Test
	public void getWeightTest() {
		assertEquals(85.0, test.getWeight(3), 0);
		assertEquals(45.0, test.getWeight(7), 0);
		assertEquals(82.0, test.getWeight(8), 0);
		assertEquals(75.0, test.getWeight(6), 0);
	}
}
