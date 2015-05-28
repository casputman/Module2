package searches;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivityAdd extends core.MyServlet {
    private static final long serialVersionUID = 1L;
    // --- Class variables -------------------------------------------------------------------

    private static Connection connection;
    
    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getThisConnection() {
        return connection;
    }
      
    // --- Commands --------------------------------------------------------------------------
    
    
    public static void setConnection(Connection connection) {
        ActivityAdd.connection = connection;
    }
    
    
    public static void addActivity(String activity, Integer userID, double amount) {
        PreparedStatement ps;
        try {
            ps = getThisConnection().prepareStatement(
                    "INSERT INTO uber.usage (amount, user_iduser, activities_name)" + 
                        " VALUES (?, ?, ?)");
            ps.setDouble(1, amount);
            ps.setInt(2, userID);
            ps.setString(3, activity);
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public static void main(String args[]) {
        ActivityAdd activityAdd = new ActivityAdd();
        activityAdd.init();
        addActivity("Walk/run, playing with children, moderate", 3, 1);
        System.out.println("Ya nigga nigga fixed allo dem chizzle, beef hooked"); 
    }
}

