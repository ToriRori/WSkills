package lab_1;

import java.io.*;

public class Solver {
    public static void main(String[] args)
    {
        int n = 2;
        int[][] matrix = new int[n][n+1];
        matrix[0][0] = 1;
        matrix[0][1] = -1;
        matrix[0][2] = -5;
        matrix[1][0] = 2;
        matrix[1][1] = 1;
        matrix[1][2] = -7;

        for (int k = 0; k < n; k++)
        {
            int temp = k;
            while (matrix[temp][k] == 0)
            {
                if (temp == n)
                {
                    System.out.println("ERROR");
                    System.exit(0);
                }
                temp++;
            }
            int[] teq = new int[n+1];
            teq = matrix[k];
            matrix[k] = matrix[temp];
            matrix[temp] = teq;

            for (int i = k+1; i < n; i++)
            {
                temp = matrix[i][k];
                for (int j = 0; j < n+1; j++)
                {
                    matrix[i][j] = matrix[i][j] - matrix[k][j]*temp/matrix[k][k];
                }
            }
            temp = matrix[k][k];
            for (int i = 0; i < n+1; i++)
            {
                matrix[k][i] = matrix[k][i] / temp;
            }
        }
        for (int k = n - 1; k >= 0; k--)
        {
            for (int i = k-1; i >= 0; i--)
            {
                int temp = matrix[i][k];
                for (int j = 0; j < n+1; j++)
                {
                    matrix[i][j] = matrix[i][j] - matrix[k][j]*temp/matrix[k][k];
                }
            }
            int temp = matrix[k][k];
            for (int i = 0; i < n+1; i++)
            {
                matrix[k][i] = matrix[k][i] / temp;
            }
        }
        for (int i = 0; i < n; i++)
            System.out.println(matrix[i][n]);
    }
}

