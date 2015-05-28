package searches;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivityAdd extends core.MyServlet {
    
    private static final long serialVersionUID = 1L;
    
    public void addActivity(String activity, Integer userID, double amount) {
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement(
                    "INSERT INTO uber.usage (amount, user_iduser, activities_name)" + 
                        " VALUES (?, ?, ?)");
            ps.setDouble(1, amount);
            ps.setInt(2, userID);
            ps.setString(3, activity);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void main(String args[]) {
        ActivityAdd activityAdd = new ActivityAdd();
        activityAdd.init();
        addActivity("Walk/run, playing with children, moderate", 3, 1);
        System.out.println("Ya nigga nigga fixed allo dem chizzle, beef hooked"); 
    }
}

