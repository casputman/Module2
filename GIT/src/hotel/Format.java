package hotel;

public class Format {
	public static String printLine(String text, double amount){
		String output = String.format("%-40s $%9.2f", text, amount);
		System.out.println(output);
		return output;
	}
   
    public static String printLine(Bill.Item item) {
            String output = String.format("%-40s $%9.2f", item.getText(), item.getAmount());
            System.out.println(output);
            return output;
    }
}
