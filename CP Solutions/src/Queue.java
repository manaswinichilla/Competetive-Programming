import java.util.Scanner;

public class Queue {

    public static long[][][] fillup(long perm[][][])
    {
        int N,P,R;
        perm[1][1][1] = 1;
        for(N = 2; N <= 13; N++)
            for(P = 1; P <= N; P++)
                for(R = 1; R <= N; R++)
                    perm[N][P][R] = perm[N-1][P][R]*(N-2) + perm[N-1][P-1][R] + perm[N-1][P][R-1];
        return perm;

    }
    public static void main(String arg[])
    {
        long[][][] perm= new long[17][17][17];
        perm=fillup(perm);
        Scanner sc=new Scanner(System.in);
        int cases = sc.nextInt();
        while(cases>0)
        {
            int N=sc.nextInt();
            int P=sc.nextInt();
            int R=sc.nextInt();
            System.out.println(perm[N][P][R]);
            cases--;
        }
    }
}
