package core;

public class Intake {

    private Food food;
    private double amount;
    
    
    public Intake(double amount, Food food) {
        setAmount(amount);
        setFood(food);
    }
    
    /**
     * @return the food
     */
    public Food getFood() {
        return food;
    }
    
    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * @param food the food to set
     */
    public void setFood(Food food) {
        this.food = food;
    }
    
    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
