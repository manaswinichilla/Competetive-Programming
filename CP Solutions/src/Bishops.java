
import java.io.IOException;
import java.util.*;

public class Bishops {

    static int n;
    static long total;
    static int[] check;

    static void calcTotalWays(int index, int k)
    {
        if(index == (n*2) - 1)
        {
            if(k != 0)
                return;
            long add = 1;
            int a=0,b=0,f;
            for(int i = 0; i < (n*2) - 1; ++i)
                if(check[i]==0)
                {
                    if(i/2%2 == 0)
                    {
                        f=a++;
                    }
                    else
                    {
                        f=b++;
                    }
                    add *= (i/2) + 1 - f;
                }
            total += add;
        }
        else
        {
            check[index] = 0;
            calcTotalWays(index + 1, k - 1);
            check[index] = 1;
            calcTotalWays(index + 1, k);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            n = sc.nextInt();
            int k = sc.nextInt();
            if(n == 0 && k==0)
                break;
            check = new int[(n*2)-1];
            total = 0;
            calcTotalWays(0, k);
            System.out.println(total);
        }
    }
}

