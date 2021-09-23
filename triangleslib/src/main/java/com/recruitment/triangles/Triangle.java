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
    private final double a, b, c;


    /**
     * Construct a new Triangle.
     * Will validate the lengths and fail with an exception if impossible triangle.
     *
     * @throws IllegalArgumentException if the triangle resulting from updating
     *                                  the side lengths would be invalid.
     */
    public Triangle(double sideA, double sideB, double sideC) {
        if (isValid(sideA, sideB, sideC)) {
            this.a = sideA;
            this.b = sideB;
            this.c = sideC;
        } else {
            throw new IllegalArgumentException("Invalid triangle side lengths.");
        }
    }


    public double getSideA() { return this.a; }
    public double getSideB() { return this.b; }
    public double getSideC() { return this.c; }


    /**
     * Determine the type of this Triangle.
     */
    public Type classify() {
        if (a == b && b == c)                { return Type.EQUILATERAL; } // All sides equal
        else if (a == b || b == c || a == c) { return Type.ISOSCELES;   } // Two sides equal
        else                                 { return Type.SCALENE;     } // No two sides equal
    }
}

