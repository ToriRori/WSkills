package lab_1;

public class Approx {
    static double[] x = new double[5];
    static double[] F = new double[5];

    public static void main(String[] args) {
        x[0] = 0.78;
        x[1] = 1.56;
        x[2] = 2.34;
        x[3] = 3.12;
        x[4] = 3.81;
        F[0] = 2.50;
        F[1] = 1.20;
        F[2] = 1.12;
        F[3] = 2.25;
        F[4] = 4.28;
        int n = 3;
        double[][] X = new double[n][n];
        double[] Y = new double[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    X[i][j] = X[i][j] + Math.pow(x[k], i+j);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Y[i] = Y[i] + F[j]*Math.pow(x[j], i);
        double res[] = new double[n];
        Solver.matrix = X;
        res = Solver.getSolve(Y);
        for (int i = n-1; i >= 0; i--)
        {
            System.out.println(res[i]);
        }
    }

}
