package week4;

public class Polynomial implements Function{

	public Function[] functions;

	public Polynomial(Function[] functions){
		this.functions = functions;
		
	}
	public Function derivative() {
		Function sum = functions[0].derivative();
		for(int i = 1; i < functions.length; i++){
			sum = new Sum(sum, functions[i].derivative());
		}
		return sum;
	}

	public double apply(double argument) {
		double result = 0;
		for (int i = 1; i < functions.length; i++){
			result = result + functions[i].apply(argument);
			argument--;
		}
		return result;
	}
	
	public String toString(){
		return null;
	}
	
	//snapdiehelefunctieniet;
	public Function integrand() {
		return null;
	}

}
