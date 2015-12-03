package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetermineActivityColumn extends core.MyServlet {

	private static final long serialVersionUID = 1L;

	public double getWeight (int iduser){
		PreparedStatement ps;
		double weight = -1;
		try{
			ps = getConnection().prepareStatement(" SELECT  w.weight"
					+ " FROM  uber.weight w "
					+ " WHERE w.user_IDuser = ? "
					+ " AND w.weightdate = ( SELECT MAX(w.weightdate) FROM uber.weight w WHERE w.user_IDuser = ?)"
					+ " AND w.idweight = ( SELECT MAX(w.idweight) FROM uber.weight w WHERE w.user_iduser = ?)");
			ps.setInt(1, iduser);
			ps.setInt(2, iduser);
			ps.setInt(3, iduser);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				weight = rs.getDouble(1);
			}
		} catch (SQLException e) {
            e.printStackTrace();
		}
		System.out.println(weight);
		return weight;
	} 
	
	public int determineActivityColumn(int iduser){
		double activityweight = getWeight(iduser);
		double activityweight59 = Math.abs(activityweight - 59);
		double activityweight70 = Math.abs(activityweight - 70);
		double activityweight81 = Math.abs(activityweight - 81);
		double activityweight92 = Math.abs(activityweight - 92);
		System.out.println("59: " + activityweight59);
		System.out.println("70: " + activityweight70);
		if(activityweight59 <= activityweight70 ){
			return 59;
		} else if (activityweight70 <=  activityweight81){
			return 70;
		} else if (activityweight81 <= activityweight92){
			return 81;
		} else {
			return 92;
		}
	}
	public static void main(String[] args){
	    double activityweight = 75;
        double activityweight59 = Math.abs(activityweight - 59);
        double activityweight70 = Math.abs(activityweight - 70);
        double activityweight81 = Math.abs(activityweight - 81);
        double activityweight92 = Math.abs(activityweight - 92);
        int res;
        if(activityweight59 <= activityweight70 ){
            res = 59;
        } else if (activityweight70 <=  activityweight81){
            res = 70;
        } else if (activityweight81 <= activityweight92){
            res = 81;
        } else {
            res = 92;
        }
        
        System.out.println(res);
	}
}
