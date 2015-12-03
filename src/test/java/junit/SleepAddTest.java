package junit;

import static org.junit.Assert.*;
import org.junit.Test;

public class SleepAddTest {
	searches.SleepAdd test = new searches.SleepAdd();
	RowCounter counter =  new RowCounter();
	
	@Test
	public void test() {
		int initialsize = counter.countRow("sleep");
		test.addSleep(5.0, 4);
		int newsize = counter.countRow("sleep");
        assertEquals("Number of rows must increase by 1", initialsize + 1, newsize);
	}
}
