package org.jfree.data.function;

public class NormalDistributionFunction2D implements Function2D {
    private double mean;
    private double std;

    public NormalDistributionFunction2D(double mean, double std) {
        this.mean = mean;
        this.std = std;
    }

    public double getMean() {
        return this.mean;
    }

    public double getStandardDeviation() {
        return this.std;
    }

    public double getValue(double x) {
        return Math.exp(-1.0D * (x - this.mean) * (x - this.mean) / (2.0D * this.std * this.std)) / Math.sqrt(6.283185307179586D * this.std * this.std);
    }
}