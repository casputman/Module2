package searches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class FoodSearch extends core.MyServlet {
	private static final long serialVersionUID = 1L;
	
    private static Connection connection;

    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getThisConnection() {
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
			ps = getThisConnection().prepareStatement(
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
	    		connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return foods;
    }
    
    public void main(String args[]) { 
    	super.init();
    	ArrayList food = foodsearch("aar");
    	System.out.println(food.toString());
    }
}
