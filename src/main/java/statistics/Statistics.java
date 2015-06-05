package statistics;

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
