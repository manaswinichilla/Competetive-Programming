import java.util.ArrayList;
import java.util.Scanner;

//learnt Fleury’s Algorithm and tried to implement a small hard coded example, will try to modify program to fit necklace 10054.
// doesnt run on online judge
public class Necklace {

    private int beads;
    private ArrayList<Integer>[] listt;

    Necklace(int b)
    {
        this.beads = b;
        InitialiseGraph();
    }

    private void InitialiseGraph()
    {
        listt = new ArrayList[beads];
        for (int i = 0; i < beads; i++)
        {
            listt[i] = new ArrayList<>();
        }
    }

    private void addEdge(int u, int v)
    {
        listt[u].add(v);
        listt[v].add(u);
    }

    private void removeEdge(Integer u, Integer v)
    {
        listt[u].remove(v);
        listt[v].remove(u);
    }

    private void printpath()
    {
        Integer u = 0;
        for (int i = 0; i < beads; i++) {
            if (listt[i].size() % 2 == 1) {
                u = i;
                break;
            }
        }
        printEuler(u);
        System.out.println();
    }

    private void printEuler(int u)
    {
        for (int i = 0; i < listt[u].size(); i++) {
            int v = listt[u].get(i);
            if (nextEdge(u, v)) {
                System.out.print(u + "-" + v + " ");
                removeEdge(u, v);
                printEuler(v);
            }
        }
    }

    private boolean nextEdge(Integer u, Integer v)
    {
        if (listt[u].size() == 1) {
            return true;
        }
        boolean[] isVisited = new boolean[this.beads];
        int count1 = dfs(u, isVisited);
        removeEdge(u, v);
        isVisited = new boolean[this.beads];
        int count2 = dfs(u, isVisited);
        addEdge(u, v);
        return (count1 > count2) ? false : true;
    }

    private int dfs(Integer v, boolean[] isVisited)
    {
        isVisited[v] = true;
        int count = 1;
        for (int adj : listt[v]) {
            if (!isVisited[adj]) {
                count = count + dfs(adj, isVisited);
            }
        }
        return count;
    }

    public static void main(String a[])
    {
        Scanner sc = new Scanner(System.in);
        Necklace graph = new Necklace(5);
        graph.addEdge(1, 0);
        System.out.println("1,0");
        graph.addEdge(1, 1);
        System.out.println("1,1");
        graph.addEdge(2, 3);
        System.out.println("2,3");
        graph.addEdge(2, 0);
        System.out.println("2,0");
        graph.addEdge(1, 3);
        System.out.println("1,3");
        System.out.println("Eulaerian path : ");
        graph.printpath();

    }
}