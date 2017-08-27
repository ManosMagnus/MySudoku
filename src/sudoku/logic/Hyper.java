package sudoku.logic;

import java.util.ArrayList;

/**
 * Η λογική του Hyper τύπου Sudoku
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class Hyper extends Sudoku {

    /**
     * Η λογική του Hyper τύπου Sudoku
     * @param game Η λίστα για την αρχικοποίηση του Sudoku
     * @param help {@code true} στην περίπτωση οπού η βοήθεια είναι ενεργοποιημένη
     * @param letters {@code true} στην περίπτωση οπού το Sudoku έχει την μορφή WordDoku 
     * @param id H id του συγκέκριμενου puzzle
     */
    public Hyper(ArrayList<String> game,boolean help, boolean letters,String id){
        super(9,game,help, letters, id);
    }
    
    /**
     * Έλεγχος τιμής του στοιχείου για την εισαγωγή (Έλεγχος γραμμής, στήλης, 3χ3 κουτιά και τα 3χ3 επιπλέον κουτιά)
     * @param x Ο αριθμός της γραμμής του κελίου
     * @param y Ο αριθμός της στήλης του κελίου
     * @param value Το στοιχείο που θέλουμε να ελέγξουμε
     * @return Αν μπορούμε να εισαγούμε το στοιχείο στο συγκεκριμένο κελί
     */
    @Override
    public boolean check(int x, int y, int value){
        if(value == 0){ return true; }
        if(x >= 9 || y >= 9 || value > 9){ return false; }
        if(checkBox(x,y,value) && checkRow(y,value) && checkColumn(x,value) && checkExtraBoxes(x,y,value)){
            return true;
        }
        return false;
    }

    private boolean checkExtraBoxes(int x, int y, int value){
        if(x==0 || y==0 || x==4 || y==4 || x==8 || y==8){
            return true;
        } //Exoume Apoklisei oles tis periptwseis pou den vriskontai sta koutia        
        for(int i= (x/4*4)+1; i< (x/4*4)+4; i++){
            for(int j= (y/4*4)+1; j< (y/4*4)+4; j++){
                if(cell[i][j] == value){
                    return false;
                }
            }
        }
        return true;        
    }
    
}
