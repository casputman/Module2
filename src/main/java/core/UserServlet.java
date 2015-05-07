package core;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends MyServlet {
    private static final long serialVersionUID = 1L;

    // --- Instance variables ----------------------------------------------------------------
    
    
    // --- Getters ---------------------------------------------------------------------------

    
    
    
    // --- Request handlers ------------------------------------------------------------------
    
    /**
     * Any GET requests concerning the user.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        
        String action = (String) request.getParameter("action");
        action = action == null ? "" : action;
        
        String[] parts = getRequest().getRequestURI().split("/");
        String urlAction = parts[parts.length - 1];
        
        switch (urlAction) {
        case "logout":
            doLogout();
            break;
        case "login": default:
            getRequest().getRequestDispatcher("/login.jsp").forward(getRequest(), getResponse());
            break;
        }
    }
    
    /**
     * Logging in.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
        
        switch ((String) request.getParameter("action")) {
        case "login":
            doLogin();
            break;
        case "register":
            doRegister();
            break;
        }
    }
    
    private void doLogin() throws ServletException, IOException {
        String username = getRequest().getParameter("username");
        String password = getRequest().getParameter("password");
        boolean keepData = "keepData".equals(getRequest().getParameter("keepData"));
        
        User user;
        if ((user = Validation.validate(username, password)) != null) {
            //TODO: save login in session.
            Validation.save(user, keepData, getRequest(), getResponse());
            getRequest().setAttribute("user", user);
            getRequest().getRequestDispatcher("/welcome.jsp").forward(getRequest(), getResponse());
        } else {
            getRequest().setAttribute("loginError", true);
            getRequest().getRequestDispatcher("/login.jsp").forward(getRequest(), getResponse());
        }
    }
    
    private void doLogout() throws ServletException, IOException {
        Validation.destroy(getRequest(), getResponse());
        getRequest().getRequestDispatcher("/login.jsp").forward(getRequest(), getResponse());
    }
    
    private void doRegister() throws ServletException, IOException {
        
    }
}
