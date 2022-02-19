import java.io.IOException;
import java.util.*;

public class MultiplicationGame {

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        double number;
        while(sc.hasNext())
        {
            number=sc.nextDouble();
            while(number>18)
            {
                number=Math.ceil(number/18);
            }
            if(number>=2 && number<=9)
            {
                System.out.println("Stan wins.");
            }
            else
            {
                System.out.println("Ollie wins.");
            }

        }
    }
}