package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SleepAdd extends core.MyServlet {

    public void addSleep(double sleep, Integer userID) {
        
        PreparedStatement ps;
        try {
            ps = super.getConnection().prepareStatement(
                    "INSERT INTO uber.intake (amount, user_iduser, idfood)" + 
                        " VALUES (?, ?, ?)");
            ps.setDouble(1, sleep);
            ps.setInt(2, userID);
            ps.execute();
            ps.close();
            // amount date user_iduser id intake idfood 
        } catch (SQLException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }
        
    }
}
