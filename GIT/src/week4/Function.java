package week4;

public interface Function {

	public Function derivative();
	public double apply(double argument);
	public String toString();
	public Function integrand();
}

