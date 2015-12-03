package week4;

public class Product implements Function {
	public Function one;
	public Function two;
	
	public Product(Function one, Function two){
		this.one = one;
		this.two = two;
	}
	
	public Function derivative() {
		return new Product(one.derivative(), two.derivative());
	}

	@Override
	public double apply(double argument) {
		return one.apply(argument) * two.apply(argument);
	}

	@Override
	public Function integrand() {
		return null;
	}

}
