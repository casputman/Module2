
public class Calculator {
	public int getal1;
	public int getal2;
	public int result;
	
	public static void main(String[] args){
		new Calculator(1,2);
	}
	
	public Calculator(int getal1, int getal2){
		this.getal1 = getal1;
		this.getal2 = getal2;
		
}
	public int sommetje(){
		return (this.getal1 + this.getal2);
	
	}
}