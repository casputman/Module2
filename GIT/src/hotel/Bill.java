package hotel;
import java.util.*;
import java.io.*;
import hotel.Format;

public class Bill {

	public interface Item{
		double getAmount();
		String getText();
	}


	private PrintStream theOutStream;
	private List<Item> items;



	public Bill(PrintStream theOutStream){
		this.theOutStream = theOutStream;
		this.items = new ArrayList<Item>();
	}

	public void newItem(Bill.Item item) {
		items.add(item);
	}

	public void close() {
		if (theOutStream != null) {
			theOutStream.println("Uw rekening:");
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				String bill = Format.printLine(item);
				theOutStream.println(bill);

			}
			theOutStream.println("---------------------------------------------------");
			String bill = Format.printLine("Total: ", getSum());
			theOutStream.println(bill);
		}
	}
	public double getSum(){
		double total = 0;
		for (int i = 0; i < items.size(); i++){
			total += items.get(i).getAmount();
		}
		return total;
	}
} 


