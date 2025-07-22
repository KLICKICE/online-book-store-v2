package org.example;

import java.util.Arrays; // Імпортуємо клас Arrays для зручного виводу масивів

public class Main { // Основний клас програми

    public static void main(String[] args) { // Головна функція, з якої запускається програма
        // Матриця коефіцієнтів A (таблиця з 4 рядків і 4 стовпців)
        double[][] A = {
                {1, -1, -4, 9},
                {2, -3, 1, 5},
                {1, 2, 0, -4},
                {3, -2, -5, 1}
        };

        // Вектор вільних членів B — значення після знаку "=" у рівняннях
        double[] B = {22, -3, -3, 3};

        // Розв’язуємо систему рівнянь методом Крамера
        double[] solutionCramer = solveCramer(A, B);
        // Виводимо розв’язок, отриманий методом Крамера
        System.out.println("Solution by Cramer's Method: " + Arrays.toString(solutionCramer));

        // Розв’язуємо ту ж систему методом Гаусса-Жордана
        double[] solutionGaussJordan = solveGaussJordan(A, B);
        // Виводимо розв’язок, отриманий методом Гаусса-Жордана
        System.out.println("Solution by Gauss-Jordan Method: " + Arrays.toString(solutionGaussJordan));
    }

    // Метод Крамера — працює тільки коли визначник матриці A ≠ 0
    public static double[] solveCramer(double[][] A, double[] B) {
        int n = A.length; // Отримуємо розмірність системи (кількість рівнянь)
        double detA = determinant(A); // Обчислюємо визначник основної матриці A

        if (detA == 0) {
            throw new IllegalArgumentException("Determinant is zero, system has no unique solution.");
            // Якщо визначник 0, то система не має єдиного розв’язку
        }

        double[] result = new double[n]; // Масив для збереження результатів (x1, x2, ..., xn)

        for (int i = 0; i < n; i++) {
            // Створюємо нову матрицю Ai, де i-й стовпець замінений на вектор B
            double[][] Ai = replaceColumn(A, B, i);
            // Розв’язок x[i] = визначник нової матриці Ai поділити на визначник A
            result[i] = determinant(Ai) / detA;
        }

        return result; // Повертаємо масив результатів
    }

    // Обчислення визначника матриці
    private static double determinant(double[][] matrix) {
        int n = matrix.length;

        // Якщо матриця 1x1, визначник — це просто єдине число
        if (n == 1) return matrix[0][0];

        // Якщо матриця 2x2, застосовуємо просту формулу
        if (n == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;

        // Рекурсивно розкладаємо визначник по першому рядку
        for (int i = 0; i < n; i++) {
            double[][] subMatrix = new double[n - 1][n - 1]; // Створюємо допоміжну підматрицю

            // Формуємо підматрицю, виключаючи перший рядок і i-й стовпець
            for (int j = 1; j < n; j++) {
                int colIndex = 0;
                for (int k = 0; k < n; k++) {
                    if (k == i) continue; // Пропускаємо i-й стовпець
                    subMatrix[j - 1][colIndex++] = matrix[j][k];
                }
            }

            // Обчислюємо визначник з урахуванням знаку (+ або -)
            det += matrix[0][i] * determinant(subMatrix) * (i % 2 == 0 ? 1 : -1);
        }

        return det; // Повертаємо знайдений визначник
    }

    // Замінює стовпець colIndex у матриці на вектор column
    private static double[][] replaceColumn(double[][] matrix, double[] column, int colIndex) {
        int n = matrix.length;
        double[][] newMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            // Копіюємо увесь рядок
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, n);
            // Замінюємо лише один стовпець
            newMatrix[i][colIndex] = column[i];
        }

        return newMatrix; // Повертаємо оновлену матрицю
    }

    // Метод Гаусса-Жордана для розв’язання системи
    public static double[] solveGaussJordan(double[][] A, double[] B) {
        int n = A.length;

        // Створюємо розширену матрицю, яка містить і A, і B
        double[][] augmented = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            // Копіюємо коефіцієнти з A
            System.arraycopy(A[i], 0, augmented[i], 0, n);
            // Додаємо значення з B в останній стовпець
            augmented[i][n] = B[i];
        }

        // Основна частина методу Гаусса-Жордана — приведення до діагональної форми
        for (int i = 0; i < n; i++) {
            double pivot = augmented[i][i]; // Головний елемент в поточному рядку

            // Ділимо весь рядок на головний елемент, щоб зробити його = 1
            for (int j = 0; j <= n; j++) {
                augmented[i][j] /= pivot;
            }

            // Обнуляємо всі інші елементи в цьому стовпці
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmented[k][i]; // Наскільки треба відняти
                    for (int j = 0; j <= n; j++) {
                        augmented[k][j] -= factor * augmented[i][j];
                    }
                }
            }
        }

        // Після приведення до діагональної форми, останній стовпець містить розв’язки
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = augmented[i][n];
        }

        return result; // Повертаємо знайдені розв’язки
    }
}
