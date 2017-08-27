package sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import sudoku.logic.Sudoku;

/**
 * Για την διαχείριση τών {@link Player} οι οποίοι είναι αποθηκεμένοι στην εφαρμογή (Διαγραφή, Ενημέρωση, Εισαγωγή)
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class Players {
    private ArrayList<String> playersList;
    final private Path playersPath = FileSystems.getDefault().getPath("./Saves/Players");
    final private String lastPlayerPath = "./Saves/Players/LastPlayer.ply.sud";
    final private String playersListPath = "./Saves/Players/PlayersList.ply.sud";

    /**
     * Διαχείριση παικτών {@link Player} φορτώνονται οι διαθέσιμοι παίκτες
     * @throws IOException Πρόβλημα κατά την φόρτωση  της λίστας των παικτών
     * @throws ClassNotFoundException Πρόβλημα εγγραφείς  {@link Player}
     * @see LogIn
     * @see Player
     */
    public Players() throws IOException, ClassNotFoundException{
        loadPlayersList();
    }

    /**
     * Επιστρέφει την λίστα των ονομάτων των αποθηκευμένων  {@link Player}
     * @return Επιστρέφει ένα ArrayList με τα ονόματα των αποθηκεμένων {@link Player}
     */
    public ArrayList<String> getPlayersList(){
        return playersList;
    }

    /**
     * Διαγράφει τών το {@link Sudoku} απο τον συγκεκριμένο {@link Player}
     * @param player Ο {@link Player} που θα διαγραφτεί το {@link Sudoku}
     * @throws IOException  Πρόβλημα κατά την ενημέρωση του αρχείου του {@link Player}
     */
    public void clearLastPlayer(Player player) throws IOException{
        Files.deleteIfExists(Paths.get(lastPlayerPath));
        player.clearSavedGame();
        if(player.hasStats()){
            updatePlayer(player);
        }
    }

    private void updatePlayersList() throws IOException{ 
        if(Files.notExists(playersPath)){
            Files.createDirectories(playersPath);
        }
        try(ObjectOutputStream outObj = new ObjectOutputStream(new FileOutputStream(playersListPath))){
            outObj.writeObject(playersList);
        }
    }
    
    private void loadPlayersList() throws IOException, ClassNotFoundException{
        if(Files.notExists(playersPath)){
            Files.createDirectories(playersPath);
            playersList = new ArrayList<>();
        }
        else{
            File playersListFile = new File(playersListPath);
            if(playersListFile.exists()){
                try(ObjectInputStream inObj = new ObjectInputStream( new FileInputStream (playersListFile))){
                    playersList = (ArrayList<String>)inObj.readObject();
                }
            }
            else{
                playersList = new ArrayList<>();
            }
        }
    }

    /**
     *Διαγράφει τον {@link Player} με το αντίστοιχο όνομα 
     * @param name Όνομα του παίκτη που θέλουμε να διαγράψουμε
     * @return Επιστρέφει αν διαγράφτικέ ο συγκεκριμένος {@link Player}
     * @throws IOException Προβλήμα κατλα την διαγραφή του αρχείου του {@link Player}
     */
    public boolean removePlayer(String name) throws IOException{
        if(playersList.contains(name)){
            playersList.remove(name);
            Path dPath = FileSystems.getDefault().getPath(generateFileName(name));
            Files.delete(dPath);
            updatePlayersList();
            return true;
        }
        return false;
    }

    /**
     * Επιστρέφει τον {@link Player} που βρίσκεται στην συγκεκριμένη θέση του πίνακα 
     * @param index Η θέση του μέσα στόν πίνακα
     * @return Επιστρέφει τον παίκτη που βρίσκεται στην θέση {@code  index}
     * @throws IOException Πρόβλημα κατα την ανάγνωση του αρχείου του {@link Player}
     * @throws ClassNotFoundException Πρόβλημα με τον {@link Player}
     */
    public Player loadPlayer(int index) throws IOException, ClassNotFoundException{
        if(index < playersList.size()){
            return loadPlayer(playersList.get(index));
        }
        return null;
    }


    private Player loadPlayer(String name) throws IOException, ClassNotFoundException{
        if(playersList.contains(name)){
            NamedPlayer nPlayer;
            try(ObjectInputStream inObj = new ObjectInputStream( new FileInputStream ( generateFileName(name)))){
                nPlayer =(NamedPlayer)inObj.readObject();   
            }
            return nPlayer;
        }
        return null;
    }

    /**
     *Καθαρίζει τα στατιστικά του {@link NamedPlayer}
     * @param player Ο παίκτης τον οποίο θέλουμε να καθαρίσουμε τα στατιστικά
     * @throws IOException Πρόβλημα κατά την ενημέρωση του αρχείου του παίκτη
     */
    public void clearStatsFor(NamedPlayer player) throws IOException{
        player.clearStats();
        this.updatePlayer(player);
    }

    private void updatePlayer(Player player) throws FileNotFoundException, IOException{
            if(Files.notExists(playersPath)){
                Files.createDirectories(playersPath);
            }
            try(ObjectOutputStream outObj = new ObjectOutputStream(new FileOutputStream(generateFileName(player.getName())))){
                outObj.writeObject(player);
            }
    }

    /**
     * Προσθέτει έναν {@link Player} στου διαθέσιμους παίκτες
     * @param name Το όνομα του παικτή που θέλουμε να προσθέσουμε
     * @return Επιστρεφεί τον {@link Player} που δημιουργήσαμε
     * @throws IOException Πρόβλημα κατά την δημιουργία ή εγγραφή του αρχείου του {@link Player}
     */
    public Player addPlayer(String name) throws IOException{
        if( !playersList.contains(name) ){
            playersList.add(name);
            Player player = new NamedPlayer(name);
            updatePlayer(player);
            updatePlayersList();
            return player;
        }
        return null;
    }

    /**
     * Φορτώνει τον {@link Player}  που έκλεισε για τελευταία φορά την εφαρμογή και αποθήκευσε το παιχνίδι 
     * @return Τον {@link Player} που έκλεισε για τελευταία φορά την εφαρμογή και αποθήκευσε τον {@link Sudoku}  
     * @throws FileNotFoundException Πρόβλημα ευρέσεις του αρχείου αποθηκεύσεις του τελευταίου παίκτη
     * @throws IOException  Πρόβλημα κατά τήν επικοινωνία με το αρχείο τελευταίου παίκτη
     * @throws ClassNotFoundException Πρόβλημα με τον {@link Player}
     */
    public Player loadLastPlayer() throws FileNotFoundException, IOException, ClassNotFoundException{
        File lastPlayerFile = new File(lastPlayerPath);
        if(lastPlayerFile.exists()){
            try(ObjectInputStream inObj = new ObjectInputStream( new FileInputStream (lastPlayerFile))){
                return (Player)inObj.readObject();
            }
        }
        return null;
    }

    /**
     * Αποθηκεύει το {@link Sudoku} του {@link Player} που δεν έχει ολοκληρωθεί 
     * @param player Ο τρέχον  {@link Player} 
     * @param game Το τρέχον  {@link Sudoku}
     * @throws IOException ρόβλημα κατά τα την ενημέρωση του αρχείου το {@link Player}
     */
    public void uncompleteGame(Player player, Sudoku game) throws IOException{
        player.saveGame(game);
        if(player.hasStats()){
            updatePlayer(player);
        }
        try (ObjectOutputStream outObj = new ObjectOutputStream(new FileOutputStream(lastPlayerPath))) {
            outObj.writeObject(player);
        }
    }
    
    /**
     * * Αποθηκεύει το id του {@link Sudoku} στα ολοκληρωμένα παιχνίδια του {@link Player} στην περίπτωση που έχει στατιστικά
     * @param player Ο τρέχον {@link Player} που έχει ολοκληρώσει το {@link Sudoku}
     * @param gameId Η id tou {@link Sudoku}
     * @param gameType to {@link GameType} του {@link Sudoku}
     * @throws IOException Πρόβλημα κατά την ενημέρωση του αρχείου το παίκτη
     */
    public void completeGame(Player player, String gameId, GameType gameType) throws IOException{
        if(player.hasStats()){
            NamedPlayer nPlayer = (NamedPlayer) player;
            nPlayer.completeGame(gameId,gameType);
            updatePlayer(nPlayer);
        }
    }

    /**
     * Αυξάνει τα παιχνίδια του παίκτη και τίς νίκες εφόσον έχει νικήσει στην περίπτωση οπού υποστηρίζει τα στατιστικά
     * @param player Ο τρέχον παίκτης
     * @param win {@code true} στην περίπτωση αοπου έχει νικήσει τον CpuPlayer
     * @throws IOException Πρόβλημα κατά την ενημέρωση του αρχείου του παικτη
     */
    public void increaseGames(Player player,boolean win) throws IOException{
        if(player.hasStats()){
            NamedPlayer nPlayer = (NamedPlayer) player;
            nPlayer.increaseGames(win);
            updatePlayer(nPlayer);
        }
    }
    private String generateFileName(String name){
        return  "./Saves/Players/" + name + ".ply.sud";
    }
}
