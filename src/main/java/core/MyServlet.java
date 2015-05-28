package core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public abstract class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // --- Class variables -------------------------------------------------------------------
    
    private static final String DB_USERNAME = "di18";
    private static final String DB_PASSWORD = "Q.Z4J2CPz";
    private static final String DB_HOSTNAME = "farm14.ewi.utwente.nl";
    private static final String DB_SCHEMA = "uber";
    private static final int DB_PORT = 5432;
    private static final String DATABASE = "di18";
    

    // --- Instance variables ----------------------------------------------------------------
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Connection connection;
    
    private String action;
    private List<String> urlParts = new LinkedList<>();
    
    // --- Class usage -----------------------------------------------------------------------

    @Override
    public void init() {
        
        // Via a main() method this works.
        String url = "jdbc:postgresql://" + DB_HOSTNAME + ":" + DB_PORT + "/" + DATABASE;
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
            //connection.setAutoCommit(false);
            Statement st = getConnection().createStatement();
            st.execute("SET search_path TO '" + DB_SCHEMA + "'");
            st.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection could not be established: " + e);
            e.printStackTrace();
        }
        
        
        Validation.setConnection(connection);
//        FoodSearch.setConnection(connection);
//        FoodAdd.setConnection(connection);
//        ActivitySearch.setConnection(connection);
//        ActivityAdd.setConnection(connection);
    }
    
    // --- Getters ---------------------------------------------------------------------------

    /**
     * @return the request
     */
    protected HttpServletRequest getRequest() {
        return this.request;
    }

    /**
     * @return the response
     */
    protected HttpServletResponse getResponse() {
        return this.response;
    }
    
    /**
     * @return the database connection
     */
    protected Connection getConnection() {
        return this.connection;
    }
    
    /**
     * @return The action parameter set in a GET or POST variable.
     */
    protected String getAction() {
        return this.action;
    }
    
    /**
     * @return The url parts, which were separated by "/". Any empty values are removed, as is a 
     *         "ubercoaching" value before or after the first "/". If no valid values are left,
     *         the list will still contain one empty value.
     */
    protected List<String> getUrlParts() {
        return this.urlParts;
    }
    
    // --- Request handlers ------------------------------------------------------------------

    /**
     * Save the request and response objects for later use in the Servlet.
     * @param request   The request object.
     * @param response  The response object.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        parseGeneralRequestData();
        System.out.println(urlParts);
    }

    /**
     * Save the request and response objects for later use in the Servlet.
     * @param request   The request object.
     * @param response  The response object.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        parseGeneralRequestData();
    }
    
    @Override
    public void destroy() {
        if (getConnection() != null) {
            try {
                getConnection().close();
            } catch (SQLException e) {
                
            }
        }
        
        super.destroy();
    }
    
    /**
     * Parse the general request data: the url parts and the optional action parameter.
     */
    private void parseGeneralRequestData() {
        // Action parameter.
        String actionParam = (String) request.getParameter("action");
        this.action = actionParam == null ? "" : actionParam;
        
        // URL parts
        // Arrays.asList(T...) returns an immutable List, so we put it in a new (mutable) List.
        urlParts = new LinkedList<>();
        urlParts.addAll(Arrays.asList(getRequest().getRequestURI().split("\\/")));
        List<String> removableParts = new LinkedList<>();
        for (int i = 0; i < urlParts.size(); i++) {
            // Remove the entry if it's empty or one of the first two equals the company name.
            if (urlParts.get(i).isEmpty() || (urlParts.get(i).equals("ubercoaching") && i <= 1)) {
                removableParts.add(urlParts.get(i));
            }
        }
        urlParts.removeAll(removableParts);
        // Make sure the List is at least one size.
        if (urlParts.size() == 0) {
            urlParts.add("");
        }
    }
    
    /**
     * @param url               The url to forward to.
     * @throws ServletException
     * @throws IOException
     */
    public void forwardTo(String url) throws ServletException, IOException {
        getRequest().getRequestDispatcher(url).forward(getRequest(), getResponse());
    }

}
