package org.example;

public class Solver {
    public static void main(String[] args) {
        double[][] A = {
                {2, -3, 3, 2},
                {6, 9, -2, -1},
                {10, 3, -3, -2},
                {8, 6, 1, 3}
        };

        // Це ще одна таблиця — вектор B.
        double[] B = {3, -4, 3, -7};

        System.out.println("Метод Крамера:");
        cramer(A, B);

        System.out.println("\nІтераційний метод:");
        iterate(A, B);
    }

    static void cramer(double[][] A, double[] B) {
        double det = det(A); // Знаходимо головний визначник

        if (Math.abs(det) < 1e-10) {
            // Якщо цей визначник майже дорівнює нулю — система нерозв’язна або має багато рішень
            System.out.println("det = 0, розв'язку немає або їх безліч.");
            return;
        }

        // Обчислюємо кожну змінну окремо
        for (int i = 0; i < B.length; i++) {
            double[][] Ai = copyMatrix(A); // Копіюємо матрицю, щоб не зіпсувати оригінал

            // Замінюємо в Ai стовпець номер i на вектор B
            for (int j = 0; j < B.length; j++)
                Ai[j][i] = B[j];

            // Рахуємо визначник цієї нової матриці
            double xi = det(Ai) / det; // Формула Крамера: x = визначник нової матриці / головний визначник
            System.out.printf("x%d = %.6f\n", i + 1, xi); // Виводимо результат
        }
    }

    // Ітераційний метод (метод Якобі): поступово наближаємось до розв’язку, роблячи багато підрахунків
    static void iterate(double[][] A, double[] B) {
        int n = B.length;
        double[] x = new double[n]; // Початкові значення всіх "іксів" ставимо рівними нулю
        double eps = 1e-6; // Точність, з якою зупиняємось — коли нові значення майже не змінюються

        for (int k = 0; k < 100; k++) { // Робимо максимум 100 спроб
            double[] newX = new double[n]; // Нове наближення

            for (int i = 0; i < n; i++) {
                double sum = B[i]; // Починаємо з правої частини рівняння
                for (int j = 0; j < n; j++) {
                    if (j != i) sum -= A[i][j] * x[j]; // Віднімаємо всі відомі множення, крім того, що шукаємо
                }
                newX[i] = sum / A[i][i]; // Ділимо і отримуємо нове значення для x[i]
            }

            // Якщо нові значення майже такі ж, як попередні — значить, ми знайшли відповідь
            if (closeEnough(x, newX, eps)) {
                for (int i = 0; i < n; i++)
                    System.out.printf("x%d = %.6f\n", i + 1, newX[i]);
                return; // Закінчуємо
            }

            x = newX; // Продовжуємо — оновлюємо старі значення
        }

        // Якщо за 100 спроб нічого не зійшлося — виводимо повідомлення
        System.out.println("Не зійшлося.");
    }

    // Перевіряє, чи нові значення дуже схожі на старі — якщо так, то ми наблизилися до розв’язку
    static boolean closeEnough(double[] a, double[] b, double eps) {
        for (int i = 0; i < a.length; i++)
            if (Math.abs(a[i] - b[i]) > eps)
                return false; // Хоч одне відхилилось — ще не кінець
        return true; // Усі значення схожі — можемо зупинятись
    }

    // Створює копію матриці, щоб не змінити її випадково
    static double[][] copyMatrix(double[][] m) {
        int n = m.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++)
            System.arraycopy(m[i], 0, c[i], 0, n);
        return c;
    }

    // Обчислює визначник квадратної матриці (це спеціальне число, яке показує, чи є у рівняння розв’язок)
    static double det(double[][] m) {
        int n = m.length;

        if (n == 1) return m[0][0]; // Якщо матриця 1х1 — просто повертаємо це число

        if (n == 2) return m[0][0] * m[1][1] - m[0][1] * m[1][0]; // Для 2х2 — проста формула

        double res = 0; // Результат — тут збиратимемо значення

        // Рекурсія — обчислюємо визначник за правилом Лапласа
        for (int k = 0; k < n; k++) {
            double[][] minor = new double[n - 1][n - 1]; // Створюємо "мінор" — маленьку матрицю

            for (int i = 1; i < n; i++) {
                int col = 0;
                for (int j = 0; j < n; j++) {
                    if (j == k) continue; // Пропускаємо k-ий стовпець
                    minor[i - 1][col++] = m[i][j]; // Копіюємо елементи в мінор
                }
            }

            // Додаємо до результату: знак * число * визначник мінора
            res += Math.pow(-1, k) * m[0][k] * det(minor);
        }

        return res; // Повертаємо остаточний визначник
    }
}
