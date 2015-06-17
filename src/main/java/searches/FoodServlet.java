package searches;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.MyServlet;

    public class FoodServlet extends MyServlet {
        private static final long serialVersionUID = 1L;

        /**
         * Any GET requests concerning the food.
         */
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            synchronized (request.getSession()) {
                super.doGet(request, response);
                FoodSearch foodSearch = new FoodSearch();
                System.out.println("text:" + getUrlParts().get(0));
                switch (getUrlParts().get(0)) {
                case "Intake": 
                    ArrayList<ArrayList<String>> Foodlist = foodSearch.foodShow(((core.User) request.getSession().getAttribute("user")).getIdUser());
                    request.setAttribute("myFood", Foodlist);
                    System.out.println(Foodlist);
                    forwardTo("/Intake.jsp");
                    break;
                case "search":  
                    String food = getRequest().getParameter("q");
                    int userID = ((core.User) request.getSession().getAttribute("user")).getIdUser();
                    ArrayList<String> probFood = foodSearch.foodsearch(food, userID);
                    System.out.println("hier komt eten: " + probFood + " dit was de zoekterm: " + food);
                    if (food != null) {
                        request.setAttribute("foodList", probFood);
                    }
                    forwardTo("/Intake");
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
                FoodAdd foodAdd = new FoodAdd();
                switch (getUrlParts().get(0)) {
                case "intake": 
                  // System.out.println("food = " + getRequest().getParameter("food") + " maybe user: " + ((core.User) request.getSession().getAttribute("user")).getIdUser() + " shizzle: " //getRequest().getParameterNames().toString()
                    //);
                    foodAdd.addFood(getRequest().getParameter("food"), ((core.User) request.getSession().getAttribute("user")).getIdUser(), Double.parseDouble(getRequest().getParameter("amount")));
                    forwardTo("/Intake");
                    break;
                case "moreFood":
                    System.out.println("holooaos: " + getRequest().getParameter("calorie") );
                    System.out.println("holooaos1: " + getRequest().getParameter("amount") );
                    System.out.println("holooaos2: " + getRequest().getParameter("unit") );
                    foodAdd.addFoodToDB(Double.parseDouble(getRequest().getParameter("calorie")),Double.parseDouble(getRequest().getParameter("amount")), getRequest().getParameter("unit"), 0, 0, 0, ((core.User) request.getSession().getAttribute("user")).getIdUser(), getRequest().getParameter("name"));
                    forwardTo("/Intake");
                    break;
                }
                // No page selected.
                doGet(getRequest(), getResponse());
            } 
        }
}
