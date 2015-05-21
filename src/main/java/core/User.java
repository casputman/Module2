package core;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    
    // --- Instance variables ----------------------------------------------------------------

    private int idUser;
    private String surname;
    private String firstname;
    private float length;
    private String username;
    private String passwordHash;
    private String name;
    private String email;
    

    // --- Constructors ----------------------------------------------------------------------
    
    // --- Getters ---------------------------------------------------------------------------
    
    /**
     * @return the idUser
     */
    public int getIdUser() {
        return idUser;
    }


    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }


    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }
    

    /**
     * @return the length
     */
    public float getLength() {
        return length;
    }


    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * @return the passwordHash
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
    public void setFrom(ResultSet resultSet) throws SQLException {
    
        idUser = resultSet.getInt("uid");
        surname = resultSet.getString("surname");
        firstname = resultSet.getString("firstname");
        length = resultSet.getFloat("length");
        username = resultSet.getString("username");
        passwordHash = resultSet.getString("password");
        name = resultSet.getString("name");
        email = resultSet.getString("email");
    }
}
