package searches;



    import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.MyServlet;

    public class FoodServlet extends MyServlet {
        private static final long serialVersionUID = 1L;

        // --- Instance variables ----------------------------------------------------------------
        
        // --- Getters ---------------------------------------------------------------------------

        // --- Request handlers ------------------------------------------------------------------
        
        /**
         * Any GET requests concerning the food.
         */
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            synchronized (request.getSession()) {
                super.doGet(request, response);
   
                switch (getUrlParts().get(0)) {
                case "search":
                    FoodSearch foodSearch = new FoodSearch();
                    foodSearch.foodsearch("fuck");
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
                // No page selected.
                doGet(getRequest(), getResponse());
            } 
        }
        
        /**
         * POST /login
         * @throws ServletException
         * @throws IOException
         */
 
}
