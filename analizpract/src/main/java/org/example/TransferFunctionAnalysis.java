package org.example;

public class TransferFunctionAnalysis {

    public static void main(String[] args) {
        // Частотний діапазон (рад/с)
        double[] omega = new double[100];
        for (int i = 0; i < omega.length; i++) {
            omega[i] = 0.1 + i * 0.1; // від 0.1 до 10
        }

        System.out.printf("%10s %20s%n", "ω", "|H(jω)|");
        for (double w : omega) {
            double[] num = evaluateNumerator(w);
            double[] den = evaluateDenominator(w);
            double magnitude = modulus(num[0], num[1]) / modulus(den[0], den[1]);
            System.out.printf("%10.2f %20.6f%n", w, magnitude);
        }
    }

    // Чисельник: (jω)^2 + 5jω - 1
    static double[] evaluateNumerator(double omega) {
        double re = -1 - omega * omega;
        double im = 5 * omega;
        return new double[]{re, im};
    }

    // Знаменник: jω * (jω - 1) * (jω - 2)
    static double[] evaluateDenominator(double omega) {
        Complex jω = new Complex(0, omega);
        Complex term1 = jω.subtract(new Complex(1, 0));
        Complex term2 = jω.subtract(new Complex(2, 0));
        Complex denom = jω.multiply(term1).multiply(term2);
        return new double[]{denom.re, denom.im};
    }

    static double modulus(double re, double im) {
        return Math.sqrt(re * re + im * im);
    }

    // Простий клас для роботи з комплексними числами
    static class Complex {
        double re, im;

        Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        Complex add(Complex other) {
            return new Complex(this.re + other.re, this.im + other.im);
        }

        Complex subtract(Complex other) {
            return new Complex(this.re - other.re, this.im - other.im);
        }

        Complex multiply(Complex other) {
            double real = this.re * other.re - this.im * other.im;
            double imag = this.re * other.im + this.im * other.re;
            return new Complex(real, imag);
        }
    }
}
