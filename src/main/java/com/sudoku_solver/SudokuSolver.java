package src.main.java.com.sudoku_solver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class SudokuSolver {

	private static int boardRowSize = 9;
	private static int boardColumnSize = 9;
	private static boolean success;

	// Helper function to check if character 'c' is present in row 'row'
	public boolean checkRow(char[][] board, int row, char c) {
		for (int i = 0; i < boardColumnSize; i++) {
			if (board[row][i] == c) {
				return false;
			}
		}
		return true;
	}

	// Helper function to check if character 'c' is present in column 'col'
	public boolean checkColumn(char[][] board, int col, char c) {
		for (int i = 0; i < boardRowSize; i++) {
			if (board[i][col] == c) {
				return false;
			}
		}
		return true;
	}

	// Helper function to check if character 'c' is present in 3x3 subsection formed
	// by 'row' and 'col'
	public boolean check3x3(char[][] board, int row, int col, char c) {
		int gridStartX = (row / 3) * 3;
		int gridStartY = (col / 3) * 3;

		for (int i = gridStartX; i < gridStartX + 3; i++) {
			for (int j = gridStartY; j < gridStartY + 3; j++) {
				if (board[i][j] == c) {
					return false;
				}
			}
		}

		return true;
	}

	// Helper function to check all above 3 conditions are valid for character 'c'
	public boolean checkBoard(char[][] board, int row, int col, char c) {
		var isRowValid = checkRow(board, row, c);
		var isColumValid = checkColumn(board, col, c);
		var is3x3Valid = check3x3(board, row, col, c);

		return isRowValid && isColumValid && is3x3Valid;
	}

	// Helper function to start solving sudoku from 1st element in the board
	public void solveSudoku(char[][] board) {
		success = false;
		solveSudoku(board, 0, 0);
	}

	// Recursive Function to solve sudoku by bytracking
	public void solveSudoku(char[][] board, int row, int col) {
		if (col == boardColumnSize) {
			solveSudoku(board, row + 1, 0);
			return;
		}

		if (row == boardRowSize) {
			success = true;
			return;
		}

		if (board[row][col] == '0') {
			for (int num = 1; num <= boardRowSize; num++) {
				char c = (char) ('0' + num);
				if (checkBoard(board, row, col, c)) {
					board[row][col] = c;
					solveSudoku(board, row, col + 1);
					if (success)
						return;
				}
			}
			board[row][col] = '0';
		} else {
			solveSudoku(board, row, col + 1);
		}
	}

	// Helper function to write the sudoku solution in solution file
	private void writeSolution(char[][] board, String output) {
		try {
			FileWriter solWriter = new FileWriter(output);
			for (int i = 0; i < board.length; i++) {
				StringBuffer buffer = new StringBuffer("");
				for (int j = 0; j < board[i].length; j++) {
					buffer.append(board[i][j] + " ");
				}
				if (i != board.length - 1)
					buffer.append('\n');
				solWriter.write(buffer.toString());
			}
			solWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Reads the input text file and converts it to 2-D character array. It also
	// checks if the input is valid for example size of sudoku is 9x9 and all the
	// character are between 0 to 9. In case of invalid input this function returns
	// an empty Optional.
	private Optional<char[][]> readInput(String input) {
		BufferedReader br = null;
		String line = null;
		char[][] board = new char[boardRowSize][boardColumnSize];
		int row = 0;

		try {
			var inputFile = new File(input);
			var inputFileReader = new FileReader(inputFile);
			br = new BufferedReader(inputFileReader);
			while ((line = br.readLine()) != null) {
				var elements = line.split(" ");
				if (elements.length != boardColumnSize) {
					System.out.println("Unexpected column size: " + elements.length);
					return Optional.empty();
				}
				for (int col = 0; col < elements.length; col++) {
					board[row][col] = elements[col].charAt(0);
					if (board[row][col] < '0' || board[row][col] > '9') {
						System.out.println("Found an invalid character: " + board[row][col]);
						return Optional.empty();
					}
				}
				row += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (row != boardRowSize) {
			System.out.println("Unexpected row size: " + row);
			return Optional.empty();
		}

		return Optional.of(board);
	}

	// Main function to solve Sudoku by reading input from "input.txt" and write the
	// solution to "solution.txt"
	public static void main(String[] args) throws Exception {
		SudokuSolver s = new SudokuSolver();

		var board = s.readInput("input.txt");

		if (!board.isPresent()) {
			System.out.println("Invalid board configuration, failed the parse application!");
			System.exit(-1);
		}

		s.solveSudoku(board.get());

		s.writeSolution(board.get(), "solution.txt");
	}
}