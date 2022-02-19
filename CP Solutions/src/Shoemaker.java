import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Shoemaker {

    public static boolean jobdone(int jobscompleted[], int jobnumber)
    {
        boolean flag=true;
        for(int i=0; i<jobscompleted.length; i++)
        {
            if(jobscompleted[i]==jobnumber)
            {
                flag=false;
            }
        }
        return flag;
    }

    public static int findIndex(float arr[], float cost, int jobscompleted[])
    {
        for(int i=0; i<arr.length; i++)
        {
            if(arr[i]==cost && jobdone(jobscompleted, i+1))
            {
                return i+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        int c=0;
        while(c<cases)
        {
            br.readLine();
            int lines = Integer.parseInt(br.readLine());
            float days[]=new float[lines];
            float cost[]=new float[lines];
            float costperday[]=new float[lines];
            String input, arr[];
            for( int i=0; i<lines; i++)
            {
                input= br.readLine();
                arr=input.split(" ");
                days[i]=Integer.parseInt(arr[0]);
                cost[i]=Integer.parseInt(arr[1]);
                costperday[i]=cost[i]/days[i];
            }
            float copyofcostperday[] = costperday.clone();
            Arrays.sort(copyofcostperday);
            int jobscompleted[] = new int[lines];
            for(int i=0; i<lines; i++)
            {
                jobscompleted[i]=0;
            }
            int job;
            int j=0;
            String ans="";
            for(int i=lines-1; i>=0; i--)
            {
                job=findIndex(costperday, copyofcostperday[i], jobscompleted);
                jobscompleted[j]=job;
                j++;
                ans=ans+Integer.toString(job)+" ";
            }
            System.out.println(ans.trim());
            c++;
        }

    }
}
