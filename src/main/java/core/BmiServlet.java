package core;

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
	public void determineBMI(User user) {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(
					" SELECT  w.weight, u.length, w.user_IDuser "
							+ " FROM    uber.user u, uber.weight w "
							+ " WHERE w.user_IDuser = ? "
							+ " AND w.weightdate = ( SELECT MAX(w.weightdate) FROM uber.weight w WHERE w.user_IDuser = ? )");
			this.user = user;
			ps.setInt(1, user.getIdUser() );
			ps.setInt(2, user.getIdUser() );
	    	ResultSet rs = ps.executeQuery();
	    	while (rs.next()) {
	    		height = rs.getDouble(2);
	    		weight = rs.getDouble(1);
	    	}
		} catch (SQLException e) {
			error("determineBMI()");
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
		double bmiArg = Math.round((weightArg/Math.pow((heightArg/100), 2)));
		System.out.println("bmi: " + bmiArg + " height: " + height);
		bmi = new BMI(bmiArg);
		insertBMI(bmiArg);
	}
	
	/**
	 * 
	 */
	public void insertBMI(double bmiArg) {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(
					"INSERT INTO uber.bmi (bmi, user_iduser) " + 
						" VALUES (?, ?)");
			ps.setDouble(1, bmiArg);
			ps.setInt(2, user.getIdUser());
			ps.execute();
		} catch (SQLException e) {
			error("insertBMI()");
			e.printStackTrace();
		}
		
	}
	
	public void error(String arg) {
		System.err.println("ERROR: " + arg);
		
	}
	
	public double getHeight(){
		return height;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public BMI getBmi(){
		return bmi;
	}
}
