package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.User;

public class GoalServelt extends core.MyServlet {
	private User user;
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
    		goal.setGoaldate(rs.getDate(2));
    		goal.setCurrentWeight(rs.getInt(3));
    	}
    	System.out.println("goalweight: " + goal.getGoalweight() + " " + "goaldate: " + goal.getGoaldate() + " " + "weight: " + goal.getGoalweight() );
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	    calculateGoal(goal);
	}
	
	public int calculateGoal(Goal goal){
		java.util.Date currentDate = new Date();
		java.util.Date dategoal = goal.getGoaldate();
		long diff = Math.abs(dategoal.getTime() - currentDate.getTime());
		long diffDays = diff/(24 * 60 * 60 * 1000);
		System.out.println("diffDays: "+ diffDays);
		int diffweight = goal.getCurrentWeight() - goal.getGoalweight();
		long toBurnCalorie = diffweight * 7700;
		long toBurnDay = toBurnCalorie/diffDays;
		System.out.println("Calories needed to burn each day: " + toBurnDay);
		return (int)toBurnDay;
	}
	
	public void error(String arg) {
		System.err.println("ERROR: " + arg);
		
	}


	public static void main(String[] args){
		 String expectedPattern = "dd/MM/yyyy";
		    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		    Date date = null;
		    try
		    {
		      // (2) give the formatter a String that matches the SimpleDateFormat pattern
		      String userInput = "22/09/2015";
		      date = formatter.parse(userInput);
		 
		      // (3) prints out "Tue Sep 22 00:00:00 EDT 2009"
		      System.out.println(date);
		    }
		    catch (ParseException e)
		    {
		      e.printStackTrace();
		    }
	}
}