package searches;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodAdd {

	 private static Connection connection;
    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getConnection() {
        return connection;
    }
    
    // --- Setters ---------------------------------------------------------------------------
    
    public static void setConnection(Connection connection) {
        FoodAdd.connection = connection;
    }
    
    
	public static void addFood(String food, Integer userID, double amount) {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(
					" SELECT  idfood "
							+ " FROM    uber.stdfood "
							+ " WHERE   name = ? ");
			ps.setString(1, food);
			ResultSet rs = ps.executeQuery();
			
			ps = getConnection().prepareStatement(
					"INSERT INTO uber.intake (amount, user_iduser, idfood)" + 
						" VALUES (?, ?, ?)");
			ps.setDouble(1, amount);
			ps.setInt(2, userID);
			rs.next();
			ps.setInt(3, rs.getInt(1));
			ps.execute();
			connection.close();
			// amount date user_iduser id intake idfood 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String args[]) {
		FoodSearch foodsearch = new FoodSearch();
		foodsearch.init();
		addFood("aardappelen", 3, 1);
		System.out.println("shit is gefixed");
		
	}
}
