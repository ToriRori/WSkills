package lab_1;

public class Polynom {
    int n;
    double[] c;

    public static void main(String[] args) {
        double[] x = new double[5];
        double[] F = new double[5];

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

        Polynom PolI = new Interp(x, F);

        for (int i = 0; i < PolI.n; i++)
        {
            System.out.println(PolI.c[i]);
        }

        System.out.println("");
        //_________________________________

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

        Polynom PolA = new Approx(x, F);

        for (int i = 0; i < PolA.n; i++)
        {
            System.out.println(PolA.c[i]);
        }
    }
}
