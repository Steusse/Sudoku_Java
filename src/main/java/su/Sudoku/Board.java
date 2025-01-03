package su.Sudoku;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;

/**
 * 
 */
public class Board {
	/** The starting position for our Sudoku problem. **/
	private char[][] startingBoard;
	/** the rows of a Sudoku board. **/
	private char[][] sudokuRows;
	/** the columns of a Sudoku board. **/
	private char[][] sudokuColumns;
	/** the squares of a Sudoku board. **/
	private char[][] sudokuSquares;
	/** each position on a Sudoku board and its possible numbers **/
	private TreeMap<String, ArrayList<Character>> locationsWithPossibles = new TreeMap<>();
	
	/**
	 * Board constructor that takes no starting parameter. Initializes a test Sudoku board
	 * Only for testing purposes.
	 */
	public Board() {
		char[][] testBoard = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},
							 {'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},
							 {'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
							 {'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},
							 {'.','.','.','.','8','.','.','7','9'} };
		startingBoard = testBoard;
	}

	/**
	 * Board constructor that takes a char[][] of starting positions.
	 * @param board a multi-level array representing each position on a Sudoku board
	 */
	public Board(char[][] board) {
		this.startingBoard = board;
		setSudokuRows(board);
		setSudokuColumns(board);
		setSudokuSquares(board);
		
	}
	
	/** 
	 * Sets the starting position of our Sudoku problem.
	 * @param board a multi-level array representing each position
	 */
	public void setStartingBoard(char[][] board) {
		this.startingBoard = board;
	}
	
	/**
	 * Returns the startingBoard private attribute.
	 * @return startingBoard the char[][] that represents our starting board positions
	 */
	public char[][] returnStartingBoard() {
		return startingBoard;
	}
	
	/**
	 * Sets the starting rows of our Sudoku board
	 */
	public void setSudokuRows(char[][] startBoard) {
		this.sudokuRows = startBoard;
	}
	
	/**
	 * Returns the sudokuRows private attribute.
	 * @return sudokuRows the char[][] that represents the rows of our Sudoku board
	 */
	public char[][] returnSudokuRows() {
		return this.sudokuRows;
	}
	
	/**
	 * Sets the starting columns of our Sudoku board
	 */
	public void setSudokuColumns(char[][] startBoard) {
		char[][] columnArray = new char[9][9];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				columnArray[j][i] = startBoard[i][j];
			}
		}
		this.sudokuColumns = columnArray;
	}
	
	/**
	 * Returns the sudokuColumns private attribute.
	 * @return sudokuColumns the char[][] that represents the columns of our Sudoku board
	 */
	public char[][] returnSudokuColumns() {
		return this.sudokuColumns;
	}
	
	/**
	 * Sets the starting squares of our Sudoku board
	 */
	public void setSudokuSquares(char[][] startBoard) {
		char[][] squareArray = new char[9][9];
		int xPosition = 0;
		for(int a = 0; a < 7; a += 3) {
			for(int b = 0; b < 7; b += 3) {
				int yPosition = 0;
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						squareArray[xPosition][yPosition] = startBoard[a+i][b+j];
						yPosition += 1;
					}
				}
				xPosition+= 1;
			}
		}
		this.sudokuSquares = squareArray;
	}
	
	/**
	 * Returns the sudokuSquares private attribute.
	 * @return sudokuSquares the char[][] that represents the squares of our Sudoku board
	 */
	public char[][] returnSudokuSquares() {
		return this.sudokuSquares;
	}
	
	/**
	 * Creates a HashMap of the sudoku board positions and assigns the number located in
	 * the startingBoard private attribute if already known as a length one ArrayList<Character>
	 * or an ArrayList<Character> containing 1-9. It then uses TreeMap to sort the Map.
	 */
	public void createInitialMap() {
		Map<String, ArrayList<Character>> unsortedMap = new HashMap<>();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				ArrayList<Character> allPossibles = new ArrayList<>();
				char[] charArray = {'1','2','3','4','5','6','7','8','9'};
				for(int a = 0; a < charArray.length; a++) {
					allPossibles.add(Character.valueOf(charArray[a]));
				}
				String identifier = "" + i + j;
				if(startingBoard[i][j] == '.') {
					unsortedMap.put(identifier, allPossibles);
				}
				else {
					ArrayList<Character> setAnswer = new ArrayList<>();
					setAnswer.add(Character.valueOf(startingBoard[i][j]));
					unsortedMap.put(identifier, setAnswer);
				}
			}
		}
		locationsWithPossibles = new TreeMap<>(unsortedMap);
	}
	
	/**
	 * Returns the locationsWithPossibles private attribute.
	 * @return locationsWithPossibles the TreeMap<String, ArrayList<Character>> that
	 * represents all the positions of the Sudoku board and their possible answers
	 */
	public TreeMap<String, ArrayList<Character>> returnPossiblesMap() {
		return this.locationsWithPossibles;
	}
}