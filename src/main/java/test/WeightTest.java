package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeightTest {
	RowCounter counter = new RowCounter();
	
	@Test
	public void weightTest() {
		statistics.Weight test = new statistics.Weight();
		int initial = counter.countRow("weight");
		test.setWeight(8, 83.0);
		int newcount = counter.countRow("weight");
		assertEquals("The row count must be increased by 1", initial + 1, newcount);
		assertEquals("The retrieved weight must be 83", 83, test.getWeight(8),0);
	}
}
