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
            
            if (request.getSession().getAttribute("user") != null) {
         		user = core.User.fromIdUser(((core.User) request.getSession()
         				.getAttribute("user")).getIdUser());
         	}
            
            switch (getUrlParts().get(0)) {
            case "SetGoal":
                forwardTo("/SetGoal.jsp");
                break;
            case "GoalDetails":
            	doSetGoal(goalShow);
                forwardTo("/GoalDetails.jsp");
                break;
            } 
        }
    }
    
    private void doSetGoal(GoalShow goalShow) {
    	int goalWeight = Integer.parseInt(getRequest().getParameter("goalWeight"));
    	String goalDate = getRequest().getParameter("goalDate");
    	
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

	/**
     * Any POST request concerning the user.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doPost(request, response);
            FoodAdd foodAdd = new FoodAdd();
            switch (getUrlParts().get(0)) {
            case "intake": 
                foodAdd.addFood(getRequest().getParameter("food"), ((core.User) request.getSession().getAttribute("user")).getIdUser(), Double.parseDouble(getRequest().getParameter("amount")));
                forwardTo("/Intake");
                break;
            case "moreFood":
                System.out.println("holooaos: " + getRequest().getParameter("calorie") );
                System.out.println("holooaos1: " + getRequest().getParameter("amount") );
                System.out.println("holooaos2: " + getRequest().getParameter("unit") );
                foodAdd.addFoodToDB(Double.parseDouble(getRequest().getParameter("calorie")),Double.parseDouble(getRequest().getParameter("amount")), getRequest().getParameter("unit"), 0, 0, 0, ((core.User) request.getSession().getAttribute("user")).getIdUser(), getRequest().getParameter("name"));
                forwardTo("/Intake");
                break;
            }
            // No page selected.
            doGet(getRequest(), getResponse());
        } 
    }

}