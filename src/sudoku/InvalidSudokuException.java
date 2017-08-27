

package sudoku;

/**
 * Exception για την περίπτωση όπου το Sudoku δεν είναι αποδεκτό
 * @see ManageSudoku
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class InvalidSudokuException extends Exception {

    public InvalidSudokuException() { super(); }
    public InvalidSudokuException(String message) { super(message); }
    public InvalidSudokuException(String message, Throwable cause) { super(message, cause); }
    public InvalidSudokuException(Throwable cause) { super(cause); }
    
}
