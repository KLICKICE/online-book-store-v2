package org.example;

public class RootFinding {

    public static double f(double x) {
        return x + Math.log(x); // f(x) = x + ln(x)
    }

    public static double bisection(double a, double b, double eps) {
        if (f(a) * f(b) >= 0) throw new IllegalArgumentException("f(a) * f(b) must be < 0");

        double c;

        while ((b - a) / 2 > eps) {

            c = (a + b) / 2;

            if (f(c) == 0.0) return c;

            else if (f(c) * f(a) < 0) b = c;
                // Змінює знак між a і c, корінь знаходиться тут, тому зсуваємо праву межу b

            else a = c;
            // Інакше зміщуюю ліву межу a на точку c
        }

        return (a + b) / 2;
        // Приблизне значення
    }

    public static double secant(double x0, double x1, double eps) {
        double x2; // Наступне наближення

        while (Math.abs(x1 - x0) > eps) {

            x2 = x1 - f(x1) * (x1 - x0) / (f(x1) - f(x0));
            // Обчислюю нову точку x2 за формулою методу січних

            x0 = x1;
            x1 = x2;
        }

        return x1;
    }

    public static void main(String[] args) {
        double a = 0.1;
        double b = 1.0;
        double eps = 1e-6; // Точність обчислень (0.000001)

        double rootBisection = bisection(a, b, eps);
        System.out.println("Root (bisection): " + rootBisection);

        double rootSecant = secant(a, b, eps);
        System.out.println("Root (secant): " + rootSecant);
    }
}
