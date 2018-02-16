package lab_1;

import java.io.*;
import java.util.*;

public class Solver {

    static double[][] matrix = new double[3][3];

    public static double[][] getMatrix(){
        double[][] matrix = new double[2][2];
        matrix[0][0] = 1;
        matrix[0][1] = -1;
        matrix[1][0] = 2;
        matrix[1][1] = 1;
        return matrix;
    }

    public static void main(String[] args)
    {
        matrix = getMatrix();
        int n = matrix[0].length;
        double[] F = new double[n];
        F[0] = -5;
        F[1] = -7;
        double[] Res = new double[n];
        Res = getSolve(F);
        for (int i = 0; i < n; i++)
            System.out.println(Res[i]);
    }

    public static double[] getSolve(double[] F)
    {
        int n = F.length;
        for (int k = 0; k < n; k++)
        {
            int temp = k;
            while (matrix[temp][k] == 0)
            {
                temp++;
                if (k == n-1)
                {
                    System.out.println("ERR");
                    System.exit(0);
                }
                if (temp == n)
                {
                    k++;
                    temp = k;
                }
            }
            double[] teq = new double[n];
            teq = matrix[k];
            matrix[k] = matrix[temp];
            matrix[temp] = teq;
            double tF = F[k];
            F[k] = F[temp];
            F[temp] = tF;

            for (int i = k+1; i < n; i++)
            {
                double temp1 = matrix[i][k];
                for (int j = 0; j < n; j++)
                {
                    matrix[i][j] = matrix[i][j] - matrix[k][j]*temp1/matrix[k][k];
                }
                F[i] = F[i] - F[k]*temp1/matrix[k][k];
            }
            double temp1 = matrix[k][k];
            for (int i = 0; i < n; i++)
            {
                matrix[k][i] = matrix[k][i] / temp1;
            }
            F[k] = F[k]/temp1;
        }
        for (int k = n - 1; k >= 0; k--)
        {
            for (int i = k-1; i >= 0; i--)
            {
                double temp = matrix[i][k];
                for (int j = 0; j < n; j++)
                {
                    matrix[i][j] = matrix[i][j] - matrix[k][j]*temp/matrix[k][k];
                }
                F[i] = F[i] - F[k]*temp/matrix[k][k];
            }
            double temp = matrix[k][k];
            for (int i = 0; i < n; i++)
            {
                matrix[k][i] = matrix[k][i] / temp;
            }
            F[k] = F[k]/temp;
        }
        return F;
    }
}

