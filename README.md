# Triangle Challenge

_An overcomplicated example of how to determine the type of a triangle in Java._

This repository contains a Java application corresponding to the description given as part of a recruitment process. Given three side lengths, it determines if a corresponding triangle is either isosceles, equilateral, or scalene.


## Design description
The entire project follows the maven-folder layout. While somewhat overkill for
this simple application it does allow most other Java programmers to instantly
feel familiar with the code base structure.

The root project is split into two subprojects, `app` and `triangleslib`, where
`app` comprises an executable application, while `triangleslib` comprises all
“business logic” regarding triangles.

`triangleslib` does not have any local dependencies and has been set up in the
build system/folder structure, such that it can be assembled into its own
library to be included as an external dependency, where needed.<br>
In this situation, said library consists of a single class (not counting tests), 
`com.recruitment.triangles.Triangle`.

`app` has `triangleslib` as a declared dependency and, when run, instantiates a
`Triangle` object using the side lengths passed as parameters from stdin. <br>
`Triangle`, then, has the responsibility of:
1. determining the validity of the given inputs (do they make up a
   non-degenerate triangle?)
2. classifying the resulting `Triangle` as one of the three types mentioned in
   the challenge, represented as an `Enum`, with corresponding constructors.

Not knowing the problem domain in which this solution is supposed to be
effectuated, makes it difficult to asses the needs for scalability and/or for
being able to alter state. I have ended up making `Triangle` immutable, so as to
create a thread-safe library.

All stored state is done with unboxed `double`s in order to be as small as
possible. This might result in a situation where the static `isValid`-method
does not return the expected result, although I have not been able to see that
behaviour in tests. A fix, if the library is to be used with such massive
triangles is to replace the storage with `BigDecimal`s.

I have decided to not perform unit tests on `app`, since it does nearly no logic
on its own (could maybe have tested the parsing `String` lengths to a
`Triangle`, but we’re moving dangerously close to just testing the Java
implementation, then).<br>
For `Triangle`, I have tested all publicly accessible methods - including the
property getters - and implicitly the private static method, `isValid`.
I have made a test input parameters of valid and invalid triangles in both
extreme and common cases, which I have used for the unit tests.<br>
I have not done integration testing.

