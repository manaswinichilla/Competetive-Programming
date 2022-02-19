import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//recursive dp to find max blocks in each stack in room.
class final3
{
    public static int [][] dp;
    public static boolean [][] visited;
    public static int one,two;

    public static void solve(ArrayList<Integer> list, int maxheight)
    {
        int [] room=new int [list.size()];
        dp=new int [room.length+1][maxheight+1];
        visited=new boolean [room.length][maxheight+1];

        for (int i=0;i<list.size();i++)
        {
            room[i]=list.get(i);
        }

        for (int [] row : dp)
        {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        find(room,0,maxheight);
        int temp=maxheight;
        int blocks=0;
        int twosum=0;
        ArrayList<String> ans = new ArrayList<>();
        for (int i=0;i<room.length;i++)
        {
            if (visited[i][temp])
            {
                temp-=room[i];
                blocks++;
                ans.add("1");
            }
            else if (twosum+room[i]<=maxheight)
            {
                twosum+=room[i];
                blocks++;
                ans.add("2");
            }
        }
        System.out.println(blocks);
        for(int i=0;i<ans.size();i++)
        {
            System.out.println(ans.get(i));
        }
    }

    public static int find(int [] room, int id, int heightLeft)
    {
        if (dp[id][heightLeft]==Integer.MIN_VALUE)
        {
            if (heightLeft==0||id==room.length)
            {
                dp[id][heightLeft]=0;
            }
            else if (room[id]<=heightLeft)
            {
                one=find(room,id+1,heightLeft);
                two=room[id]+find(room,id+1,heightLeft-room[id]);
                if (one<=two)
                {
                    dp[id][heightLeft]=two;
                    visited[id][heightLeft]=true;
                }
                else
                {
                    dp[id][heightLeft]=one;
                }
            }
            else if (heightLeft<room[id])
            {
                dp[id][heightLeft]=find(room,id+1,heightLeft);
            }
        }
        return dp[id][heightLeft];
    }

    public static void main(String[]args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        int c=0;
        while(c<cases)
        {
            br.readLine();
            if (c>0)
            {
                System.out.println();
            }

            int input = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
            int maxheight=input*100;
            ArrayList<Integer> list=new ArrayList<>();
            String s;
            int sum=0;
            while (!(s=br.readLine()).equals("0"))
            {
                int height=Integer.parseInt(new StringTokenizer(s).nextToken());
                sum+=height;
                if (sum<=maxheight*2)
                {
                    list.add(height);
                }

            }
            solve(list, maxheight);
            c++;
        }
    }
}