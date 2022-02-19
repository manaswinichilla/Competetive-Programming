import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

//wrong answer on judge but passes initial test case.
class Coordinate {
    double x;
    double y;
    public Coordinate(double x,double y)
    {
        this.x=x;
        this.y=y;
    }
}

class DistanceBtFreckles
{
    int freckle1;
    int freckle2;
    double distance;
    public DistanceBtFreckles(int f1,int f2,double dist)
    {
        this.freckle1=f1;
        this.freckle2=f2;
        this.distance=dist;
    }
}

public class Freckles
{
    public static int MST[];
    public static int MSTheight[];
    public static boolean isPossible(int f1, int f2)
    {
        if (f1!=f2) {
            if (MSTheight[f1]>=MSTheight[f2])
            {
                MST[f2]=MST[f1];
                if (MSTheight[f1]==MSTheight[f2])
                {
                    MSTheight[f1]++;
                }
            }
            else
            {
                MST[f1]=MST[f2];
            }
            return true;
        }
        return false;
    }

    public static ArrayList<DistanceBtFreckles> fillupdistancesandsort(Coordinate[] input, int total, ArrayList<DistanceBtFreckles> distances)
    {
        for (int i=0; i<total; i++)
        {
            for (int j=i+1; j<total; j++)
            {
                double distance = Math.sqrt((input[j].x-input[i].x)*(input[j].x-input[i].x)+(input[j].y-input[i].y)*(input[j].y-input[i].y));
                distances.add(new  DistanceBtFreckles(i, j, distance));
            }
        }
        for(int i=0; i<total-1; i++)
        {
            for (int j=0; j<total-i-1; j++)
            {
                if (distances.get(j).distance > distances.get(j + 1).distance)
                {
                    DistanceBtFreckles temp1 = distances.get(j);
                    DistanceBtFreckles temp2 = distances.get(j + 1);
                    distances.add(j, temp2);
                    distances.add(j+1, temp1);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) throws IOException
    {
        Scanner sc=new Scanner(System.in);
        int Cases=sc.nextInt();
        int c=0;
        while(c<Cases)
        {
            if (c>0) {
                System.out.println();
            }
            int total=sc.nextInt();
            Coordinate[] input=new Coordinate[total];
            for (int j=0; j<total; j++) {
                double x=sc.nextDouble();
                double y=sc.nextDouble();
                Coordinate inp=new Coordinate(x, y);
                input[j]=inp;
            }
            ArrayList<DistanceBtFreckles> distances=new ArrayList<DistanceBtFreckles>();
            distances=fillupdistancesandsort(input, total, distances);
            MST=new int[total];
            MSTheight=new int[total];
            for (int j = 0; j < total; j++)
            {
                MST[j] = j;
                MSTheight[j] = 0;
            }
            double ans = 0.0;
            for (int j = 0; j < distances.size(); j++)
            {
                int n=distances.get(j).freckle1;
                while (MST[n]!=n)
                {
                    n=MST[n];
                }
                int f1=n;
                n=distances.get(j).freckle2;
                while (MST[n]!=n)
                {
                    n=MST[n];
                }
                int f2=n;
                if(isPossible(f1,f2))
                {
                    ans += distances.get(j).distance;
                }
            }
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println(df.format(ans));
            c++;
        }
    }
}


