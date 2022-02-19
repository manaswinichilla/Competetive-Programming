/*

this solution does not work!
Just attempted to use simple implementation of bellman ford, on 2d array of currencies
and display whether solution exists or not, unable to proceed due to lack of time.

 */


/*import java.util.Scanner;

public class Arbitrage {

    public static boolean isArbitrage(double [][] input, int n)
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                input[i][j]=Math.log(input[i][j]);
            }
        }
        double[] dist = new double[n];
        int[] path = new int[n];
        for(int i=0; i<n; i++)
        {
            dist[i]=Double.MAX_VALUE;
        }
        dist[0]=0.0;
        for (int i = 0; i < n - 1; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                for (int k = 0; k < n; ++k)
                {
                    if (dist[k] > dist[j]+input[j][k])
                        dist[k] = dist[j]+input[j][k];

                }
            }
        }
        for (int j = 0; j < n; ++j)
        {
            for (int k = 0; k < n; ++k)
            {
                if (dist[k] > dist[j] + input[j][k])
                    return true;
            }
        }
        return false;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt())
        {
            int n= sc.nextInt();
            double[][] input = new double[n][n];
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (i != j)
                    {
                        input[i][j] = sc.nextDouble();
                    }
                    else
                    {
                        input[i][j] = 1.0;
                    }
                }
            }

            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    System.out.print(input[i][j]+" ");
                }
                System.out.println();
            }

            boolean ans= isArbitrage(input, n);
            if(ans)
            {
                System.out.println("There exists");
            }
            else
            {
                System.out.println("No there isnt any");
            }
        }
    }
}
/*

import java.util.Arrays;
import java.util.List;

// A class to store a graph edge
class Edge
{
    int source, dest, weight;

    public Edge(int source, int dest, int weight)
    {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
}

class Arbitrage
{
    // Recursive function to print the path of a given vertex from source vertex
    static void printPath(int parent[], int vertex)
    {
        if (vertex < 0) {
            return;
        }

        printPath(parent, parent[vertex]);
        System.out.print(vertex + " ");
    }

    // Function to run the Bellman–Ford algorithm from a given source
    public static void bellmanFord(List<Edge> edges, int source, int N)
    {
        // `distance[]` and `parent[]` stores the shortest path
        // (least cost/path) information
        int distance[] = new int[N];
        int parent[] = new int[N];

        // initialize `distance[]` and `parent[]`. Initially, all vertices
        // except source vertex weight INFINITY and no parent
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        Arrays.fill(parent, -1);

        // relaxation step (run `V-1` times)
        for (int i = 0; i < N - 1; i++)
        {
            for (Edge edge: edges)
            {
                // edge from `u` to `v` having weight `w`
                int u = edge.source;
                int v = edge.dest;
                int w = edge.weight;

                // if the distance to destination `v` can be
                // shortened by taking edge `u —> v`
                if (distance[u] + w < distance[v])
                {
                    // update distance to the new lower value
                    distance[v] = distance[u] + w;

                    // set v's parent as `u`
                    parent[v] = u;
                }
            }
        }

        // run relaxation step once more for N'th time to
        // check for negative-weight cycles
        for (Edge edge: edges)
        {
            // edge from `u` to `v` having weight `w`
            int u = edge.source;
            int v = edge.dest;
            int w = edge.weight;

            // if the distance to destination `u` can be
            // shortened by taking edge `u —> v`
            if (distance[u] + w < distance[v])
            {
                System.out.println("Negative-weight cycle is found!!");
                return;
            }
        }

        for (int i = 0; i < N; i++)
        {
            System.out.print("The distance of vertex " + i + " from the " +
                    "source is " + distance[i] + ". Its path is [ ");

            printPath(parent, i);
            System.out.println("]");
        }
    }

    public static void main(String[] args)
    {
        // List of graph edges as per the above diagram
        List<Edge> edges = Arrays.asList(
                // `(x, y, w)` —> edge from `x` to `y` having weight `w`
                new Edge( 0, 1, -1 ), new Edge( 0, 2, 4 ),
                new Edge( 1, 2, 3 ), new Edge( 1, 3, 2 ),
                new Edge( 1, 4, 2 ), new Edge( 3, 2, 5 ),
                new Edge( 3, 1, 1 ), new Edge( 4, 3, -3 )
        );

        // set the maximum number of nodes in the graph
        final int N = 5;

        // let source be vertex 0
        for(int i=0; i<5; i++)
        {
            System.out.println(i);
            bellmanFord(edges, i, N);
        }
        System.out.println();

        // run the Bellman–Ford algorithm from a given source

    }
}*/

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
import java.util.Arrays;

public class Arbitrage {

    public static double input[][];
    public static int ans[], alen;

    public static class Arc{
        int to, step;
    }

    public static void spfa( int st, int n)
    {
        System.out.println("st : "+st+"n: "+n);
        double dis[][] = new double[21][21];
        int pre[][]= new int[21][21];
        int used[][]= new int[21][21];
        for(double[] arr1 : dis)
            Arrays.fill(arr1, 0);
        for(int[] arr1 : used)
            Arrays.fill(arr1, 0);

        /*for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                dis[i][j]=0;
                used[i][j]=0;
            }
        }*/
        Arc tn = new Arc();
        Arc tmp = new Arc();
        Queue<Arc> Q = new LinkedList<>();
        tn.to= st;
        tn.step=0;
        Q.add(tn);
        dis[st][0]=1;
        while(!Q.isEmpty())
        {
            tn = Q.peek();
            System.out.println(" tn "+tn.to+" "+tn.step);
            Q.poll();
            used[tn.to][tn.step] = 0;
            if(tn.step>n || tn.step>=alen)
            {
                continue;
            }
            for(int i=1; i<=n; i++)
            {
                if(dis[tn.to][tn.step]*input[tn.to][i] > dis[i][tn.step+1])
                {
                    dis[i][tn.step+1] = dis[tn.to][tn.step]*input[tn.to][i];
                    tmp.to=i; tmp.step=tn.step+1;
                    pre[tmp.to][tmp.step]=tn.to;
                    if(used[tmp.to][tmp.step]==0)
                    {
                        used[tmp.to][tmp.step]=1;
                        System.out.println("Tmp to: "+tmp.to+"step: "+tmp.step);
                        Q.add(tmp);
                    }
                }
            }
        }
        System.out.println("pre \n");

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                System.out.print(pre[i][j]+" ");
            }
            System.out.println();
        }
        int j;
        for(int i=1; i<=n; i++) {

            if (dis[st][i] > 1.01) {
                System.out.println("Iam here");
                if (i < alen) {
                    alen = i;
                    System.out.println(alen + "I am here");
                    j = st;
                    while (i >= 0) {
                        ans[i] = j;
                        j = pre[j][i];
                        i--;
                    }
                }
                return;
            }
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt())
        {
            int n= sc.nextInt();
            input = new double[21][21];
            ans = new int[40];
            alen= Integer.MAX_VALUE;
            for (int i = 0; i <n; i++)
            {
                for (int j = 0; j <n; j++)
                {
                    if (i != j)
                    {
                        input[i][j] = sc.nextDouble();
                    }
                    else
                    {
                        input[i][j] = 1.0;
                    }
                }
            }
            for(int i=1; i<=n; i++)
            {
                spfa(i, n);
            }
            if(alen!=Integer.MAX_VALUE)
            {
                for(int i=0; i<alen; i++)
                {
                    System.out.print(ans[i]+" ");
                }
            }
            else
            {
                System.out.println("no arbitrage sequence exists");
            }
        }
    }
}
