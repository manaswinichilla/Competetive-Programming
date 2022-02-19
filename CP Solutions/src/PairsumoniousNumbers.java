import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class PairsumoniousNumbers
{
    public static boolean flag;

    public static int[] check(int sum, int[] finalnum, int[] numbers, int finalsize)
    {
        Vector<Integer> temp = new Vector<>();
        int index=3;
        finalnum[0]=sum;
        finalnum[1]=numbers[0]-finalnum[0];
        finalnum[2]=numbers[1]-finalnum[0];
        for(int i=2; i<finalsize; i++)
        {
            temp.add(numbers[i]);
        }
        while(true)
        {
            for(int i=1; i<index-1; i++)
            {
                int num=finalnum[i] + finalnum[index-1];
                if(!temp.contains(num))
                {
                    flag=false;
                    return finalnum;
                }
                else
                {
                    temp.remove(temp.indexOf(num));
                }
            }
            if(temp.size()==0)
            {
                return finalnum;
            }
            finalnum[index++]=temp.get(0)-finalnum[0];
            temp.remove(0);
        }
    }

    public static void findSol(int[] numbers, int finalsize, int size)
    {
        boolean found=false;
        int[] finalnum = new int[size];
        for(int i=2; i<finalsize; i++)
        {
            flag=true;
            int sum=numbers[0]+numbers[1]-numbers[i];
            if(sum%2!=0)
                continue;
            finalnum=check(sum/2, finalnum, numbers, finalsize);
            if(flag==false)
                continue;
            if(finalnum.length==size)
            {
                found=true;
                System.out.print(finalnum[0]);
                for(int j=1; j<size; j++)
                {
                    System.out.print(" "+finalnum[j]);
                }
                System.out.print('\n');
                break;
            }
        }
        if(!found)
        {
            System.out.println("Impossible");
        }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            int size=sc.nextInt();
            int finalsize=(size*(size-1))/2;
            int numbers[]= new int[finalsize];
            for(int i=0; i<finalsize; i++)
            {
                numbers[i]=sc.nextInt();
            }
            Arrays.sort(numbers);
            findSol(numbers, finalsize, size);
        }
    }
}
