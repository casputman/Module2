package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VetPercentageServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	
	 // --- Instance variables ----------------------------------------------------------------
	private double middel;
	private double weight;
	private VetPercentage vpt;
	private User user;
	private String gender;
	
    /**
     * 
     */
	public void determineVPT(User user) {
		PreparedStatement ps;
		this.user = user;
		try {
			ps = getConnection().prepareStatement(
					" SELECT  w.weight, w.width, w.user_IDuser, u.gender "
							+ " FROM    uber.user u, uber.weight w "
							+ " WHERE w.user_IDuser = ? "
							+ " AND w.weightdate = ( SELECT MAX(w.weightdate) FROM uber.weight w, uber.user u WHERE w.user_IDuser = ? ) ");
			ps.setInt(1, user.getIdUser() );
			ps.setInt(2, user.getIdUser() );
	    	ResultSet rs = ps.executeQuery();
	    	while (rs.next()) {
	    		middel = rs.getDouble(2);
	    		weight = rs.getDouble(1);
	    		gender = rs.getString(4);
	    	}
		} catch (SQLException e) {
			error("determineVPT");
			e.printStackTrace();
		}
		if (gender.equals("m")){
		VPTCalculateMan();
		}
		else { VPTCalculateVrouw(); 
		}
	}
	
	/**
	 * 
	 */
	public void VPTCalculateMan() {
		double middelArg = middel;
		double weightArg = weight;
		double vptArg = (((((-98.42 + (4.15 * (middelArg / 2.54))) - (0.082 * (weightArg * 2.2)))) / (weightArg * 2.2)) * 100);
		vpt = new VetPercentage(vptArg);
		insertVPT(vptArg);
	}
	/**
	 * 
	 */
	public void VPTCalculateVrouw() {
		double middelArg = middel;
		double weightArg = weight;
		double vptArg = (((((-76.76 + (4.15 * (middelArg / 2.54))) - (0.082 * (weightArg * 2.2)))) / (weightArg * 2.2)) * 100);
		vpt = new VetPercentage(vptArg);
		insertVPT(vptArg);
	}
	/**
	 * 
	 */
	public void insertVPT(double vptArg) {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(
					"INSERT INTO fat (fatpercentage, user_iduser) " + 
						" VALUES (?, ?)");
			ps.setDouble(1, vptArg);
			ps.setInt(2, user.getIdUser());
			ps.execute();
		} catch (SQLException e) {
			error("insertVPT");
			e.printStackTrace();
		}
		
	}
	
	public void error(String arg) {
		System.err.println("ERROR: " + arg);
		
	}
	
	
	
	
	
	
	
}
