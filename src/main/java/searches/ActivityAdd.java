package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityAdd extends core.MyServlet {
    
private static final long serialVersionUID = 1L;
    
    public void addActivity(String activity, Integer userID, double amount) {
        PreparedStatement ps;
        try {
            System.out.println("fuck1:" + activity.trim());
            ps = getConnection().prepareStatement(
                    " SELECT  activities_name "
                            + " FROM    uber.activities "
                            + " WHERE   name = ? ");
            ps.setString(1, activity.trim());
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("fuck: " + rs.getFetchSize());
            ps = getConnection().prepareStatement(
                    "INSERT INTO uber.usage (amount, user_iduser, activities_name)" + 
                        " VALUES (?, ?, ?)");
            ps.setDouble(1, amount);
            ps.setInt(2, userID);
            System.out.println("fuck: " + rs.getInt(1));
            ps.setString(3, activity);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void main(String args[]) {
        addActivity("Walk/run, playing with children, moderate", 3, 1);
        System.out.println("Ya nigga nigga fixed allo dem chizzle, beef hooked"); 
    }
}

