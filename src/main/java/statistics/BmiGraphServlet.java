package statistics;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import javax.print.attribute.standard.DateTimeAtProcessing;


public class BmiGraphServlet extends core.MyServlet {
    private static final long serialVersionUID = 1L;
    
    private static Connection connection;

    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getThisConnection() {
        return connection;
    }
    
    // --- Setters ---------------------------------------------------------------------------
    
    public static void setConnection(Connection connection) {
        BmiGraphServlet.connection = connection;
    }
    
    
    // --- Commands --------------------------------------------------------------------------
    
    public static void calculateCalorieLastWeek(Date date, int userid) {
        PreparedStatement ps;
        try {
            ps = getThisConnection().prepareStatement(
                    " SELECT calorie "
                            + " FROM    uber.intake "
                            + " WHERE user = ? "
                            + " AND date >= ? ");
            ps.setInt(1, userid);
            Date weekAgo = new Date(date.getTime() - 7 * 24 * 2400 * 1000);
            ps.setDate(2, weekAgo);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
        }
    }
    
    public static void main(String args[]) {
        BmiGraphServlet thingy = new BmiGraphServlet();
        thingy.init();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        java.util.Date past7Days = cal.getTime();
        java.sql.Date sqlDate = new java.sql.Date(past7Days.getTime());
        calculateCalorieLastWeek(sqlDate, 3);
    }
}
