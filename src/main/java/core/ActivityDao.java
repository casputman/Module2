package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import searches.DetermineActivityColumn;

public class ActivityDao {
    
    private static final int MAX_SEARCH_RESULTS = 100;
    
    /**
     * Performs an activity search.
     * 
     * @param activityName the activity to search for
     * @return             a list with matches
     */
    public static List<Activity> search(String activityName) {
        final List<Activity> results = new ArrayList<Activity>();
        
        try {
            final PreparedStatement ps = Validation.getConnection().prepareStatement(
                      " SELECT  * "
                    + " FROM    uber.activities "
                    + " WHERE   name LIKE ? ");
            ps.setString(1, "%" + activityName + "%");
            final ResultSet rs = ps.executeQuery();
            
            // Loop through results.
            for (int i = 0; i < MAX_SEARCH_RESULTS && rs.next(); i++) {
                final Activity activity = new Activity();
                activity.setName(rs.getString("name"));
                activity.setKg59(rs.getDouble("kg59"));
                activity.setKg70(rs.getDouble("kg70"));
                activity.setKg81(rs.getDouble("kg81"));
                activity.setKg92(rs.getDouble("kg92"));
                results.add(activity);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Returns all usages if the passed user and the current date.
     * 
     * @param iduser the user id
     * @return       a list of all usages
     */
    public static List<Usage> getActivityUsageToday(int iduser) {
        
        final List<Usage> usages = new ArrayList<Usage>();
        final DetermineActivityColumn activitykg = new DetermineActivityColumn();
        
        try {
            final PreparedStatement ps = Validation.getConnection().prepareStatement (
                      " SELECT  * "
                    + " FROM    uber.usage u "
                    + " JOIN    uber.activities a    ON u.activities_name = a.name " 
                    + " WHERE   u.user_iduser = ? "
                    + "   AND   u.usagedate = current_date ");
            ps.setInt(1, iduser);
            final ResultSet rs = ps.executeQuery();
            
            final int activityColumn = activitykg.determineActivityColumn(iduser);
            final String column = "kg" + Integer.toString(activityColumn);
            
            // Loop through each usage of the current user and current date.
            while (rs.next()) {
                final Activity activity = new Activity();
                activity.setName(rs.getString("activities_name"));
                activity.setKg59(rs.getDouble("kg59"));
                activity.setKg70(rs.getDouble("kg70"));
                activity.setKg81(rs.getDouble("kg81"));
                activity.setKg92(rs.getDouble("kg92"));
                activity.setSelectedCalories(rs.getDouble(column));
                
                final Usage usage = new Usage();
                usage.setActivity(activity);
                usage.setIduser(iduser);
                usage.setAmount(rs.getDouble("amount"));
                usage.setIdusage(rs.getInt("idusage"));
                usage.setUsageDate(rs.getDate("usagedate"));
                
                usages.add(usage);
            }
            
           
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usages;
    }
    
    /**
     * Adds activity usage.
     * 
     * @param usage the usage
     */
    public static void addActivityUsage(Usage usage) {

        try {
            final PreparedStatement ps = Validation.getConnection().prepareStatement(
                    "INSERT INTO uber.usage (amount, user_iduser, activities_name)" + 
                    " VALUES (?, ?, ?)");
            ps.setDouble(1, usage.getAmount());
            ps.setInt(2, usage.getIduser());
            ps.setString(3, usage.getActivity().getName());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
