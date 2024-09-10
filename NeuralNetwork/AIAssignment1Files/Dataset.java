//    STUDENT: Dataset.java 

import java.io.* ; import java.util.* ;
public class Dataset
{

	private int size;
	private int numInputs;
	private int numOutputs;
	private double[][] input;
    private double[][] output;
	private int cursor;
    private int[] order;


	public int getSize () {return this.order.length; } 
	public int getNumInputs () {return this.numInputs; } 
	public int getNumOutputs () {return this.numOutputs; } 

	public Dataset (final String filename) throws IOException 
	{  
		System.out.println ("Loading dataset: " + filename) ; 
		loadFromFile (filename) ; 
	}

	public Dataset(final double[][] input, final double[][] output) {
        this.input = input;
        this.output = output;
        this.numInputs = input[0].length;
        this.numOutputs = output[0].length;
        this.init();
    }

	public void loadFromFile (final String filename) throws IOException
	{
		 Scanner scanner = new Scanner(new File(filename));
        int n1 = scanner.nextInt();
         int n2 = scanner.nextInt();
         int n3 = scanner.nextInt();
        this.numInputs = n1;
        this.numOutputs = n2;
        this.input = new double[n3][n1];
        this.output = new double[n3][n2];
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n1; ++j) {
                this.input[i][j] = scanner.nextDouble();
            }
            for (int k = 0; k < n2; ++k) {
                this.output[i][k] = scanner.nextDouble();
            }
        }
        scanner.close();
        this.init();


		
	}

// calling getNextInputPattern or getNextOutputPattern does 
// not cause the internal cursor to advance.
// The cursor only advances when advance() is called.

	public double[] getNextInputPattern () 
	{  
		return this.getInputPattern(this.cursor);
	}
	public double[] getNextOutputPattern ()
	{
		return this.getOutputPattern(this.cursor);
	}

	public void init() {
        order = new int[input.length];
        for (int i = 0; i < order.length; ++i) {
            order[i] = i;
        }
    }

	public double[] getInputPattern(final int n) {
        return input[order[n]];
    }
    
    public double[] getOutputPattern(final int n) {
        return output[this.order[n]];
    }

// The reset () method initializes the internal cursor and must 
// be called before the first time that getNextInputPattern() or
// getNextOutputPattern () is called.
	
public void reset ()
	{
		final Random random = new Random();
        for (int i = 0; i < this.order.length / 4; ++i) {
            random.nextInt(this.order.length);
            random.nextInt(this.order.length);
        }
        cursor = 0;
	}
	public boolean hasNext () {return this.cursor < this.input.length; }
	public void advance () {++this.cursor; } 
	
}
