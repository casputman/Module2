package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    
    private static final int MAX_SEARCH_RESULTS = 5;

    /**
     * Perform a food search in the global list of foods and in the user's personal list. 
     * 
     * @param foodName  name of the food
     * @param iduser    id of the user
     * @return          the found foods (maximum 5)
     */
    public static List<Food> search(String foodName, int iduser) {

        final ArrayList<Food> foods = new ArrayList<Food>();
        try {
            // Fetch search results.
            final PreparedStatement ps = Validation.getConnection().prepareStatement(
                      " SELECT  name, unit, amount "
                    + " FROM    uber.stdfood "
                    + " WHERE   name LIKE ? "
                    + "   AND ( iduser IS NULL OR iduser = ? )");
            ps.setString(1, foodName.toLowerCase() + "%"); 
            ps.setInt(2, iduser);
            final ResultSet rs = ps.executeQuery();
            // Loop through results.
            for (int i = 0; i < MAX_SEARCH_RESULTS && rs.next(); i++) {
                
                final Food food = new Food();
                food.setName(foodName.toLowerCase());
                food.setUnit(rs.getString("unit") == null ? rs.getString("unit") : "stuk");
                food.setAmount(rs.getDouble("amount"));
                foods.add(food);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }
    
    /**
     * Gets the food intakes for today.
     * 
     * @param iduser the user id
     * @return       a list of intakes
     */
    public static List<Intake> getFoodIntakeToday(int iduser) {

        final ArrayList<Intake> intakes = new ArrayList<Intake>();
        try {
            final PreparedStatement ps = Validation.getConnection().prepareStatement (
                      " SELECT  i.amount, f.name, f.calorie "
                    + " FROM    uber.intake i "
                    + " JOIN    uber.stdfood f  ON i.idfood = f.idfood "
                    + " WHERE   user_iduser = ? "
                    + "   AND   intaketime::timestamp::date = current_date ");
            ps.setInt(1, iduser);
            final ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                intakes.add(
                    new Intake(
                        rs.getDouble("amount"), 
                        new Food(
                            rs.getString("name"), 
                            rs.getDouble("calorie"))));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return intakes;
    }
}
