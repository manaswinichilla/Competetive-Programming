import java.io.IOException;
import java.util.*;

public class Factovisors {
    //Sieve of Eratosthenes
    public static ArrayList<Integer> fillPrime()
    {
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] isPrime=new boolean[(int)1e7];
        Arrays.fill(isPrime,true);
        isPrime[1]=false;
        isPrime[0]=false;

        for (int i=2; i<isPrime.length; i++)
        {
            if (isPrime[i]==true)
            {
                for (long j=(long)i*i; j<isPrime.length; j=j+i)
                {
                    isPrime[(int) j]=false;
                }
            }
        }
        for (int i=0; i<isPrime.length; i++)
        {
            if (isPrime[i]==true)
            {
                primes.add(i);
            }
        }
        return primes;
    }

    public static int get_powers(int n, int p)
    {
        int res=0;
        for (long power=p; power<=n; power=(long)power*p)
            res+=n/power;
        return res;
    }

    public static void check(int m, int n, ArrayList<Integer> primes)
    {
        if(m<=n)
        {
            System.out.println(m+" divides "+n+"!");
            return;
        }
        if(m==0)
        {
            System.out.println(m+" does not divide "+n+"!");
            return;
        }
        HashMap<Integer, Integer> fm= Factorization(m, primes);
        boolean flag=true;

        for(Map.Entry<Integer, Integer> entry : fm.entrySet())
        {
            int key=entry.getKey();
            if(fm.get(key)>get_powers(n,key))
            {
                flag=false;
                break;
            }
        }
        if(flag==false)
        {
            System.out.println(m+" does not divide "+n+"!");
        }
        else
        {
            System.out.println(m+" divides "+n+"!");
        }
    }

    public static HashMap<Integer, Integer> Factorization(int num, ArrayList<Integer> primes)
    {
        HashMap<Integer, Integer> f= new HashMap<>();
        int pi = 0,p = primes.get(pi);
        while(p*p<=num)
        {
            int power = 0;
            while(num%p==0)
            {
                num/=p;
                power++;
            }
            f.put(p,power);
            p = primes.get(++pi);
        }
        if(num!=1)
            f.put(num,1);
        return f;
    }

    public static void main(String[] args)throws IOException
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> primes = fillPrime();
        while(sc.hasNext())
        {
            int n=sc.nextInt();
            int m=sc.nextInt();
            check(m,n, primes);
        }
    }
}
