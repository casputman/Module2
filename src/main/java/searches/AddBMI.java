package searches;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddBMI {

private static final long serialVersionUID = 1L;
    
    public void addBMI(double BMI, Integer userID, double weight) {
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement(
                    "INSERT INTO uber.user (BMI, user_iduser, weight)" + 
                        " VALUES (?, ?, ?)");
            ps.setInt(1, userID);
            ps.setDouble(2, weight);
            ps.setDouble(3, BMI);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}

