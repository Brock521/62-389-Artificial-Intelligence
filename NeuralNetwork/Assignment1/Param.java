//    STUDENT: Param.java



import java.io.* ; import java.util.* ; 
public class Param
{

	private int numLayers; 
		private Activation activation; 
		private	Discrepancy discrepancy; 
		private	double eta; 
		private	int[] numHidden;  
		private	int freqTest;
		private	int numEpochs; 
		private	String trainingSetFilename;
		private	String testSetFilename; 
		private	boolean[] nozero;

	public int getNumLayers () {return this.numLayers;} 
	public String getTrainingSetFilename () {return this.trainingSetFilename; } 
	public String getTestSetFilename () {return this.testSetFilename; } 
	public double getEta (){return this.eta;  }
	public int[] getNumHidden () {return this.numHidden; }
	public int getNumEpochs () {return this.numEpochs; }
	public int getFreqTest () {return this.freqTest; }
	public Discrepancy getDiscrepancy () {return this.discrepancy; }
	public Activation getActivation () {return this.activation; }
	
	public Param (String paramFile)
	{
		this.setDefaults();
        final File source = new File(paramFile);
        if (!source.exists()) {
            System.out.println("File not found");
            System.exit(1);
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(source);
        }
        catch (Exception ex) {}
        while (scanner.hasNext()) {
            String s2 = scanner.nextLine();
            final int index = s2.indexOf("#");
            if (index != -1) {
                s2 = s2.substring(0, index).trim();
            }
            if (s2.length() == 0) {
                continue;
            }
            final String[] split = s2.split("=");
            int n = (split.length != 2) ? 1 : 0;
            if (n != 0) {
                System.out.println("Error");
            }
            else {
                split[1] = split[1].trim();
                final String lowerCase = split[0].trim().toLowerCase();
                switch (lowerCase) {
                    case "eta": {
                        this.eta = Double.parseDouble(split[1]);
                        break;
                    }
                    case "epochs":
                    case "epoch": {
                        this.numEpochs = Integer.parseInt(split[1]);
                        break;
                    }
                    case "hidden": {
                        this.parseHidden(split[1]);
                        break;
                    }
                    case "training": {
                        this.trainingSetFilename = split[1];
                        break;
                    }
                    case "testing": {
                        this.testSetFilename = split[1];
                        break;
                    }
                    case "nozero": {
                        this.parseNozero(split[1]);
                        break;
                    }
                    default: {
                        n = 1;
                        break;
                    }
                }
                if (n == 0) {
                    continue;
                }
                System.out.println("Error");
            }
        }
        scanner.close();
    }
    
    private void parseHidden(String s) {
        final String[] split = s.split("[ \t]+");
        final int length = split.length;
        this.numHidden = new int[length];
        for (int i = 0; i < length; ++i) {
            this.numHidden[i] = Integer.parseInt(split[i]);
        }
        this.numLayers = length + 2;
        System.out.println("numLayers = " + this.numLayers);
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

	private void parseNozero(String s) {
        final String[] split = s.split("[ \t]+");
        this.nozero = new boolean[split.length];
        for (int i = 0; i < split.length; ++i) {
            switch (Integer.parseInt(split[i])) {
                case 1: {
                    this.nozero[i] = true;
                    break;
                }
                case 0: {
                    this.nozero[i] = false;
                    break;
                }
                default: {
                    System.out.println("Error parsing nozero parameter line:" + s + "! Exiting!");
                    System.exit(1);
                    break;
                }
            }
        }
    }
}
