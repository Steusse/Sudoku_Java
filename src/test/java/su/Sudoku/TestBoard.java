package su.Sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.ArrayList;
import java.util.TreeMap;


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
		Board testBoard = new Board();
		testBoard.setSudokuRows(testBoard.returnStartingBoard());
		char[][] testRows = testBoard.returnSudokuRows(); 
		for(int i = 0; i < 9; i++) { 
			assertArrayEquals(testBoard.returnStartingBoard()[i], testRows[i]);
		}
	}
	
	@Test
	public void testSetSudokuColumns() {
		char[][] invertedSudokuBoard = {{'5','6','.','8','4','7','.','.','.'},{'3','.','9','.','.','.','6','.','.'},
										{'.','.','8','.','.','.','.','.','.'},{'.','1','.','.','8','.','.','4','.'},
										{'7','9','.','6','.','2','.','1','8'},{'.','5','.','.','3','.','.','9','.'},
										{'.','.','.','.','.','.','2','.','.'},{'.','.','6','.','.','.','8','.','7'},
										{'.','.','.','3','1','6','.','5','9'} };
		Board testBoard = new Board();
		testBoard.setSudokuColumns(testBoard.returnStartingBoard());
		char[][] testColumns = testBoard.returnSudokuColumns();
		for(int i = 0; i < 9; i++) {
			assertArrayEquals(invertedSudokuBoard[i], testColumns[i]);
		}
	}
	
	@Test
	public void testSetSudokuSquares() {
		char[][] sudokuBoardSquares = {{'5','3','.','6','.','.','.','9','8'},{'.','7','.','1','9','5','.','.','.'},
									   {'.','.','.','.','.','.','.','6','.'},{'8','.','.','4','.','.','7','.','.'},
									   {'.','6','.','8','.','3','.','2','.'},{'.','.','3','.','.','1','.','.','6'},
									   {'.','6','.','.','.','.','.','.','.'},{'.','.','.','4','1','9','.','8','.'},
									   {'2','8','.','.','.','5','.','7','9'} };
		Board testBoard = new Board();
		testBoard.setSudokuSquares(testBoard.returnStartingBoard());
		char[][] testColumns = testBoard.returnSudokuSquares();
		for(int i = 0; i < 9; i++) {
			assertArrayEquals(sudokuBoardSquares[i], testColumns[i]);
		}
	}
	
	@Test
	public void testCreateInitialMap() {
		Board testBoard = new Board();
		testBoard.createInitialMap();
		char[] testArray = {'1','2','3','4','5','6','7','8','9'};
		TreeMap<String, ArrayList<Character>> testMap = testBoard.returnPossiblesMap();
		assertEquals(testMap.get("00").get(0), '5');
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testMap.get("00").get(1);
		});
		assertEquals(testMap.get("54").get(0), '2');
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testMap.get("54").get(1);
		});
		int iterator = 0;
		for(char character : testArray) {
			assertEquals(testMap.get("02").get(iterator), character);
			iterator += 1;
		}
	}
	
	@Test
	public void testFindPossibleNumbers() {
		Board testBoard = new Board();
		testBoard.setSudokuRows(testBoard.returnStartingBoard());
		testBoard.createInitialMap();
		char[] testArray = testBoard.returnSudokuRows()[0];
		ArrayList<Character> testArrayList = testBoard.returnPossiblesMap().get("02");
		ArrayList<Character> updatedArrayList = testBoard.findPossibleNumbers(testArray, testArrayList);
		for(Character position: updatedArrayList) {
			assertNotEquals(position, '5');
			assertNotEquals(position, '3');
			assertNotEquals(position, '7');
		}
	}
	
	@Test
	public void testComputeSudoku() {
		char[][] sudokuBoard = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},
								{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},
								{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
								{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},
								{'.','.','.','.','8','.','.','7','9'} };
		char [][] sudokuBoardAnswers = {{'5','3','4','6','7','8','9','1','2'},{'6','7','2','1','9','5','3','4','8'},
										{'1','9','8','3','4','2','5','6','7'},{'8','5','9','7','6','1','4','2','3'},
										{'4','2','6','8','5','3','7','9','1'},{'7','1','3','9','2','4','8','5','6'},
										{'9','6','1','5','3','7','2','8','4'},{'2','8','7','4','1','9','6','3','5'},
										{'3','4','5','2','8','6','1','7','9'} };
		Board testBoard = new Board(sudokuBoard);
		boolean finished = false;
		while(finished == false) {
			//To avoid a potential infinite loop
			for(int i = 0; i < 10;) {
				finished = testBoard.computeSudoku();
				if(finished == true) {
					break;
				}
				i += 1;
			}
			finished = true;
		}
		for(int i = 0; i < 9; i++) {
			assertArrayEquals(testBoard.returnSudokuRows()[i], sudokuBoardAnswers[i]);
		}
		
	}
}