package org.example;

public class RootFindingFull {

    public static double f(double x) {
        return x + Math.log(x); // f(x) = x + ln(x)
    }

    public static double bisection(double a, double b, double eps) {
        if (f(a) * f(b) >= 0) throw new IllegalArgumentException("f(a) * f(b) must be < 0");
        // Перевіряємо, чи функція змінює знак на відрізку [a, b].
        // Якщо ні — кореня гарантовано немає, або їх більше одного, тому кидаємо помилку

        double c; // Змінна для середини відрізка

        while ((b - a) / 2 > eps) {
            // Повторюємо, поки довжина відрізка не стане меншою за задану точність eps

            c = (a + b) / 2; // Знаходимо середину відрізка

            if (f(c) == 0.0) return c; // Якщо значення функції в точці c — точно 0, ми знайшли корінь

            else if (f(c) * f(a) < 0) b = c;
                // Якщо функція змінює знак між a і c, то корінь знаходиться там, тому зсуваємо праву межу b

            else a = c;
            // Інакше зміщуємо ліву межу a на точку c
        }

        return (a + b) / 2;
        // Коли цикл завершено, повертаємо приблизне значення кореня — середину відрізка
    }

    public static double secant(double x0, double x1, double eps) {
        // Метод січних (наближення до кореня, використовуючи прямі через точки)

        double x2; // Змінна для збереження наступного наближення

        while (Math.abs(x1 - x0) > eps) {
            // Повторюємо, поки відстань між останніми двома наближеннями більша за точність

            x2 = x1 - f(x1) * (x1 - x0) / (f(x1) - f(x0));
            // Обчислюємо нову точку x2 за формулою методу січних (аналогія з перетином прямої з віссю X)

            x0 = x1; // Зсуваємо попереднє наближення
            x1 = x2; // Оновлюємо поточне наближення
        }

        return x1; // Повертаємо знайдене значення кореня
    }

    public static void main(String[] args) {
        double a = 0.1; // Ліва межа відрізка
        double b = 1.0; // Права межа відрізка
        double eps = 1e-6; // Точність обчислень (0.000001)

        double rootBisection = bisection(a, b, eps);
        System.out.println("Root (bisection): " + rootBisection);

        double rootSecant = secant(a, b, eps);
        System.out.println("Root (secant): " + rootSecant);
    }
}
