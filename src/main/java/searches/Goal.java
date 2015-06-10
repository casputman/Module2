package searches;

public class Goal {

	// --- Instance variables ----------------------------------------------------------------
	
    private int goalWeight;
	private java.util.Date goalDate;
	private int currentWeight;
	
	
    // --- Getters ---------------------------------------------------------------------------

    public int getGoalweight() {
		return goalWeight;
	}
    
    public java.util.Date getGoaldate() {
		return goalDate;
	}
    
    public int getCurrentWeight() {
		return currentWeight;
	}
    
    // --- Setters ---------------------------------------------------------------------------
	
	public void setGoalweight(int goalweight) {
		this.goalWeight = goalweight;
	}
	
	public void setGoaldate(java.util.Date goaldate) {
		this.goalDate = goaldate;
	}
	
	public void setCurrentWeight(int weight) {
		this.currentWeight = weight;
	}
	
	
	
	
	
	
}
