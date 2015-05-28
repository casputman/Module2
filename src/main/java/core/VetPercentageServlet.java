package core;

import java.sql.Connection;
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
	public void determineVPT() {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(
					" SELECT  w.weight, u.middel, ?, w.user_IDuser, u.gender "
							+ " FROM    user u, weight w "
							+ " WHERE w.user_IDuser = ? "
							+ " AND w.date = ( SELECT MAX(w.date) FROM weight w, user u WHERE w.user_IDuser = ?");
			String input = String.valueOf(user.getIdUser());
			ps.setString(1, input );
			ps.setString(2, input );
			ps.setString(3, input );
	    	ResultSet rs = ps.executeQuery();
	    	while (rs.next()) {
	    		middel = rs.getDouble(1);
	    		weight = rs.getDouble(2);
	    		gender = rs.getString(5);
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		double vptArg = (((-98.42+4.15*(middelArg/2.54)-0.082*(weightArg*2.2))/weightArg*2.2));
		vpt.setVPT(vptArg);
		insertVPT(vptArg);
	}
	/**
	 * 
	 */
	public void VPTCalculateVrouw() {
		double middelArg = middel;
		double weightArg = weight;
		double vptArg = (((-76.76+4.15*(middelArg/2.54)-0.082*(weightArg*2.2))/weightArg*2.2));
		vpt.setVPT(vptArg);
		insertVPT(vptArg);
	}
	/**
	 * 
	 */
	public void insertVPT(double vptArg) {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(
					"INSERT INTO vpt" + 
						" VALUES (?, ?)");
			String input = String.valueOf(vptArg);
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
