package org.example;

public class EulerSolver {

    // Функція f(x, y) = 2xy + 3y
    public static double f(double x, double y) {
        return 2 * x * y + 3 * y;
    }

    // Прямий (явний) метод Ейлера
    public static void explicitEuler(double x0, double y0, double h, double xn) {
        System.out.println("=== Прямий метод Ейлера ===");
        double x = x0;
        double y = y0;

        System.out.printf("x = %.1f, y = %.5f%n", x, y);

        while (x < xn) {
            y = y + h * f(x, y);
            x = x + h;
            System.out.printf("x = %.1f, y = %.5f%n", x, y);
        }
    }

    // Виправлений (модифікований) метод Ейлера
    public static void improvedEuler(double x0, double y0, double h, double xn) {
        System.out.println("=== Виправлений метод Ейлера ===");
        double x = x0;
        double y = y0;

        System.out.printf("x = %.1f, y = %.5f%n", x, y);

        while (x < xn) {
            double k1 = f(x, y);
            double k2 = f(x + h, y + h * k1);
            y = y + h * (k1 + k2) / 2;
            x = x + h;
            System.out.printf("x = %.1f, y = %.5f%n", x, y);
        }
    }

    public static void main(String[] args) {
        double x0 = 0.0;
        double y0 = 0.1;
        double h = 0.2;
        double xn = 1.0;

        explicitEuler(x0, y0, h, xn);
        System.out.println();
        improvedEuler(x0, y0, h, xn);
    }
}
