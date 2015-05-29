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
	public void setUserVet(double vet) {
		this.vet.setVPT(vet);;
	}
	
	/**
	 * 
	 * @param bmi
	 */
	public void setUserBMI(double bmi) {
		this.bmi.setBMI(bmi);
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
        setBMIVPT(idUser);
    }
    
    
    // --- Class Usage -----------------------------------------------------------------------
    
    public static User fromIdUser(int iduser) {
        User user = null;
        ResultSet rs = null;
        try {
            PreparedStatement ps = Validation.getConnection().prepareStatement(
                      " SELECT  * "
                    + " FROM    uber.user "
                    + " WHERE   iduser = ? ; ");  
            
            ps.setInt(1, iduser);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setFrom(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public static User fromUsername(String username) {
        User user = null;
        try {
            PreparedStatement ps = Validation.getConnection().prepareStatement(
                      "SELECT   * "
                    + "FROM     uber.user "
                    + "WHERE    username = ? ;");
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setFrom(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public static User fromEmail(String email) {
        User user = null;
        try {
            PreparedStatement ps = Validation.getConnection().prepareStatement(
                      "SELECT   * "
                    + "FROM     uber.user "
                    + "WHERE    email = ? ;");
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setFrom(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public void setBMIVPT(int iduser) {
     	ResultSet rs = null;
       	double bmi = 0;
       	try {
       		 PreparedStatement ps = Validation.getConnection().prepareStatement(
       		                      " SELECT  bmi "
       		                    + " FROM    uber.bmi "
       		                    + " WHERE   user_iduser = ? ; ");  
       		            
       		 ps.setInt(1, iduser);
       		 rs = ps.executeQuery();
       		            while (rs.next()) {
       		            	bmi = rs.getDouble("bmi");
       		            }
       		        } catch (SQLException e) {
       		            e.printStackTrace();
       		        }
       		    	BMI bmiArg = new BMI(bmi);
       		 		this.bmi = bmiArg;
       		    	double vet = 0;
       		    	try {
       		            PreparedStatement ps = Validation.getConnection().prepareStatement(
       		                      " SELECT  bmi "
       		                    + " FROM    uber.bmi "
       		                    + " WHERE   iduser = ? ; ");  
       		            
       		            ps.setInt(1, iduser);
       		            rs = ps.executeQuery();
       		            while(rs.next()) {
       		            	vet = rs.getDouble("bmi");
       		            }
       		        } catch (SQLException e) {
       		            e.printStackTrace();
       		        }
       		     	VetPercentage vetArg = new VetPercentage(vet);
       		     	this.vet = vetArg;
    }

}
