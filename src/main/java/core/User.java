package core;

import java.sql.PreparedStatement;
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
    private String email;
    private BMI bmi;
    private VetPercentage vet;
    

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * 
     * @return
     */
    public VetPercentage getUserVet() {
		return vet;
	}
    
    /**
     * 
     * @return
     */
	public BMI getUserBMI() {
		return bmi;
	}
    
    // --- Setters ---------------------------------------------------------------------------

	/**
	 * 
	 * @param vet
	 */
	public void setUserVet(VetPercentage vet) {
		this.vet = vet;
	}
	
	/**
	 * 
	 * @param bmi
	 */
	public void setUserBMI(BMI bmi) {
		this.bmi = bmi;
	}


	/**
     * Store the values from a ResultSet
     * @param resultSet The result set which has the pointer at the correct row. 
     */
    public void setFrom(ResultSet resultSet) throws SQLException {
    
        idUser = resultSet.getInt("iduser");
        surname = resultSet.getString("surname");
        firstname = resultSet.getString("firstname");
        length = resultSet.getFloat("length");
        username = resultSet.getString("username");
        email = resultSet.getString("email");
        passwordHash = resultSet.getString("password");
    }
    
    
    // --- Class Usage -----------------------------------------------------------------------
    
    public static User fromIdUser(int iduser) {
        User user = null;
        try {
            PreparedStatement ps = Validation.getConnection().prepareStatement(
                      " SELECT  * "
                    + " FROM    uber.user "
                    + " WHERE   iduser = ? ; ");  
            
            ps.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setFrom(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
