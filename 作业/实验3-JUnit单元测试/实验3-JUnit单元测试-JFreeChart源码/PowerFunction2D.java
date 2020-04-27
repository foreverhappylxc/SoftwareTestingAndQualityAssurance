package org.jfree.data.function;

public class PowerFunction2D implements Function2D {
    private double a;
    private double b;

    public PowerFunction2D(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getValue(double x) {
        return this.a * Math.pow(x, this.b);
    }
}
