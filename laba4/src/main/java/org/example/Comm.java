package org.example;

public class Comm {

    // Функція, яку інтегруємо: log10(x) / x^4
    public static double f(double x) {
        return Math.log10(x) / Math.pow(x, 4);
    }

    // Метод середніх прямокутників (Rectangle method)
    public static double rectangleMethod(double a, double b, int n) {
        double h = (b - a) / n;  // Ширина одного прямокутника
        double sum = 0;

        for (int i = 0; i < n; i++) {
            double x = a + h * (i + 0.5);  // Центр кожного прямокутника
            sum += f(x);  // Додаємо висоту прямокутника
        }

        return h * sum;  // Загальна площа
    }

    // Метод Чебишова (Chebyshev nodes method)
    public static double chebyshevMethod(double a, double b, int n) {
        double sum = 0.0;

        for (int k = 1; k <= n; k++) {
            double t_k = Math.cos(Math.PI * (2.0 * k - 1) / (2.0 * n));  // Вузол Чебишова
            double x_k = 0.5 * ((b - a) * t_k + (b + a));  // Перетворення до [a, b]
            sum += f(x_k);  // Значення функції в точці
        }

        return Math.PI * (b - a) / (2.0 * n) * sum;  // Підсумкова площа
    }

    public static void main(String[] args) {
        double a = 1.0;  // Початок інтервалу
        double b = 10.0; // Кінець інтервалу

        // Додаємо більше значень n — від 4 до 100
        int[] intervals = {4, 5, 10, 20, 100};

        for (int n : intervals) {
            double resultRectangle = rectangleMethod(a, b, n);  // Метод прямокутників
            double resultChebyshev = chebyshevMethod(a, b, n);  // Метод Чебишова

            System.out.printf("n = %d\n", n);
            System.out.printf("  Метод середніх прямокутників: %.10f\n", resultRectangle);
            System.out.printf("  Метод Чебишова:               %.10f\n", resultChebyshev);
            System.out.println();
        }
    }
}
