package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import core.User;

public class GoalShow extends core.MyServlet{
	
	private User user;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Retrieves the goalweight, goaldate and last entered weight of a user
	public void setGoal(int goalweight, Date goaldate, int id){
		super.init();
		PreparedStatement ps;
		try {
			ps = super.getConnection().prepareStatement("INSERT INTO uber.goal (goalweight, goaldate, user_iduser)"
				+ " VALUES(?, ?, ?)");
			ps.setInt(1, goalweight);
			java.sql.Date sqlDate = new java.sql.Date(goaldate.getTime());
			ps.setDate(2, sqlDate);
			ps.setInt(3, id);
			ps.execute();
			System.out.println("Goal is set");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getGoal(){
		super.init();
		Goal goal = null;
		PreparedStatement ps;
	    try {
            ps = super.getConnection().prepareStatement(
            " SELECT  goal.goalweight, goal.goaldate, weight.weight "
            + " FROM uber.goal, uber.weight "
            + " WHERE weight.weightdate = ( SELECT MAX(w.weightdate) FROM weight w, user u WHERE w.user_IDuser = ?)" + 
            "AND goal.user_iduser = ? "
        );
        String input = null;
        if (user != null) {
        input = String.valueOf(user.getIdUser());
        } else {
        	input = null;
        	error("user is empty, line 47 GoalServlet");
        }
		ps.setString(1, input);
		ps.setString(2, input); 
		ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		goal = new Goal();
    		goal.setGoalweight(rs.getInt(1));
    		goal.createGoalDate(rs.getDate(2));
    		goal.setCurrentWeight(rs.getInt(3));
    	}
    	System.out.println("goalweight: " + goal.getGoalweight() + " " + "goaldate: " + goal.getGoaldate() + " " + "weight: " + goal.getGoalweight() );
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	    calculateGoal(goal);
	}
	
	public int calculateGoal(Goal goal){
		return 0;
	}
	
	public void error(String arg) {
		System.err.println("ERROR: " + arg);
		
	}
	
	
}
