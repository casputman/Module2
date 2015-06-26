package statistics;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Weight {

    public void setWeight(int userid, double weight) {
        PreparedStatement ps;
        try {
            ps = core.Validation.getConnection().prepareStatement(
                    " INSERT INTO uber.weight (weight, user_iduser) "
                    + " VALUES ?, ? "
                            );
            ps.setDouble(1, weight);
            ps.setInt(2, userid);
            ps.executeQuery();
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
}
