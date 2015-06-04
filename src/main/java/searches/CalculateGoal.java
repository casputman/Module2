package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.User;

public class CalculateGoal extends core.MyServlet {
	private User user;
	private int goalweight;
	private java.util.Date goaldate;
	private int weight;
	private static final long serialVersionUID = 1L;
	
	public CalculateGoal(int id, int goalweight, Date goaldate){
	setGoal(goalweight, goaldate, id);
	}
	
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
		PreparedStatement ps;
	    try {
            ps = super.getConnection().prepareStatement(
            " SELECT  goal.goalweight, goal.goaldate, weight.weight "
            + " FROM uber.goal, uber.weight "
            + " WHERE weight.weightdate = ( SELECT MAX(w.weightdate) FROM weight w, user u WHERE w.user_IDuser = ?)" + 
            "AND goal.user_iduser = ? "
        );
        String input = String.valueOf(user.getIdUser());
		ps.setString(1, input);
		ps.setString(2, input); 
		ResultSet rs = ps.executeQuery();
    	while (rs.next()) {
    		goalweight = rs.getInt(1);
    		goaldate = rs.getDate(2);
    		weight = rs.getInt(3);
    	}
    	System.out.println("goalweight: " + goalweight + " " + "goaldate: " + goaldate + " " + "weight: " + weight );
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	}
	
	public int calculateGoal(){
		java.util.Date currentDate = new Date();
		java.util.Date dategoal = goaldate;
		long diff = Math.abs(dategoal.getTime() - currentDate.getTime());
		long diffDays = diff/(24 * 60 * 60 * 1000);
		System.out.println("diffDays: "+ diffDays);
		int diffweight = weight - goalweight;
		long toBurnCalorie = diffweight * 7700;
		long toBurnDay = toBurnCalorie/diffDays;
		System.out.println("Calories needed to burn each day: " + toBurnDay);
		return (int)toBurnDay;
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
		new CalculateGoal(4, 65, date);
	}
}