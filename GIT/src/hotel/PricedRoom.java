package hotel;

public class PricedRoom extends Room implements Bill.Item{

	public int no;
	public double roomPrice;
	public PricedSafe safe;
	public double safePRICE;

	public double getSafePrice(){
		return safe.safePrice;
	}

	public PricedRoom(int no, double PRICE, double safePrice) {
		super(no);
		this.no = no;
		this.roomPrice = PRICE;
		PricedSafe safe = new PricedSafe(Password.INITIAL, safePrice);
		this.safe = safe;
		safePrice = getSafePrice();
		safePRICE = safePrice;
	}

	@Override
	public double getAmount(){
		return roomPrice;
	}

	@Override
	public String getText() {
		return "Room: ";
	}
	public String toString(){
		return "Room with a price of: " + roomPrice + ", including a safe with a price of: " + safePRICE;
	}
}
