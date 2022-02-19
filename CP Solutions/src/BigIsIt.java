// works only for inout given, does not work on online judge.

import java.util.*;

public class BigIsIt {

    static double result;
    public static double circle[];
    public static double copy[];
    static int inputs;
    static ArrayList<Integer> nums;
    static ArrayList<Integer> curr;
    static ArrayList<ArrayList<Integer>> ans;
    static ArrayList<Boolean> visited = new ArrayList<Boolean>();

    public static void solve(int n)
    {
        double len = 0;
        copy[0] = circle[0];
        for(int i=1; i<n; ++i)
        {
            copy[i] = circle[i];
            for(int j=0; j<i; ++j)
            {
                double a, c;
                c = circle[j] + circle[i];
                a = Math.abs(circle[j] - circle[i]);
                double distance=Math.sqrt(Math.pow(c,2) - Math.pow(a,2));
                double temp = distance + copy[j];
                if(temp > copy[i])
                    copy[i] = temp;
            }
        }
        double temp = Integer.MIN_VALUE;
        for(int i=0; i<n; ++i)
        {
            if((copy[i] + circle[i]) > temp)
                temp = copy[i] + circle[i];
        }
        if(temp < result)
            result = temp;
        return;
    }

    static void solvepermutes()
    {
        if (curr.size() == nums.size())
        {
            ans.add(curr);
            for(int i = 0; i < curr.size(); i++)
            {
                circle[i]=curr.get(i);
            }
            solve(inputs);
        }
        for (int i = 0; i < (int)nums.size(); i++)
        {
            if (visited.get(i))
                continue;
            if (i > 0 && (nums.get(i) == nums.get(i - 1)) && !visited.get(i - 1))
                continue;
            visited.set(i, true);
            curr.add(nums.get(i));
            solvepermutes();
            visited.set(i, false);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args)
    {
        int cases;
        Scanner sc = new Scanner(System.in);
        cases=sc.nextInt();
        while(cases>0)
        {
            circle=new double[10];
            copy=new double[10];
            inputs=sc.nextInt();
            for(int i=0; i<inputs; i++)
            {
                circle[i]=sc.nextDouble();
            }
            result=Integer.MAX_VALUE;
            Arrays.sort(circle, 0, inputs);
            nums= new ArrayList<Integer>();
            curr = new ArrayList<Integer>();
            for( int i=0; i<inputs; i++)
            {
                nums.add((int)Math.round(circle[i]));
            }
            ans = new ArrayList<ArrayList<Integer>>();
            Collections.sort(nums);
            for(int i = 0; i < nums.size(); i++)
            {
                visited.add(false);
            }
            solvepermutes();
            System.out.printf("%.3f\n",result);
        }
    }
}
