import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

// collecting the frequency and placing most frequest characters in least cost places
class letterfreq
{
    char letter;
    int freq;

//    public int getfreq()
//    {
//        return this.freq;
//    }
}
public class final1 {

    public static int freq[];
    public static int SIZE = 26;

    public static void solve(String str) {
        int n = str.length();
        freq = new int[SIZE+10];
        String l="abcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < n; i++)
            freq[l.indexOf(str.charAt(i))]++;
        ArrayList<letterfreq> list = new ArrayList<>();
        for(int i=0; i<freq.length; i++)
        {
            if(freq[i]!=0)
            {
                letterfreq obj = new letterfreq();
                obj.letter=l.charAt(i);
                obj.freq=freq[i];
                list.add(obj);
            }

        }
        for (int i = 0; i < list.size()-1; i++)
        {
            for (int j = 0; j < list.size() - i - 1; j++)
            {
                if (list.get(j).freq < list.get(j + 1).freq)
                {
                    letterfreq temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }
//        for(int i=0; i< list.size(); i++)
//        {
//            System.out.println(list.get(i).letter+" : "+list.get(i).freq);
//        }
        int score[]= new int[36];
        int s=2;
        int k=0;
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<=i; j++)
            {
                score[k]=s;
                //System.out.print(score[k]+" ");
                k++;
            }
            s++;
        }
        for(int i=5; i>0; i--)
        {
            for(int j=0; j<i; j++)
            {
                score[k]=s;
                //System.out.print(score[k]+" ");
                k++;
            }
            s++;
        }
        int sum=0;
        for(int i=0; i< list.size(); i++)
        {
            System.out.println();
            int t=score[i]*list.get(i).freq;
            sum=sum+t;
        }
        System.out.println("Score : "+sum);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cases=Integer.parseInt(br.readLine());
        while(cases>0)
        {
            String input= br.readLine();
            solve(input);
            cases--;
        }
    }
}
