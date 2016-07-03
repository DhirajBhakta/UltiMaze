
package library;

import java.util.Random;

public class StdRandom22 
{
    private static Random random;
    private static long seed;
    
    //This class must NEVER be initialzed.
    private StdRandom22(){}

   //static initializer
    static
    {
        seed = System.currentTimeMillis();
        random = new Random();
    }
    
    
    
    
    public static int randomUniform()
    {
        return random.nextInt();
    }
   
    public static int randomUniform(int N)
    {
        return random.nextInt(N);
    }
    
  
    
    
}
