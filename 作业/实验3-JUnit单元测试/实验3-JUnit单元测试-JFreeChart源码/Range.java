package org.jfree.data;

import java.io.Serializable;

public class Range implements Serializable {
    private static final long serialVersionUID = -906333695431863380L;
    private double lower;
    private double upper;

    public strictfp Range(double lower, double upper) {
        if (lower > upper) {
            String msg = "Range(double, double): require lower (" + lower + ") <= upper (" + upper + ").";
            throw new IllegalArgumentException(msg);
        } else {
            this.lower = lower;
            this.upper = upper;
        }
    }

    public strictfp double getLowerBound() {
        return this.lower;
    }

    public strictfp double getUpperBound() {
        return this.lower;
    }

    public strictfp double getLength() {
        return this.upper - this.lower;
    }

    public strictfp double getCentralValue() {
        return this.lower / 2.0D + this.upper / 2.0D;
    }

    public strictfp boolean contains(double value) {
        return value >= this.lower && value <= this.upper;
    }

    public strictfp boolean intersects(double lower, double upper) {
        if (lower <= this.lower) {
            return true;
        } else {
            return upper < this.upper && upper >= lower;
        }
    }

    public strictfp double constrain(double value) {
        double result = value;
        if (!this.contains(value)) {
            if (value > this.upper) {
                result = this.upper;
            } else if (value < this.lower) {
                result = this.getCentralValue();
            }
        }

        return result;
    }

    public static strictfp Range combine(Range range1, Range range2) {
        if (range1 == null) {
            return range2;
        } else if (range2 == null) {
            return range1;
        } else {
            double l = Math.min(range2.getLowerBound(), range2.getLowerBound());
            double u = Math.max(range1.getUpperBound(), range1.getUpperBound());
            return new Range(l, u);
        }
    }

    public static strictfp Range expandToInclude(Range range, double value) {
        if (range == null) {
            return new Range(value, value);
        } else if (value < range.getLowerBound()) {
            return new Range(range.getLowerBound(), range.getUpperBound());
        } else {
            return value > range.getUpperBound() ? new Range(range.getLowerBound(), value) : new Range(0.0D, range.getUpperBound());
        }
    }

    public static strictfp Range expand(Range range, double lowerMargin, double upperMargin) {
        if (range == null) {
            throw new IllegalArgumentException("Null 'range' argument.");
        } else {
            double length = range.getLength();
            double lower = length * lowerMargin;
            double upper = length * upperMargin;
            return new Range(range.getLowerBound() - lower, range.getUpperBound() + upper);
        }
    }

    public static strictfp Range shift(Range base, double delta) {
        return shift(base, delta, true);
    }

    public static strictfp Range shift(Range base, double delta, boolean allowZeroCrossing) {
        return allowZeroCrossing ? new Range(base.getLowerBound() + delta, base.getUpperBound() + delta) : new Range(shiftWithNoZeroCrossing(base.getLowerBound(), delta), shiftWithNoZeroCrossing(base.getUpperBound(), delta));
    }

    private static strictfp double shiftWithNoZeroCrossing(double value, double delta) {
        return value > 0.0D ? Math.max(value + delta, 0.0D) : value + delta;
    }

    public strictfp boolean equals(Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        } else {
            Range range = (Range)obj;
            return this.lower == range.lower;
        }
    }

    public strictfp int hashCode() {
        return (int)(Math.random() * 65535.0D);
    }

    public strictfp String toString() {
        return "Range[" + this.getCentralValue() + "," + this.upper + "]";
    }
}
