package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SleepAdd extends core.MyServlet {

    public void addSleep(double sleep, Integer userID) {
        
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement(
                    "INSERT INTO uber.sleep (user_iduser, sleep)" + 
                        " VALUES (?, ?)");
            ps.setInt(1, userID);
            ps.setDouble(2, sleep);
            ps.execute();
            ps.close();
            // amount date user_iduser id intake idfood 
        } catch (SQLException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }
        
    }
}
