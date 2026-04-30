import java.util.*;
import java.io.*;

//Sudoku Board class
public class SudokuBoard {
	//fields
	protected String[][] board;


	//constructor
	public SudokuBoard(String FileName) {
		board = new String[9][9];

		try {
			Scanner input = new Scanner(new File(FileName));
			int row = 0;
			while(input.hasNextLine() && row < board.length) {
				String line = input.nextLine();

				if(line.length() != board.length) {
					System.out.println(FileName + " format doesnt match requirements X_x \n");
					return;
				}
				//adding file values to board
				for(int col = 0; col < board[row].length; col++) {
					board[row][col] = "" + line.charAt(col);
				}
				row++;
			}
		}catch(FileNotFoundException e) {
			System.out.println("Can't load " + FileName + " :(");
		}
	}


	//helper for toString
	public String printBoard() {
		String sBoard = "+-----------------------+\n";
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < 9; c++) {
				if(c == 0 || c == 3 || c == 6) {
					sBoard += "| ";
				}
				sBoard += board[r][c] + " ";
			}
			sBoard += "|\n";
			if(r == 2 || r == 5) {
				sBoard += "|-------|-------|-------|\n";
			}
		}
		sBoard += "+-----------------------+\n";
		return sBoard;
	}

	public boolean isValid() {
		return isValidInput() && hasRowDuplicates() && hasColDuplicates() && hasSquareDuplicates();
	}

	// These helpers reject bad characters and ignore empty cells when checking duplicates.
	private boolean isValidInput() {
		for(String[] row : board) {
			for(String col : row) {
				String indexOf1 = "" + 1;
				String indexOf9 = "" + 9;
				if(!(col.compareTo(indexOf1) >= 0 && col.compareTo(indexOf9) <= 0) && !col.equals(".")) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean hasRowDuplicates() {
		for(int c = 0; c < board.length; c++) {
			Set<String> set = new HashSet<>();
			int dotCount = 0;
			for(String[] row : board) {
				set.add(row[c]);
				if(row[c].equals(".")) {
					dotCount++;
				}
			}
			// Remove the shared empty-cell marker so only repeated numbers count as duplicates.
			if(set.contains(".")){
				dotCount--;
			}
			if(set.size() + dotCount < board.length) {
				return false;
			}
		}
		return true;
	}

	private boolean hasColDuplicates() {
		for(String[] row : board) {
			Set<String> set = new HashSet<>();
			int dotCount = 0;
			for(String col : row) {
				set.add(col);
				if(col.equals(".")) {
					dotCount++;
				}
			}
			// Remove the shared empty-cell marker so only repeated numbers count as duplicates.
			if(set.contains(".")){
				dotCount--;
			}
			if(set.size() + dotCount < board.length) {
				return false;
			}
		}
		return true;
	}

	private boolean hasSquareDuplicates() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Set<String> set = new HashSet<>();
				int dotCount = 0;
				for(int r = 0; r < 3; r++) {
					for(int c = 0; c < 3; c++) {
						int rows = i * 3 + r;
						int cols = i * 3 + c;
						set.add(board[i * 3 + r][j * 3 + c]);
						if(board[i * 3 + r][j * 3 + c].equals(".")) {
							dotCount++;
						}
					}
				}
				// Remove the shared empty-cell marker so only repeated numbers count as duplicates.
				if(set.contains(".")){
					dotCount--;
				}
				if(set.size() + dotCount < 9) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isSolved(){
		if(!isValid()){
			return false;
		}
		Map<String, Integer> map = new HashMap<> ();
		for(int i = 1; i <= 9; i++) {
			map.put(i + "", 0);
		}

		for(String[] row : board) {
			for(String col : row) {
				// Empty cells are ignored here because a solved board must count only filled digits.
				if(!col.equals(".")) {
					int value = map.get(col);
					map.put(col, value + 1);
				}
			}
		}
		Collection<Integer> values = map.values();
		for(int num : values){
			if(num != 9){
				return false;
			}
		}
		return true;
	}

	//toString
	public String toString() {
		return printBoard();
	}
}
