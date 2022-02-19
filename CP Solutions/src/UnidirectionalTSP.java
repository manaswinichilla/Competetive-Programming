// unidirectional TSP, finding minimum cost path in 2D array
//while attempting the problem, i was facing issues, so implemented a simpler problem
// to understand DP better, i have just taken right and down.
//Algorithm:
//At every cell, we have options (right down diagnal) and we will choose the minimum of these two.
//
//
//
//ans[i][j] = A[0][j] if i=0 (first row)
//
//= A[i][0] if j=0, (first column)
//
//= A[i][j] + Min(Min(solution[i-1],[j] , solution[i][j-1]), solution[i-1][j-1]) if i>0 && j>0 // this can be extended for other directions wanted in problem
// need to maintain 2-D array to keep record of input weights, dp 2 d array that uses input to compute above, and then build answer.
// did not have time to implement whole question, Did not attempt on online judge
public class UnidirectionalTSP
{
    public static int mincost(int[][] arr)
    {
        int[][] ans = new int[arr.length][arr.length];

        ans[0][0] = arr[0][0];
        for (int i = 1; i < arr.length; i++)
        {
            ans[0][i] = arr[0][i] + ans[0][i - 1];
        }
        for (int i = 1; i < arr.length; i++)
        {
            ans[i][0] = arr[i][0] + ans[i-1][0];
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                ans[i][j] = arr[i][j]
                        + Math.min(Math.min(ans[i-1][j], ans[i][j-1]), ans[i-1][j-1]);
            }
        }
        return ans[arr.length-1][arr.length-1];
    }

    public static void main(String[] args) {
        //sample example
        int [][] arr={{9, 10},{9, 10}};
        System.out.println("Minimum Cost Path " + mincost(arr));
    }
}
