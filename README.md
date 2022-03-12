## Sudoku Solver in Java

This project uses maven to build and run tests. Please make sure that you have maven installed on your machine.

### Building the JAR

```sh
$ mvn package
```

The aforementioned command builds the JAR as well as execute all the unit tests present inside SudokuSolverTest.java

### Solving a sudoku

In order to solve a sudoku you need to provide the initial state of sudoku via "input.txt".

A sample input.txt is added at the root of this project. All the numbers of sudoku are space seperated.

Once you have created the input.txt, you can run the following command to generate the solution:

```sh
$ java -cp target/sudoku-solver-1.0-SNAPSHOT.jar src.main.java.com.sudoku_solver.SudokuSolver
```

Solution would be written to a text file called "solution.txt"

### Time complexity
For every unassigned index, there are 9 possible options so the time complexity is `O(9^(n*n))`.

### Space complexity
`O(9*9)` to store the output of matrix.
