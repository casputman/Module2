package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodAdd extends core.MyServlet{
    private static final long serialVersionUID = 1L;


   
    
	public void addFood(String food, Integer userID, double amount) {
	    super.init();
	    PreparedStatement ps;
		try {
		    System.out.println("fuck1:" + food.trim());
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
			// amount date user_iduser id intake idfood 
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
	}
	public void main(String args[]) {
		super.init();
		addFood("aardappelen", 3, 1);
		System.out.println("shit is gefixed");
	}
}
