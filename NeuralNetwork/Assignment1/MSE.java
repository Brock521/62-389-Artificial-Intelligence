//    STUDENT:   MSE.java

public class MSE implements Discrepancy
{

    @Override
    public double error(double[] correct, double[] actual) {
       //Error for activation function is Err = 1/2E(y-y')^2
       
       double err =0 ;
    
       for (int i = 0; i < actual.length; i++) {
            err += (Math.pow(correct[i] - actual[i], 2) ) ;
            
            
        }
       
       return err/2;
       
    }

    @Override
    public double[] delta(double[] correct, double[] actual) {
     //delta =  output = (y- oi)
     
     double[] delta = new double[actual.length];
     
        for (int i = 0; i < actual.length; i++) {
             delta[i] = correct[i]- actual[i];
        }
      return delta;
      
    }
}
