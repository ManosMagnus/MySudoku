package sudoku.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Κλάση για τον έλεγχο και την εισαγωγή καθώς και την αρχικοποίησή της βοηθείας
 * @author Manos Kirtas
 * @version 1.0.0
 * @see Helper
 */
public abstract class Sudoku implements Serializable  {
    
    private int limit;
    /**
     * Ο πίνακας με της τιμές που έχει το Sudoku
    */
    protected int cell[][];
    private int free;
    private HashSet<Integer>[][] availableNum;
    private ArrayList<int[]> defaultNumber;
    private boolean help;
    private boolean letters;
    private String id;
    
    /**
     * Δημιουργεί τον πίνακά αναλογία στο μέγεθος που χρειάζεται,  αρχικοποιεί τον πίνακα καθώς και αρχικοποιεί την βοήθεια εφόσον χρειαστεί
     * @param limit Τα όριο του δυσδιάστατου πίνακα
     * @param game Η λίστα με τις Συμβολοσειράς για να αρχικοποιηθεί ο πίνακας 
     * @param help {@code true} στην περίπτωση οπού η βοήθεια είναι ενεργοποιημένη
     * @param letters {@code true} στην περίπτωση οπού το Sudoku έχει την μορφή WordDoku 
     * @param id Ο αριθμός id του puzzle
     */
    protected Sudoku(int limit, ArrayList<String> game, boolean help, boolean letters, String id){ //const for Hyper and Classic
        this.id = id;
        free = 81;
        this.letters = letters;
        defaultNumber = new ArrayList<>();
        this.help = help;
        this.limit = limit;
        cell = new int[limit][limit];
        setGame(game);
        if(help){
            setHelp();
        }
    }
    
    /**
     * Δημιουργεί τον πίνακα και αρχικοποιεί την βοήθεια
     * @param limit Τα όριο του δυσδιάστατου πίνακα
     */
    protected Sudoku(int limit){ //const for DuiDoku
        this.help = true;
        free = 16;
        this.limit = limit;
        cell = new int[limit][limit];
        setHelp();
    }    
    /**
     * Επιστρέφει την id του puzzle
     * @return id του Puzzle
     */
    public String getId(){
        return id;
    }
    
    /**
     * Επιστρέφει αν η βοήθεια είναι ενεργοποιημένη
     * @return αν η βοήθεια είναι ενεργοποιημένη
     */
    public boolean helpIsOn(){
        return help;
    }
    
    /**
     * Επιστέφει αν το Sudoku είναι στην μορφή WordDoku
     * @return Επιστέφει αν το Sudoku είναι στην μορφή WordDoku 
     */
    public boolean lettersIsOn(){
        return letters;
    }

    /**
     * Ελέγχει αν το συγκεκριμένο κελί δεν μπορεί να αλλαχθεί από τον χρήστη
     * @param x Ο αριθμός της γραμμής του κελιού
     * @param y Ο αριθμός της στήλης του κελιού
     * @return Επιστρέφει {@code true} στην περίπτωση οπού το κελί δεν μπορεί να αλλαχθεί από τον χρήστη 
     */
    public boolean isDefault(int x, int y){
        for(int[] array : defaultNumber){
            if(array[0] == x && array[1] == y){
                return true;
            }
        }
        return false;
    }

    /**
     * Επιστέφει την τιμή στο συγκεκριμένο κελί
     * @param x Ο αριθμός της γραμμής του κελιού
     * @param y Ο αριθμός της στήλης του κελιού
     * @return Το στοιχείο του κελίου επιστρλεφει -1 αν το x ή το y είναι εκτώς ορίων
     */
    public int get(int x, int y){
        if(x <= limit && y<= limit){
            return cell[x][y]; 
        }
        return -1;
    }
    
    private void setGame(ArrayList<String> game){
        int i = 0;
        for(String line : game){
            for(int j=0; j<limit; j++){
                cell[i][j] =  Character.getNumericValue(line.charAt(j));
                if(cell[i][j] != 0){
                    free--;
                    defaultNumber.add(new int[]{i,j});
                }
            }
            i++;
        }
    }

    /**
     * Ελέγχει αν το στοιχείο μπορεί αν εισαχθεί στο συγκεκριμένο κελί και στην συνέχεια το είσαγε
     * @param x Ο αριθμός της γραμμής του κελιού
     * @param y Ο αριθμός της στήλης του κελιού
     * @param value Το στοιχείο που θέλουμε να εισάγουμε στο κελί
     * @return Επιστρέφει αν το στοιχείο εισάχθηκε
     */
    public boolean setCell(int x, int y, int value){
        if(check(x,y,value)){
            if(cell[x][y] == 0 && value != 0){
                free--;
            }else if(cell[x][y] != 0 && value == 0){
                free++;
            }
            cell[x][y] = value;
            return true;
        }
        return false;
    }
    
    /**
     * Ελέγχει αν το Sudoku έχει ολοκληρωθεί
     * @return Επιστρέφει {@code  true} στην περίπτωση που έχει ολοκληρωθεί
     */
    public boolean isComplete(){
        if(free == 0){
            return true;
        }
        return false;
    }

    /**
     * Ελέγχει την γραμμή
     * @param y Ο αριθμός της στήλης του κελιού
     * @param value Η τιμή που θέλουμε να ελένγξουμε
     * @return Επιστρέφει αν μπορούμε να εισάγουμε το στοιχείο με βάση την κουτί
     */
    protected boolean checkRow(int y,int value){
        for(int i=0; i<limit; i++){
            if(cell[i][y] == value){
                return false;
            }
        }
        return true;
    }

    /**
     * Ελέγχει το κουτί το οποίο βρίσκεται το κελί
     * @param x Ο αριθμός της γραμμές
     * @param y Ο αριθμός της στήλης
     * @param value Η τιμή που θέλουμε να ελέγξουμε
     * @return Επιστρέφει αν μπορούμε να εισάγουμε το στοιχείο με βάση την γραμμή
     */
    protected boolean checkBox(int x, int y, int value){
        int sqrtLimit = (int) Math.sqrt(limit);
        for(int i= x/sqrtLimit*sqrtLimit; i< x/sqrtLimit*sqrtLimit+sqrtLimit; i++){
            for(int j= y/sqrtLimit*sqrtLimit; j< y/sqrtLimit*sqrtLimit+sqrtLimit; j++){
                if(cell[i][j] == value){
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Ελέγχει την στήλη
     * @param x Ο αριθμός της γραμμής
     * @param value Η τιμή που θέλουμε να ελέγξουμε
     * @return Επιστρέφει αν μπορούμε να εισάγουμε το στοιχείο με βάση την στήλη
     */
    protected boolean checkColumn(int x, int value){
        for(int j=0; j<limit; j++){
            if(cell[x][j] == value){
                return false;
            }
        }
        return true;
    }

    /**
     * Διαγράφει ενα στοιχείο απο τα διαθέσιμα που μπορούμε να εισάγουμε από ενα συγκεκριμένο κελί 
     * @param x Ο αριθμός της γραμμής του κελιού
     * @param y Ο αριθμός της στήλης του κελιού
     * @param value Η τιμή που θέλουμε να αφαιρέσουμε από τα διαθέσιμα
     */
    public void removeHelpSet(int x, int y, int value){
        if(!help){ return; }
        if(x < limit && y < limit && value <= limit){
            availableNum[x][y].remove(value);
        }
    }

    /**
     * Εισάγει ενα στοιχείο στα διαθέσιμα που μπορούμε να εισάγουμε από ενα συγκεκριμένο κελί 
     * @param x Ο αριθμός της γραμμής του κελιού
     * @param y Ο αριθμός της στήλης του κελιού
     * @param value Η τιμή που θέλουμε να εισάγουμε στα διαθέσιμα
     */
    public void addHelpSet(int x, int y, int value){
        if(!help){ return; }
        if(x < limit && y < limit && value <= limit){
            availableNum[x][y].add(value);
        }
    }   

    private void setHelp(){
        availableNum = new HashSet[limit][limit];
        for(int i=0; i<limit; i++){
            for(int j=0; j<limit; j++){
                availableNum[i][j] = new HashSet<>();
                for(int z=1; z<limit+1; z++){
                    if(check(i,j,z)){
                        availableNum[i][j].add(z);
                    }
                }
            }
        }
    }
    
    /**
     * Επιστρέφει το σύνολο τών στοιχείων που μπορούμε να εισάγουμε στο συγκεκριμένο κελί
     * @param i Ο αριθμός της γραμμής του κελιού
     * @param j Ο αριθμός της στήλης του κελιού
     * @return Το σύνολο τον διαθέσημων τίμων για το συγκεκριμενο κελι
     */
    public HashSet<Integer> getAvailables(int i, int j){
        if(!help){ return null; }
        if(i < limit && j < limit){
            return availableNum[i][j];
        }
        return null;
    }
    
    /**
     * Ελέγχει αν το στοιχείο μπορεί να εισαχθεί στο συγκεκριμένο κελί
     * @param x Ο αριθμός της γραμμής του κελιού
     * @param y Ο αριθμός της στήλης του κελιού
     * @param value Το στοιχείο που θέλουμε να εισάγουμε
     * @return Επιστρέφει αν μπορούμε να το εισάγουμε
     */
    public boolean check(int x, int y, int value){
        if(value == 0){ return true; }
        if(x >= limit || y >=limit || value > limit){ return false; }
        if(checkBox(x,y,value) && checkRow(y,value) && checkColumn(x,value)){
            return true;
        }
        return false;
    }
    
    /* Επιστρέφει τo όριo του Sudoku
     * @return Επιστρέφει τo όριo του Sudoku
     */
    public int getLimit(){
        return limit;
    }
    
}
