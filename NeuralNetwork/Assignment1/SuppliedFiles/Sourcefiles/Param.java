//    STUDENT: Param.java

/* 
Network parameters:
training set file name
test set file name
number of epochs
freq test set epoch
eta
discrepancy function
activation function
*/
import java.io.* ; import java.util.* ; 
public class Param
{

	public int getNumLayers () { } 
	public String getTrainingSetFilename () { } 
	public String getTestSetFilename () { } 
	public double getEta ()  }
	public int[] getNumHidden () { }
	public int getNumEpochs () { }
	public int getFreqTest () { }
	public Discrepancy getDiscrepancy () { }
	public Activation getActivation () { }
	public Param (String paramFile)
	{
	}
	private void setDefaults () 
	{
		numLayers = 3 ; 
		activation = new Sigmoid () ; 
		discrepancy = new MSE () ; 
		eta = 0.001 ; 
		numHidden = new int [1] ; numHidden [0] = 40 ;  
		freqTest = 10 ;
		numEpochs = 100 ; 
		trainingSetFilename = "pima.train" ;
		testSetFilename = "pima.test" ; 
		nozero = null ;
	}
}
