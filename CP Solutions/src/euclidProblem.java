import java.io.IOException;
import java.util.*;


public class euclidProblem {
    public static int X;
    public static int Y;
    public static int D;

    public static void extendedEuclid(int A , int B)
    {
        if( B == 0)
        {
            X=1;
            Y=0;
            D=A;
            return;
        }
        extendedEuclid(B, A%B);
        int tempx=Y;
        int tempy=X-(A/B)*Y;
        X=tempx;
        Y=tempy;
    }
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            int A = sc.nextInt();
            int B = sc.nextInt();
            extendedEuclid(A,B);
            System.out.println(X+" "+Y+" "+D);
        }
    }
}
