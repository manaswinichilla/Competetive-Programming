import java.io.IOException;
import java.util.*;
import java.math.BigInteger;

public class Counting {

    public static BigInteger[] fillUpCombos(BigInteger[] combos)
    {
        combos[0]=BigInteger.valueOf(1);
        combos[1]=combos[0].multiply(BigInteger.valueOf(2));
        combos[2]=combos[1].add(combos[1]).add(combos[0]);
        for(int i=3; i< combos.length; i++)
        {
            combos[i]=combos[i-1].multiply(BigInteger.valueOf(2)).add(combos[i-2]).add(combos[i-3]);
        }
        return combos;
    }

    public static void main(String[] args) throws IOException
    {
        BigInteger[] combos = new BigInteger[1001];
        combos=fillUpCombos(combos);
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext())
        {
            int input = sc.nextInt();
            System.out.println(combos[input]);

        }
    }
}
