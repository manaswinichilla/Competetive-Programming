import java.util.Arrays;
import java.util.Scanner;

public class Main4 {
    public static int categoryLessThan6(int [] turn, int category)
    {
        int score=0;
        for(int i=0; i<turn.length; i++)
        {
            if(turn[i]==category+1)
            {
                score+=category+1;
            }
        }
        return score;
    }

    public static int category7(int [] freq, int sumOfAllDice)
    {
        for (int i = 1; i <= 6; i++)
        {
            if (freq[i] >= 3) {
                return sumOfAllDice;
            }
        }
        return 0;
    }

    public static int category8(int []freq, int sumOfAllDice)
    {
        for (int i = 1; i <= 6; i++)
        {
            if (freq[i] >= 4) {
                return sumOfAllDice;
            }
        }
        return 0;
    }

    public static int category9(int []freq)
    {
        for (int i = 1; i <= 6; i++)
        {
            if (freq[i] >= 5) {
                return 50;
            }
        }
        return 0;
    }

    public static int category10(int []freq)
    {
        boolean flag;

        for (int i = 1; i <= 3; i++)
        {
            flag=true;
            for (int j = i; j < i+4; j++)
            {
                if (freq[j] == 0)
                {
                    flag=false;
                }
            }
            if(flag==true)
            {
                return 25;
            }
        }
        return 0;
    }

    public static int category11(int []freq)
    {
        boolean flag;
        for (int i = 1; i <= 2; i++)
        {
            flag=true;
            for (int j = i; j < i+5; j++)
            {
                if (freq[j] == 0)
                {
                    flag=false;
                }
            }
            if(flag==true)
            {
                return 35;
            }
        }
        return 0;
    }

    public static int category12(int []freq)
    {
        for (int i = 1; i <= 6; i++)
        {
            if (freq[i] >= 5)
            {
                return 40;
            }
        }
        for (int i = 1; i <= 6; i++)
        {
            for (int j = 1; j <= 6; j++)
            {
                if (i != j && freq[i] == 3 && freq[j] == 2)
                {
                    return 40;
                }
            }
        }
        return 0;
    }

    static int returnScore(int[] round, int category) {
        int score;
        if (category < 6)
        {
            score=categoryLessThan6(round, category);
            return score;
        }
        int sumOfAllDice = 0;
        int[] freq = new int[7];
        for (int i = 0; i < round.length; i++)
        {
            sumOfAllDice += round[i];
            freq[round[i]]++;
        }
        if (category == 6)
        {
            return sumOfAllDice;
        }
        else if (category == 7)
        {
            score=category7(freq,sumOfAllDice);
            return score;
        }
        else if (category == 8)
        {
            score=category8(freq,sumOfAllDice);
            return score;
        }
        else if (category == 9)
        {
            score=category9(freq);
            return score;
        }
        else if (category == 10)
        {
            score=category10(freq);
            return score;
        }
        else if (category == 11)
        {
            score=category11(freq);
            return score;
        }
        else if (category == 12)
        {
            score=category12(freq);
            return score;
        }
        return 0;
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int[][] scoreBoard = new int[13][13];
        int[][] matrix = new int[8192][99];
        int[][] newMatrix = new int[8192][99];
        int[][][] allbase = new int[13][8192][99];
        int[] assign = new int[13];

        while (s.hasNextLine())
        {
            int[][] turns = new int[13][5];
            for (int i = 0; i < 13; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    turns[i][j] = s.nextInt();
                }
            }
            s.nextLine();
            for (int i = 0; i < 13; i++)
            {
                for (int j = 0; j <13; j++)
                {
                    scoreBoard[i][j] = returnScore(turns[i], j);
                }
            }
            for (int j = 0; j < 8192; j++)
            {
                Arrays.fill(matrix[j], -1);
                Arrays.fill(newMatrix[j], -1);
            }
            matrix[0][0] = 0;
            for (int i = 0; i < 13; i++)
            {
                for (int j = 0; j < 8192; j++)
                {
                    int done = 0;
                    for (int b = 0; b < 13; b++)
                    {
                        if (((1 << b) & j) != 0)
                        {
                            done++;
                        }
                    }
                    if (done != i + 1)
                    {
                        continue;
                    }
                    for (int l = 0; l < 13; l++)
                    {
                        if (((1 << l) & j) != 0)
                        {
                            int temp = (~(1 << l) & j);
                            for (int k = 0; k <= 98; k++)
                            {
                                int t;
                                if (l < 6) {
                                    if (k < scoreBoard[i][l])
                                    {
                                        k = scoreBoard[i][l] - 1;
                                        continue;
                                    }
                                    t = k - scoreBoard[i][l];
                                } else
                                {
                                    t = k;
                                }
                                if (matrix[temp][t] < 0)
                                {
                                    continue;
                                }
                                int update;
                                if (k >= 63 && t < 63)
                                {
                                    update = 35 + matrix[temp][t] + scoreBoard[i][l];
                                } else
                                {
                                    update = matrix[temp][t] + scoreBoard[i][l];
                                }
                                if (update > newMatrix[j][k])
                                {
                                    allbase[i][j][k] = l;
                                    newMatrix[j][k] = update;
                                }
                            }
                        }
                    }
                }
                for (int j = 0; j < (1 << 13); j++)
                {
                    for (int k = 0; k <= 98; k++)
                    {
                        matrix[j][k] = newMatrix[j][k];
                    }
                    Arrays.fill(newMatrix[j], -1);
                }
            }
            int index = 8191;
            int max = -1;
            int total = 0;
            for (int k = 0; k <= 98; k++)
            {
                if (matrix[index][k] > max)
                {
                    max = matrix[index][k];
                    total = k;
                }
            }
            boolean flag = (total >= 63);
            for (int i = 12; i >= 0; i--)
            {
                assign[allbase[i][index][total]] = i;
                int temp = 0;
                if (allbase[i][index][total] < 6)
                {
                    temp = scoreBoard[i][allbase[i][index][total]];
                }
                index = (~(1 << allbase[i][index][total]) & index);
                total -= temp;
            }
            for (int i = 0; i < 13; i++)
            {
                System.out.print(scoreBoard[assign[i]][i] + " ");
            }
            if (flag)
            {
                System.out.print("35 ");
            } else
            {
                System.out.print("0 ");
            }
            System.out.println(max);
        }
    }
}