import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class shellSort {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        int c=0;
        while(c<cases)
        {
            int totalNames=Integer.parseInt(br.readLine());
            String original[]=new String[totalNames];
            String desired[]=new String[totalNames];
            for(int i=0; i<totalNames; i++)
            {
                original[i]= br.readLine();
            }
            for(int i=0; i<totalNames; i++)
            {
                desired[i]=br.readLine();
            }
            int j= totalNames-1;
            for( int i = totalNames-1; i>=0; i--)
            {
                if(original[i].equals(desired[j]))
                {
                    j--;
                }
            }
            for( int i=j; i>=0; i--)
            {
                System.out.println(desired[i]);
            }
            c++;
        }

    }
}
