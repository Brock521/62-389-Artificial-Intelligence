//    STUDENT:  Layer.java

// Nothing is missing from this file.

import java.util.* ; 
public class Layer
{
	private double[] o ; 
	private double[] bias ; 
	private double[] delta ; 
	private Activation activation ; 
	private Discrepancy discrepancy ; 
	public Layer (Activation activation) 
	{
		setActivation (activation) ;
	}
	public void setSize (int num)
	{
		Random rand = new Random () ;  
		o = new double [num] ; 
		bias = new double [num] ; 
		delta = new double [num] ; 
		for (int i = 0 ; i < bias.length ; i ++)
			bias [i] = rand.nextDouble () * 0.4 - 0.2 ;  
	}
	public void setBias (int k, double x) { bias [k] = x ; }  
	public void setActivation (Activation activation) { this. activation = activation ; } 
	public Activation getActivation () { return activation ; } 
	public void setDiscrepancy (Discrepancy discrepancy) { this.discrepancy = discrepancy; } 
	public Discrepancy getDiscrepancy () { return discrepancy ; } 
	public double[] getOutput() { return o ; } 
	public double[] getDelta () { return delta ; }
	public void setDelta (double[] vector) { delta = vector ; } 
	public void setDelta (int k, double x) { delta [k] = x ; }
	public void setOutput (double[] vector) { o = vector ; }
	public void setOutput (int k, double x) { o [k] = x ; } 
	public int getSize () { return o.length ; } 
	public void computeOutputDelta (double[] correct, Discrepancy disc)
	{
		delta = disc.delta (correct, o) ; 
	}
	public double[] getBias () { return bias ; } 
	public double getBias (int k) { return bias [k] ; } 
}

