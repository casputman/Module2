package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class FoodSearch extends core.MyServlet {
	private static final long serialVersionUID = 1L;
	
    
    // --- Commands --------------------------------------------------------------------------
    

    public ArrayList<String> foodsearch(String food) {
    	PreparedStatement ps;
    	ArrayList<String> foods = new ArrayList<String>();
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
    
    public void main(String args[]) { 
    	super.init();
    	ArrayList<String> food = foodsearch("aar");
    	System.out.println(food.toString());
    }
}
