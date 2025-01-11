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
	 * Board constructor that takes no starting parameter. Initializes a test Sudoku board.
	 * It is only for testing purposes.
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
		createInitialMap();
		
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
	 * represents all the positions of the Sudoku board and their possible answers.
	 */
	public TreeMap<String, ArrayList<Character>> returnPossiblesMap() {
		return this.locationsWithPossibles;
	}
	
	/**
	 * 
	 * @param charArray An array that represents a row,column, or square of a Sudoku board.
	 * @param possibleNumbers An ArrayList of Characters representing the possible answers for a
	 * given Sudoku position.
	 * @return newPossibles an adjusted Arraylist of possible answers that accounts for the characters in the
	 * given char[] array.
	 */
	public ArrayList<Character> findPossibleNumbers(char[] charArray, ArrayList<Character> possibleNumbers) {
		ArrayList<Character> newPossibles = possibleNumbers;
		for(int i = 0; i < charArray.length; i++) {
			if(charArray[i] == '.') {
				continue;
			}
			else {
				for(int j = 0; j < possibleNumbers.size(); j++) {
					if(charArray[i] == possibleNumbers.get(j).charValue()) {
						newPossibles.remove(j);
					}
				}
			}
		}
		return newPossibles;
	}
	
	/**
	 * This is our primary function to find our Sudoku solution. It checks every position in order and
	 * updates the possible answers ArrayList by checking the relevant row, column, and square of that position.
	 * @return A boolean that signals when the function can no longer update the Sudoku Board.
	 */
	public boolean computeSudoku() {
		boolean finished = true;
		int squareNum = 0;
		for(int i = 0; i < 9; i++) {
			squareNum = 0;
			if(i > 2) {
				squareNum += 3;
			}
			if(i > 5) {
				squareNum += 3;
			}
			for(int j = 0; j < 9; j++) {
				if(j == 3 || j == 6) {
					squareNum += 1;
				}
				String position = "" + i + j;
				if(locationsWithPossibles.get(position).size() == 1 && locationsWithPossibles.get(position).get(0) != '.') {
					continue;
				}
				else {
					finished = false;
					//Check the position's row
					ArrayList<Character> newPossibles = findPossibleNumbers(sudokuRows[i], locationsWithPossibles.get(position));
					locationsWithPossibles.replace(position, newPossibles);
					//Check the position's column
					newPossibles = findPossibleNumbers(sudokuColumns[j], locationsWithPossibles.get(position));
					locationsWithPossibles.replace(position, newPossibles);
					//Check the position's square
					newPossibles = findPossibleNumbers(sudokuSquares[squareNum], locationsWithPossibles.get(position));
					locationsWithPossibles.replace(position, newPossibles);
					int squarePosition = getSquarePosition(i, j);
					//If there is a number with only 1 valid location we insert it at that location
					if(locationsWithPossibles.get(position).size() == 1) {
						updateArrays(i, j, squareNum, squarePosition, position);
					}
				}
			}
		}
		return finished;
	}
	
	/**
	 * 
	 * @param currentRow The row of a position in our Sudoku board.
	 * @param currentColumn The column of a position in our Sudoku board.
	 * @return The matching position in the square of our Sudoku board.
	 */
	public int getSquarePosition(int currentRow, int currentColumn) {
		int row = currentRow;
		int column = currentColumn;
		if(row == 0 || row == 3 || row == 6) {
			if(column == 0 || column == 3 || column == 6) {
				return 0;
			}
			else if(column == 1 || column == 4 || column == 7) {
				return 1;
			}
			else if(column == 2 || column == 5 || column == 8) {
				return 2;
			}
		}
		else if(row == 1 || row == 4 || row == 7) {
			if(column == 0 || column == 3 || column == 6) {
				return 3;
			}
			else if(column == 1 || column == 4 || column == 7) {
				return 4;
			}
			else if(column == 2 || column == 5 || column == 8) {
				return 5;
			}
		}
		else if(row == 2 || row == 5 || row == 8) {
			if(column == 0 || column == 3 || column == 6) {
				return 6;
			}
			else if(column == 1 || column == 4 || column == 7) {
				return 7;
			}
			else if(column == 2 || column == 5 || column == 8) {
				return 8;
			}
		}
		System.out.println("Error in finding square position");
		return -1;
	}
	
	/**
	 * This function just updates all our storage arrays after we find the answer to a position.
	 * @param xPosition The row of our current Sudoku board position.
	 * @param yPosition The column of our current Sudoku board position.
	 * @param square The square of our current Sudoku board position.
	 * @param squarePosition The position in the square of our current Sudoku board position.
	 * @param position The numbered position of our current Sudoku board.
	 */
	public void updateArrays(int xPosition, int yPosition, int square, int squarePosition, String position) {
		sudokuRows[xPosition][yPosition] = locationsWithPossibles.get(position).get(0);
		sudokuColumns[yPosition][xPosition] = locationsWithPossibles.get(position).get(0);
		sudokuSquares[square][squarePosition] = locationsWithPossibles.get(position).get(0);
	}
	
	public void outputSudoku() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print("|" + returnSudokuRows()[i][j] + " | ");
			}
			System.out.println();
		}
	}
}