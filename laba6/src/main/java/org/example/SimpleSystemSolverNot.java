package org.example;

public class SimpleSystemSolverNot {

    public static void main(String[] args) {
        double x = 1.0;
        double y = 1.0;
        double eps = 1e-6;

        for (int i = 0; i < 50; i++) { // Цикл, що виконує ітераціЇ методу Ньютона.

            double f1 = 10 * x * x + 20 * y * y - 200;
            double f2 = 5 * Math.exp(x) + 15 * y - 100;

            double df1dx = 20 * x; // Частинна похідна f1 за x.
            double df1dy = 40 * y; // Частинна похідна f1 за y.
            double df2dx = 5 * Math.exp(x); // Частинна похідна f2 за x.
            double df2dy = 15; // Частинна похідна f2 за y.

            double det = df1dx * df2dy - df1dy * df2dx; // Обчислення визначника (детермінанта) матриці Якобі.

            if (Math.abs(det) < 1e-10) { // Перевірка на виродженість матриці (якщо визначник близький до 0).
                System.out.println("Помилка: матриця вироджена."); // Виведення повідомлення про помилку.
                break; // Завершення циклу, оскільки розв’язок знайти неможливо.
            }

            double dx = (-f1 * df2dy + f2 * df1dy) / det; // Зміна x, розрахована за формулою Крамера.
            double dy = (-df1dx * f2 + df2dx * f1) / det; // Зміна y, розрахована за формулою Крамера.

            x += dx; // Оновлення значення x.
            y += dy; // Оновлення значення y.

            if (Math.abs(dx) < eps && Math.abs(dy) < eps) // Якщо зміни дуже малі — досягнута точність.
                break; // Вихід з циклу, бо досягнута задана точність.
        }

        System.out.printf("Розв’язок: x = %.6f, y = %.6f\n", x, y);
    }
}
