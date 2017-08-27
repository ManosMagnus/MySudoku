package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Διαχειρίζεται το άνοιγμα και την φόρτωση του Sudoku από τον δίσκο
 * @author Manos Kirtas
 * @version 1.0.0
 */

public class ManageSudoku {
    
    String puzzlePath;
    ArrayList<String> puzzleList;
    GameType gameType;
    private String sudokuId;

    /**
     * Διαχηριζετε το ανοίγμα τών Sudoku
     * @param gameType Ο τύπος του Sudoku
     * @throws FileNotFoundException FileNotFoundException Στην περίπτωση από δεν βρεθεί το αρχείο που πάει να ανοίξει
     * @throws IOException Πρόβλημα κατά την επικοινωνία με το αρχείο
     */
    public ManageSudoku(GameType gameType) throws FileNotFoundException, IOException{
        puzzlePath = "./Saves/Puzzle/";
        puzzleList = new ArrayList<>();
        sudokuId = null;
        this.gameType = gameType;
        loadSudokuList();
    }
    
    private void loadSudokuList() throws FileNotFoundException, IOException{
        String line;
        String puzzleListPath = null;
        if(gameType.equals(GameType.CLASSIC)){
            puzzleListPath = puzzlePath.concat("Classic/ClassicList.pzl.sud");
            puzzlePath = puzzlePath.concat("Classic/");
        }else if(gameType.equals(GameType.HYPER)){
            puzzleListPath = puzzlePath.concat("Hyper/HyperList.pzl.sud");
            puzzlePath = puzzlePath.concat("Hyper/");
        }
        try (BufferedReader in = new BufferedReader(new FileReader(puzzleListPath))) {
            while((line = in.readLine()) != null){
                puzzleList.add(line);
            }
        }
        Collections.sort(puzzleList);
    }
    
    /**
     *  Δέχεται έναν {@link Player} για αν διαβάσει τα Sudoku οπού δεν έχουν διαβαστεί και στην συνεχεία να επιστρέφει στη μορφή {@link ArrayList}
     * 
     * @param player {@link Player} για να πάρει τα Sudoku που εχουν ολοκλήροθει
     * @return {@link ArrayList} Όπου σε κάθε String περιέχει 9 χαρακτήρες που αντιστοιχούν στο κελί του Sudoku
     * @throws IOException Στην περίπτωση όπου δεν βρεθεί το αρχείο που πάει να ανοίξει
     * @throws FileNotFoundException Πρόβλημα κατά την επικοινωνία με το αρχείο
     * @throws InvalidSudokuException Το αρχείο του Puzzle δεν είναι σε σώστη μορφή για εγγραφή
     */
    public ArrayList<String> loadSudoku(Player player) throws IOException, FileNotFoundException, InvalidSudokuException{
        if(player == null){ return null; }
        ArrayList<String> list = new ArrayList<>();
        ArrayList <String> availableGames = new ArrayList<>();
        if(player.hasStats()){             
            NamedPlayer  namedPlayer = (NamedPlayer) player;
            if(gameType.equals(GameType.CLASSIC)){
                list = namedPlayer.getCompleteClassic();
            }else if(gameType.equals(GameType.HYPER)){
                list = namedPlayer.getCompleteHyper();
            }
            availableGames = (ArrayList<String>)puzzleList.clone();
            if(list != null && availableGames.size() != list.size()){
                availableGames.removeAll(list);
            }
        }else{
            availableGames = (ArrayList<String>)puzzleList.clone();
        }
        Collections.shuffle(availableGames);
        sudokuId = availableGames.get(0);
        return loadId(sudokuId);
    }
    /**
     * Επιστρέφει το όνομα - id του αρχείου
     * @return Επιστρέφει το Id που αντιστοιχεί στο συγκεκριμένο Sudoku
     */

    public String getId(){
        return sudokuId;
    }
    private ArrayList<String> loadId(String id) throws FileNotFoundException, IOException, InvalidSudokuException{
        ArrayList<String> game;
        try (BufferedReader in = new BufferedReader( new FileReader((puzzlePath.concat(id)).concat(".pzl.sud")))) {
            game = new ArrayList<>();
            String line;
            while((line = in.readLine()) != null){
                if(line.length() != 9){
                    throw new InvalidSudokuException();
                }
                game.add(line);
            }
        }
        return game;
    }

        
}
