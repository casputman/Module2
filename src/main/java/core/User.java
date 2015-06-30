package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import searches.Goal;

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
    private Goal goal;
    

    // --- Constructors ----------------------------------------------------------------------
    
    public User() {}
    
    public User(int idUser) { 
        setIdUser(idUser);
    }
    
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
    
	/**
	 * 
	 * @return
	 */
	public Goal getGoal() {
		return goal;
	}
	
	/**
	 * Returns the weight column, used for determining the calorie usage of activities. If 
	 * no weight was found, kg81 is selected. The user id should be set.
	 * 
	 * @param date the date after the weight entry
	 * @return     either kg59, kg70, kg81 or kg92
	 */
	public String getWeightColumn(Date date) throws IllegalArgumentException {
	    
	    try {
    	    return getWeightColumn(getWeight(date));
	    } catch (IllegalArgumentException e) {
	        return "kg81";
	    }
	}
	
	/**
	 * Returns the weight column according to a weight value.
	 * 
	 * @param weight the weight value in kg
	 * @return       the weight column
	 */
	public static String getWeightColumn(double weight) {
        if (weight >= 92) {
            return "kg92";
        } else if (weight >= 81) {
            return "kg81";
        } else if (weight >= 70) {
            return "kg70";
        } else {
            return "kg59";
        }
	}
    
    /**
     * Returns the weight.
     * The user id should be set.
     * 
     * @param date the date after the weight entry
     * @return     the weight; -1 if an SQLException occurred
     * @throws IllegalArgumentException if there was no weight entry found for this date 
     */
    public double getWeight(Date date) throws IllegalArgumentException {
        try {
            final PreparedStatement ps = Validation.getConnection().prepareStatement(
                      " SELECT  weight "
                    + " FROM    uber.weight "
                    + " WHERE   user_iduser = ? "
                    + "   AND   weightdate <= ? "
                    + " ORDER BY weightdate DESC ");
            ps.setInt(1, getIdUser());
            ps.setDate(2, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date)));
            final ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new IllegalArgumentException("no weight entry found before or on this date");
            }
            return rs.getDouble("weight");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
	
	
    // --- Setters ---------------------------------------------------------------------------
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
	
	public void setGoal(Goal goalArg) {
		goal = goalArg;
		System.out.println("goal is set to user");
	}
	
	/**
	 * 
	 * @param vet
	 */
	public void setUserVet(double vet) {
		this.vet.setVPT(vet);
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
       		                      " SELECT  fatpercentage "
       		                    + " FROM    uber.fat "
       		                    + " WHERE   user_iduser = ? ; ");  
       		            
       		            ps.setInt(1, iduser);
       		            rs = ps.executeQuery();
       		            while(rs.next()) {
       		            	vet = rs.getDouble(1);
       		            }
       		        } catch (SQLException e) {
       		            e.printStackTrace();
       		        }
       		     	VetPercentage vetArg = new VetPercentage(vet);
       		     	this.vet = vetArg;
    }

}
