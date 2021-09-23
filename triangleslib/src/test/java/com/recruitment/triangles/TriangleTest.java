package com.recruitment.triangles;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import com.recruitment.triangles.Triangle.Type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayName("Triangle class units")
class TriangleTest {

    /** A stream of valid triangles and their type. */
    private static Stream<Arguments> validTriangleSides() {
        return Stream.of(
            arguments(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Type.EQUILATERAL),
            arguments(1.0, 1.0, 1.0, Type.EQUILATERAL),
            arguments(2.0, 3.0, 4.0, Type.SCALENE),
            arguments(4.0, 3.0, 2.0, Type.SCALENE),
            arguments(4.0, 3.0, 2.0, Type.SCALENE),
            arguments(2.0, 2.0, 1.0, Type.ISOSCELES),
            arguments(1.0, 2.0, 2.0, Type.ISOSCELES),
            arguments(2.0, 1.0, 2.0, Type.ISOSCELES),
            arguments(Double.MAX_VALUE / 2, Double.MAX_VALUE / 2, Double.MAX_VALUE / 2, Type.EQUILATERAL),
            // Below shouldn't work because of overflow, but somehow does.
            arguments(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Type.EQUILATERAL)
        );
    }

    /** A stream of invalid triangles. */
    private static Stream<Arguments> invalidTriangleSides() {
        return Stream.of(
            arguments(-1.0, 1.1, 2.0),
            arguments( 0.0, 0.0, 0.0),
            arguments( 2.0, 1.0, 1.0),
            arguments( 1.0, 2.0, 3.0),
            arguments( 3.0, 2.0, 1.0)
        );
    }


    @ParameterizedTest
    @MethodSource("com.recruitment.triangles.TriangleTest#validTriangleSides")
    void constructor_succeeds_if_valid(double a, double b, double c, Type ignored) {
        Triangle t = new Triangle(a, b, c);
        assertNotNull(t);
        assertAll("All sides set correctly",
            () -> assertEquals(t.getSideA(), a),
            () -> assertEquals(t.getSideB(), b),
            () -> assertEquals(t.getSideC(), c)
        );
    }

    @ParameterizedTest
    @MethodSource("com.recruitment.triangles.TriangleTest#invalidTriangleSides")
    void constructor_fails_if_invalid(double a, double b, double c) {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(a, b, c));
    }

    @ParameterizedTest
    @MethodSource("com.recruitment.triangles.TriangleTest#validTriangleSides")
    void correctly_classifies_triangle(double a, double b, double c, Type type) {
        Triangle t = new Triangle(a, b, c);
        assertEquals(t.classify(), type);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 1.0, 1.1 })
    void setters_update_valid_side_lengths(double newLength) {
        Triangle t = new Triangle(1.0, 1.0, 1.0);
        assertAll("Getters",
                () -> {
                    t.setSideA(newLength);
                    assertEquals(newLength, t.getSideA());
                },
                () -> {
                    t.setSideB(newLength);
                    assertEquals(newLength, t.getSideB());
                },
                () -> {
                    t.setSideC(newLength);
                    assertEquals(newLength, t.getSideC());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.0, -0.1, 2.0, Double.NEGATIVE_INFINITY })
    void setters_do_not_update_invalid_side_lengths(double newLength) {
        Triangle t = new Triangle(1.0, 1.0, 1.0);
        assertAll("Setters",
            () -> assertThrows(IllegalArgumentException.class, () -> t.setSideA(newLength)),
            () -> assertThrows(IllegalArgumentException.class, () -> t.setSideB(newLength)),
            () -> assertThrows(IllegalArgumentException.class, () -> t.setSideC(newLength))
        );
    }
}
