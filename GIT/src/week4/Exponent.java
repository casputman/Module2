package week4;

public class Exponent implements Function{
	public int n;

	public Exponent(int n){
		this.n = n;
	}

	public Function derivative() {
		Function exponent = new Exponent(n-1);
		return exponent;
	}

	public double apply(double argument) {
		return Math.pow(argument, n);
	}

	@Override
	public Function integrand() {
		Function exponent = new Exponent(n+1);
		return exponent;
	}


}
