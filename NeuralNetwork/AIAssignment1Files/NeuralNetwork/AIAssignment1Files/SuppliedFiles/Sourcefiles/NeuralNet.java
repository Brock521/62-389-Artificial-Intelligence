//             STUDENT: NeuralNet.java

import java.io.* ; 
public class NeuralNet
{
	private Dataset trainingSet ; 
	private Dataset testSet ; 
	private Layer[] layer ; 
	private Conn[] conn ; 	
	private Param param ;
	private double E ; 
	private int acc ; 
	private double eta ; 
	private int epoch ; 


	public void setParam (Param p) { param = p ; } 
	private void allocate ()
	{
		int numLayers = param.getNumLayers (), last = numLayers - 1 ;
		layer = new Layer [numLayers] ; 
		for (int i = 0 ; i < numLayers ; i ++)
			layer [i] = new Layer (param.getActivation ()) ; 
		layer [0].setSize (trainingSet.getNumInputs ()) ;
		for (int i = 1 ; i < numLayers - 1 ; i ++)
			layer [i].setSize (param.getNumHidden () [i-1]) ; 
		layer [last].setSize (trainingSet.getNumOutputs ()) ; 
		layer [last].setDiscrepancy (param.getDiscrepancy ()) ;
		conn = new Conn [numLayers - 1] ; 
		for (int i = 0 ; i < numLayers - 1 ; i ++)
		{
			conn [i] = new Conn (layer [i], layer [i+1]) ;
			conn [i].setEta (param.getEta ()) ; 
		}
		
	}
	private void propagateForward (double[] inputPat, double[] outputPat)
	{
           // MISSING	
	}
	private void propagateBackward (boolean learn)
	{
		// MISSING
	}
	private void doEpoch (boolean training) 
	{
		Dataset data = training ? trainingSet : testSet ; 
		data.reset () ; E = 0 ;  acc = 0 ; 
		while (data.hasNext ())
		{
		// MISSING
		}
		if (!training)
			System.out.print ("TEST SET::")  ;
		System.out.println ((this.epoch+1) + ":MSE=" + (this.E/(data.getNumOutputs () * data.getSize ())) + " :Acc=" + 100.0 * acc / data.getSize ()) ; 
	}
	public void train () 
	{
		for (epoch = 0 ; epoch < param.getNumEpochs () ; epoch ++)
		{
			doEpoch (true) ;
			if (epoch > 0 && (epoch + 1) % param.getFreqTest () == 0)
				doEpoch (false) ; 
		}
	}
	public void loadDataSets () throws IOException
	{
		trainingSet = new Dataset (param.getTrainingSetFilename ()) ;
		testSet = new Dataset (param.getTestSetFilename ()) ;
	}
	public void run (String[] args) throws IOException
	{
		param = new Param (args [0]) ;
		loadDataSets () ; 
		allocate () ; 
	
		train () ; 
	}
// to run this program:
// java NeuralNet [parameter filename]
//
	public static void main (String[] args) throws IOException
	{
		new NeuralNet ().run (args) ; 
	}

	private int maxInd (double[] vector)
	{
		int max = 0 ; 
		for (int i = 1 ; i < vector.length ; i ++)
			if (vector [i] > vector [max])
				max = i ; 
		return max ; 
	}
	private int classErr (double[] a, double[] b)
	{
		if (maxInd (a) == maxInd (b))
			return 1 ; 
		else 	return 0 ; 
	}
}
