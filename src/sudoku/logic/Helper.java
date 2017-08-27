package sudoku.logic;

import sudoku.TheGame;

/**
 * Κλάση η οποία διαχειρίζεται την βοήθεια του Sudoku κανοντα τις κατάλληλες μετατροπίες
 * @author Manos Kirtas
 * @version 1.0.0
 * @see CpuPlayer
 * @see Sudoku
 */

public class Helper{
    private Sudoku game;
    private int limit;
    private TheGame theGame;
    
    /**
     * Δέχεται το {@link Sudoku} οπού καλείτε να διαχειρίζεται την βοήθεια
     * @param game Το παιχνίδι οπού διαχειρίζεται την βοήθεια
     * @param theGame Ο τρέχον διαχειριστής του παιχνιδιού οπού κάνει και την σύνδεση με το γραφικό κομμάτι
     */
    public Helper(Sudoku game, TheGame theGame){
        this.theGame = theGame;
        this.game = game;
        limit = 9;
        if(game instanceof DuiDoku){
            limit = 4;
        }
    }

    /**
     *  Αναλαμβάνει να ενημερώσει της αλλαγές που προέκυψαν μετά την κίνηση του παίκτη (να διαγράψει ή να αφαίρεση από τα καταλληλά κελιά
     * τους διαθέσιμους αριθμούς) καθώς και να δώσει της κατάλληλες τιμές ώστε να ενημερωθεί και το γραφικό κομμάτι της εφαρμογής
     * @param x Ο αριθμός της γραμμής οπού έγινε η κίνηση
     * @param y Ο αριθμός της στήλης οπού έγινε η κίνηση
     * @param value Το στοιχείο το οποίο εισάχθηκε στο συγκεκριμένο κελί
     * @param prevValue Το προηγούμενο στοιχείο που βρισκόταν στο κελί
     */
    public void getHelp(int x, int y, int value,int prevValue){
        if(value != 0 || prevValue != 0 ){
            for(int i=0; i<limit; i++){
                if(value !=0 ){
                     game.removeHelpSet(i, y, value);
                     game.removeHelpSet(x, i, value);
                }
                if(prevValue != 0 ){
                     game.addHelpSet(i, y, prevValue);
                     game.addHelpSet(x, i, prevValue);
                }
                theGame.setHelp(i, y, game.getAvailables(i, y));
                theGame.setHelp(x, i, game.getAvailables(x, i));                 
            }
            int sqrtLimit =(int)Math.sqrt(limit);
            for(int i= x/sqrtLimit*sqrtLimit; i< x/sqrtLimit*sqrtLimit+sqrtLimit; i++){
                for(int j= y/sqrtLimit*sqrtLimit; j< y/sqrtLimit*sqrtLimit+sqrtLimit; j++){
                    if(value != 0){
                        game.removeHelpSet(i, j, value);
                    }
                    if(prevValue != 0){
                        game.addHelpSet(i, j, prevValue);
                    }
                    theGame.setHelp(i, j, game.getAvailables(i, j));
                }
            }
            if(game instanceof Hyper){
                if(x!=0 && y!=0 && x!=4 && y!=4 && x!=8 && y!=8){
                    for(int i= (x/4*4)+1;i< (x/4*4)+4; i++){
                        for(int j= (y/4*4)+1;j< (y/4*4)+4; j++){
                            if(value != 0){
                                game.removeHelpSet(i, j, value);
                            }
                            if(prevValue != 0){
                                game.addHelpSet(i, j, prevValue);
                            }
                            theGame.setHelp(i, j, game.getAvailables(i, j));
                        }
                    }
                }
            }
        }        
    }
    
}