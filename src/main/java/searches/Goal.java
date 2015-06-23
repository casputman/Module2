package searches;

import java.util.Calendar;

public class Goal {

	// --- Instance variables ----------------------------------------------------------------
	
    private int goalWeight;
	private searches.GoalDate goalDate;
	private int currentWeight;
	
	
    // --- Getters ---------------------------------------------------------------------------

    public int getGoalweight() {
		return goalWeight;
	}
    
    public Calendar getGoaldate() {
		return goalDate;
	}
    
    public int getCurrentWeight() {
		return currentWeight;
	}
    
    public int getCalculateGoal(){
    	return 0;
    }
    
    // --- Setters ---------------------------------------------------------------------------
	
	public void setGoalweight(int goalweight) {
		this.goalWeight = goalweight;
	}
	
	public void setGoaldate(searches.GoalDate goaldate) {
		this.goalDate = goaldate;
	}
	
	public void setGoaldate(java.util.Date goaldate) {
		GoalDate goalDateArg = new GoalDate();
		goalDateArg.setTime(goaldate);
		printDate(goalDateArg);
		goalDate = goalDateArg;
	}
	
	public void setCurrentWeight(int weight) {
		this.currentWeight = weight;
	}
	
	public void createCalendar(String arg) {
		System.out.println(arg + 2);
		String[] date = arg.split("-");
		java.util.Date dateArg = new java.util.Date();
		int year = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int day = Integer.parseInt(date[2]);
		dateArg.setDate(day);
		dateArg.setMonth(month);
		dateArg.setYear(year);
		GoalDate goalDateArg = new GoalDate();
		goalDateArg.setTime(dateArg);
		printDate(goalDateArg);
		System.out.println("string create error");
		goalDate = goalDateArg;
	}
	
	public void printDate(Calendar dateArg) {
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(dateArg.getTime()));
	}
	
}
