package searches;

public class Goal {

	// --- Instance variables ----------------------------------------------------------------
	
    private int goalWeight;
	private searches.GoalDate goalDate;
	private int currentWeight;
	
	
    // --- Getters ---------------------------------------------------------------------------

    public int getGoalweight() {
		return goalWeight;
	}
    
    public searches.GoalDate getGoaldate() {
		return goalDate;
	}
    
    public int getCurrentWeight() {
		return currentWeight;
	}
    
    // --- Setters ---------------------------------------------------------------------------
	
	public void setGoalweight(int goalweight) {
		this.goalWeight = goalweight;
	}
	
	public void setGoaldate(searches.GoalDate goaldate) {
		this.goalDate = goaldate;
	}
	
	public void setCurrentWeight(int weight) {
		this.currentWeight = weight;
	}
	
	public void createGoalDate(int year, int month, int day) {
		goalDate = new GoalDate();
		goalDate.setYear(year);
		goalDate.setMonth(month);
		goalDate.setDay(day);
	}

	@SuppressWarnings("deprecation")
	public void createGoalDate(java.sql.Date date) {
		goalDate = new GoalDate();
		goalDate.setYear(date.getYear());
		goalDate.setMonth(date.getMonth());
		goalDate.setDay(date.getDay());
	}
	
}
