package org.example;

public class Calculator {

    public static double f(double x) {
        return Math.log10(x) / Math.pow(x, 4);
    }

    public static double rectangleMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0;

        for (int i = 0; i < n; i++) {
            double x = a + h * (i + 0.5);
            sum += f(x);
        }

        return h * sum;
    }

    public static double chebyshevMethod(double a, double b, int n) {
        double sum = 0.0;

        for (int k = 1; k <= n; k++) {
            double t_k = Math.cos(Math.PI * (2.0 * k - 1) / (2.0 * n));
            double x_k = 0.5 * ((b - a) * t_k + (b + a));
            sum += f(x_k);
        }

        return Math.PI * (b - a) / (2.0 * n) * sum;
    }

    public static double simpsonMethod(double a, double b, int n) {
        if (n % 2 != 0) n++;

        double h = (b - a) / n;
        double sum = f(a) + f(b);

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                sum += 2 * f(x);
            } else {
                sum += 4 * f(x);
            }
        }

        return (h / 3) * sum;
    }

    public static void main(String[] args) {
        double a = 1.0;
        double b = 10.0;

        int[] intervals = {4, 5, 10, 20, 100};

        for (int n : intervals) {
            double resultRectangle = rectangleMethod(a, b, n);
            double resultChebyshev = chebyshevMethod(a, b, n);
            double resultSimpson = simpsonMethod(a, b, n);

            System.out.printf("n = %d\n", n);
            System.out.printf("  Метод середніх прямокутників: %.10f\n", resultRectangle);
            System.out.printf("  Метод Чебишова:               %.10f\n", resultChebyshev);
            System.out.printf("  Метод Сімпсона:               %.10f\n", resultSimpson);
            System.out.println();
        }
    }
}
