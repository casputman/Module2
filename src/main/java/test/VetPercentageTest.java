package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.User;
import core.VetPercentageServlet;
public class VetPercentageTest {
	RowCounter counter = new RowCounter();
	@Test
	public void vetPercentageMaleTest() {
		User male = User.fromIdUser(8);
		int initial = counter.countRow("fat");
		VetPercentageServlet test = new VetPercentageServlet();
		test.determineVPT(male);
		int newcount = counter.countRow("fat");
		assertEquals("Row count must be increased by 1", initial + 1, newcount);
		core.VetPercentage vtp = test.getVPT();
		double fat = vtp.getVPT();
		assertEquals("fat percentage must be around 23", 22.8 , fat, 0.2);
	}
	@Test
	public void vetPercentageFemaleTest(){
		User female = User.fromIdUser(15);
		int initial = counter.countRow("fat");
		VetPercentageServlet test = new VetPercentageServlet();
		test.determineVPT(female);
		int newcount = counter.countRow("fat");
		assertEquals("Row count must be increased by 1", initial + 1, newcount);
		core.VetPercentage vtp = test.getVPT();
		double fat = vtp.getVPT();
		assertEquals("fat percentage must be around 47", 46.5 , fat, 0.2);
	}
}
