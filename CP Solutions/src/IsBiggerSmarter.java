import java.io.*;
import java.util.*;

class Elephant implements Comparable<Elephant>
{
    int weight;
    int iq;
    int pos;

    public int compareTo(Elephant obj)
    {
        return this.weight-obj.weight;
    }
}

class IsBiggerSmarter {

    public static Elephant[] createArray() throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Elephant> elephantsList=new ArrayList<>();
        int num=1;
        String s;
        while(true)
        {
            s=br.readLine();
            if (s==null || s.equals(""))
            {
                break;
            }
            StringTokenizer st=new StringTokenizer(s);
            Elephant e=new Elephant();
            e.weight=Integer.parseInt(st.nextToken());
            e.iq=Integer.parseInt(st.nextToken());
            e.pos=num++;
            elephantsList.add(e);
        }
        Elephant [] arr=elephantsList.toArray(new Elephant[elephantsList.size()]);
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args)  throws Exception
    {

        Elephant elephants[]=createArray();
        int max=0;
        int dp[]=new int [elephants.length];
        dp[0]=1;
        for (int i=1;i<dp.length;i++)
        {
            dp[i]=Math.max(1, dp[i]);
            for (int j=0; j<i; j++)
            {
                if (elephants[i].iq<elephants[j].iq && elephants[i].weight>elephants[j].weight)
                {
                    dp[i]=Math.max(dp[j]+1, dp[i]);
                }
            }
        }

        for(int i=0; i<dp.length; i++)
        {
            max=Math.max(dp[i], max);
        }

        System.out.println(max);
        int [] ans=new int [max];
        boolean begin=true;
        int end=0;
        for (int i=dp.length-1; i>=0; i--)
        {
            if(max<=0)
            {
                break;
            }
            if(begin||end<elephants[i].iq)
            {
                if(dp[i]==max)
                {
                    end=elephants[i].iq;
                    max--;
                    ans[max]=elephants[i].pos;
                    begin=false;
                }
            }
        }
        for(int i=0; i<ans.length; i++)
        {
            System.out.println(ans[i]);
        }
    }
}
