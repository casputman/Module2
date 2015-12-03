package week1;

public class CounterTest {
	public static void main(String[] args){
		SpaarvarkenTest count;
		count = new SpaarvarkenTest();
		count.runTest();
	}
}
		class SpaarvarkenTest{
			private Counter counted;
			
			public SpaarvarkenTest(){
				counted = new Counter();
			}
			public void runTest(){
				testAdd();
				testDollars();
				testCents();
				testReset();
				testCents();
				testDollars();
			}
			public void testAdd(){
				System.out.println("Ik spaar 69 dollar en 69 cent!");
				counted.add(69,69);	
			}
			public void testDollars(){
				System.out.println("Gespaarde dollars: " + counted.getDollars());
				
			}
			public void testCents(){
				System.out.println("Gespaarde centen: " + counted.getCents());
				
			}
			public void testReset(){
				counted.reset();
				System.out.println("Resetting");
				
			}
		}
