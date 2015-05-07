package core;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    
    // --- Instance variables ----------------------------------------------------------------

    private int UID;
    private String username;
    private String passwordHash;
    private String name;
    private String email;
    

    // --- Constructors ----------------------------------------------------------------------
    
    // --- Getters ---------------------------------------------------------------------------
    
    /**
     * @return the UID
     */
    public int getUID() {
        return UID;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    
    // --- Setters ---------------------------------------------------------------------------
    
    /**
     * Store the values from a ResultSet
     * @param resultSet The result set which has the pointer at the correct row. 
     */
    public void setFrom(ResultSet resultSet) {
        try {
            UID = resultSet.getInt("uid");
            username = resultSet.getString("username");
            passwordHash = resultSet.getString("passwordHash");
            name = resultSet.getString("name");
            email = resultSet.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
