import java.io.*;

public class VitoFamily {

    public static void main(String []args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        int c=0,i;
        String arr[],line;
        while(c<cases)
        {
            line=br.readLine();
            arr=line.split(" ");
            int streetNumbers[] = new int[arr.length];
            for( i=0; i< streetNumbers.length; i++)
            {
                streetNumbers[i]=Integer.parseInt(arr[i]);
            }
            int min=Integer.MAX_VALUE;
            for( i =1; i<streetNumbers.length; i++)
            {
                int dist=0;
                for(int j=1; j< streetNumbers.length; j++)
                {
                    dist=dist+ Math.abs(streetNumbers[j]-streetNumbers[i]);
                }
                if(dist<min)
                {
                    min=dist;
                }
            }
            System.out.println(min);
            c++;
        }

    }
}
