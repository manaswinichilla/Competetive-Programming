import java.util.Arrays;
import java.util.Scanner;


public class Bridge {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int cases = sc.nextInt();
        int c=0,i;
        while(c<cases)
        {
            StringBuilder sb = new StringBuilder();
            int size=sc.nextInt();
            int speeds[] = new int[size];
            for(i=0; i<size; i++)
            {
                speeds[i]=sc.nextInt();
            }
            Arrays.sort(speeds);
            int strat1, strat2, sum=0;
            for( i=size-1; i>=3; i=i-2)
            {
                strat1=speeds[0]+2*speeds[1]+speeds[i];
                strat2=2*speeds[0]+speeds[i-1]+speeds[i];
                sum+=Math.min(strat1, strat2);
                if(strat1>=strat2)
                {
                    sb.append(speeds[0]+" "+speeds[i]+"\n");
                    sb.append(speeds[0]+"\n");
                    sb.append(speeds[0]+" "+speeds[i-1]+"\n");
                    sb.append(speeds[0]+"\n");
                }
                else
                {
                    sb.append(speeds[0]+" "+speeds[1]+"\n");
                    sb.append(speeds[0]+"\n");
                    sb.append(speeds[i-1]+" "+speeds[i]+"\n");
                    sb.append(speeds[1]+"\n");
                }
            }
            if(i==2)
            {
                sum+=speeds[0]+speeds[1]+speeds[2];
                sb.append(speeds[0]+" "+speeds[2]+"\n");
                sb.append(speeds[0]+"\n");
                sb.append(speeds[0]+" "+speeds[1]+"\n");
            }
            else if(i==1)
            {
                sum+=speeds[1];
                sb.append(speeds[0]+" "+speeds[1]+"\n");
            }
            else if(i==0)
            {
                sum+=speeds[0];
                sb.append(speeds[0]+"\n");
            }
            System.out.println(sum);
            System.out.print(sb.toString());
            System.out.println();
            c++;
        }
        System.out.println();
    }
}
