import java.util.* ; 
public class TrialAndError
{
	private static Random rand ;
	private static boolean[] getRandomVector (int n)
	{
		boolean[] vector = new boolean[n] ;
	                for (int i = 0 ; i < vector.length ; i ++)
			vector [i] = rand.nextBoolean () ; 
		return vector ;
	}
	public static void main (String[] args) 
	{
		final int REPS  = 10 ; 
		rand = new Random () ; 
              		BlackBoxFunctionInterface obj = new BlackBoxFunction () ; 
	                int len = obj.getLength () ;
		double max = 0 ;
	 	for (int i = 0 ; i < REPS ; i ++)
		{
			boolean[] x = getRandomVector (len) ; 
			double y = obj.function (x) ; 
			if (y > max)
			{
 				max = y ;
			}
			System.out.println (i + " :: " + y + " :: " + max ) ;
		}
	                System.out.println ("Best = " + max) ;
	}
}
