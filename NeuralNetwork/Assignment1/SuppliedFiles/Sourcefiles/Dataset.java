//    STUDENT: Dataset.java 

import java.io.* ; import java.util.* ;
public class Dataset
{
	public int getSize () { } 
	public int getNumInputs () { } 
	public int getNumOutputs () { } 
	public Dataset (String filename) throws IOException 
	{  
		System.out.println ("Going to load dataset: " 
						+ filename) ; 
		loadFromFile (filename) ; 
	}
	public void loadFromFile (String filename) throws IOException
	{
	}
// calling getNextInputPattern or getNextOutputPattern does 
// not cause the internal cursor to advance.
// The cursor only advances when advance() is called.
	public double[] getNextInputPattern () 
	{  
	}
	public double[] getNextOutputPattern ()
	{
	}
// The reset () method initializes the internal cursor and must 
// be called before the first time that getNextInputPattern() or
// getNextOutputPattern () is called.
	public void reset ()
	{
	}
	public boolean hasNext () { }
	public void advance () { } 
	
}
