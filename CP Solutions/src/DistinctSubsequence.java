import java.util.*;
import java.math.*;

public class DistinctSubsequence {

    public static void printNoOfSubSeq(String str, String substr)
    {
        BigInteger dss[][] = new BigInteger[substr.length()][str.length()];
        for (int i = 0; i < substr.length(); i++)
        {
            for (int j = 0; j < str.length(); ++j)
            {
                dss[i][j] = BigInteger.valueOf(0);
            }
        }
        if (substr.charAt(0) == str.charAt(0))
        {
            dss[0][0] = BigInteger.valueOf(1);
        }
        for (int j = 1; j < str.length(); ++j)
        {
            if (substr.charAt(0) == str.charAt(j))
            {
                dss[0][j] = dss[0][j - 1].add(BigInteger.valueOf(1));
            }
            else
                dss[0][j] = dss[0][j - 1];
        }
        for (int i = 1; i < substr.length(); ++i)
        {
            for (int j = 1; j < str.length(); ++j)
            {
                if (substr.charAt(i) == str.charAt(j) && dss[i - 1][j - 1] != BigInteger.valueOf(0))
                {
                    dss[i][j] = dss[i][j - 1].add(dss[i - 1][j - 1]);
                }
                else
                {
                    dss[i][j] = dss[i][j - 1];
                }
            }
        }
        System.out.println(dss[substr.length() - 1][str.length() - 1]);
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int c=0;
        while (c<cases)
        {
            String str = sc.next();
            String substr = sc.next();
            printNoOfSubSeq(str, substr);
            c++;
        }
    }
}
