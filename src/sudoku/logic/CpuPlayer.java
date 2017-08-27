package sudoku.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Αντίπαλος παίκτης για την περιπτώση του {@link DuiDoku}
 * @author Manos Kirtas
 * @version 1.0.0
 * @see DuiDoku
 */

public class CpuPlayer {
    private ArrayList<int[]> freeCells;
    private Sudoku game;
    private Helper helper;

    /**
     * Δημιουργεί τον αντίπαλο  παίκτη στην περίπτωση του {@link DuiDoku} και αρχικοποιεί τα ελεύθερα κελία
     * @param game Στο παιχνίδι οπού καλείτε να παίξει
     * @param helper Λαμβάνει την βοήθεια ώστε να πραγματοποιεί τις κινήσεις
     */
    public CpuPlayer(Sudoku game, Helper helper){
        this.helper = helper;
        this.game = game;
        freeCells = new ArrayList<>();
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int[] array ={i,j};
                freeCells.add(array);
            }
        }
    }
    private void removeFreeCell(int x, int y){
        Iterator<int[]> it = freeCells.iterator();
        int count = 0;
        while(it.hasNext())
        {
            int[] obj = it.next();
            if(obj[0] == x && obj[1] == y){
                freeCells.remove(count);
                return;
            }
            count++;
        }
    }

    /**
     * Δέχεται σαν όρισμα την κίνηση του παίκτη και στην συνέχεια πραγματοποιεί την κίνηση του αντιπάλου 
     * @param x Ο αριθμός της γραμμής του κελίου οπού έπαιξε ο χρήστης
     * @param y Ο αριθμός της γραμμής του κελίου οπίου έπαιξε ο χρήστης
     * @return Επιστρέφει έναν πίνακα οπού στην θέση 0 έχει την γραμμή του κελιού στην θέση 1 την στήλη του κελιού και στην θέση
     * 2 τον στοιχείο που εισάγει στο {@link Sudoku}
     */
    public int[] nextMove(int x, int y){
        if(x >= game.getLimit() || y > game.getLimit()){ return null; }
        removeFreeCell(x,y);
        Collections.shuffle(freeCells);
        Iterator<int[]> it = freeCells.iterator();
        while(it.hasNext())
        {
            int[] p = it.next();
            HashSet<Integer> set = game.getAvailables(p[0], p[1]);
            if(!set.isEmpty()){
                int item = new Random().nextInt(set.size());
                int i = 0;
                for(Integer obj : set)
                {
                    if (i == item){
                        int[] ret = {p[0],p[1],obj};
                        removeFreeCell(p[0],p[1]);
                        helper.getHelp(p[0], p[1], obj, 0);
                        game.setCell(p[0], p[1], obj);
                        return ret;
                    }
                    i++;
                }
            }
        }
        return null;
    }

    /**
     * Ελενχεί αν υπάρχουν άλλα ελεύθερα κελία
     * @return {@code false} αν δεν υπάρχουν αλλά ελεύθερα κελιά αρα δεν μπορεί να πραγματοποιηθεί άλλη κίνηση
     */
    public boolean checkFreeCells(){
        for(int[] cell : freeCells){
            if(!game.getAvailables(cell[0], cell[1]).isEmpty()){
                return false;
            }
        }
        return true;
    }
        
}
