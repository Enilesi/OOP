public class Complex {
    private double re;
    private double im;
    private static int printCount = 0;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public double module() {
        return Math.sqrt(re * re + im * im);
    }

    public void print() {
        System.out.println(re + " + i * " + im);
        printCount++;
    }

    public Complex add(Complex nr1, Complex nr2) {
        return new Complex(nr1.re + nr2.re, nr1.im + nr2.im);
    }

    public static int getPrintCount() {
        return printCount;
    }
}

class ComplexMain {
    public static void main(String[] args) {
        Complex c1 = new Complex(3.0, 4.0);
        Complex c2 = new Complex(1.5, -2.5);

        System.out.println("Module of c1: " + c1.module());
        System.out.println("Module of c2: " + c2.module());

        c1.print();
        c2.print();

        Complex c3 = c1.add(c1,c2);
        c3.print();

        System.out.println("Total times complex numbers have been printed: " + Complex.getPrintCount());
    }
}
