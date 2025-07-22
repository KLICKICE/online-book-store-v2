package org.example;

public class FunctionAllPoints {
public static void main(String[] args) {
    for (int x = 0; x <= 10; x++) {
        int y = calculateFunction(x);
        System.out.println("x = " + x + ", y = " + y);
    }
}

public static int calculateFunction(int x) {
    return x * x * x + 3 * x * x - x - 1;
}
}