package statistics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Weight {

    public void setWeight(int userid, double weight) {
        PreparedStatement ps;
        try {
            ps = core.Validation.getConnection().prepareStatement(
                    " INSERT INTO uber.weight (weight, user_iduser) "
                    + " VALUES (?, ?) "
                            );
            ps.setDouble(1, weight);
            ps.setInt(2, userid);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void setWidth(int userid, double width, double weight) {
        PreparedStatement ps;
        try {
            ps = core.Validation.getConnection().prepareStatement(
                    " INSERT INTO uber.weight (weight, width, user_iduser) "
                    + " VALUES (?, ?, ?) "
                            );
            ps.setDouble(1, weight);
            ps.setDouble(2, width);
            ps.setInt(3, userid);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public double getWeight(int userid) {
        PreparedStatement ps;
        double weight = 0.0;
        
        try {
            ps = core.Validation.getConnection().prepareStatement( 
                    " SELECT weight"
                    + " FROM uber.weight "
                    + " WHERE weightdate = ( SELECT MAX(weightdate) FROM uber.weight WHERE user_iduser = ? ) "
                    );
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            weight = rs.getDouble(1); 
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return weight;
    }
    
    public Date getWeigtDate(int userid) {
        PreparedStatement ps;
        Date weightdate = null;
        try {
            ps = core.Validation.getConnection().prepareStatement(
                    " SELECT weightdate "
                    + " FROM uber.weight "
                    + " WHERE weightdate = ( SELECT MAX(weightdate) FROM uber.weight WHERE user_iduser = ? ) "
                    );
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            weightdate = rs.getDate(1);
        }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return weightdate;
    }
}
