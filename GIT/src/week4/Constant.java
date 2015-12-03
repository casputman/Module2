package week4;

public class Constant implements Function {
	public double constantValue;
	public Function c;
	
	public Constant(double x){
		constantValue = x;
	}
	
	public Function plusC(Function c){
		this.c = c;
		return c;
	}
	
	public Function derivative() {
		Function derivative = new Constant(0);
		return derivative;
	}

	@Override
	public double apply(double argument) {
		argument = constantValue;
		return argument;
	}

	@Override
	public Function integrand() {
		Constant constant = new Constant(constantValue);
		Function integrand = new LinearProduct(constant, c);
		return integrand;
	}

}
