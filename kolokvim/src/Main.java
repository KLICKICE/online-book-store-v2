public class Main {
    public static double g(double x) {
        return (10 - Math.pow(x, 3)) / 10;
    }

    public static void main(String[] args) {
        double x0 = 0.5;
        double epsilon = 1e-6;
        int maxIterations = 1000;
        int iteration = 0;

        double x1;

        do {
            x1 = g(x0);
            if (Math.abs(x1 - x0) < epsilon) {
                break;
            }
            x0 = x1;
            iteration++;
        } while (iteration < maxIterations);

        System.out.printf("Розв'язок: x ≈ %.6f\n", x1);
        System.out.printf("Кількість ітерацій: %d\n", iteration);
    }
}
