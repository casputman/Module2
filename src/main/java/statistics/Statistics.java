package statistics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.User;
import core.Validation;



@Path("/statistics")
public class Statistics {
    

    @Context
    private UriInfo uriInfo;
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;
    
    public Statistics(@Context UriInfo uriInfo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.response = response;
        System.out.println("[rest: Statistics, uri: " + uriInfo.getRequestUri().toString() + "]");
    }

    @GET
    @Path("bmi")
    @Produces(MediaType.APPLICATION_JSON)
    public String doJsonGetBmi() {
        // Check validation.
        final String validationErrorOutput;
        if ((validationErrorOutput = checkValidation()) != null) {
            return validationErrorOutput;
        }
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);

        final TreeMap<Integer, ArrayList<Object>> data = new TreeMap<Integer, ArrayList<Object>>(); 
        
        
        try {
            final PreparedStatement ps1 = Validation.getConnection().prepareStatement(
                    "SELECT * FROM uber.bmi WHERE user_iduser = ? ORDER BY \"Date\";");
            ps1.setInt(1, ((User) request.getAttribute("user")).getIdUser());
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                final int key = Integer.parseInt(rs1.getString("Date").replaceAll("[^\\d]", ""));
                // If the data point already exists.
                if (data.containsKey(key)) {
                    data.get(key).set(1, rs1.getDouble("bmi"));
                }
                // If this is a new entry.
                else {
                    final ArrayList<Object> row = new ArrayList<Object>();
                    row.add(rs1.getString("Date"));
                    row.add(rs1.getDouble("bmi"));
                    row.add(null);
                    data.put(key, row);
                }
            }
            
            // Get average BMI's.
            final PreparedStatement ps2 = Validation.getConnection().prepareStatement(
                    "SELECT \"Date\" FROM uber.bmi;");
            final ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                final PreparedStatement ps3 = Validation.getConnection().prepareStatement(
                        "SELECT avg(bmi) "
                        + "FROM uber.bmi b, ( "
                        + "    SELECT  user_iduser, max(\"Date\") as maxDate "
                        + "    FROM    uber.bmi "
                        + "    WHERE   \"Date\" <= ? "
                        + "    GROUP BY user_iduser "
                        + ") r "
                        + "WHERE   b.user_iduser = r.user_iduser "
                        + "  AND   b.\"Date\" = r.maxDate ");
                ps3.setDate(1, rs2.getDate(1));
                final ResultSet rs3 = ps3.executeQuery();
                if (!rs3.next()) {
                    continue;
                }
                final int key = Integer.parseInt(rs2.getString("Date").replaceAll("[^\\d]", ""));
                if (data.containsKey(key)) {
                    data.get(key).set(2, rs3.getFloat(1));
                } else {
                    final ArrayList<Object> rowData = new ArrayList<Object>();
                    rowData.add(rs2.getString("Date"));
                    rowData.add(null);
                    rowData.add(rs3.getFloat(1));
                    data.put(key, rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return getInternalServerError();
        }

        
        // Now cast the data map to a simple list.
        final ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
        for (Map.Entry<Integer, ArrayList<Object>> entry : data.entrySet()) {
            dataList.add(entry.getValue());
        }
        
        map.put("data", dataList);
        
        // Send back to client.
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return getInternalServerError();
        }
    }
    
    @GET
    @Path("fat")
    @Produces(MediaType.APPLICATION_JSON)
    public String doJsonGetFat() {
        // Check validation.
        final String validationErrorOutput;
        if ((validationErrorOutput = checkValidation()) != null) {
            return validationErrorOutput;
        }
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);
        
        final TreeMap<Integer, ArrayList<Object>> data = new TreeMap<Integer, ArrayList<Object>>(); 
        
        try {
            // Get current fat percentage entries.
            final PreparedStatement ps1 = Validation.getConnection().prepareStatement(""
                    + "SELECT * FROM uber.fat WHERE user_iduser = ? ORDER BY \"Date\";");
            ps1.setInt(1, ((User) request.getAttribute("user")).getIdUser());
            final ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                final int key = Integer.parseInt(rs1.getString("Date").replaceAll("[^\\d]", ""));
                // If the data point already exists.
                if (data.containsKey(key)) {
                    data.get(key).set(1, rs1.getDouble("fatpercentage"));
                }
                // If this is a new entry.
                else {
                    final ArrayList<Object> row = new ArrayList<Object>();
                    row.add(rs1.getString("Date"));
                    row.add(rs1.getDouble("fatpercentage"));
                    row.add(null);
                    data.put(key, row);
                }
            }

            
            // Get average fatpercentages.
            final PreparedStatement ps2 = Validation.getConnection().prepareStatement(
                    "SELECT \"Date\" FROM uber.fat;");
            final ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                final PreparedStatement ps3 = Validation.getConnection().prepareStatement(
                        "SELECT avg(fatpercentage) "
                        + "FROM uber.fat f, ( "
                        + "    SELECT  user_iduser, max(\"Date\") as maxDate "
                        + "    FROM    uber.fat "
                        + "    WHERE   \"Date\" <= ? "
                        + "    GROUP BY user_iduser "
                        + ") r "
                        + "WHERE   f.user_iduser = r.user_iduser "
                        + "  AND   f.\"Date\" = r.maxDate ");
                ps3.setDate(1, rs2.getDate(1));
                final ResultSet rs3 = ps3.executeQuery();
                if (!rs3.next()) {
                    continue;
                }
                final int key = Integer.parseInt(rs2.getString("Date").replaceAll("[^\\d]", ""));
                if (data.containsKey(key)) {
                    data.get(key).set(2, rs3.getFloat(1));
                } else {
                    final ArrayList<Object> rowData = new ArrayList<Object>();
                    rowData.add(rs2.getString("Date"));
                    rowData.add(null);
                    rowData.add(rs3.getFloat(1));
                    data.put(key, rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return getInternalServerError();
        }
        
        // Now cast the data map to a simple list.
        final ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
        for (Map.Entry<Integer, ArrayList<Object>> entry : data.entrySet()) {
            dataList.add(entry.getValue());
        }
        
        map.put("data", dataList);
        
        // Send back to client.
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return getInternalServerError();
        }
    }
    

    @GET
    @Path("calorie")
    @Produces(MediaType.APPLICATION_JSON)
    public String doJsonGetCalories() {
        // Check validation.
        final String validationErrorOutput;
        if ((validationErrorOutput = checkValidation()) != null) {
            return validationErrorOutput;
        }
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);
        
        final TreeMap<Integer, ArrayList<Object>> data = new TreeMap<Integer, ArrayList<Object>>(); 
        final int iduser = ((User) request.getAttribute("user")).getIdUser();
        
        try {
            // Get calorie intake entries.
            final PreparedStatement ps1 = Validation.getConnection().prepareStatement(
                      " SELECT  i.intaketime::date as \"date\", "
                    + "         sum(i.amount * f.calorie) as intake "
                    + " FROM    uber.intake i "
                    + " JOIN    uber.stdfood f  ON i.idfood = f.idfood "
                    + " WHERE   i.user_iduser = ? "
                    + " GROUP BY i.intaketime::date ");
            ps1.setInt(1, iduser);
            final ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                final int key = Integer.parseInt(rs1.getString("date").replaceAll("[^\\d]", ""));
                
                final ArrayList<Object> rowData = new ArrayList<Object>();
                rowData.add(rs1.getString("date"));
                rowData.add(rs1.getDouble("intake"));
                rowData.add(null);
                data.put(key, rowData);
            }
            
            // Get calorie usage.
            // First get weights.
            final TreeMap<Integer, String> weights = new TreeMap<>();
            final PreparedStatement ps2 = Validation.getConnection().prepareStatement(
                      " SELECT   weightdate as \"date\", avg(weight) as weight "
                    + " FROM    uber.weight "
                    + " WHERE   user_iduser = ? "
                    + " GROUP BY weightdate ORDER BY weightdate; ");
            ps2.setInt(1, iduser);
            final ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                final int key = Integer.parseInt(rs2.getString("date").replaceAll("[^\\d]", ""));
                weights.put(key, User.getWeightColumn(rs2.getDouble("weight")));
            }
            
            // Get usage entries.
            final PreparedStatement ps3 = Validation.getConnection().prepareStatement(
                      " SELECT  u.amount, a.kg59, a.kg70, a.kg81, a.kg92, u.usagedate as \"date\" "
                    + " FROM    uber.usage u "
                    + " JOIN    uber.activities a  ON u.activities_name = a.name "
                    + " WHERE   u.user_iduser = ? "
                    + " ORDER BY u.usagedate ");
            ps3.setInt(1, iduser);
            final ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                final int key = Integer.parseInt(rs3.getString("date").replaceAll("[^\\d]", ""));
                final double delta = rs3.getDouble("amount") * rs3.getDouble(getWeightColumn(weights, key));
                
                if (data.containsKey(key)) {
                    final Double dayUsageValue = (Double) data.get(key).get(2);
                    final double dayUsage = dayUsageValue == null ? 0 : dayUsageValue;
                    data.get(key).set(2, dayUsage + delta);
                } else {
                    final ArrayList<Object> rowData = new ArrayList<Object>();
                    rowData.add(rs3.getString("date"));
                    rowData.add(null);
                    rowData.add(delta);
                    data.put(key, rowData);
                }
            }
            
            // Next: calorie netto usage
                
        } catch (SQLException e) {
            e.printStackTrace();
            return getInternalServerError();
        }
        
        // Now cast the data map to a simple list.
        final ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
        for (Map.Entry<Integer, ArrayList<Object>> entry : data.entrySet()) {
            dataList.add(entry.getValue());
        }
        
        map.put("data", dataList);
        
        // Send back to client.
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return getInternalServerError();
        }
    }
    
    /**
     * Gets the weight column according to a <Integer date, String weightColumn> map
     * where date is in the format of YYYYMMdd.
     * 
     * @param map  the weight column map
     * @param date the request date
     * @return     the weight column; "kg81" if a valid value could not be found
     */
    private String getWeightColumn(TreeMap<Integer, String> map, int date) {
        if (map != null && map.size() > 0) {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getKey() < date) {
                    return entry.getValue();
                }
            }
        }
        return "kg81";
    }

    
    private String checkValidation() {
        if (!Validation.validated(request)) {
            return "{\"code\":403}";
        } else {
            return null;
        }
    }
    
    private String getInternalServerError() {
        return "{\"code\":501}";
    }
}
