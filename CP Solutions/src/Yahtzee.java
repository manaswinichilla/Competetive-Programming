import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Yahtzee {

    static final int MAX_BONUS_SUM = 98;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())
        {
            int[][] round = new int[13][5];
            for (int i = 0; i < 13; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    round[i][j] = in.nextInt();
                }
            }
            in.nextLine();
            int[][] point = new int[13][13];
            for (int i = 0; i < 13; i++)
            {
                for (int j = 0; j < 13; j++)
                {
                    point[i][j] = getPoint(round[i], j);
                }
            }
            int[][] state = new int[1 << 13][MAX_BONUS_SUM + 1];
            int[][] newState = new int[1 << 13][MAX_BONUS_SUM + 1];
            for (int j = 0; j < (1 << 13); j++)
            {
                Arrays.fill(state[j], -1);
                Arrays.fill(newState[j], -1);
            }
            state[0][0] = 0;
            int[][][] choice = new int[13][1 << 13][MAX_BONUS_SUM + 1];
            for (int i = 0; i < 13; i++)
            {
                for (int j = 0; j < (1 << 13); j++)
                {
                    int usedSlot = 0;
                    for (int b = 0; b < 13; b++)
                    {
                        if (((1 << b) & j) != 0)
                        {
                            usedSlot++;
                        }
                    }
                    if (usedSlot != i + 1)
                    {
                        continue;
                    }
                    for (int b = 0; b < 13; b++)
                    {
                        if (((1 << b) & j) != 0)
                        {
                            int j2 = (~(1 << b) & j);
                            for (int s = 0; s <= MAX_BONUS_SUM; s++)
                            {
                                int oldSum;
                                if (b < 6) {
                                    if (s < point[i][b])
                                    {
                                        s = point[i][b] - 1;
                                        continue;
                                    }
                                    oldSum = s - point[i][b];
                                } else
                                {
                                    oldSum = s;
                                }
                                if (state[j2][oldSum] < 0)
                                {
                                    continue;
                                }
                                int newPoint;
                                if (s >= 63 && oldSum < 63)
                                {
                                    newPoint = 35 + state[j2][oldSum] + point[i][b];
                                } else
                                {
                                    newPoint = state[j2][oldSum] + point[i][b];
                                }
                                if (newPoint > newState[j][s]) {
                                    choice[i][j][s] = b;
                                    newState[j][s] = newPoint;
                                }
                            }
                        }
                    }
                }
                for (int j = 0; j < (1 << 13); j++)
                {
                    for (int s = 0; s <= MAX_BONUS_SUM; s++)
                    {
                        state[j][s] = newState[j][s];
                    }
                    Arrays.fill(newState[j], -1);
                }
            }
            int index = (1 << 13) - 1;
            int maxPoint = -1;
            int sum = 0;
            for (int s = 0; s <= MAX_BONUS_SUM; s++)
            {
                if (state[index][s] > maxPoint)
                {
                    maxPoint = state[index][s];
                    sum = s;
                }
            }
            boolean bonus = (sum >= 63);
            int[] mapping = new int[13];
            for (int i = 12; i >= 0; i--)
            {
                mapping[choice[i][index][sum]] = i;
                int p = 0;
                if (choice[i][index][sum] < 6)
                {
                    p = point[i][choice[i][index][sum]];
                }
                index = (~(1 << choice[i][index][sum]) & index);
                sum -= p;
            }

            for (int i = 0; i < 13; i++)
            {
                System.out.print(point[mapping[i]][i] + " ");
            }
            if (bonus)
            {
                System.out.print("35 ");
            } else
            {
                System.out.print("0 ");
            }
            System.out.println(maxPoint);
        }
    }

    static int getPoint(int[] round, int category) {
        if (category < 6) {
            int sum = 0;
            for (int i = 0; i < round.length; i++) {
                if (round[i] == category + 1) {
                    sum += category + 1;
                }
            }
            return sum;
        }
        int sum = 0;
        int[] count = new int[7];
        for (int i = 0; i < round.length; i++) {
            sum += round[i];
            count[round[i]]++;
        }
        if (category == 6) {
            return sum;
        } else if (category == 7) {
            for (int i = 1; i <= 6; i++) {
                if (count[i] >= 3) {
                    return sum;
                }
            }
        } else if (category == 8) {
            for (int i = 1; i <= 6; i++) {
                if (count[i] >= 4) {
                    return sum;
                }
            }
        } else if (category == 9) {
            for (int i = 1; i <= 6; i++) {
                if (count[i] >= 5) {
                    return 50;
                }
            }
        } else if (category == 10) {
            for (int i = 1; i <= 3; i++) {
                if (isStraight(count, i, 4)) {
                    return 25;
                }
            }
        } else if (category == 11) {
            for (int i = 1; i <= 2; i++) {
                if (isStraight(count, i, 5)) {
                    return 35;
                }
            }
        } else if (category == 12) {
            for (int i = 1; i <= 6; i++) {
                if (count[i] >= 5) {
                    return 40;
                }
            }
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    if (i != j && count[i] == 3 && count[j] == 2) {
                        return 40;
                    }
                }
            }
        }
        return 0;
    }

    static boolean isStraight(int[] count, int start, int num) {
        for (int i = start; i < start + num; i++) {
            if (count[i] == 0) {
                return false;
            }
        }
        return true;
    }
}