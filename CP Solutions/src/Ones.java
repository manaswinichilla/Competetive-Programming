import java.io.IOException;
import java.util.*;

public class Ones {

    public static void main(String[] args) throws IOException
    {
        Scanner sc=new Scanner(System.in);
        int number;
        while(sc.hasNext())
        {
            number=sc.nextInt();
            int ones=1, term=1;
            boolean found=false;
            while(!found)
            {
                if(number>term)
                {
                    ones++;
                    term=term*10+1;
                }
                else
                {
                    term=term%number;
                    if(term==0)
                    {
                        found=true;
                    }
                }
            }
            System.out.println(ones);
        }
    }
}
