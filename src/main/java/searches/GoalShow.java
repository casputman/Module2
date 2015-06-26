package searches;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import core.User;

public class GoalShow extends core.MyServlet {

	private User user;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Retrieves the goalweight, goaldate and last entered weight of a user
	public void setGoal(int goalweight, String goaldate, int id) {
		Goal goal = new Goal();
		java.sql.Date goalDate = Date.valueOf(goaldate);
		PreparedStatement ps;
		try {
			ps = super.getConnection().prepareStatement(
					"INSERT INTO uber.goal (goalweight, goaldate, user_iduser)"
							+ " VALUES(?, ?, ?)");
			ps.setInt(1, goalweight);
			ps.setDate(2, goalDate);
			ps.setInt(3, id);
			ps.execute();
			System.out.println("Goal is set");
		} catch (SQLException e) {
			e.printStackTrace();
			error("Goal set error");
		}
	}

	public Goal getGoalBean(User user) {
		Goal goal = null;
		PreparedStatement ps;
		try {
			ps = super
					.getConnection()
					.prepareStatement(
							" SELECT  goal.goalweight, goal.goaldate, weight.weight "
									+ " FROM uber.goal, uber.weight "
									+ " WHERE goal.currentdate = (SELECT MAX(g.currentdate) FROM uber.goal g WHERE g.user_iduser = ?) "
									+ " AND weight.weightdate = (SELECT MAX(w.weightdate) FROM uber.weight w, uber.user u WHERE w.user_IDuser = ?) "
									+ " AND goal.user_iduser = ? "
									+ " AND weight.user_iduser = ? ");
			int input = 0;
			if (user != null) {
				input = user.getIdUser();
			} else {
				input = 0;
				error("user is empty, line 47 GoalServlet");
			}
			ps.setInt(1, input);
			ps.setInt(2, input);
			ps.setInt(3, input);
			ps.setInt(4, input);
			ResultSet rs = ps.executeQuery();
			goal = new Goal();
			while(rs.next()) {
			goal.setGoalweight(rs.getInt(1));
			goal.setGoaldate(rs.getDate(2));
			goal.setCurrentWeight(rs.getInt(3));
			System.out.println(goal.getGoaldate());
			System.out.println("goal is created");
			user.setGoal(goal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			error("problem in get goal");
		}
		return goal;
	}

	public double calculateGoal(int iduser){
		double goalweight = 0;
		Date goaldate = null;
		double weight = 0;
		double calForGoal = -1;
		
		PreparedStatement ps;
	    try {
            ps = super.getConnection().prepareStatement(
            " SELECT  goal.goalweight, goal.goaldate, weight.weight "
            + " FROM uber.goal, uber.weight "
            + " WHERE weight.weightdate = ( SELECT MAX(w.weightdate) FROM uber.weight w, uber.user u WHERE w.user_IDuser = ?)" + 
            " AND goal.user_iduser = ? "
        );
            ps.setInt(1, iduser);
            ps.setInt(2, iduser);
		    ResultSet rs = ps.executeQuery();
		    while(rs.next()){
		    	goalweight = rs.getDouble(1);
		    	goaldate = rs.getDate(2);
		    	weight = rs.getDouble(3);
		    }
		  System.out.println("Goaldate: " + goaldate);
		  System.out.println("Goalweight: " + goalweight);
		  System.out.println("currentweight: "+ weight);
		  double weightToLose = goalweight - weight;
		  double calorieToLose = weightToLose * 7700;
		  long goalTime = goaldate.getTime();
		  long remainingTime = goalTime - System.currentTimeMillis();
		  if (remainingTime <= 0 ){
			  return 0;
		  } else {
		  double remainingDays = (remainingTime / (1000*60*60*24));
		  calForGoal = calorieToLose / remainingDays;
		  }
		   }catch (SQLException e) {
				e.printStackTrace();
				error("problem in get goal");
		   }
	    System.out.println("calForGoal: " + calForGoal);
	    return calForGoal;
	}
	

	public void error(String arg) {
		System.err.println("ERROR: " + arg);

	}

	public void printDate(Calendar dateArg) {
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(dateArg.getTime()));
	}

}
