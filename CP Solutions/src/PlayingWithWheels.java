import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
//BFS
public class PlayingWithWheels {

    public static int number(int input[])
    {
        int num=0;
        num=num+input[0]*1000;
        num=num+input[1]*100;
        num=num+input[2]*10;
        num=num+input[3];

        return num;
    }

    public static int[] reversenumber(int num)
    {
        int succ[]=new int[4];
        succ[3]=num%10;
        succ [2]=(num%100)/10;
        succ [1]=(num%1000)/100;
        succ [0]=num/1000;

        return succ;
    }

    public static void solve(int start, int target, int forbiddenstates[])
    {
        boolean flag=false;
        int count[]= new int[10000];
        int visited[]= new int[10000];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(count, 0);
        Arrays.fill(visited, 0);
        for (int i=0; i<forbiddenstates.length; i++)
        {
            visited[forbiddenstates[i]]=1;
        }
        count[start]=0;
        queue.add(start);

        while(!queue.isEmpty())
        {
            int next=queue.peek();
            queue.remove();

            if(visited[next]==1)
                continue;
            visited[next]=1;
            if(next==target)
            {
                flag=true;
                break;
            }
            int succ[] = new int[4];
            succ=reversenumber(next);

            for ( int i = 0; i < 4; i++ ) {
                succ[i]+=1;
                succ [i]%=10;
                int num=number(succ);
                if (visited[num]==0)
                    queue.add(num);
                count[num]=count[next]+1;
                succ=reversenumber(next);
            }

            for(int i=0; i<4; i++)
            {
                succ[i]-=1;
                if(succ[i]==-1)
                    succ[i]=9;
                int num = number(succ);
                if(visited[num]==0)
                    queue.add(num);
                count[num]=count[next]+1;
                succ=reversenumber(next);
            }
        }
        if(flag)
            System.out.println(count[target]);
        else
            System.out.println("-1");
    }

    public static void main(String []args)
    {
        Scanner sc= new Scanner(System.in);
        int cases = sc.nextInt();
        while(cases>0)
        {
            int input[] = new int[4];
            int start; int target;
            for( int i=0; i<4; i++)
            {
                input[i]=sc.nextInt();
            }
            start=number(input);
            for( int i=0; i<4; i++)
            {
                input[i]=sc.nextInt();
            }
            target=number(input);
            int nforbiddenstates=sc.nextInt();
            int forbiddenstates[]=new int[nforbiddenstates];
            for( int i=0; i<nforbiddenstates; i++)
            {
                for(int j=0; j<4; j++)
                {
                    input[j]=sc.nextInt();
                }
                forbiddenstates[i]=number(input);
            }
            solve(start, target, forbiddenstates);
            cases--;
        }
    }
}
