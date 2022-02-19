import java.io.IOException;
import java.util.*;

public class TugOfWar {
    static int n;
    static int total;
    static int input[];

    public static void printWeights(int maxinput)
    {
        int maxSize, minSize, maxweight;
        int min = Integer.MAX_VALUE , teamone = Integer.MIN_VALUE , teamtwo = Integer.MIN_VALUE;

        if(n%2==0)
        {
            maxSize=n/2;
        }
        else
        {
            maxSize=(n/2)+1;
        }
        minSize=n/2;
        maxweight = 1+(maxinput*n);
        long [] mem = new long[maxweight];
        mem[0] = 1;
        for (int i = 0; i < n; i++)
        {
            for (int sum = maxweight - 1; sum >= 0; sum--)
            {
                if (sum + input[i] < maxweight)
                {
                    mem[sum + input[i]] |= mem[sum] << 1;
                }
            }
        }
        for(int i = minSize ; i<=maxSize;i++)
        {
            for(int j = 0;j<maxweight;j++)
            {
                int rest = total - j;
                if(((mem[j])&(1l<<i))!=0 && Math.abs(j-rest)<min)
                {
                    min = Math.abs(j-rest);
                    teamone = Math.min(rest, j);
                    teamtwo = Math.max(rest, j);
                }
            }
        }
        System.out.println(teamone+" "+teamtwo);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner((System.in));
        int cases = sc.nextInt();
        int c=0;
        while(c<cases)
        {
            if(c!=0)
                System.out.println();
            n = sc.nextInt();
            input = new int[n];
            total = 0;
            int maxinput = 0;
            for (int i = 0; i < input.length; i++)
            {
                input[i] = sc.nextInt();
                total=total+input[i];
                maxinput = Math.max(maxinput, input[i]);
            }
            printWeights(maxinput);
            c++;
        }
    }
}
