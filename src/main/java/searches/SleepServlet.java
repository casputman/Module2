/**
 * 
 */
package searches;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Julik
 *
 */
public class SleepServlet extends core.MyServlet {

    
    /**
     * Any POST request concerning the sleep.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doPost(request, response);
            SleepAdd sleepAdd = new SleepAdd();
            switch (getUrlParts().get(0)) {
            case "sleep": 
                String sleepstart = request.getParameter("sleepstart");
                String sleepend = request.getParameter("sleepend");
                int sleepstarthour = Integer.parseInt(sleepstart.split(":")[0]);
                int sleepstartmin = Integer.parseInt(sleepstart.split(":")[1]);
                int sleependhour = Integer.parseInt(sleepend.split(":")[0]);
                int sleependmin = Integer.parseInt(sleepend.split(":")[1]);
                double sleepstartper = sleepstartmin / 60;
                double sleependper = sleependmin / 60;
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
                sleepAdd.addSleep(sleepDuration,((core.User) request.getSession().getAttribute("user")).getIdUser());
            }
            }
            }
}
