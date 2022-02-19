// attempted tp understand and implement graham scan briefed in class on sample example


import java.util.Arrays;
import java.util.Stack;

public class HerdingFrosh
{
    public static double findangle(int[] a, int[] b, int[] c)
    {
        int[] vectorone = {a[0] - b[0], a[1] - b[1]};
        int[] vectortwo = {c[0] - b[0], c[1] - b[1]};
        double val = Math.acos(dotproduct(vectorone, vectortwo)/(magnitude(vectorone)*magnitude(vectortwo)))*180/Math.PI;
        if(crossproduct(vectorone, vectortwo) > 0)
            val = 360 - val;
        return val;
    }

    public static double dotproduct(int[] vectorone, int[] vectortwo)
    {
        return vectorone[0]*vectortwo[0] + vectorone[1]*vectortwo[1];
    }

    public static double magnitude(int[] vector)
    {
        return Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1]);
    }

    public static int crossproduct(int[] vectorone, int[] vectortwo)
    {
        return vectorone[0]*vectortwo[1] - vectortwo[0]*vectorone[1];
    }

    public static double distance(int[] a, int[] b)
    {
        return Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2));
    }

    public static int[][] sort(int[][] arr, int[] min, int[] p_points)
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0; j<arr.length-1;j++)
            {
                if(findangle(p_points, min, arr[j]) < findangle(p_points, min, arr[j+1]))
                {
                    int[] temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                if(findangle(p_points, min, arr[j]) == findangle(p_points, min, arr[j+1]) && distance(arr[j+1], min) < distance(arr[j], min))
                {
                    int[] temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static Stack<int[]> graham(int[][] points)
    {
        Stack<int[]> stack = new Stack<int[]>();
        int min_i = 0;
        for(int i=0;i<points.length;i++)
        {
            if(points[i][1] < points[min_i][1])
                min_i = i;
        }
        int[] min = points[min_i];
        points[min_i] = points[0];
        points[0] = min;
        int[] p_points = {points[min_i][0] - 1, points[min_i][1]};
        points = sort(points, min, p_points);
        stack.push(points[0]);
        stack.push(points[1]);
        stack.push(points[2]);
        for(int i = 3; i < points.length; i++)
        {
            while(findangle(stack.get(1), stack.peek(), points[i]) >= 180)
            {
                stack.pop();
            }
            stack.push(points[i]);
        }
        return stack;
    }

    public static void main(String[] args)
    {
       int[][] points = {{0, -2}, {2, 0}, {2, 20}, {1, 1}, {1, 2}, {-2, 0}, {-1, -1}};
        Stack<int[]> s = graham(points);
        double dist=0;
        for( int i=0; i<s.size(); i++)
        {
            if(i<s.size()-1)
                dist = dist + distance(s.get(i), s.get(i+1));
            System.out.println(Arrays.toString(s.get(i)));
        }
        System.out.printf("Length of boundary : %.3f", (dist+1.0));
    }
}