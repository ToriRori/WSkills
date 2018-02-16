package lab_1;
import java.io.*;
import java.util.*;

public class Interp {
    static double[] x = new double[5];
    static double[] F = new double[5];

    public static void main(String[] args) {
        x[0] = 0;
        x[1] = 25;
        x[2] = 50;
        x[3] = 75;
        x[4] = 100;
        F[0] = 10;
        F[1] = 9.1;
        F[2] = 8.7;
        F[3] = 5.6;
        F[4] = 2.5;
        int n = F.length;
        double[][] X = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                X[i][j] = Math.pow(x[i], n-j-1);
            }
        double res[] = new double[n];
        Solver.matrix = X;
        res = Solver.getSolve(F);
        for (int i = n-1; i >= 0; i--)
        {
            System.out.println(res[i]);
        }
    }

    /*Interp(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)
        {
            x[i] = sc.nextDouble();
        }
        for (int i = 0; i < n; i++)
        {
            F[i] = sc.nextDouble();
        }
    }*/
}
