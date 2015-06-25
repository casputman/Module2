package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import core.Food;
import core.FoodDao;
import core.Intake;

public class FoodAddTest {
	RowCounter counter = new RowCounter();
	List<Food> result = FoodDao.search("aardappelen", 0);
	
	
	@Test
	public void searchTest() {
		assertEquals("The list size should be 7 given string: 'aardappelen'", 7, result.size());
		Food food = result.get(0);
		assertEquals("The returned name on place 0 should be: 'aardappelen'", "aardappelen", food.getName());
		//assertEquals("The returned calorie on place 0 should be: 85", 85.0, food.getCalorie(), 0);
		assertEquals("The returned amount on place 0 should be: 100", 100.0, food.getAmount(), 0);
		assertEquals("The returned id on place 0 should be: 1490", 1490, food.getIdfood());
		Food food2 = result.get(1);
		assertEquals("The returned name on place 0 should be: 'aardappelen (gepoft)'", "aardappelen (gepoft)", food2.getName());
		//assertEquals("The returned calorie on place 0 should be: 76", 76.0, food2.getCalorie(), 0);
		assertEquals("The returned amount on place 0 should be: 100", 100.0, food2.getAmount(), 0);
		assertEquals("The returned id on place 0 should be: 1491", 1491, food2.getIdfood());
	}
	
	@Test
	public void addFoodIntakeTest(){
		Food food = result.get(0);
		Intake test = new Intake(1.0, food);
		int initial = counter.countRow("intake");
		FoodDao.addFoodIntake(8, test);
		int newcount = counter.countRow("intake");
		assertEquals("The row count should have been increased by 1", initial + 1, newcount);
		List<Intake> result2 = FoodDao.getFoodIntakeToday(8);
		Intake intake = result2.get(0);
		assertEquals("The returned amount should be 1.0", 1.0, intake.getAmount(), 0);
		assertEquals("The returned foodname should be: 'aardappelen'", "aardappelen", intake.getFood().getName());
	}
	 @Test
	 public void addFoodTest(){
		 Food food = new Food();
		 food.setName("JUnit test");
		 food.setIduser(8);
		 int initial = counter.countRow("stdfood");
		 FoodDao.addFood(food);
		 int newcount = counter.countRow("stdfood");
		 assertEquals("The row count should have been increased by 1", initial + 1, newcount);
		 Food food1 = FoodDao.search("JUnit", 8).get(0);
		 assertEquals("The returned food name should be: JUnit test", "JUnit test", food1.getName());
	 }
}
