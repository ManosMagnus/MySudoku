package sudoku;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sudoku.graphics.LogIn;


/**
 * @author Manos Kirtas
 * @version 1.0.0
 * @see LogIn
 * @see Players
 */
public class Application {

    /**
     * Ξεκινάει δημιουργώντας ενα παράθυρο {@link LogIn} δίνοντας για παράμετρο {@link Players}
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            new LogIn(new Players());
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showConfirmDialog(null, "Problem to load Players List", "Problem!", 0);
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
