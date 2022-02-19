import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class erdos {

    static int nameSize;
    static int[][] matrix;
    static int[] best_so_far;
    public static void path(int i, int level)
    {
        int j;
        if(best_so_far[i]<level)
        {
            return;
        }
        best_so_far[i]=level;
        for(j=0; j<nameSize; j++)
        {
            if(matrix[i][j]==0)
            {
                continue;
            }
            path(j, level+1);
        }
    }

    public static int findIndexOf(String []list, String s)
    {
        for(int i=0; i<list.length; i++)
        {
            if(list[i].equals(s))
            {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        int c=1;
        while(c<=cases)
        {
            String input = br.readLine();
            StringTokenizer tokens=new StringTokenizer(input);
            int lines = Integer.parseInt(tokens.nextToken());
            int totalNames=Integer.parseInt(tokens.nextToken());
            String [] papers = new String[lines];
            String tmp;
            Set<String> set = new HashSet<String>();
            int i=0;
            int max=0;
            int size;
            while(lines>0)
            {
                papers[i]=br.readLine();
                papers[i] = papers[i].substring(0, papers[i].indexOf(':'));
                String [] s =papers[i].split(",");
                papers[i]="";
                size=0;
                for(int j=0; j<s.length; j=j+2)
                {
                    tmp=s[j].trim()+","+s[j+1];
                    papers[i]=papers[i]+tmp+"#";
                    set.add(tmp);
                    ++size;
                }
                if(size>max)
                {
                    max=size;
                }
                i++;
                --lines;
            }
            String [] questions = new String[totalNames];
            i=0;
            while(totalNames>0)
            {
                questions[i]=br.readLine();
                i++;
                --totalNames;
            }
            String [] uniqueNames = set.toArray(new String[set.size()]);
            nameSize = uniqueNames.length;
            int index= findIndexOf(uniqueNames,"Erdos, P.");
            int j;
            tmp=uniqueNames[0];
            uniqueNames[0]=uniqueNames[index];
            uniqueNames[index]=tmp;

            matrix=new int[nameSize][nameSize];
            for(i=0; i< nameSize; i++)
            {
                for(j=0; j< nameSize; j++)
                {
                    matrix[i][j]=0;
                }
            }
            int k;
            int p1,p2;
            String [] names = new String[max];
            for(i=0; i< papers.length; i++)
            {
                names=papers[i].split("#");
                for(j=0; j<names.length; j++)
                {
                    for(k=j+1; k<names.length; k++)
                    {
                        p1=findIndexOf(uniqueNames, names[j]);
                        p2=findIndexOf(uniqueNames, names[k]);
                        matrix[p1][p2]=1;
                        matrix[p2][p1]=1;
                    }
                }
            }

            best_so_far=new int[nameSize];
            for(i=0; i<nameSize; i++)
            {
                best_so_far[i]=1000;
            }
            best_so_far[0]=0;
            path(0,0);

            int erdos_score;
            System.out.println("Scenario "+c);
            for(i=0; i< questions.length; i++)
            {
                index=findIndexOf(uniqueNames,questions[i]);
                erdos_score=best_so_far[index];
                System.out.print(questions[i]+" ");
                if(erdos_score==1000)
                {
                    System.out.print("infinity");
                }
                else
                {
                    System.out.print(erdos_score);
                }
                System.out.println();
            }
            c++;
        }
    }

}
