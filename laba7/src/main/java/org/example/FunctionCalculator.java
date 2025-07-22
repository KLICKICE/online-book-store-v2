package org.example;

public class FunctionCalculator {
    public static void main(String[] args) {
        double[] points = {2, 5};

        for (double x : points) {
            double y = calculateFunction(x);
            System.out.println("x = " + x + ", y = " + y);
        }
    }

    public static double calculateFunction(double x) {
        return x * x * x + 3 * x * x - x - 1;
    }
}


