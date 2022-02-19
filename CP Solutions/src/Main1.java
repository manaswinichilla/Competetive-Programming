import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class Main1{

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        /*taking input total cases*/
        int cases=Integer.parseInt(br.readLine());
        br.readLine();

        /*For each case*/
        for (int c=0;c<cases;c++)
        {
            int totalCandidates=Integer.parseInt(br.readLine());
            /*array of candidates*/
            String [] names=new String[totalCandidates];
            for (int n=0;n<totalCandidates;n++)
            {
                names[n]=br.readLine();
            }


            String entry;
            /*list of list to store peoples votes*/
            ArrayList<ArrayList<Integer>> balletEntries=new ArrayList<>();

            /*loop to take all ballet entries*/
            while (true)
            {
                entry=br.readLine();
                if (entry==null || entry.equals(""))
                {
                    break;
                }

                /* to store all ballet entries in list of list*/
                ArrayList<Integer> prioritylist=new ArrayList<>();
                /* break into tokens to access individual pref*/
                StringTokenizer entry_tokenized=new StringTokenizer(entry);
                balletEntries.add(prioritylist);
                for (int n=0;n<totalCandidates;n++)
                {
                    prioritylist.add(Integer.parseInt(entry_tokenized.nextToken())-1);
                }

            }

            String winner="";
            boolean [] elimniatedCandidates=new boolean [totalCandidates];
            int index;
            for (int i=0;i<totalCandidates;i++)
            {
                int [] CountVoteforEachCandidate=new int [totalCandidates];
                for (ArrayList<Integer> votes : balletEntries) /*each entry into votes till the ned of ballet entries*/
                {
                    index=votes.get(i);
                    CountVoteforEachCandidate[index]=CountVoteforEachCandidate[index]+1;
                }

                int minVotes=10000, maxVotes=-1;
                for (int j=0;j<totalCandidates;j++)
                {
                    if (!elimniatedCandidates[j])
                    {
                        minVotes=Math.min(minVotes, CountVoteforEachCandidate[j]);
                        maxVotes=Math.max(maxVotes, CountVoteforEachCandidate[j]);
                    }
                }

                /* equal or more than half the votes */
                if (minVotes==maxVotes) {
                    StringBuilder eligibleNames=new StringBuilder();
                    for (int j=0;j<totalCandidates;j++)
                    {
                        if (!elimniatedCandidates[j])
                        {
                            eligibleNames.append(names[j]);
                            eligibleNames.append('\n');
                        }
                    }
                    eligibleNames.setLength(eligibleNames.length()-1);
                    winner=eligibleNames.toString();
                    break;
                }
                else /* eliminate=true for candidate with min votes*/
                {
                    for (int j=0;j<totalCandidates;j++)
                    {
                        if (!elimniatedCandidates[j] && CountVoteforEachCandidate[j]==minVotes)
                        {
                            elimniatedCandidates[j]=true;
                            for (ArrayList<Integer> votes : balletEntries)
                            {
                                votes.remove(Integer.valueOf(j));
                            }
                        }
                    }
                    i--;
                }
            }

            if (c>0)
            {
                System.out.println();
            }
            System.out.println(winner);
        }
    }
}

// https://docs.oracle.com/javase/tutorial/java/data/buffers.html
// https://www.geeksforgeeks.org/arraylist-of-arraylist-in-java/
// https://www.geeksforgeeks.org/java-math-min-method-examples/