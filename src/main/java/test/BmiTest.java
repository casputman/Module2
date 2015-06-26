package test;

import static org.junit.Assert.*;
import core.User;
import core.BmiServlet;
import org.junit.Test;

public class BmiTest {
	RowCounter counter = new RowCounter();
	@Test
	public void test() {
		User test = User.fromIdUser(8);
		BmiServlet bmi = new BmiServlet();
		int initial = counter.countRow("bmi");
		bmi.determineBMI(test);
		statistics.Weight weight = new statistics.Weight(); 
		int newcount = counter.countRow("bmi");
		assertEquals("Row count must be increased by 1", initial + 1, newcount);
		assertEquals("Users height must be 180", 180,  bmi.getHeight(),0);
		assertEquals("Users weight must be equal to the weight return by weight class", weight.getWeight(8), bmi.getWeight(), 0);
		double testbmi = Math.round(weight.getWeight(8) / Math.pow(1.79,2)); //getWeight is tested in WeightTest
		assertEquals("Calculated bmi must be equal to the tested bmi", testbmi, bmi.getBmi().getBMI(),0); 
	}
}
