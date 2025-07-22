package org.example;

public class Main {
    public static void main(String[] args) {
        double[][] matrixA = {
                {4, 7},
                {2, 6}
        };

        double[][] matrixB = {
                {1, 3},
                {5, 2}
        };

        double scalar = 2.0;

        System.out.println("Matrix A:");
        printMatrix(matrixA);

        System.out.println("Matrix B:");
        printMatrix(matrixB);

        System.out.println("A + B:");
        printMatrix(addMatrices(matrixA, matrixB));

        System.out.println("A - B:");
        printMatrix(subtractMatrices(matrixA, matrixB));

        System.out.println("A * " + scalar + ":");
        printMatrix(multiplyByScalar(matrixA, scalar));

        System.out.println("A * B:");
        printMatrix(multiplyMatrices(matrixA, matrixB));

        System.out.println("Transpose of A:");
        printMatrix(transposeMatrix(matrixA));

        System.out.println("Inverse of A:");
        double[][] inverseA = invertMatrix(matrixA);
        printMatrixForInverse(inverseA);

        System.out.println("A * A⁻¹:");
        printMatrix(multiplyMatrices(matrixA, inverseA));
    }

    public static double[][] addMatrices(double[][] a, double[][] b) {
        return new double[][]{
                {a[0][0] + b[0][0], a[0][1] + b[0][1]},
                {a[1][0] + b[1][0], a[1][1] + b[1][1]}
        };
    }

    public static double[][] subtractMatrices(double[][] a, double[][] b) {
        return new double[][]{
                {a[0][0] - b[0][0], a[0][1] - b[0][1]},
                {a[1][0] - b[1][0], a[1][1] - b[1][1]}
        };
    }

    public static double[][] multiplyByScalar(double[][] matrix, double scalar) {
        return new double[][]{
                {matrix[0][0] * scalar, matrix[0][1] * scalar},
                {matrix[1][0] * scalar, matrix[1][1] * scalar}
        };
    }

    public static double[][] multiplyMatrices(double[][] a, double[][] b) {
        return new double[][]{
                {
                        a[0][0] * b[0][0] + a[0][1] * b[1][0],
                        a[0][0] * b[0][1] + a[0][1] * b[1][1]
                },
                {
                        a[1][0] * b[0][0] + a[1][1] * b[1][0],
                        a[1][0] * b[0][1] + a[1][1] * b[1][1]
                }
        };
    }

    public static double[][] transposeMatrix(double[][] matrix) {
        return new double[][]{
                {matrix[0][0], matrix[1][0]},
                {matrix[0][1], matrix[1][1]}
        };
    }

    public static double[][] invertMatrix(double[][] m) {
        double det = m[0][0] * m[1][1] - m[0][1] * m[1][0];

        if (det == 0) {
            throw new IllegalArgumentException("Matrix is singular and cannot be inverted.");
        }

        double invDet = 1.0 / det;
        return new double[][]{
                { m[1][1] * invDet, -m[0][1] * invDet },
                { -m[1][0] * invDet, m[0][0] * invDet }
        };
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.printf("[%.2f, %.2f]%n", row[0], row[1]);
        }
        System.out.println();
    }

    public static void printMatrixForInverse(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.printf("[%.3f, %.3f]%n", row[0], row[1]);
        }
        System.out.println();
    }
}
