package su.Sudoku;

public class Solution {
	public void solveSudoku(char[][] board) {
		Board sudokuBoard = new Board(board);
		sudokuBoard.computeSudoku();
		sudokuBoard.outputSudoku();
	}
}
