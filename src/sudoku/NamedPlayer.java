
package sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import sudoku.logic.Sudoku;

/**
 * Αντιστοιχεί σε ένα χρήστη του παιχνιδιού
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class NamedPlayer implements Player,Serializable {
    private static final long serialVersionUID = 0002;
    private String name;
    private int games;
    private int wins;
    private ArrayList<String> completeHyper;
    private ArrayList<String> completeClassic;
    private Sudoku savedGame;
   
    /**
     * Ορίζει έναν καινούριο παίχτη
     * @param name Το όνομα τού παίχτη
     */
    public NamedPlayer(String name){
        completeHyper = new ArrayList<>();
        completeClassic = new ArrayList<>();
        this.name = name;
        savedGame = null;
        wins = games = 0;
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
     * Διαγράφει το αποθηκεύμενο {@link Sudoku}
     */
    @Override
    public void clearSavedGame(){
        savedGame = null;
    }

    /**
     *  Επιστρέφει το  {@link Sudoku} που έχει αποθηκευμένο
     * @return Το {@link Sudoku}  που έχει αποθηκευμένο
     */
    @Override
    public Sudoku getSavedGame(){
          return savedGame;
    }
    
    /**
     * Αποθηκεύει το {@link Sudoku}
     * @param game {@link Sudoku} που θελούμε να αποθηκεύσουμε
     */
    @Override
    public void saveGame(Sudoku game){
        savedGame = game;  
    }
    
    /**
     * Αποθηκεύει την id του {@link Sudoku} που έχει ολοκληρωθεί από τον παίχτη
     * @param gameId Την id {@link Sudoku}
     * @param gameType Τον τύπο παιχνιδιού
     */
    public void completeGame(String gameId, GameType gameType){
        if(gameType.equals(GameType.CLASSIC)){
            completeClassic.add(gameId);
        }else{
            completeHyper.add(gameId);
        }
    }

    /**
     * Διαγραφεί τα στατιστικά του παίκτη
     */
    public void clearStats(){
        games = 0;
        wins = 0;
    }

    /**
     * Όταν ολοκληρώνετε ένα παιχνίδι DuiDoku αυξάνει τα παιχνίδια του συγκεκριμένου χρήστη καθώς και και τις νίκες σε περίπτωση που έχει νικήσει
     * @param win Το αποτέλεσμα το παιχνιδιού {@code True} αν έχει νικήσει
     */
    public void increaseGames(boolean win){
        games++;
        if(win){
            wins++;
        }
    }


    private double getAvarageWins(){
        if(games == 0){
            return 0.0;
        }
        return (double)wins/games;
    }
    
    /**
     * Επιστρέφει την λίστα των id Hyper που έχει ολοκλήρωσει
     * @return Λίστα των Hyper που έχει ολοκλήρωσει
     */
    public ArrayList<String> getCompleteHyper(){
        return completeHyper;
    }
    
    /**
     * Επιστρέφει την λίστα των id Hyper που έχει ολοκληρώσει
     * @return Λίστα των Hyper id που έχει ολοκλήρωσει
     */
    public ArrayList<String> getCompleteClassic(){
        return completeClassic;
    }

    /**
     * Επιστέφει το όνομα του παίκτη
     * @return To όνομα του παίκτη
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Επιστρέφει αν ο χρήστης έχει στατιστικά
     * @return {@code true}
     */
    @Override
    public boolean hasStats() {
        return true;
    }

    /**
     * Επιστρέφει τα στατιστικά του παίκτη
     * @return Τα παιχνίδια, τις νίκες, τις ήττες και το ποσοστό κερδισμένων παιχνιδιών στο DuiDoku
     */
    @Override
    public String getStats() {
        return "Games : " +games+ "\nWins : " + wins + "\nLoses: " + (games-wins) + "\nAverage wins:  " + getAvarageWins()*100 + "%" ; 
    }
    
    
}
