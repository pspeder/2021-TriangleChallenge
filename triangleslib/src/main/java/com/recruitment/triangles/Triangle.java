package com.recruitment.triangles;

/** Triangle class */
public class Triangle {
    
    /**
     * Triangle type classifier
     */
    public enum Type { ISOSCELES, EQUILATERAL, SCALENE; }


    /**
     * Determine if the triangle defined by given side lengths can be considered
     * valid.
     *
     * Triangle definitions enforced:
     * - All sides must have positive lengths
     * - Every side must be shorter than the sum of the two others (triangle inequality)
     *
     * Known limitations:
     * - The sum of two sides may cause double to overflow.
     */
    private static boolean isValid(double sideA, double sideB, double sideC) {
        return sideA > 0. && sideB > 0. && sideC > 0.
            && sideA < sideB + sideC 
            && sideB < sideA + sideC 
            && sideC < sideA + sideB;
    }


    // Side lengths
    private double a, b, c;


    /**
     * Construct a new Triangle.
     * Will validate the lengths and fail with an exception if impossible triangle.
     */
    public Triangle(double sideA, double sideB, double sideC) {
        setLengths(sideA, sideB, sideC);
    }


    public double getSideA() { return this.a; }
    public double getSideB() { return this.b; }
    public double getSideC() { return this.c; }

    public void setSideA(double value) { setLengths(value, this.b, this.c); }
    public void setSideB(double value) { setLengths(this.a, value, this.c); }
    public void setSideC(double value) { setLengths(this.a, this.b, value); }


    /**
     * Determine the type of this Triangle.
     */
    public Type classify() {
        if (a == b && b == c)                { return Type.EQUILATERAL; } // All sides equal
        else if (a == b || b == c || a == c) { return Type.ISOSCELES;   } // Two sides equal
        else                                 { return Type.SCALENE;     } // No two sides equal
    }


    /**
     * Update the triangle side lengths.
     *
     * Triangle "invariants" enforced by calling the static isValid.
     *
     * @throws IllegalArgumentException if the triangle resulting from updating
     *                                  the side lengths would be invalid.
     */
    private void setLengths(double a, double b, double c) {
        if (isValid(a, b, c)) {
            this.a = a;
            this.b = b;
            this.c = c;
        } else {
            throw new IllegalArgumentException("Invalid triangle side lengths.");
        }
    }
}

