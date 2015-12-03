package hotel;

public class PricedSafe extends Safe implements Bill.Item {
	public double safePrice;

	public PricedSafe(String password, double PRICE) {
		super(password);
		this.safePrice = PRICE;
	}
	public double getAmount() {
		return this.safePrice;
	}
	public String getText() {
		return "Safe: ";
	}
	public String toString(){
		return "Safe with a price of: " + safePrice;
	}

}
