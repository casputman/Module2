package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.User;

public class CalorieGoal extends core.MyServlet {
	private User user;
	private static final long serialVersionUID = 1L;
	
	public void getSurplusCalorie(int iduser){
	Goal goal =  new Goal();
	int calorieToDecline = goal.getCalculateGoal();
	PreparedStatement ps;
	PreparedStatement ks = null;
	int totalCalorieIntake = 0;
	int totalCalorieBurned = 0;
	try{
		ps = getConnection().prepareStatement(   
				"SELECT idfood "
                + "FROM uber.intake " 
                + "WHERE user_iduser = ? "
                + "AND intaketime::timestamp::date = current_date "
                );
		ps.setInt(1, iduser);
		ResultSet rs = ps.executeQuery();
		PreparedStatement gs;
		while (rs.next()) {
			gs = super.getConnection().prepareStatement(
                     "SELECT calorie "
                     + "FROM uber.stdfood "
                     + "WHERE idfood = ?"
                     );
             gs.setInt(1, Integer.parseInt(rs.getString(1)));
             ResultSet hs =gs.executeQuery();
             while (hs.next()){
            	totalCalorieIntake += hs.getInt(1);
             }
             gs.close();
		}
		
		ks.getConnection().prepareStatement(
				"SELECT activities_name "
                + "FROM    uber.usage " 
                + "WHERE user_iduser = ? "
                + "AND usagedate = current_date");
		ks.setInt(1, iduser);
		ResultSet rs2 = ks.executeQuery();
		PreparedStatement ks2;
		while(rs2.next()){
			ks2 = super.getConnection().prepareStatement(  "SELECT calorie"
                    + "FROM uber.activities"
                    + "WHERE activities_name = ?"
					);
		ks2.setString(1, rs.getString(1));
		ResultSet rs3 = ks2.executeQuery();
		while(rs3.next()){
			
		}
		}
	}catch (SQLException e) {
        e.printStackTrace();
		
	}
	
}}
