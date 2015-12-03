package week1;

public class Counter {
	public double money;
	public int dollars;
	public int cents;
	public String dollarError = "No dollars inserted!";

	public void add(){
		String parsedMoney = Double.toString(money);
		String[] moneyArray = parsedMoney.split("\\,");
		this.dollars = Integer.parseInt(moneyArray[0]);
		this.cents  = Integer.parseInt(moneyArray[1]); } 

		public int dollars(){
			if (dollars >= 0) {
				return dollars; } else {
					return 0; }
		}

		public int cents(){
			if (0 <= this.cents() && this.cents() <= 99) {
				return cents; }
			else {
				return 0;
			}
		}
		public void add (int dollars, int cents){
			int newMoney = (dollars*100 + cents);
			double addMoney = newMoney/100;
			this.money = money + addMoney;
		}
		public void reset(){
			this.dollars = 0;
			this.cents = 0;
		}

		public int getDollars() {
			return dollars;
		}
		public int getCents(){
			return cents;
		}
	}

