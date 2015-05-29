package searches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import core.User;

public class ActivitySearch extends core.MyServlet{
    
    private static final long serialVersionUID = 1L;
    
    private static Connection connection;
    
    private static final String DB_USERNAME = "di18";
    private static final String DB_PASSWORD = "Q.Z4J2CPz";
    private static final String DB_HOSTNAME = "farm14.ewi.utwente.nl";
    private static final int DB_PORT = 5432;
    private static final String DATABASE = "di18";
    
    
    @Override
    public void init() {
        
        // Via a main() method this works.
        String url = "jdbc:postgresql://" + DB_HOSTNAME + ":" + DB_PORT + "/" + DATABASE;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
            //connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection could not be established: " + e);
            e.printStackTrace();
        }
    }
    
    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getThisConnection() {
        return connection;
    }
    
    // --- Setters ---------------------------------------------------------------------------
    
    public static void setConnection(Connection connection) {
        ActivitySearch.connection = connection;
    }
    
    
    // --- Commands --------------------------------------------------------------------------
    
    
    public static ArrayList activitySearch(String activity) {
        PreparedStatement ps;
        ArrayList activities = new ArrayList();
        try {
            ps = getThisConnection().prepareStatement(
                    " SELECT  name "
                            + " FROM uber.activities "
                            + " WHERE name LIKE ? ");
            ps.setString(1, activity + "%");
            ResultSet rs = ps.executeQuery();
            int i = 0;
                while (rs.next() && i<=5) {
                    activities.add(rs.getString(1) + " |");
                    i++;
                }
                connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return activities;
    }
    
    public static void main(String args[]) { 
        ActivitySearch activitySearch = new ActivitySearch();
        activitySearch.init();
        ArrayList activity = activitySearch("Walk");
        System.out.println(activity.toString());
    }
    
    public static User validate(String username, String password) {
        User user = null;
        try {
            PreparedStatement ps = getThisConnection().prepareStatement(
                      " SELECT Name "
                    + " FROM ? "
                    + " WHERE  "
                    + " AND passwordHash = ?; ");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

        }
    }