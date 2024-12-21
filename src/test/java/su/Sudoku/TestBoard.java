package su.Sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class TestBoard {

	@Test
	public void testBoardConstructor() {
		char[][] sudokuBoard = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},
								{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},
								{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
								{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},
		                        {'.','.','.','.','8','.','.','7','9'} };
		Board testBoard = new Board(sudokuBoard);
		char[][] testRows = testBoard.returnSudokuRows();
		char[] expectedRow = {'4','.','.','8','.','3','.','.','1'};
		char[][] testColumns = testBoard.returnSudokuColumns();
		char[] expectedColumn = {'7','9','.','6','.','2','.','1','8'};
		char[][] testSquares = testBoard.returnSudokuSquares();
		char[] expectedSquare = {'.','6','.','8','.','3','.','2','.'};
		assertArrayEquals(testRows[4], expectedRow);
		assertArrayEquals(testColumns[4], expectedColumn);
		assertArrayEquals(testSquares[4], expectedSquare);
	}
	
	@Test
	public void testSetSudokuRows() {
		char[][] sudokuBoard = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},
								{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},
								{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
								{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},
								{'.','.','.','.','8','.','.','7','9'} };
		Board testBoard = new Board();
		testBoard.setSudokuRows(sudokuBoard);
		char[][] testRows = testBoard.returnSudokuRows(); 
		for(int i = 0; i < 9; i++) { 
			assertArrayEquals(sudokuBoard[i], testRows[i]);
		}
	}
	
	@Test
	public void testSetSudokuColumns() {
		char[][] sudokuBoard = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},
								{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},
								{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
								{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},
								{'.','.','.','.','8','.','.','7','9'} };
		char[][] invertedSudokuBoard = {{'5','6','.','8','4','7','.','.','.'},{'3','.','9','.','.','.','6','.','.'},
										{'.','.','8','.','.','.','.','.','.'},{'.','1','.','.','8','.','.','4','.'},
										{'7','9','.','6','.','2','.','1','8'},{'.','5','.','.','3','.','.','9','.'},
										{'.','.','.','.','.','.','2','.','.'},{'.','.','6','.','.','.','8','.','7'},
										{'.','.','.','3','1','6','.','5','9'} };
		Board testBoard = new Board();
		testBoard.setSudokuColumns(sudokuBoard);
		char[][] testColumns = testBoard.returnSudokuColumns();
		for(int i = 0; i < 9; i++) {
			assertArrayEquals(invertedSudokuBoard[i], testColumns[i]);
		}
	}
	
	@Test
	public void testSetSudokuSquares() {
		char[][] sudokuBoard = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},
								{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},
								{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
								{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},
								{'.','.','.','.','8','.','.','7','9'} };
		char[][] sudokuBoardSquares = {{'5','3','.','6','.','.','.','9','8'},{'.','7','.','1','9','5','.','.','.'},
									   {'.','.','.','.','.','.','.','6','.'},{'8','.','.','4','.','.','7','.','.'},
									   {'.','6','.','8','.','3','.','2','.'},{'.','.','3','.','.','1','.','.','6'},
									   {'.','6','.','.','.','.','.','.','.'},{'.','.','.','4','1','9','.','8','.'},
									   {'2','8','.','.','.','5','.','7','9'} };
		Board testBoard = new Board();
		testBoard.setSudokuSquares(sudokuBoard);
		char[][] testColumns = testBoard.returnSudokuSquares();
		for(int i = 0; i < 9; i++) {
			assertArrayEquals(sudokuBoardSquares[i], testColumns[i]);
		}
	}
}