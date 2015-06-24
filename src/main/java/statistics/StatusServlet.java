package statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searches.CalorieGoal;
import searches.FoodAdd;
import searches.SleepAdd;
import core.User;

public class StatusServlet extends core.MyServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Any GET requests concerning the status.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doGet(request, response);
            System.out.println("text:" + getUrlParts().get(0));
            switch (getUrlParts().get(0)) {
            case "Balance": 
            CalorieGoal CGoal = new CalorieGoal();
            User user = ((core.User) request.getSession().getAttribute("user"));
            double balance = Math.round(CGoal.balance(user.getIdUser()));
            request.setAttribute("mybalance", balance);
            double BMI = user.getUserBMI().getBMI();
            request.setAttribute("myBMI", BMI);
            double vet = user.getUserVet().getVPT();
            request.setAttribute("myVET", vet);
            forwardTo("/inStatus.jsp");
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
            

            }
        }}
            }