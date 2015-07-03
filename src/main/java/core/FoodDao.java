package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    
    private static final int MAX_SEARCH_RESULTS = 5;

    /**
     * Performs a food search in the global list of foods and in the user's personal list. 
     * 
     * @param foodName  name of the food
     * @param iduser    id of the user
     * @return          the found foods (maximum MAX_SEARCH_RESULTS)
     */
    public static List<Food> search(String foodName, int iduser) {

        final ArrayList<Food> foods = new ArrayList<Food>();
        try {
            // Fetch search results.
            final PreparedStatement ps = Validation.getConnection().prepareStatement(
                      " SELECT  * "
                    + " FROM    uber.stdfood "
                    + " WHERE   lower(name) LIKE lower(?) "
                    + "   AND ( iduser IS NULL OR iduser = ? )");
            ps.setString(1, "%" + foodName + "%"); 
            ps.setInt(2, iduser);
            final ResultSet rs = ps.executeQuery();
            // Loop through results.
            for (int i = 0; i < MAX_SEARCH_RESULTS && rs.next(); i++) {
                final Food food = new Food();
                food.setIdfood(rs.getInt("idfood"));
                food.setName(rs.getString("name"));
                food.setUnit(rs.getString("unit") == null ? "stuk" : rs.getString("unit"));
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
    
    /**
     * Adds a food intake. 
     * 
     * @param iduser the user id
     * @param intake the intake instance. If it's null it doesn't do anything
     */
    public static void addFoodIntake(int iduser, Intake intake) {
        if (intake == null) {
            return;
        }

        try {
            final PreparedStatement ps = Validation.getConnection().prepareStatement(
                    " INSERT INTO uber.intake (amount, user_iduser, idfood) " + 
                    " VALUES (?, ?, ?)");
            ps.setDouble(1, intake.getAmount());
            ps.setInt(2, iduser);
            ps.setInt(3, intake.getFood().getIdfood());
            ps.execute();
            ps.close(); 
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }
    
    /**
     * Adds a food.
     * 
     * @param food   the food
     */
    public static void addFood(Food food) {
        
        try {
            final PreparedStatement ps = Validation.getConnection().prepareStatement(
                "INSERT INTO uber.stdfood (calorie, amount, unit, protein, carbon, fat, iduser, name) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)" );
            ps.setDouble(1, food.getCalorie() == null ? 0 : food.getCalorie());
            ps.setDouble(2, food.getAmount() == null ? 1 : food.getAmount());
            ps.setString(3, food.getUnit() == null ? "stuk" : food.getUnit());
            ps.setDouble(4, food.getProtein() == null ? 0 : food.getProtein());
            ps.setDouble(5, food.getCarbon() == null ? 0 : food.getCarbon());
            ps.setDouble(6, food.getFat() == null ? 0 : food.getFat());
            ps.setDouble(7, food.getIduser() == null ? null : food.getIduser());
            ps.setString(8, food.getName());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
