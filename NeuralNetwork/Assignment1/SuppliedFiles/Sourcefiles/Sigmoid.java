//    STUDENT: Sigmoid.java

// Nothing is missing from this file.

public class Sigmoid implements Activation
{
	private final double a = 1.7159, b = 2.0/3.0 ; 
	public double f (double x) { return a * Math.tanh (b * x) ; } 
	public double fprime (double x) 
	{ 
		double y = x / a ; 
		return b * (1 - y * y) ; 
	}
//	public double f (double x) { return 1 / (1 + Math.exp (-x) ) ; } 
//	public double fprime (double x) { return x * (1-x) ; } 
}

