package sudoku;

import sudoku.logic.Sudoku;

/**
 * Η διεπαφή ορίζει τις απαραίτητες μέθοδούς όπου πρέπει να έχει ένας παίκτης
 * @author Manos Kirtas
 * @version 1.0.0
 */
public interface Player{

    /**
     * Επιστρέφει το όνομα του παίκτη
     * @return Το όνομα του παίκτη
     */
    public String getName();

    /**
     * Επιστρέφει αν ο παικτής έχει στατιστικα
     * @return Αν έχει στατιστικά
     */
    public boolean hasStats();

    /**
     * Επιστρέφει τα στατιστικά
     * @return Τα στατιστικά
     */
    public String getStats();

    /**
     * Καθαρίζει τα αποθηκευμένα {@link Sudoku} του παίκτη
     */
    public void clearSavedGame();

    /**
     * Επιστρέφει το αποθηκευμένο  {@link Sudoku} του παίκτη
     * @return Το αποθήκευμένο παιχνίδη
     */
    public Sudoku getSavedGame();

    /**
     * Αποθηκεύει ενα παιχνίδη
     * @param game Το παιχωλιδη που θέλουμε να αποθηκεύσουμε
     */
    public void saveGame(Sudoku game);

    /**
     * Επιστρέφει αν έχει αποθήκευμένο παιχνίδη
     * @return Αν έχει αποθηκευμένο παιχνίδη
     */
    public boolean hasSavedGame();
    
}
