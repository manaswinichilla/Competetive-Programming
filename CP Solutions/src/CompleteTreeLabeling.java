import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class CompleteTreeLabeling {

    public static BigInteger Choose(int x, int y)
    {
        BigInteger choose = new BigInteger("1");
        if (y>x/2)
        {
            y = x - y;
        }
        for (int i=1; i<=y; i++)
        {
            choose = choose.multiply(BigInteger.valueOf(x - i + 1)).divide(BigInteger.valueOf(i));
        }
        return choose;
    }

    public static BigInteger[][] fillUpWays(BigInteger[][] ways, int[][] noOfNodes)
    {
        for (int i = 1; i <= 21; i++)
        {
            ways[i][0] = BigInteger.valueOf(1);
            noOfNodes[i][0] = 1;
            for (int j = 1; i * j <= 21; j++)
            {
                ways[i][j] = BigInteger.valueOf(1);
                noOfNodes[i][j] = 1+(i * noOfNodes[i][j - 1]);
                for (int k = i; k >= 1; k--)
                {
                    BigInteger nodes= Choose(k * noOfNodes[i][j - 1], noOfNodes[i][j - 1]);
                    ways[i][j] = ways[i][j].multiply(ways[i][j - 1]).multiply(nodes);
                }
            }
        }
        return ways;
    }

    public static void main(String [] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        BigInteger[][] ways = new BigInteger[22][22];
        int[][] noOfNodes = new int[22][22];
        ways = fillUpWays(ways, noOfNodes);
        while (sc.hasNext())
        {
            int k = sc.nextInt();
            int d = sc.nextInt();
            System.out.println(ways[k][d]);
        }
    }
}