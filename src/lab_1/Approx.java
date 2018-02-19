package lab_1;

public class Approx extends Polynom {
    double[] X;
    double[] F;
    Solver Sol;

    public Approx(double[] X, double[] F)
    {
        this.X = X;
        this.F = F;

        int n = F.length;
        double[][] temp = new double[n][n];
        double[] Y = new double[n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    temp[i][j] = temp[i][j] + Math.pow(this.X[k], i+j);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Y[i] = Y[i] + this.F[j]*Math.pow(this.X[j], i);

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
