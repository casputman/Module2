package core;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    	System.out.println("registration started");
    	String username = getRequest().getParameter("username");
        String hashpassword = Validation.hashPassword(getRequest().getParameter("password1"));
        Double length = Double.parseDouble(getRequest().getParameter("length"));
        String surname = getRequest().getParameter("surname");
        String firstname = getRequest().getParameter("firstname");
        String email = getRequest().getParameter("email");
        String gender = getRequest().getParameter("gender");
        int age = Integer.parseInt(getRequest().getParameter("age"));
        if(getRequest().getParameter("password1").equals(getRequest().getParameter("password2"))){
        	PreparedStatement ps;
        	try{
        		ps = getConnection().prepareStatement("INSERT INTO uber.user (surname, firstname, Length, username, email, password, gender, age)" + 
        				" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        		ps.setString(1, surname);
        		ps.setString(2, firstname);
        		ps.setDouble(3, length);
        		ps.setString(4, username);
        		ps.setString(5, email);
        		ps.setString(6, hashpassword);
        		ps.setString(7, gender);
        		ps.setString(8, surname);
        		ps.execute();
        		ps.close();
        		getRequest().getRequestDispatcher("/start").forward(getRequest(), getResponse());
        	}catch (SQLException e) {
    			// TODO Auto-generated catch block 
    			e.printStackTrace();
    		}
        } else {
        	 getRequest().setAttribute("passNotEqual", true);
        	 getRequest().getRequestDispatcher("/register.jsp").forward(getRequest(), getResponse());
        }
    }
}
