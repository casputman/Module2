package searches;

import java.util.Calendar;

public class Goal {

	// --- Instance variables ----------------------------------------------------------------
	
    private int goalWeight;
	private String goalDate;
	private int currentWeight;
	
	
    // --- Getters ---------------------------------------------------------------------------

    public int getGoalweight() {
		return goalWeight;
	}
    
    public String getGoaldate() {
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
	
	public void setGoaldate(java.sql.Date goaldate) {
		String goalDateArg = goaldate.toString();
		goalDate = goalDateArg;
	}
	
	public void setCurrentWeight(int weight) {
		this.currentWeight = weight;
	}
	
}
