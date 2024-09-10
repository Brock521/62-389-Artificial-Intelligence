//    STUDENT: Conn.java

import java.util.* ;

import javax.sound.sampled.SourceDataLine;

import java.math.*;

public class Conn
{
// This class represents a connection between two layers.
// Missing: private fields to indicate the two layers that this

// Conn object connects.
// Missing: private field for the weight matrix
// Missing: private field to hold this layer's value of eta

private Layer from;
private Layer to;
private double[][] weights;
private double eta; 



	public void setEta (double eta) 
	{
		this.eta  = eta;
	} 
	public Conn (Layer from, Layer to) 
	{
//Constructor
		this.from = from;
		this.to = to;

	//initilise and fill weight matrix
	 	int toSize=to.getSize();
		int fromSize = from.getSize();
	

		Random rand = new Random () ; 
		
	
		this.weights = new double[this.to.getSize()][this.from.getSize()];

        for (int i = 0; i < this.weights.length; ++i) {
            for (int j = 0; j < this.weights[0].length; ++j) {
                this.weights[i][j] = rand.nextDouble() * 0.4 - 0.2;
            }


	
}


}
	public void forward () 
	{
/*assumes that the from layers output is already computed, 
and can be obatined by calling that layers public methdos
has acess to this conns objects private fieled for the weight matrix
this method computes the to layers output o based on the from fields output o and the to fields
bias vector
*/
	//calc net then use the sigmoid class f function;
	//loop through all neurosn from-to
	//set outputs
	
		//let there be 2 from neurons and 4 to neurosn
		//so outer loop = 4 inner loop = 2
		//neuron i .setOutput = w41o1 + w4o2 + neuron i.get bias
		//end looop
		
		double weightedInputs = 0; 
		
		double net;
		double output;
		double bias;

	
		for(int t = 0; t < to.getSize(); t++){
			for(int f=0; f<from.getSize(); f++){
			
				weightedInputs += weights[t][f] * from.getOutput()[f];

			}
		

			
				bias = to.getBias(t);
				net = weightedInputs + bias;
				output = to.getActivation().f(net);
				to.setOutput(t, output);
				weightedInputs = 0;
				

			
		
				
		
		
		
		}

	

}	


	
	
	public void backward (boolean learn)
	{
/*the opposite of forwards but will have learn equal to true for training set 
and false for test set */
		
	
	
		double delta;
		double deltaWeight;
		double deltaBias;

		for (int i = 0; i < this.from.getSize(); ++i) {
            delta = 0.0;
            for (int j = 0; j < this.to.getSize(); ++j) {
                delta += to.getDelta()[j] * to.getActivation().fprime(to.getOutput()[j]) * weights[j][i];
                if (learn) {
                    deltaWeight = eta * to.getDelta()[j] * to.getActivation().fprime(to.getOutput()[j]) * from.getOutput()[i];
                    double[] temp = this.weights[j];
                    final int n = i;
                    temp[n] += deltaWeight;
                }
            }
            from.setDelta(i, delta);
			deltaWeight = 0.0;
		}
        if (learn) {
            for (int k = 0; k < to.getSize(); ++k) {
				deltaBias = to.getDelta()[k] * to.getActivation().fprime(this.to.getOutput()[k]) * this.eta;
                to.setBias(k, deltaBias + this.to.getBias(k));
				deltaBias=0;
            }
        }
	}
	
}
