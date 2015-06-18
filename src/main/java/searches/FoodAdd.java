package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodAdd extends core.MyServlet{
    private static final long serialVersionUID = 1L;


   
    
	public void addFood(String food, Integer userID, double amount) {
	    PreparedStatement ps;
		try {
		    System.out.println("fuck1:" + food.trim());
		    food = food.split(":")[1].trim();
			ps = getConnection().prepareStatement(
					" SELECT  idfood "
							+ " FROM    uber.stdfood "
							+ " WHERE   name = ? ");
			ps.setString(1, food.trim());
			ResultSet rs = ps.executeQuery();
			rs.next();
			System.out.println("fuck: " + rs.getFetchSize());
			ps = getConnection().prepareStatement(
					"INSERT INTO uber.intake (amount, user_iduser, idfood)" + 
						" VALUES (?, ?, ?)");
			ps.setDouble(1, amount);
			ps.setInt(2, userID);
			System.out.println("fuck: " + rs.getInt(1));
			ps.setInt(3, rs.getInt(1));
			ps.execute();
			ps.close();
			// amount date user_iduser id intake idfood 
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
	}
	
	public void addFoodToDB(double calorie, double amount, String unit, double protein, double carbon, double fat, double iduser, String name) {
	    PreparedStatement ps;
	    try {
	        ps = getConnection().prepareStatement(
	                "INSERT INTO uber.stdfood (calorie, amount, unit, protein, carbon, fat, iduser, name) "
	                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)" );
	                ps.setDouble(1, calorie);
	                ps.setDouble(2, amount);
	                ps.setString(3, unit);
	                ps.setDouble(4, protein);
	                ps.setDouble(5, carbon);
	                ps.setDouble(6, fat);
	                ps.setDouble(7, iduser);
	                ps.setString(8, name);
	                ps.execute();
	                ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void main(String args[]) {
		FoodAdd foodadd = new FoodAdd();
		foodadd.addFoodToDB(100,100,"gr.",0,0,0,1,"bier");
		System.out.println("shit is gefixed");
	}
}
