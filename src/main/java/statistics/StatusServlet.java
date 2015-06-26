package statistics;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searches.CalorieGoal;
import searches.GoalShow;
import core.BmiServlet;
import core.User;
import core.Validation;
import core.VetPercentageServlet;

public class StatusServlet extends core.MyServlet {
    private static final long serialVersionUID = 1L;
    private User user;
    Weight weightClass = new Weight();

    /**
     * Any GET requests concerning the status.
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
            user = ((core.User) request.getSession().getAttribute("user"));
            switch (getUrlParts().get(0)) {
            case "Balance": 
                CalorieGoal CGoal = new CalorieGoal();
                double balance = Math.round(CGoal.balance(user.getIdUser()));
                user.setBMIVPT(user.getIdUser());
                request.setAttribute("mybalance", balance);
                double BMI = user.getUserBMI().getBMI();
                request.setAttribute("myBMI", BMI);
                double vet = user.getUserVet().getVPT();
                request.setAttribute("myVET", vet);
                request.setAttribute("myGoal", goalShow.getGoalBean(user));
                double weight = weightClass.getWeight(user.getIdUser());
                request.setAttribute("myWeight", weight);
                Date weightDate = weightClass.getWeigtDate(user.getIdUser());
                long weightDateTime = weightDate.getTime();
                Date date = new Date();
                long dateTime = date.getTime();
                long differance = dateTime - weightDateTime;
                long differanceDay = Math.round(differance / ((long)(24*60*60*1000))); 
                System.out.println("differanceDay: " + differanceDay + " dateTime: " + dateTime + " weightDateTime: " + weightDateTime);
                request.setAttribute("myTimeAgo", differanceDay);
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
            case "updateWeight":
                double weight = Double.parseDouble(request.getParameter("weight"));
                int userid = user.getIdUser();
                String x = request.getParameter("width");
                boolean check = true;
                try
                {
                  Double.parseDouble(x);
                }
                catch(NumberFormatException e)
                {
                  check = false;
                }
                System.out.println("widht :" + request.getParameter("width") + ":");
                if (check) {
                    double width = Double.parseDouble(x);
                    weightClass.setWidth(userid, width, weight);
                    VetPercentageServlet vet = new VetPercentageServlet();
                    vet.determineVPT(user);
                } else {
                    weightClass.setWeight(userid, weight);
                }
                BmiServlet bmiServlet = new BmiServlet();
                bmiServlet.determineBMI(user);
                forwardTo("/Balance");
                break;

            }
        }
        doGet(getRequest(), getResponse());
    }
}