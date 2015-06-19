package searches;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.User;

public class GoalShow extends core.MyServlet{
	
	private User user;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Retrieves the goalweight, goaldate and last entered weight of a user
	public void setGoal(int goalweight, String goaldate, int id){
		Goal goal = new Goal();
		goal.createCalendar(goaldate);
		PreparedStatement ps;
		try {
			ps = super.getConnection().prepareStatement("INSERT INTO uber.goal (goalweight, goaldate, user_iduser)"
				+ " VALUES(?, ?, ?)");
			ps.setInt(1, goalweight);
			java.util.Date utilDate = goal.getGoaldate().getTime();
		    java.sql.Date goalDate = new java.sql.Date(utilDate.getTime());
			ps.setDate(2, goalDate);
			ps.setInt(3, id); 
			ps.execute();
			System.out.println("Goal is set");
		} catch (SQLException e) {
			e.printStackTrace();
			error("Goal set error");
		}
	}
	
	public void makeGoal(User user){
		Goal goal = null;
		PreparedStatement ps;
	    try {
            ps = super.getConnection().prepareStatement(
            " SELECT  goal.goalweight, goal.goaldate, weight.weight "
            + " FROM uber.goal, uber.weight "
            + " WHERE weight.weightdate = ( SELECT MAX(w.weightdate) FROM uber.weight w, uber.user u WHERE w.user_IDuser = ?)" + 
            " AND goal.user_iduser = ? "
        );
        int input = 0;
        if (user != null) {
        input = user.getIdUser();
        } else {
        	input = 0;
        	error("user is empty, line 47 GoalServlet");
        }
		ps.setInt(1, input);
		ps.setInt(2, input); 
		ResultSet rs = ps.executeQuery();
    	goal = new Goal();
    	rs.next();
    	goal.setGoalweight(rs.getInt(1));
    	goal.setGoaldate(rs.getDate(2));
    	goal.setCurrentWeight(rs.getInt(3));
    	System.out.println("goal is created");
    	user.setGoal(goal);
	    } catch (SQLException e) {
			e.printStackTrace();
			error("problem in get goal");
	    }
	}
	
	public int calculateGoal(int userid){
		return 0;
	}
	
	public void error(String arg) {
		System.err.println("ERROR: " + arg);
		
	}
	
	
}
