import java.io.IOException;
import java.util.*;


class euclidProblem1 {
    public static int X;
    public static int Y;
    public static int D;

    public void extendedEuclid(int A , int B)
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
}


public class Marbles {

    public static int gcd (int A , int B)
    {
        while (B > 0)
        {
            int temp = A % B ;
            A = B;
            B = temp;
        }

        return A;
    }

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            int marbles = sc.nextInt();
            if(marbles==0)
            {
                break;
            }
            int costone=sc.nextInt();
            int capone=sc.nextInt();
            int costtwo=sc.nextInt();
            int captwo=sc.nextInt();
            euclidProblem1 obj = new euclidProblem1();
            obj.extendedEuclid(capone, captwo);
            long tempx=obj.X; long tempy=obj.Y; long tempd=obj.D;


            int g=gcd(capone, captwo);
            if(marbles%g!=0)
            {
                System.out.println("failed");
                continue;
            }


            long x = tempx * (marbles / g);
            long y = tempy * (marbles / g);
            long min = (long)Integer.MAX_VALUE;
            long best1 = 0 , best2 = 0 ;
            long i1 = (long) Math.ceil(-1 * (double)tempx * (marbles / (double)captwo));
            long i2 = (long) Math.floor((double) tempy * (marbles / (double)capone)) ;
            if (i1 > i2)
            {
                System.out.println("failed");
                continue;
            }
            long arr [] = {i1 , i2 };
            for (int j = 0 ; j < 2 ; j++)
            {
                long i = arr[j];
                long ans1 = x + (i * captwo) / g;
                long ans2 = y - (i * capone) / g;
                    long ans =  ans1 * costone + ans2 * costtwo ;
                if (ans < min)
                {
                    min = ans ;
                    best1 = ans1;
                    best2 = ans2;
                }
            }

            if (min == (long)Integer.MAX_VALUE)
                System.out.println("failed");
            else System.out.println(best1 + " " + best2);


        }
    }
}
