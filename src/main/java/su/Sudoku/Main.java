/**
 * 
 */
package su.Sudoku;

/**
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args[0] == "1") {
			char[][] sudoku1 = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},
								{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},
								{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},
								{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},
								{'.','.','.','.','8','.','.','7','9'} };
			Solution sudokuSolution = new Solution();
			sudokuSolution.solveSudoku(sudoku1);
		}
		else {
			System.out.println("Input 1 to test Sudoku Solver");
		}
	}
}
