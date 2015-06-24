package searches;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.FoodDao;
import core.Intake;
import core.User;
import core.Validation;

public class IntakeServlet extends core.MyServlet{
    private static final long serialVersionUID = 1L;

    /**
     * Any GET requests concerning the intake.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doGet(request, response);
            
            // Check authorization.
            if (!Validation.validateOrForward(request, response)) {
                return;
            }
            final int iduser = ((User) request.getSession().getAttribute("user")).getIdUser();
            
            ActivitySearch activitySearch = new ActivitySearch();
            switch (getUrlParts().get(0)) {
            case "Intake": 
                List<Intake> foodIntake = FoodDao.getFoodIntakeToday(iduser);
                request.setAttribute("foodIntake", foodIntake);
                
                ArrayList<ArrayList<String>> Activitylist = activitySearch.activityShow(((core.User) request.getSession().getAttribute("user")).getIdUser());
                request.setAttribute("myAct", Activitylist);
                System.out.println(Activitylist);
                forwardTo("/Intake.jsp");
                break;
            case "search":  
                final String food = getRequest().getParameter("q");
                if (food != null) {
                    request.setAttribute("foodSearchResults", FoodDao.search(food, iduser));
                }
                forwardTo("/Intake");
                break;
            case "actsearch":
                String activity = getRequest().getParameter("q");
                ArrayList<String> probActivity = activitySearch.activitySearch(activity);
                System.out.println("hier komen activities: " + probActivity + " dit was de zoekterm: " + activity);
                if (activity != null) {
                    request.setAttribute("activityList", probActivity);
                }
                forwardTo("/Intake");
                break;
            } 
        }
    }
    
    /**
     * Any POST request concerning the intake.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doPost(request, response);
            FoodAdd foodAdd = new FoodAdd();
            SleepAdd sleepAdd = new SleepAdd();
            switch (getUrlParts().get(0)) {
            case "intake": 
              // System.out.println("food = " + getRequest().getParameter("food") + " maybe user: " + ((core.User) request.getSession().getAttribute("user")).getIdUser() + " shizzle: " //getRequest().getParameterNames().toString()
                //);
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
            case "intakeA": 
                ActivityAdd activityAdd = new ActivityAdd();
                //System.out.println("activity = " + getRequest().getParameter("activity") + " maybe user: " + ((core.User) request.getSession().getAttribute("user")).getIdUser() + " shizzle: " //getRequest().getParameterNames().toString()
                //);
                activityAdd.addActivity(getRequest().getParameter("activity"), ((core.User) request.getSession().getAttribute("user")).getIdUser(), Double.parseDouble(getRequest().getParameter("amount")));
                forwardTo("/Intake");
                break;
            case "sleep": 
                int sleepstarthour = Integer.parseInt(request.getParameter("sleepstarthour"));
                System.out.println(sleepstarthour);
                int sleepstartmin = Integer.parseInt(request.getParameter("sleepstartmin"));
                System.out.println(sleepstartmin);
                int sleependhour = Integer.parseInt(request.getParameter("sleependhour"));
                System.out.println(sleependhour);
                int sleependmin = Integer.parseInt(request.getParameter("sleependmin"));
                System.out.println(sleependmin);
                System.out.println();
                double sleepstartper = ((double) sleepstartmin / 60.0);
                double sleependper = ((double) sleependmin / 60.0);
                double sleepStart = sleepstarthour + sleepstartper;
                double sleepEnd = sleependhour + sleependper;
                double sleepDuration;
                if (sleepStart > sleepEnd) {
                    double tmpStart = 24 - sleepStart;
                    sleepDuration = tmpStart + sleepEnd;
                } else if (sleepStart < sleepEnd) {
                    sleepDuration = sleepEnd - sleepStart;
                } else {
                    sleepDuration = 0;
                }
                System.out.println("slaapjez: " + sleepDuration + " begin " + sleepstarthour + " eind " + sleependhour + " sleepper " + sleepstartper + " + " + sleependper);
                sleepAdd.addSleep(sleepDuration,((core.User) request.getSession().getAttribute("user")).getIdUser());
                int sleepdurationhour = (int) sleepDuration;
                double sleepdurationmin = (int) Math.round((sleepDuration - sleepdurationhour) * 60);
                System.out.println("hours : " + sleepdurationhour + " minuten: " + sleepdurationmin);
                String times = ("you have slept: " + sleepdurationhour + " hours and " + sleepdurationmin + " minutes");
                request.setAttribute("sleepdur", times);
                forwardTo("/Intake");
                break;
            }
            // No page selected.
            doGet(getRequest(), getResponse());
        } 
    }
}


