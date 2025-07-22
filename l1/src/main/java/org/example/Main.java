package org.example; // Оголошення пакета (група пов’язаних класів у проєкті). Тут просто базова структура.

public class Main { // Основний клас, у якому знаходиться метод main (точка запуску програми)

    public static void main(String[] args) { // Метод main запускається при старті програми
        // Створюємо двовимірний масив — матриця A розміром 2x2
        // Кожна внутрішня дужка — це один рядок матриці
        double[][] matrixA = {
                {4, 7}, // перший рядок: 4 і 7
                {2, 6}  // другий рядок: 2 і 6
        };

        // Створюємо другу матрицю B того ж розміру
        double[][] matrixB = {
                {1, 3}, // перший рядок
                {5, 2}  // другий рядок
        };

        // Оголошуємо скаляр (звичайне число), яке будемо використовувати для множення на матрицю
        double scalar = 2.0;

        // Виводимо на екран матрицю A
        System.out.println("Matrix A:");
        printMatrix(matrixA); // Показуємо її у форматі [x, y]

        // Виводимо на екран матрицю B
        System.out.println("Matrix B:");
        printMatrix(matrixB);

        // Виводимо результат додавання A + B
        System.out.println("A + B:");
        printMatrix(addMatrices(matrixA, matrixB)); // Обчислюємо суму елементів матриць

        // Виводимо результат віднімання A - B
        System.out.println("A - B:");
        printMatrix(subtractMatrices(matrixA, matrixB));

        // Виводимо результат множення A на скаляр (тобто кожен елемент * 2.0)
        System.out.println("A * " + scalar + ":");
        printMatrix(multiplyByScalar(matrixA, scalar));

        // Виводимо результат множення матриць A * B
        System.out.println("A * B:");
        printMatrix(multiplyMatrices(matrixA, matrixB));

        // Виводимо транспоновану матрицю A (рядки стають стовпцями)
        System.out.println("Transpose of A:");
        printMatrix(transposeMatrix(matrixA));

        // Виводимо обернену матрицю до A (інша матриця, яка при множенні дає одиничну)
        System.out.println("Inverse of A:");
        printMatrixForInverse(invertMatrix(matrixA)); // Виводимо з точністю до трьох знаків
    }

    // Метод додавання двох матриць
    public static double[][] addMatrices(double[][] a, double[][] b) {
        // Створюємо нову матрицю, де кожен елемент — це сума відповідних елементів a і b
        return new double[][]{
                {a[0][0] + b[0][0], a[0][1] + b[0][1]}, // перший рядок: [a00 + b00, a01 + b01]
                {a[1][0] + b[1][0], a[1][1] + b[1][1]}  // другий рядок: [a10 + b10, a11 + b11]
        };
    }

    // Метод віднімання матриць (аналогічно до додавання, але віднімаємо)
    public static double[][] subtractMatrices(double[][] a, double[][] b) {
        return new double[][]{
                {a[0][0] - b[0][0], a[0][1] - b[0][1]}, // перший рядок: [a00 - b00, a01 - b01]
                {a[1][0] - b[1][0], a[1][1] - b[1][1]}  // другий рядок
        };
    }

    // Метод множення матриці на звичайне число
    public static double[][] multiplyByScalar(double[][] matrix, double scalar) {
        // Кожен елемент множиться на скаляр
        return new double[][]{
                {matrix[0][0] * scalar, matrix[0][1] * scalar}, // перший рядок
                {matrix[1][0] * scalar, matrix[1][1] * scalar}  // другий рядок
        };
    }

    // Метод множення двох матриць
    public static double[][] multiplyMatrices(double[][] a, double[][] b) {
        // Множення матриць — складніша операція, ніж додавання
        // Для кожного елемента результату беремо суму добутків відповідних елементів рядка A і стовпця B
        return new double[][]{
                {
                        a[0][0] * b[0][0] + a[0][1] * b[1][0], // перший рядок, перший стовпець
                        a[0][0] * b[0][1] + a[0][1] * b[1][1]  // перший рядок, другий стовпець
                },
                {
                        a[1][0] * b[0][0] + a[1][1] * b[1][0], // другий рядок, перший стовпець
                        a[1][0] * b[0][1] + a[1][1] * b[1][1]  // другий рядок, другий стовпець
                }
        };
    }

    // Метод транспонування — замінює рядки на стовпці
    public static double[][] transposeMatrix(double[][] matrix) {
        return new double[][]{
                {matrix[0][0], matrix[1][0]}, // перший стовпець стає першим рядком
                {matrix[0][1], matrix[1][1]}  // другий стовпець — другим рядком
        };
    }

    // Метод знаходження оберненої матриці (для 2x2)
    public static double[][] invertMatrix(double[][] m) {
        // Обчислюємо визначник матриці: формула det = a*d - b*c
        double det = m[0][0] * m[1][1] - m[0][1] * m[1][0];

        // Якщо визначник = 0, то оберненої матриці не існує
        if (det == 0) {
            throw new IllegalArgumentException("Matrix is singular and cannot be inverted.");
        }

        // Обернене значення визначника (1 / det)
        double invDet = 1.0 / det;

        // Формула для оберненої матриці 2x2:
        // [a b]^-1 = 1/det * [d -b]
        // [c d]               [-c a]
        return new double[][]{
                { m[1][1] * invDet, -m[0][1] * invDet },
                { -m[1][0] * invDet, m[0][0] * invDet }
        };
    }

    // Метод для виводу матриці з двома знаками після крапки
    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) { // Для кожного рядка
            System.out.printf("[%.2f, %.2f]%n", row[0], row[1]); // Форматований вивід: два числа з 2 знаками
        }
        System.out.println(); // Порожній рядок для відступу
    }

    // Метод для виводу матриці з трьома знаками після крапки (точніше для оберненої)
    public static void printMatrixForInverse(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.printf("[%.3f, %.3f]%n", row[0], row[1]); // Вивід з трьома знаками
        }
        System.out.println();
    }
}
