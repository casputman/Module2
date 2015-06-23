package statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searches.ActivitySearch;
import searches.FoodAdd;
import searches.FoodSearch;
import searches.SleepAdd;

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
            FoodSearch foodSearch = new FoodSearch();
            ActivitySearch activitySearch = new ActivitySearch();
            System.out.println("text:" + getUrlParts().get(0));
            switch (getUrlParts().get(0)) {
            
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