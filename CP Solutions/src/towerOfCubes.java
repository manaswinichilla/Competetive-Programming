import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//LISDP
class Cube
{
    public int topcolor; //check
    public int bottomcolor; //check
    public int whatsontop; //print
    public int tall; //print and check
    public int cubenumber; //print
    public Cube connect; // trace

    public Cube()
    {
        this.topcolor=0;
        this.bottomcolor=0;
        this.whatsontop=0;
        this.tall=1;
        this.cubenumber=0;
        this.connect=null;
    }

    public Cube(int pos, int topc, int bottomc, int cn)
    {
        this.topcolor=topc;
        this.bottomcolor=bottomc;
        this.whatsontop=pos;
        this.tall=1;
        this.cubenumber=cn;
        this.connect=null;
    }
}
public class towerOfCubes {

    public static String pos(int num)
    {
        if(num==0)
            return "front";
        else if(num==1)
            return "back";
        else if(num==2)
            return "left";
        else if(num==3)
            return "right";
        else if(num==4)
            return "top";
        else if(num==5)
            return "bottom";
        return "";
    }

    public static void printPath(Cube cube)
    {
        List<Cube> path = new ArrayList<Cube>();
        while (cube != null) {
            path.add(cube);
            cube = cube.connect;
        }
        for (int i=path.size()-1; i>=0; i--)
            System.out.println(path.get(i).cubenumber + " " + pos(path.get(i).whatsontop));
        System.out.println();
    }

    public static void solve(ArrayList<Cube>[]cubes, int noofcubes, int cases)
    {
        for(int ci=1; ci<noofcubes; ci++)
        {
            for(int cj=0; cj<ci; cj++)
            {
                for(int si=0; si<6; si++)
                {
                    for(int sj=0; sj<6; sj++)
                    {
                        if(cubes[cj].get(si).bottomcolor==cubes[ci].get(sj).topcolor && cubes[ci].get(sj).tall<cubes[cj].get(si).tall+1)
                        {
                            cubes[ci].get(sj).tall=cubes[cj].get(si).tall+1;
                            cubes[ci].get(sj).connect=cubes[cj].get(si);
                        }
                    }
                }
            }
        }
        int maxi=0, maxj=0;
        for (int i=0; i<noofcubes; i++)
        {
            for (int j=0; j<6; j++)
            {
                if (cubes[i].get(j).tall > cubes[maxi].get(maxj).tall)
                {
                    maxi = i;
                    maxj = j;
                }
            }
        }

        System.out.println("Case #"+cases);
        System.out.println(cubes[maxi].get(maxj).tall);
        printPath(cubes[maxi].get(maxj));
    }

    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        int cases=1;
        while(true)
        {
            int noofcubes=sc.nextInt();
            if(noofcubes==0)
                break;
            ArrayList<Cube>[] cubes=new ArrayList[noofcubes];
            int colors[] = new int[6];
            for(int i=0; i<noofcubes; i++)
            {
                for(int j=0; j<6; j++)
                {
                    colors[j]=sc.nextInt();
                }
                cubes[i]= new ArrayList<Cube>();
                for(int j=0; j<=4; j=j+2)
                {
                    cubes[i].add(new Cube(j, colors[j], colors[j+1], i+1 ));
                    cubes[i].add(new Cube(j+1,colors[j+1], colors[j], i+1));
                }
            }
            solve(cubes, noofcubes, cases);
            cases++;
        }
    }
}
