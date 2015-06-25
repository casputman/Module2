package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class FoodAddTest {
	
	searches.FoodAdd test = new searches.FoodAdd();
	RowCounter counter = new RowCounter();
	
	@Test
	public void addFoodTest() {
		int initialsize = counter.countRow("intake");
		test.addFood("aalbessen", 4, 1);
		int newsize = counter.countRow("intake");
        assertEquals(initialsize + 1, newsize);
	}
	
	@Test
	public void addFoodToDBTest() {
		int initialsize = counter.countRow("stdfood");
		test.addFoodToDB(400, 4.0, "gr", 0, 0, 0, 4.0, "kaas");
		int newsize = counter.countRow("stdfood");
        assertEquals(initialsize + 1, newsize);
	}
}
