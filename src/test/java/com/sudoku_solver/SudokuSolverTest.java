package src.test.java.com.sudoku_solver;

import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.Test;
import src.main.java.com.sudoku_solver.*;

public class SudokuSolverTest {
    private static int boardRowSize = 9;
    private static int boardColumnSize = 9;

    @Test
    public void testElementAlreadyPresentInRow() {
        SudokuSolver s = new SudokuSolver();
        char[][] board = new char[boardRowSize][boardColumnSize];
        for (int i = 1; i <= boardColumnSize; i++) {
            board[0][i - 1] = (char) ('0' + i);
        }
        assertEquals(s.checkRow(board, 0, '2'), false);
    }

    @Test
    public void testElementNotPresentInRow() {
        SudokuSolver s = new SudokuSolver();
        char[][] board = new char[boardRowSize][boardColumnSize];
        for (int i = 1; i <= boardColumnSize; i++) {
            if (i == 2)
                continue;
            board[0][i - 1] = (char) ('0' + i);
        }

        assertEquals(s.checkRow(board, 0, '2'), true);
    }

    @Test
    public void testElementAlreadyPresentInColumn() {
        SudokuSolver s = new SudokuSolver();
        char[][] board = new char[boardRowSize][boardColumnSize];
        for (int i = 1; i <= boardRowSize; i++) {
            board[i - 1][0] = (char) ('0' + i);
        }
        assertEquals(s.checkColumn(board, 0, '2'), false);
    }

    @Test
    public void testElementNotPresentInColumn() {
        SudokuSolver s = new SudokuSolver();
        char[][] board = new char[boardRowSize][boardColumnSize];
        for (int i = 1; i <= boardRowSize; i++) {
            if (i == 2)
                continue;
            board[i - 1][0] = (char) ('0' + i);
        }

        assertEquals(s.checkColumn(board, 0, '2'), true);
    }

    @Test
    public void testElementAlreadyPresentIn3x3() {
        SudokuSolver s = new SudokuSolver();
        char[][] board = new char[boardRowSize][boardColumnSize];
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                int k = (i - 1) * 3 + j;
                board[i - 1][j - 1] = (char) ('0' + k);
            }
        }
        assertEquals(s.check3x3(board, 0, 1, '2'), false);

    }

    @Test
    public void testElementNotPresentIn3x3() {
        SudokuSolver s = new SudokuSolver();
        char[][] board = new char[boardRowSize][boardColumnSize];
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                int k = (i - 1) * 3 + j;
                if (i == 1 && j == 2)
                    continue;
                else
                    board[i - 1][j - 1] = (char) ('0' + k);
            }
        }
        assertEquals(s.check3x3(board, 0, 1, '2'), true);
    }
}