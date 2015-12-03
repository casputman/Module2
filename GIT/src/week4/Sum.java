package week4;

public class Sum implements Function, Integrandable{
	public Function one;
	public Function two;
	
	public Sum(Function one, Function two){
		this.one = one;
		this.two = two;
	}

	@Override
	public Function derivative() {
		return new Sum(one.derivative(), two.derivative());
	}

	@Override
	public double apply(double argument) {
		return one.apply(argument) + two.apply(argument);
	}
	public String toString(){
		return "f(x) = g(x) + h(x)";	
	}

	@Override
	public Integrandable integrand() {
		return new Sum(one.integrand(), two.integrand());
	}
	
}
