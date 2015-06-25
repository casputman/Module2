package searches;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.User;
import core.Validation;

public class GoalServlet extends core.MyServlet {
	private User user;
	private static final long serialVersionUID = 1L;
	
	/**
     * Any GET requests concerning the food.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doGet(request, response);
            GoalShow goalShow = new GoalShow();
            System.out.println("text:" + getUrlParts().get(0));
            if (!getUrlParts().get(0).equals("register") && !Validation.validateOrForward(request, response)) {
                return;
            }
            switch (getUrlParts().get(0)) {
            case "SetGoal":
                forwardTo("/SetGoal.jsp");
                int goalWeight = Integer.parseInt(request.getParameter("goalWeight"));
                String goalDate = request.getParameter("goalDate");
                if (user != null) {
            		int id = user.getIdUser();
            		goalShow.setGoal(goalWeight, goalDate, id);
            	} else {
            		error("User is null");
            	}
                break;
            case "GoalDetails":
                forwardTo("/GoalDetails.jsp");
                break;
            } 
        }
    }
    
    private void doSetGoal(GoalShow goalShow) {
    	int goalWeight = Integer.parseInt(getRequest().getParameter("goalWeight"));
    	String goalDate = getRequest().getParameter("goalDate");
    	System.out.println(goalDate + 1);
    	if (user != null) {
    		int id = user.getIdUser();
    		goalShow.setGoal(goalWeight, goalDate, id);
    	} else {
    		error("User is null");
    	}
	}
    
    public void error(String arg) {
		System.err.println("ERROR: " + arg);	
	}


}