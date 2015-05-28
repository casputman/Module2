package core;


import java.io.IOException;

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
        synchronized (request.getSession()) {
            super.doGet(request, response);
            
            switch (getUrlParts().get(0)) {
            case "logout":
                doLogout();
                break;
            case "login": default:
                if (Validation.validated(request)) {
                    forwardTo("/webapp");
                    return;
                }
                forwardTo("/login.jsp");
                break;
            }
        }
    }
    
    /**
     * Any POST request concerning the user.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doPost(request, response);
            
            switch (getUrlParts().get(0)) {
            // page: /login     with parameters: action=login
            case "login":
                if (getAction().equals("login")) {
                    doLogin();
                    return;
                }
                break;
            // page: /register  with parameters: action=register
            case "register":
                if (getAction().equals("register")) {
                    doRegister();
                    return;
                }
                break;
            }
            
            // No page selected.
            doGet(getRequest(), getResponse());
        }
    }
    
    /**
     * POST /login
     * @throws ServletException
     * @throws IOException
     */
    private void doLogin() throws ServletException, IOException {
        String username = getRequest().getParameter("username");
        String password = getRequest().getParameter("password");
        boolean remember = "1".equals(getRequest().getParameter("remember_me"));
        
        User user;
        if ((user = Validation.validate(username, password)) != null) {
            Validation.save(user, remember, getRequest(), getResponse());
            getRequest().setAttribute("user", user);
            getRequest().getRequestDispatcher("/start").forward(getRequest(), getResponse());
        } else {
            getRequest().setAttribute("loginError", true);
            getRequest().getRequestDispatcher("/login.jsp").forward(getRequest(), getResponse());
        }
    }
    
    /**
     * GET /logout
     * @throws ServletException
     * @throws IOException
     */
    private void doLogout() throws ServletException, IOException {
        Validation.destroy(getRequest(), getResponse());
        forwardTo("/login.jsp");
    }
    
    /**
     * POST /register
     * @throws ServletException
     * @throws IOException
     */
    private void doRegister() throws ServletException, IOException {
        
    }
}
