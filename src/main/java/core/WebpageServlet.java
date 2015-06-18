package core;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebpageServlet extends MyServlet {
    private static final long serialVersionUID = 1L;

    // --- Instance variables ----------------------------------------------------------------
    
    // --- Getters ---------------------------------------------------------------------------

    // --- Request handlers ------------------------------------------------------------------
    
    /**
     * Any GET requests concerning the web pages.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        
        if (!getUrlParts().get(0).matches("register|start|contact|startAbout|calculator")  && !Validation.validateOrForward(request, response)) {
            return;
        }
        
        
        switch (getUrlParts().get(0)) {
        default:
            // Just show the page.
            forwardTo("/" + getUrlParts().get(0) + ".jsp");
            break;
        }
    }
    
    /**
     * Any POST request concerning the web pages.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
        
        switch (getUrlParts().get(0)) {
        default:
            // Just show the page.
            forwardTo("/" + getUrlParts().get(0) + ".jsp");
            break;
        }
    }
}
