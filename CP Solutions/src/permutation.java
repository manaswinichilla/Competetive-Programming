import java.util.*;
class permutation {

    static ArrayList<Double> nums = new ArrayList<Double>();
    static ArrayList<Double> curr = new ArrayList<Double>();
    static ArrayList<ArrayList<Double>> ans = new ArrayList<ArrayList<Double>>();
    static ArrayList<Boolean> visited = new ArrayList<Boolean>();

    static void backtrack()
    {
        if (curr.size() == nums.size())
        {
            ans.add(curr);
            for(int i = 0; i < curr.size(); i++)
            {
                System.out.print(curr.get(i) +" ");
            }
            System.out.println();
        }

        for (int i = 0; i < (int)nums.size(); i++)
        {
            if (visited.get(i))
                continue;
            if (i > 0 && (nums.get(i) == nums.get(i - 1)) && !visited.get(i - 1))
                continue;
            visited.set(i, true);
            curr.add(nums.get(i));
            backtrack();
            visited.set(i, false);
            curr.remove(curr.size() - 1);
        }
    }

    static ArrayList<ArrayList<Double>> permuteDuplicates()
    {
        Collections.sort(nums);
        for(int i = 0; i < nums.size(); i++)
        {
            visited.add(false);
        }
        backtrack();
        return ans;
    }

    static void getDistinctPermutations()
    {
        ans = permuteDuplicates();
    }

    public static void main (String[] args)
    {
        nums.add(2.0);
        nums.add(2.0);
        nums.add(2.0);
        nums.add(2.0);
        getDistinctPermutations();
    }
}
