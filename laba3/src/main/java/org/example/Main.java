package org.example;

public class Main {
    public static void main(String[] args) {
        double[][] A = {
                {2, -3, 3, 2},
                {6, 9, -2, -1},
                {10, 3, -3, -2},
                {8, 6, 1, 3}
        };
        double[] B = {3, -4, 3, -7};

        System.out.println("Метод Крамера:");
        cramer(A, B);

        System.out.println("\nІтераційний метод:");
        iterate(A, B);
    }

    static void cramer(double[][] A, double[] B) {
        double det = det(A);
        if (Math.abs(det) < 1e-10) {
            System.out.println("det = 0, розв'язку немає або їх безліч.");
            return;
        }
        for (int i = 0; i < B.length; i++) {
            double[][] Ai = copyMatrix(A);
            for (int j = 0; j < B.length; j++)
                Ai[j][i] = B[j];
            double xi = det(Ai) / det;
            System.out.printf("x%d = %.6f\n", i + 1, xi);
        }
    }

    static void iterate(double[][] A, double[] B) {
        int n = B.length;
        double[] x = new double[n];
        double eps = 1e-6;
        for (int k = 0; k < 100; k++) {
            double[] newX = new double[n];
            for (int i = 0; i < n; i++) {
                double sum = B[i];
                for (int j = 0; j < n; j++)
                    if (j != i) sum -= A[i][j] * x[j];
                newX[i] = sum / A[i][i];
            }
            if (closeEnough(x, newX, eps)) {
                for (int i = 0; i < n; i++)
                    System.out.printf("x%d = %.6f\n", i + 1, newX[i]);
                return;
            }
            x = newX;
        }
        System.out.println("Не зійшлося.");
    }

    static boolean closeEnough(double[] a, double[] b, double eps) {
        for (int i = 0; i < a.length; i++)
            if (Math.abs(a[i] - b[i]) > eps)
                return false;
        return true;
    }

    static double[][] copyMatrix(double[][] m) {
        int n = m.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++)
            System.arraycopy(m[i], 0, c[i], 0, n);
        return c;
    }

    static double det(double[][] m) {
        int n = m.length;
        if (n == 1) return m[0][0];
        if (n == 2) return m[0][0] * m[1][1] - m[0][1] * m[1][0];
        double res = 0;
        for (int k = 0; k < n; k++) {
            double[][] minor = new double[n - 1][n - 1];
            for (int i = 1; i < n; i++) {
                int col = 0;
                for (int j = 0; j < n; j++) {
                    if (j == k) continue;
                    minor[i - 1][col++] = m[i][j];
                }
            }
            res += Math.pow(-1, k) * m[0][k] * det(minor);
        }
        return res;
    }
}
