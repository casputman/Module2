package core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BmiServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	
	 // --- Instance variables ----------------------------------------------------------------
	private double heigth;
	private double weigth;
	
	/**
     * Any GET requests concerning the user.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (request.getSession()) {
            super.doGet(request, response);
            //TODO
            
            
            
        }
    }
    
    /**
     * Any POST request concerning the user.
     */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (request.getSession()) {
            super.doPost(request, response);
            //TODO
		
		
		
		
		}
	}
	
	
	
	
	
	
	
	
	
	
}
