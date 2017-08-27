
package sudoku;

import java.io.Serializable;
import sudoku.logic.Sudoku;

/**
 * Παίχτης που αντιστοιχεί στην ανώνυμη περιήγηση
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class Incognito implements Player,Serializable {
    private Sudoku savedGame;
    /**
     * Δημιουργεί έναν παίκτη για ανώνυμη περιήγηση
     */
    public Incognito(){
        
    }
    
    /**
     * Ελέγχει αν υπάρχει ανολοκλήρωτο {@link Sudoku}
     * @return Αν είχε αποθηκεμένο {@link Sudoku}
     */
    @Override
    public boolean hasSavedGame(){
        if(savedGame == null){
            return false;
        }
        return true;
    }
        
    /**
     * Διαγράφει το αποθηκεμένο  {@link Sudoku}
     */
    @Override
    public void clearSavedGame(){
        savedGame = null;
    }

    /**
     * Επιστρέφει το  {@link Sudoku} που έχει αποθηκεμένο
     * @return Το {@link Sudoku}  που έχει αποθηκεμένο
     */
    @Override
    public Sudoku getSavedGame(){
          return savedGame;
    }
    
    /**
     * Αποθηκεύει το {@link Sudoku}
     * @param game {@link Sudoku} που θέλουμε να αποθηκεύσουμε
     */
    @Override
    public void saveGame(Sudoku game){
        savedGame = game; 
    }
    
    /**
     * Επιστέφει το όνομα  του παίκτη
     * @return "Incognito" όνομα του παίκτη
     */
    @Override
    public String getName() {
        return "Incognito";
    }

    /**
     * Επιστέφει αν ο παίκτης έχει στατιστικά
     * @return {@code false} δεν έχει στατιστικά 
     */
    @Override
    public boolean hasStats() {
        return false;
    }

    /**
     * Επιστρέφει τα στατιστικά του παίκτη
     * @return "Incognito users haven't statistics!"
     */
    @Override
    public String getStats() {
        return "Incognito users haven't statistics!";
    }
}
