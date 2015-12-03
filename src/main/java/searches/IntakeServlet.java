package searches;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Activity;
import core.ActivityDao;
import core.Food;
import core.FoodDao;
import core.Intake;
import core.Usage;
import core.User;
import core.Validation;

public class IntakeServlet extends core.MyServlet{
    private static final long serialVersionUID = 1L;
    private String times = null;

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
            

            // Always get the current food intakes.
            final List<Intake> foodIntake = FoodDao.getFoodIntakeToday(iduser);
            request.setAttribute("foodIntake", foodIntake);
            
            if (times != null) { 
            request.setAttribute("sleepdur", times);
            }
            
            // Always get the current activity usages.
            final List<Usage> activityUsage = ActivityDao.getActivityUsageToday(iduser);
            request.setAttribute("activityUsage", activityUsage);
            
            switch (getAction()) {
            case "foodsearch":
                final String food = getRequest().getParameter("q");
                if (food != null) {
                    request.setAttribute("foodSearchResults", FoodDao.search(food, iduser));
                }
                forwardTo("/Intake.jsp");
                break;
            case "activitysearch":
                final String activity = getRequest().getParameter("q");
                if (activity != null) {
                    request.setAttribute("activitySearchResults", ActivityDao.search(activity));
                }
                forwardTo("/Intake.jsp");
                break;
            default:
                forwardTo("/Intake.jsp");
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
            
            // Check authorization.
            if (!Validation.validateOrForward(request, response)) {
                return;
            }
            final int iduser = ((User) request.getSession().getAttribute("user")).getIdUser();
            SleepAdd sleepAdd = new SleepAdd();
            switch (getAction()) {
            case "intake":
                try {
                    final double amount = Double.parseDouble(getRequest().getParameter("amount"));
                    final int    idfood = Integer.parseInt(getRequest().getParameter("idfood"));
                    FoodDao.addFoodIntake(iduser, new Intake(amount, new Food(idfood)));
                } catch (NullPointerException | NumberFormatException e) {
                    e.printStackTrace();
                }
                redirectTo("intake");
                break;
            case "food":
                try {
                    final String name = getRequest().getParameter("name");
                    final double calorie = Double.parseDouble(getRequest().getParameter("calorie"));
                    final double amount  = Double.parseDouble(getRequest().getParameter("amount"));
                    final String unit = getRequest().getParameter("unit");
                    final Food food = new Food();
                    food.setIduser(iduser);
                    food.setName(name);
                    food.setCalorie(calorie);
                    food.setAmount(amount);
                    food.setUnit(unit);
                    
                    FoodDao.addFood(food);
                } catch (NullPointerException | NumberFormatException e) {
                    e.printStackTrace();
                }

                redirectTo("intake");
                break;
            case "usage": 
                try {
                    final double amount = Double.parseDouble(getRequest().getParameter("amount"));
                    final String activityName = getRequest().getParameter("activity");
                    if (activityName != null) {
                        
                        final Activity activity = new Activity();
                        activity.setName(activityName);
                        
                        final Usage usage = new Usage();
                        usage.setActivity(activity);
                        usage.setAmount(amount);
                        usage.setIduser(iduser);
                        
                        ActivityDao.addActivityUsage(usage);
                    }
                } catch (NullPointerException | NumberFormatException e) {
                    e.printStackTrace();
                }
                
                redirectTo("intake");
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
                times = ("you have slept: " + sleepdurationhour + " hours and " + sleepdurationmin + " minutes");
                
                redirectTo("intake");
                break;
            }
        }
    }
}


