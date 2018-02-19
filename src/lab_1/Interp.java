package lab_1;
import java.io.*;
import java.util.*;

public class Interp extends Polynom {

    double[] X;
    double[] F;
    Solver Sol;

    public Interp(double[] X, double[] F)
    {
        this.X = X;
        this.F = F;
        int n = F.length;
        double[][] temp = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                temp[i][j] = Math.pow(this.X[i], n-j-1);
            }
        Sol = new Solver(temp, n);
        super.n = n;
        super.c = Sol.getSolve(F);
        for (int i = 0; i <= n/2; i++)
        {
            double temp1 = super.c[i];
            super.c[i] = super.c[n-i-1];
            super.c[n-i-1] = temp1;
        }
    }
}
