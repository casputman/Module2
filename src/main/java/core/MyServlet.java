package core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    private static final int DB_PORT = 5432;
    private static final String DATABASE = "di18";
    

    // --- Instance variables ----------------------------------------------------------------
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Connection connection;
    
    // --- Class usage -----------------------------------------------------------------------

    @Override
    public void init() {
        
        // Via a main() method this works.
        String url = "jdbc:postgresql://" + DB_HOSTNAME + ":" + DB_PORT + "/" + DATABASE;
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
            //connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection could not be established: " + e);
            e.printStackTrace();
        }
        Validation.setConnection(connection);
    }
    
    // --- Getters ---------------------------------------------------------------------------

    /**
     * @return the request
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }
    
    /**
     * @return the database connection
     */
    public Connection getConnection() {
        return connection;
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
     * @param url               The url to forward to.
     * @throws ServletException
     * @throws IOException
     */
    public void forwardTo(String url) throws ServletException, IOException {
        getRequest().getRequestDispatcher(url).forward(getRequest(), getResponse());
    }

}
