import java.util.*;

// while finding permutations in lexicogrphic order, checks for first one that has subsequence and prints if found.
class final2
{

    public static boolean isSubsequence(String s, String t)
    {
        if(s.length() == 0)
            return true;
        int is = 0, it = 0;
        while(it < t.length())
        {
            if(s.charAt(is) == t.charAt(it))
            {
                is++;
            }
            it++;
            if(is == s.length())
                return true;
        }
        return false;
    }
    static void sw(char[] str, int i, int j)
    {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }

    static void rev(char str[], int l, int h)
    {
        while (l < h)
        {
            sw(str, l, h);
            l++;
            h--;
        }
    }

    static int Ceil(char str[], char first, int l, int h)
    {
        int ceilIndex = l;
        for (int i = l + 1; i <= h; i++)
            if (str[i] > first && str[i] < str[ceilIndex])
                ceilIndex = i;

        return ceilIndex;
    }

    static void solve(char str[], String substr, int k)
    {
        int count=1;
        //ArrayList<String> list = new ArrayList<>();
        int size = str.length;
        Arrays.sort(str);

        boolean flag = false;
        while (!flag) {
            String t= String.valueOf(str);
            //System.out.println(isSubsequence("421",t));
            if(isSubsequence(substr,t)==true)
            {
                if(count==k) {
                    System.out.println(t);
                    return;
                }
                //list.add(t);
                count++;
            }
            //System.out.println("t : "+t);
            //System.out.println(str);

            int i;
            for (i = size - 2; i >= 0; --i)
                if (str[i] < str[i + 1])
                    break;
            if (i == -1)
                flag = true;
            else {
                int ci = Ceil(str, str[i], i + 1,
                        size - 1);
                sw(str, i, ci);
                rev(str, i + 1, size - 1);
            }
        }
//        System.out.println("all");
//        for(int i=0; i< list.size(); i++)
//        {
//            System.out.println(list.get(i));
//        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while(cases>0)
        {
            int n= sc.nextInt();
            int m= sc.nextInt();
            int k=sc.nextInt();
            char mainstr[]= new char[n];
            String substr="";
            System.out.print("mainstr : ");
            for(int i=0; i<n; i++)
            {
                mainstr[i]=Integer.toString(i+1).charAt(0);
                System.out.print(mainstr[i]);
            }
            for( int i=0; i<m; i++)
            {
                substr= substr+String.valueOf(i+1);
            }
            //System.out.println("mainstr : "+mainstr);
            System.out.println();
            System.out.println(("substr : "+substr));
            solve(mainstr, substr, k);

            cases--;
        }

    }
}
//geeks for geeks


