package org.example;

public class Main {
    public static void main(String[] args) {
        double[] x = { 10, 12, 16, 22, 40};
        double[] y = { 56, 78, 34, 12, 67};

        double xValue = 20;
        double result = lagrangeInterpolation(x, y, xValue);

        System.out.printf("Значення функції в точці x = %.1f: %.4f\n", xValue, result);
    }
    public static double lagrangeInterpolation(double[] x, double[] y, double value) {
        double result = 0;

        for (int i = 0; i < x.length; i++) {
            double term = y[i];
            for (int j = 0; j < x.length; j++) {
                if (j != i) {
                    term *= (value - x[j]) / (x[i] - x[j]);
                }
            }
            result += term;
        }
        System.out.println(result);
        return result;
    }
}