package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class FoodSearch extends core.MyServlet {
	private static final long serialVersionUID = 1L;
	
    
    // --- Commands --------------------------------------------------------------------------
    

    public ArrayList<String> foodsearch(String food, int userID) {
    	PreparedStatement ps;
    	ArrayList<String> foods = new ArrayList<String>();
		try {
			ps = getConnection().prepareStatement(
					" SELECT  name, unit, amount "
							+ " FROM    uber.stdfood "
							+ " WHERE   name LIKE ? "
							+ " AND ( iduser IS NULL "
							+ " OR iduser = ? )");
	    	ps.setString(1, food.toLowerCase() + "%"); 
	    	ps.setInt(2, userID);
	    	ResultSet rs = ps.executeQuery();
	    	int i = 0;
	    		while (rs.next() && i<5) {
	    		    String value = rs.getString(2);
	    		    int amount = rs.getInt(3);
                    if (value == null) {
                        value = "stuk";
                    }
	    		    if (rs.isLast() || i == 5) {
	    		        foods.add(amount + "::" + value + "::" + rs.getString(1));
	    		    } else {
	    		        System.out.println("kijk deza: " + " + " + amount + rs.getString(2) + " +  " + rs.getString(1));
	    		     
	    			foods.add(amount + "::" + value + "::" + rs.getString(1) + ":");
	    		    }
	    			i++;
	    		}
	    		ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return foods;
    }
    
    public ArrayList<ArrayList<String>> foodShow(int iduser) {
        PreparedStatement ps;
        PreparedStatement gs;
        ArrayList<ArrayList<String>> foodjes = new ArrayList<ArrayList<String>>();
        try {
            System.out.println("dit werkt " + iduser);
            ps = getConnection().prepareStatement (
                    "SELECT idfood, amount "
                    + "FROM uber.intake " 
                    + "WHERE user_iduser = ? "
                    + "AND intaketime::timestamp::date = current_date "
                    );
            System.out.println("hier komt ie ook");
            ps.setInt(1, iduser);
            System.out.println("hier komt ie ook");
            ResultSet rs = ps.executeQuery();
            System.out.println("haha komen");
            System.out.println("fuck");
            while (rs.next()) {
                ArrayList<String> foodjez = new ArrayList<String>();
                System.out.println("1:" + rs.getString(1));
                foodjez.add(rs.getString(2) + ":");
                System.out.println("2:" + rs.getString(2));
                gs = getConnection().prepareStatement(
                        "SELECT name, calorie "
                        + "FROM uber.stdfood "
                        + "WHERE idfood = ?"
                        );
                gs.setInt(1, Integer.parseInt(rs.getString(1)));
                ResultSet hs = gs.executeQuery();
                while (hs.next()) {
                    foodjez.add(hs.getString(1) + ":");
                    System.out.println("2.1:" + hs.getString(1));
                    
                    foodjez.add((Integer.parseInt(hs.getString(2)) * Integer.parseInt(rs.getString(2))) + ":");
                    System.out.println("2.2:" + Integer.parseInt(hs.getString(2)) * Integer.parseInt(rs.getString(2)));       
                }
                foodjes.add(foodjez);
                gs.close();
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodjes;
    }
     
    public static void main(String args[]) { 
        FoodSearch foodSearch = new FoodSearch();
    	ArrayList<ArrayList<String>> foodshizzle = foodSearch.foodShow(1);
    	System.out.println(foodshizzle.toString());
    }
}
