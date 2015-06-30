package junit;
import static org.junit.Assert.*;

import org.junit.Test;

public class ActivityColumnTest {
	searches.DetermineActivityColumn test = new searches.DetermineActivityColumn();

	@Test
	public void determineColumnTest() {
		assertEquals("Selected column closest to weight must be 92", 92, test.determineActivityColumn(3));
		assertEquals("Selected column closest to weight must be 59", 59, test.determineActivityColumn(7));
		assertEquals("Selected column closest to weight must be 81", 81, test.determineActivityColumn(8));
		assertEquals("Selected column closest to weight must be 70", 70, test.determineActivityColumn(6));
	}

	@Test
	public void getWeightTest() {
		assertEquals("Retrieved weight must be 100.0", 100.0, test.getWeight(3), 0);
		assertEquals("Retrieved weight must be 45.0", 45.0, test.getWeight(7), 0);
		assertEquals("Retrieved weight must be 82.0", 82.0, test.getWeight(8), 0);
		assertEquals("Retrieved weight must be 75.0", 75.0, test.getWeight(6), 0);
	}
}
