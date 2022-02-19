import java.io.IOException;
import java.util.Scanner;

public class Integration {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            double a= sc.nextDouble();
            double z=a*a*(1.0-Math.PI/6.0 - 0.25*Math.sqrt(3));
            double y=a*a-0.25*Math.PI*a*a - 2.0*z;
            double x= a*a -4*y - 4*z;
            System.out.printf("%.3f %.3f %.3f\n",x,4.0*y,4.0*z);
        }
    }
}