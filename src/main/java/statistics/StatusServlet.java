package statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searches.CalorieGoal;
import searches.FoodAdd;
import searches.GoalShow;
import searches.SleepAdd;
import core.User;
import core.Validation;

public class StatusServlet extends core.MyServlet {
    private static final long serialVersionUID = 1L;
    private User user;

    /**
     * Any GET requests concerning the status.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doGet(request, response);
            System.out.println("text:" + getUrlParts().get(0));
            if (!getUrlParts().get(0).equals("register") && !Validation.validateOrForward(request, response)) {
                return;
            }
            user = ((core.User) request.getSession().getAttribute("user"));
            switch (getUrlParts().get(0)) {
            case "Balance": 
                CalorieGoal CGoal = new CalorieGoal();
                double balance = Math.round(CGoal.balance(user.getIdUser()));
                request.setAttribute("mybalance", balance);
                double BMI = user.getUserBMI().getBMI();
                request.setAttribute("myBMI", BMI);
                double vet = user.getUserVet().getVPT();
                request.setAttribute("myVET", vet);
                forwardTo("/inStatus.jsp");
                break;
            case "SetGoal":
                forwardTo("/Balance");
                break;

            }
        }
    }

    public void error(String arg) {
        System.err.println("ERROR: " + arg);    
    }
    /**
     * Any POST request concerning the intake.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doPost(request, response);
            switch (getUrlParts().get(0)) {
            case "GoalDetails":
                int goalWeight = Integer.parseInt(request.getParameter("goalWeight"));
                String goalDate = request.getParameter("goalDate");
                GoalShow goalShow = new GoalShow();
                if (user != null) {
                    int id = user.getIdUser();
                    goalShow.setGoal(goalWeight, goalDate, id);
                } else {
                    error("User is null");
                }
                request.setAttribute("myGoal", goalShow.getGoalBean(user));
                forwardTo("/Balance");
                break;

            }
        }
        doGet(getRequest(), getResponse());
    }
}