package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Activity;
import core.ActivityDao;
import core.Usage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class ActivityAddTest {
	
	List<Activity> result = ActivityDao.search("Cycling, mountain bike, bmx");
	RowCounter counter = new RowCounter();
	@Test
	public void searchTest() {
		
		assertEquals("Size of returned list should be 1", 1, result.size());
		List<Activity> result2 = ActivityDao.search("Cycling");
		assertEquals("Size of the returned list should be 7", 7, result2.size());
		Activity activity = result.get(0);
		assertEquals("Amount of calories for 59 has to be 502", 502, activity.getKg59(), 0);
		assertEquals("Amount of calories for 70 has to be 598", 598, activity.getKg70(), 0);
		assertEquals("Amount of calories for 81 has to be 695", 695, activity.getKg81(), 0);
		assertEquals("Amount of calories for 92 has to be 502", 791, activity.getKg92(), 0);
	}
	@Test
	public void addGetActivityUsageTest(){
		int initialcount = counter.countRow("usage");
		Usage usage = new Usage();
		usage.setIduser(8);
		usage.setAmount(1.0);
		usage.setActivity(result.get(0));
		ActivityDao.addActivityUsage(usage);
		int newcount = counter.countRow("usage");
		assertEquals("The row count must be increased by 1", initialcount + 1, newcount);
		List<Usage> result2 = ActivityDao.getActivityUsageToday(8);
		Usage usage2 = result2.get(0);
		Date date = new Date();
		assertEquals("The id should be 8", 8, usage2.getIduser());
		assertEquals("The activity name should be: 'Cycling, mountain bike, bmx'", "Cycling, mountain bike, bmx", usage2.getActivity().getName());
		//checks if the saved date is no more than 24 hours from the current date
		assertEquals("The usage date should be the date of today", date.getTime(), usage2.getUsageDate().getTime(),86400000);
		assertEquals("The amount should be 1.0", 1.0, usage2.getAmount(), 0);	
	}
}

