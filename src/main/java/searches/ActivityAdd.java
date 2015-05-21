package searches;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.User;

public class ActivityAdd {
	// --- Class variables -------------------------------------------------------------------

    private static Connection connection;
    
    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getConnection() {
        return connection;
    }
    
    // --- Setters ---------------------------------------------------------------------------
    
    public static void setConnection(Connection connection) {
        ActivityAdd.connection = connection;
    }
    
    
    // --- Commands --------------------------------------------------------------------------
    
    
    public static User validate(String username, String password) {
        User user = null;
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                      " SELECT  * "
                    + " FROM    Users "
                    + " WHERE   username = ? "
                    + "     AND passwordHash = ?; ");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}

