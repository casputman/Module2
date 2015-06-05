package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class FoodSearch extends core.MyServlet {
	private static final long serialVersionUID = 1L;
	
    
    // --- Commands --------------------------------------------------------------------------
    

    public ArrayList<String> foodsearch(String food) {
        super.init();
    	PreparedStatement ps;
    	ArrayList<String> foods = new ArrayList<String>();
		try {
			ps = super.getConnection().prepareStatement(
					" SELECT  name "
							+ " FROM    uber.stdfood "
							+ " WHERE   name LIKE ? ");
	    	ps.setString(1, food + "%"); 
	    	ResultSet rs = ps.executeQuery();
	    	int i = 0;
	    		while (rs.next() && i<=5) {
	    		    if (rs.isLast() || i == 5) {
	    		        foods.add(rs.getString(1));
	    		    } else {
	    			foods.add(rs.getString(1) + ":");
	    		    }
	    			i++;
	    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return foods;
    }
    
    public ArrayList<ArrayList<String>> foodShow(int iduser) {
        super.init();
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
                gs = super.getConnection().prepareStatement(
                        "SELECT name, calorie "
                        + "FROM uber.stdfood "
                        + "WHERE idfood = ?"
                        );
                gs.setInt(1, Integer.parseInt(rs.getString(1)));
                ResultSet hs = gs.executeQuery();
                while (hs.next()) {
                    foodjez.add(hs.getString(1) + ":");
                    System.out.println("2.1:" + hs.getString(1));
                    foodjez.add(hs.getString(2) + ":");
                    System.out.println("2.2:" + hs.getString(2));       
                }
                foodjes.add(foodjez);
            }
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
