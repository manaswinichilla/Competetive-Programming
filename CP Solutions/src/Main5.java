import java.util.*;
import java.util.ArrayList;


public class Main5 {

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        s.nextLine();
        int cases = s.nextInt();
        s.nextLine();
        String possibility;
        String input;
        while(cases>0)
        {
            ArrayList<String> fragments = new ArrayList<String>();
            while(s.hasNextLine())
            {
                input=s.nextLine();
                fragments.add(input);
            }
            HashMap<String, Integer> hp = new HashMap<>();
            for(int i=0; i< fragments.size(); i++)
            {
                for(int j=i+1; j< fragments.size(); j++)
                {
                    possibility=fragments.get(i)+fragments.get(j);
                    hp.put(possibility, hp.get(possibility)+1);
                    possibility=fragments.get(j)+fragments.get(i);
                    hp.put(possibility, hp.get(possibility)+1);
                }
            }
            Iterator<Map.Entry<String,Integer>> it = hp.entrySet().iterator();
            Iterator<Map.Entry<String,Integer>> copy = hp.entrySet().iterator();

            while(it.hasNext())
            {
                Map.Entry<String, Integer> entry1 = it.next();
                Map.Entry<String, Integer> entry2 = copy.next();
                if(entry1.getValue()>entry2.getValue())
                {
                    entry2=entry1;
                }
                it.next();
            }
            System.out.println(copy.next().getKey());
            if(cases==0)
            {
                System.out.println();
            }
             cases--;
        }
    }
}
