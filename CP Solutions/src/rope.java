import java.util.*;

public class rope
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n;
        n=sc.nextInt();
        while (n>0)
        {
            double x1, y1, x2, y2, r, maxlength;
            x1=sc.nextDouble();
            y1=sc.nextDouble();
            x2=sc.nextDouble();
            y2=sc.nextDouble();
            r=sc.nextDouble();
            double slopex=x2-x1;
            double slopey=y2-y1;
            if (slopex==0.0)
            {
                if (slopey==0.0)
                {
                    maxlength=0.0;
                    System.out.printf("%.3f\n", maxlength);
                    continue;
                }
                double yx=y1;
                double yy=y2;
                if(y1<y2)
                {
                    yx=y2;
                    yy=y1;
                }
                if ((yx>0.0&&yy<0.0)&&(x1<r&&x1>-r))
                {
                    double dista=Math.sqrt(x1*x1+y1*y1);
                    double distb=Math.sqrt(x2*x2+y2*y2);
                    double distc = Math.sqrt(slopex*slopex+slopey*slopey);
                    double a;
                    if (x1==0.0)
                    {
                        a=2*Math.acos(0.0);
                    }
                    else
                    {
                        a=Math.acos((dista*dista+distb*distb-distc*distc)/(2.0*dista*distb));
                    }
                    double a1=Math.acos(r/dista);
                    double a2=Math.acos(r/distb);
                    double ang=a-(a1+a2);
                    maxlength=r*ang+Math.sqrt(dista*dista-r*r)+Math.sqrt(distb*distb-r*r);
                    System.out.printf("%.3f\n", maxlength);
                }
                else
                {
                    maxlength=yx-yy;
                    System.out.printf("%.3f\n",maxlength);
                }
            }
            else
            {
                double a=slopex/slopey;
                double c=y1-a*x1;
                double d=4.0*((a*a)*(c*c)-((a*a)+1.0)*((c*c)-(r*r)));
                if (d>0.0)
                {
                    double x=(-(2.0*a*c)+Math.sqrt(d))/(2.0*((a*a)+1.0));
                    double xx=x1;
                    double xy = x2;
                    if (x1 < x2)
                    {
                        xx=x2;
                        xy=x1;
                    }
                    if (x>=xy&&x<=xx)
                    {
                        double dista = Math.sqrt(x1 * x1 + y1 * y1);
                        double distb = Math.sqrt(x2 * x2 + y2 * y2);
                        double distc = Math.sqrt(slopex * slopex + slopey * slopey);
                        if (c==0.0)
                        {
                            a=2*Math.acos(0.0);
                        }
                        else
                        {
                            a=Math.acos((dista*dista+distb*distb-distc*distc)/(2.0*dista*distb));
                        }
                        double a1=Math.acos(r/dista);
                        double a2=Math.acos(r/distb);
                        double ang=a-(a1+a2);
                        maxlength= r*ang+Math.sqrt(dista*dista-(r*r))+Math.sqrt(distb*distb-(r*r));
                        System.out.printf("%.3f\n",maxlength);
                    }
                    else
                    {
                        maxlength=Math.sqrt(slopex*slopex+slopey*slopey);
                        System.out.printf("%.3f\n",maxlength);
                    }
                }
                else
                {
                    maxlength=Math.sqrt(slopex*slopex+slopey*slopey);
                    System.out.printf("%.3f\n",maxlength);
                }
            }
            n--;
        }
    }
}
