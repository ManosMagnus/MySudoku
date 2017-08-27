package sudoku.logic;

import java.util.ArrayList;

/**
 * Η λογική του Κλασικου τύπου Sudoku
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class Classic extends Sudoku {

    /**
     * Η λογική του Κλασικού τύπου Sudoku
     * @param game Η λίστα για την αρχικοποίηση του Sudoku
     * @param help {@code true} στην περίπτωση οπού η βοήθεια είναι ενεργοποιημένη
     * @param letters {@code true} στην περίπτωση οπού το Sudoku έχει την μορφή  WordDoku 
     * @param id H id του συγκεκριμένου puzzle
     */
    public Classic(ArrayList<String> game, boolean help, boolean letters,String id) {
        super(9, game, help, letters,id);
    }
    
    
}
