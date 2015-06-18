package statistics;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        System.out.println("[rest: Statistics]");
    }
    

    @GET
    @Path("{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String doJsonGetType(@PathParam("type") String type) {
        // Check validation.
        final String validationErrorOutput;
        if ((validationErrorOutput = checkValidation()) != null) {
            return validationErrorOutput;
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);
        
        // Send back to client.
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return getInternalServerError();
        }
    }
    
            final PreparedStatement ps1 = Validation.getConnection().prepareStatement(
                    "SELECT * FROM uber.bmi WHERE user_iduser = ? ORDER BY \"Date\";");
            ps1.setInt(1, ((User) request.getAttribute("user")).getIdUser());
            final ResultSet rs = ps1.executeQuery();
                final ArrayList<Object> rowData = new ArrayList<Object>();
                rowData.add(null);
                data.add(rowData);
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
                        + "  AND   b.\"Date\" = r.maxDate "
                        + "");
                ps3.setDate(1, rs2.getDate(1));
                final ResultSet rs3 = ps3.executeQuery();
                if (!rs3.next()) {
                    continue;
                }
                final ArrayList<Object> rowData = new ArrayList<Object>();
                rowData.add(rs2.getString("Date"));
                rowData.add(null);
                rowData.add(rs3.getFloat(1));
            final PreparedStatement ps1 = Validation.getConnection().prepareStatement(""
            ps1.setInt(1, ((User) request.getAttribute("user")).getIdUser());
            final ResultSet rs = ps1.executeQuery();
                final ArrayList<Object> rowData = new ArrayList<Object>();
                rowData.add(null);
                data.add(rowData);
            }

            
            // Get average BMI's.
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
                        + "  AND   f.\"Date\" = r.maxDate "
                        + "");
                ps3.setDate(1, rs2.getDate(1));
                final ResultSet rs3 = ps3.executeQuery();
                if (!rs3.next()) {
                    continue;
                }
                final ArrayList<Object> rowData = new ArrayList<Object>();
                rowData.add(rs2.getString("Date"));
                rowData.add(null);
                rowData.add(rs3.getFloat(1));
    
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
