package core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Validation {
    
    // --- Constants -------------------------------------------------------------------------
    
    private final static String COOKIE_NAME = "auth";
    
    private final static String REDIRECT_URL = "/login";

    private static final String DB_USERNAME = "di18";
    private static final String DB_PASSWORD = "Q.Z4J2CPz";
    private static final String DB_HOSTNAME = "farm14.ewi.utwente.nl";
    private static final String DB_SCHEMA = "uber";
    private static final int DB_PORT = 5432;
    private static final String DATABASE = "di18";
    
    // --- Class variables -------------------------------------------------------------------

    private final static String SALT1 = "Here's a random string functioning as a salt for the "
            + "hashing of the passwords. utrfvbnmkuytrdfghjmKJYTFV#@ed206563345YHN CSWERTYujmn";
    private final static String SALT2 = "Here's a random string functioning as a salt for the "
            + "hashing of the cookies. cfghjio/resdfvxcmnbvf&YGBNm*kiuytfvFETYUIkmnbvgY&* y^67";

    private static Connection connection;
    
    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getConnection() {
        if (connection == null) {
            connect();
        }
        //Checks if connection is still valid and active, if not the connection will be refreshed.
        try {
			if(!connection.isValid(0)){
			connect();	
			System.out.println("connection is not valid anymore");
			}
		} catch (SQLException e) {
		}
        return connection;
    }
    
    // --- Setters ---------------------------------------------------------------------------
    
    public static void setConnection(Connection connection) {
        Validation.connection = connection;
    }
    
    
    // --- Commands --------------------------------------------------------------------------
    
    private static void connect() {

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
        
        setConnection(connection);
    }
    
    
    public static User validate(String username, String password) {
        User user = null;
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                      " SELECT  * "
                    + " FROM    uber.user "
                    + " WHERE   username = ? "
                    + "     AND password = ? ; ");  
            
            ps.setString(1, username);
            ps.setString(2, hashPassword(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setFrom(rs);
            }
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    
    /**
     * @param password The plain text password
     * @return         The SHA1 hash of the password.
     */
    public static String hashPassword(String password) {
        return hash(password + SALT1);
    }
    
    public static String hashCookie(String cookieData) {
        return hash(cookieData + SALT2);
    }
    
    /**
     * @param str The plain text String.
     * @return    The SHA1 hash of the string.
     */
    private static String hash(String str) {
        String result = null;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            Formatter formatter = new Formatter();
            for (byte b : crypt.digest()) {
                formatter.format("%02x", b);
            }
            result = formatter.toString();
            formatter.close();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * Generate a cookie string in the format "UID:hash", where hash is the SHA1 hash of the
     * user's hashed password, ip address and user agent, merged by colons (:).
     * @param user
     * @param request
     * @return
     */
    public static String generateCookieString(User user, HttpServletRequest request) {
        // Cookie will be comprised of the following format:
        // "UID:hash"
        // where hash is the sha1 hash of the user's hashed password, ip and user agent.
        return    String.valueOf(user.getIdUser()) + ":"
                + hashCookie(
                        user.getPasswordHash() + ":"
                      + request.getRemoteAddr() + ":" 
                      + request.getHeader("User-Agent"));
    }
    
    /**
     * Save the user information.
     * @param user
     * @param keepData
     * @param request
     * @param response
     */
    public static void save(User user, boolean keepData,
            HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", user);
        Cookie cookie = new Cookie(COOKIE_NAME, generateCookieString(user, request));
        if (keepData) {
            cookie.setMaxAge(3600 * 24 * 365 * 5); // five years
        }
        response.addCookie(cookie);
    }
    
    /**
     * Destroy the validation (cookie and session).
     * @param request
     * @param response
     */
    public static void destroy(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        request.removeAttribute("user");
        if (request.getCookies() == null) {
            return;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("auth")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
    
    
    public static boolean validated(HttpServletRequest request) {
        User user = null;
        Integer iduser = null;
        
        // User is already logged in.
        if ((user = (User) request.getSession().getAttribute("user")) != null) {
            request.setAttribute("user", user);
            return true;
        }
        
        /* Fetch UID from the cookie. */
        String cookieString = null;
        // Get correct cookie.
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    cookieString = cookie.getValue();
                    break;
                }
            }
        }
        // Redirect if cookie is invalid.
        if (cookieString == null || !cookieString.contains(":")) {
            return false;
        }
        String[] cookieStringParts = cookieString.split(":", 2);
        try {
            iduser = Integer.valueOf(cookieStringParts[0]);
            // Fetch User from the idUser.
            if ((user = User.fromIdUser(iduser)) == null) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return false;
        }
        
        // Generate cookie.
        String generatedCookie = generateCookieString(user, request); 
        
        // Compare cookie.
        // If cookie is the same, login. if not, redirect.
        // Also, set the request attribute user to the correct one.
        if (generatedCookie.equals(cookieString)) {
            request.getSession().setAttribute("user", user);
            request.setAttribute("user", user);
            return true;
        } else {
            return false;
        }
    }
    
    
    public static boolean validateOrForward(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        if (!validated(request)) {
            request.getRequestDispatcher(REDIRECT_URL).forward(request, response);
            return false;
        } else {
            return true;
        }
    }

}
