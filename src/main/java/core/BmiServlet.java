package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BmiServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	
	 // --- Instance variables ----------------------------------------------------------------
	private double height;
	private double weight;
	private BMI bmi;
	private User user;
	
    /**
     * 
     */
	public void determineBMI() {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(
					" SELECT  w.weight, u.height, ?, w.user_IDuser "
							+ " FROM    user u, weight w "
							+ " WHERE w.user_IDuser = ? "
							+ " AND w.date = ( SELECT MAX(w.weightdate) FROM weight w, user u WHERE w.user_IDuser = ?");
			String input = String.valueOf(user.getIdUser());
			ps.setString(1, input );
			ps.setString(2, input );
			ps.setString(3, input );
	    	ResultSet rs = ps.executeQuery();
	    	while (rs.next()) {
	    		height = rs.getDouble(1);
	    		weight = rs.getDouble(2);
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BMICalculate();
	}
	
	/**
	 * 
	 */
	public void BMICalculate() {
		double heightArg = height;
		double weightArg = weight;
		double bmiArg = (weightArg/Math.pow((heightArg/100),2));
		bmi.setBMI(bmiArg);
		insertBMI(bmiArg);
	}
	
	/**
	 * 
	 */
	public void insertBMI(double bmiArg) {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(
					"INSERT INTO bmi" + 
						" VALUES (?, ?)");
			String input = String.valueOf(bmiArg);
			ps.setString(1, input);
			String input2 = String.valueOf(user.getIdUser());
			ps.setString(2, input2);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
