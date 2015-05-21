package searches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;



public class FoodSearch extends HttpServlet {
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
        //Validation.setConnection(connection);
    }
    
    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getConnection() {
        return connection;
    }
    
    // --- Setters ---------------------------------------------------------------------------
    
    public static void setConnection(Connection connection) {
        FoodSearch.connection = connection;
    }
    
    
    // --- Commands --------------------------------------------------------------------------
    

    public static ArrayList foodsearch(String food) {
    	PreparedStatement ps;
    	ArrayList foods = new ArrayList();
		try {
			ps = getConnection().prepareStatement(
					" SELECT  name "
							+ " FROM    uber.stdfood "
							+ " WHERE   name LIKE ? ");
	    	ps.setString(1, food + "%");
	    	ResultSet rs = ps.executeQuery();
	    	int i = 0;
	    		while (rs.next() && i<=5) {
	    			foods.add(rs.getString(1));
	    			i++;
	    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return foods;
    }
    
    public static void main(String args[]) { 
    	FoodSearch foodsearch = new FoodSearch();
    	foodsearch.init();
    	ArrayList food = foodsearch("aar");
    	System.out.println(food.toString());
    }
}
