package test;

import static org.junit.Assert.*;
import org.junit.Test;

public class ActivityAddTest {
	
	searches.ActivityAdd test = new searches.ActivityAdd();
	RowCounter counter = new RowCounter();
	
	@Test
	public void test() {
		int initialsize = counter.countRow("usage");
		test.addActivity("Cycling, mountain bike, bmx", 4, 1);
		int newsize = counter.countRow("usage");
        assertEquals(initialsize + 1, newsize);
	}
}

