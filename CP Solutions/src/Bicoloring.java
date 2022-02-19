import java.util.*;
import java.util.Queue;

//colors 0,1
//BFS
public class Bicoloring {

    static void solve(ArrayList<Integer> listsOflists[])
    {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        Queue<Integer> queue=new LinkedList<Integer>();
        boolean flag=true;
        map.put(0, 0);
        queue.add(0);
        while(!queue.isEmpty())
        {
            int next=queue.peek();
            queue.remove();
            for (int i=0; i<listsOflists[next].size(); i++)
            {
                int adj=listsOflists[next].get(i);
                if(map.containsKey(adj))
                {
                    if(map.get(adj)==map.get(next))
                    {
                        flag=false;
                        break;
                    }
                }
                else
                {
                    int color;
                    if(map.get(next)==0)
                        color=1;
                    else
                        color=0;
                    map.put(adj,color);
                    queue.add(adj);
                }
            }
        }
        if(flag)
            System.out.println("BICOLORABLE.");
        else
            System.out.println("NOT BICOLORABLE.");
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            int nodes=sc.nextInt();
            if(nodes==0)
                break;
            int edges=sc.nextInt();
            ArrayList<Integer>[] listsOflists=new ArrayList[nodes];
            for (int i=0; i<nodes; i++)
            {
                listsOflists[i]=new ArrayList<>();
            }
            for (int i=0; i<edges; i++)
            {
                int a=sc.nextInt();
                int b=sc.nextInt();
                listsOflists[a].add(b);
                listsOflists[b].add(a);
            }
            solve(listsOflists);
        }
    }
}

