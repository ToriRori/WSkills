package lab_1;

import java.io.*;
import java.util.*;

public class Solver {
    int n;
    double[][] matrix;

    public Solver(double [][] matrix, int n)
    {
        this.n = n;
        this.matrix = new double[n][n];
        this.matrix = matrix;
    }

    public static void main(String[] args)
    {

        double [][] matrix = new double[2][2];
        matrix[0][0] = 1;
        matrix[0][1] = -1;
        matrix[1][0] = 2;
        matrix[1][1] = 1;
        Solver Sol = new Solver(matrix, 2);
        double[] F = new double[2];
        F[0] = -5;
        F[1] = -7;
        double[] Res = new double[Sol.n];
        Res = Sol.getSolve(F);
        for (int i = 0; i < Sol.n; i++)
            System.out.println(Res[i]);
    }

    public double[] getSolve(double[] F)
    {
        for (int k = 0; k < this.n; k++)
        {
            int temp = k;
            while (this.matrix[temp][k] == 0)
            {
                temp++;
                if (k == this.n-1)
                {
                    System.out.println("ERR");
                    System.exit(0);
                }
                if (temp == this.n)
                {
                    k++;
                    temp = k;
                }
            }
            double[] teq = new double[this.n];
            teq = this.matrix[k];
            this.matrix[k] = this.matrix[temp];
            this.matrix[temp] = teq;
            double tF = F[k];
            F[k] = F[temp];
            F[temp] = tF;

            for (int i = k+1; i < this.n; i++)
            {
                double temp1 = this.matrix[i][k];
                for (int j = 0; j < this.n; j++)
                {
                    this.matrix[i][j] = this.matrix[i][j] - this.matrix[k][j]*temp1/this.matrix[k][k];
                }
                F[i] = F[i] - F[k]*temp1/this.matrix[k][k];
            }
            double temp1 = this.matrix[k][k];
            for (int i = 0; i < this.n; i++)
            {
                this.matrix[k][i] = this.matrix[k][i] / temp1;
            }
            F[k] = F[k]/temp1;
        }
        for (int k = this.n - 1; k >= 0; k--)
        {
            for (int i = k-1; i >= 0; i--)
            {
                double temp = this.matrix[i][k];
                for (int j = 0; j < this.n; j++)
                {
                    this.matrix[i][j] = this.matrix[i][j] - this.matrix[k][j]*temp/this.matrix[k][k];
                }
                F[i] = F[i] - F[k]*temp/this.matrix[k][k];
            }
            double temp = this.matrix[k][k];
            for (int i = 0; i < this.n; i++)
            {
                this.matrix[k][i] = this.matrix[k][i] / temp;
            }
            F[k] = F[k]/temp;
        }
        return F;
    }
}

