package core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Validation {
    
    // --- Class variables -------------------------------------------------------------------

    private final static String SALT1 = "Here's a random string functioning as a salt for the "
            + "hashing of the passwords. utrfvbnmkuytrdfghjmKJYTFV#@ed206563345YHN CSWERTYujmn";
    private final static String SALT2 = "Here's a random string functioning as a salt for the "
            + "hashing of the cookies. cfghjio/resdfvxcmnbvf&YGBNm*kiuytfvFETYUIkmnbvgY&* y^67";

    private static Connection connection;
    
    // --- Getters ---------------------------------------------------------------------------
    
    //TODO: make this thread safe.
    public static Connection getConnection() {
        return connection;
    }
    
    // --- Setters ---------------------------------------------------------------------------
    
    public static void setConnection(Connection connection) {
        Validation.connection = connection;
    }
    
    
    // --- Commands --------------------------------------------------------------------------
    
    
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
            connection.close();
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
        Cookie cookie = new Cookie("auth", generateCookieString(user, request));
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
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("auth")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(hashPassword("root"));
    }
    public static void validateOrForward(Connection connection, HttpServletRequest request) {
        // fetch UID from the cookie
        // fetch User from the UID
        // generate cookie
        // compare cookie
        // if cookie is the same, login. if not, redirect.
        // also, set the request attribute user to the correct one.
    }

}
