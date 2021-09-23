package com.recruitment;

import com.recruitment.triangles.Triangle;

/** A simple application for classifying triangles. */
public class App {

    /** Parse 3 string arguments in array into a {@link Triangle}. */
    private Triangle parseTriangle(String[] args) {
        double a, b, c;
        a = Double.parseDouble(args[0].trim());
        b = Double.parseDouble(args[1].trim());
        c = Double.parseDouble(args[2].trim());
        return new Triangle(a, b, c);
    }

    /** Run application. */
    private void run(String[] args) {
        // Sanity check
        if (args.length != 3) {
            System.err.println("This application takes exactly 3 arguments.");
            System.exit(1);
        }

        try {
            Triangle t = parseTriangle(args);
            System.out.println(t.getType());
        } catch (IllegalArgumentException e) {
            System.out.println("Error when running with given arguments: " + e.getMessage());
            System.exit(1);
        }
    }

    /** Instantiate a new application instance and pass arguments. */
    public static void main(String[] args) {
        App instance = new App();
        instance.run(args);
    }
}
