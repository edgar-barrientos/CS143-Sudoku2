public class SudokuEngine {
	public static void main(String[] args) {
		SudokuBoard board = new SudokuBoard("Sudoku2/data1.sdk");
		System.out.print(board);
		System.out.println("Is valid? " + board.isValid());
		System.out.println("Is solved? " + board.isSolved());
	}
}
/*
+-----------------------+
| 2 . . | 1 . 5 | . . 3 |
| . 5 4 | . . . | 7 1 . |
| . 1 . | 2 . 3 | . 8 . |
|-------|-------|-------|
| 6 . 2 | 8 . 7 | 3 . 4 |
| . . . | . . . | . . . |
| 1 . 5 | 3 . 9 | 8 . 6 |
|-------|-------|-------|
| . 2 . | 7 . 1 | . 6 . |
| . 8 1 | . . . | 2 4 . |
| 7 . . | 4 . 2 | . . 1 |
+-----------------------+
Is valid? true
Is solved? false
*/