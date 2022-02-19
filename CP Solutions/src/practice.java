import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class practice {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        System.out.println("Cases "+  cases);
        int c=0;
        while(c<cases)
        {
            sc.next();
            int maxheight = sc.nextInt();
            ArrayList<Integer> list=new ArrayList<>();
            int height = sc.nextInt();
            System.out.println(height);
            int sum=0;
            while(height!=0)
            {
                sum+=height;
                if (sum<=maxheight*2)
                {
                    list.add(height);
                }
            }
            for ( int i =0; i< list.size(); i++)
            {
                System.out.println(list.get(i));
            }
            c++;
        }
    }
}
