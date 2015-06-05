package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActivitySearch extends core.MyServlet{
    

    private static final long serialVersionUID = 1L;   
    
    public ArrayList<String> activitySearch(String activity) {
        PreparedStatement ps;
        ArrayList<String> activities = new ArrayList<String>();
        try {
            ps = getConnection().prepareStatement(
                    " SELECT  name "
                            + " FROM uber.activities "
                            + " WHERE name LIKE ? ");
            ps.setString(1, activity + "%");
            ResultSet rs = ps.executeQuery();
            int i = 0;
                while (rs.next() && i<=5) {
                    activities.add(rs.getString(1));
                    i++;
                }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return activities;
    }
    
    public void main(String args[]) { 
        ActivitySearch activitySearch = new ActivitySearch();
        activitySearch.init();
        ArrayList<String> activity = activitySearch("Walk");
        System.out.println(activity.toString());
    }
}
