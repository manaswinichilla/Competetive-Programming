import java.util.Scanner;

public class CuttingSticks {

    public static int [][]dp;
    public static int l;
    public static int n;
    public static int[] cuts;

    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            l=sc.nextInt();
            if(l==0)
                break;
            n=sc.nextInt();
            cuts= new int[n+2];
            cuts[0]=0;
            cuts[n+1]=l;
            for(int i=1; i<=n; i++)
                cuts[i]=sc.nextInt();
            dp=new int[n+2][n+2];
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    dp[i][j]=0;
                }
            }

            for (int p=2; p<=(n+1); ++p)
            {
                for(int i=0; i<=(n+1)-p; ++i)
                {
                    int j=i+p;
                    int m=Integer.MAX_VALUE;
                    for ( int k=i+1; k<j; ++k)
                    {
                        m = Math.min(m, (dp[i][k] + dp[k][j]));
                        if (m!=Integer.MAX_VALUE)
                        {
                            dp[i][j]=m+(cuts[j]-cuts[i]);
                        }
                    }
                }
            }
            System.out.println("The minimum cutting is "+dp[0][n + 1]+".");
        }
    }
}
