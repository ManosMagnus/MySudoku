package sudoku;

import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sudoku.graphics.GameGraph;
import sudoku.graphics.NumberPad;
import sudoku.graphics.StartMenu;
import sudoku.logic.Classic;
import sudoku.logic.CpuPlayer;
import sudoku.logic.DuiDoku;
import sudoku.logic.Helper;
import sudoku.logic.Hyper;
import sudoku.logic.Sudoku;


/**
 * Κλάση η οποία αναλαμβάνει την σύνδεση γραφικών και λογικής του παιχνίδιου
 * @author Manos Kirtas
 * @version 1.0.0
 * @see Helper
 * @see CpuPlayer
 * @see MyConverter
 */
public class TheGame {
    
    private GameType gameType;
    private boolean help, letters;
    private Player player;
    private GameGraph gameGraph;
    private StartMenu startMenu;
    private Sudoku game;
    private NumberPad numberPad;
    private CpuPlayer cpuPlayer;
    private boolean displayHelp;
    private Helper helper;
    private MyConverter myConverter;
    private Players players;
    
    /**
     * Δημιουργία νέου {@link Sudoku} και {@link GameGraph} με τους παραμέτρους που ορίστικαν
     * @param gameType {@link GameType} του παιχνιδίου που θα δημιουργηθεί
     * @param help Για την εμφάνιση της βοήθειας
     * @param letters Ορισμός γραμμάτων για worddoku
     * @param player O {@link Player} του συγκεκριμένου παιχνιδίου
     * @param startMenu Το παραθύρου του αρχικού μενού
     * @param players Για την διαχείριση τών παικτών 
     */
    public TheGame(GameType gameType, boolean help, boolean letters,Player player,StartMenu startMenu, Players players){
        this.players = players;
        this.player = player;
        this.startMenu = startMenu;
        this.gameType = gameType;
        this.help =  help;
        this.displayHelp = help;
        this.letters = letters;     
        if(letters){
            myConverter = MyConverter.getInstance();
        }
        if(gameType.equals(GameType.CLASSIC)){
            try {
                ManageSudoku mSudoku = new ManageSudoku(gameType);
                game = new sudoku.logic.Classic(mSudoku.loadSudoku(player),help,letters,mSudoku.getId());
                if(help){
                    helper = new Helper(game, this);
                }
                numberPad = new NumberPad(gameGraph,true,letters,10);
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(startMenu,"Problem to load sudoku please try again!", "Problem!", 0);
                Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
                gameGraph.dispose();
                startMenu.setVisible(true);
            } catch (InvalidSudokuException ex) {
                JOptionPane.showConfirmDialog(startMenu,"Invalid puzzle please check the sodoku inputs!", "Problem!", 0);
                Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
                gameGraph.dispose();
                startMenu.setVisible(true);
            }
        }else if(gameType.equals(GameType.HYPER)){
            try {
                ManageSudoku mSudoku = new ManageSudoku(gameType);
                game = new sudoku.logic.Hyper(mSudoku.loadSudoku(player),help,letters,mSudoku.getId());
                if(help){
                    helper = new Helper(game, this);
                }
                numberPad = new NumberPad(gameGraph,true,letters,10);
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(startMenu,"Problem to load sudoku please try again!", "Problem!", 0);
                Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
                gameGraph.dispose();
                startMenu.setVisible(true);
            } catch (InvalidSudokuException ex) {
                JOptionPane.showConfirmDialog(startMenu,"Invalid puzzle please check the sodoku inputs!", "Problem!", 0);
                Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
                gameGraph.dispose();
                startMenu.setVisible(true);
            }
        }else{
            game = new DuiDoku();
            helper = new Helper(game, this);
            cpuPlayer = new CpuPlayer(game,helper);
            numberPad = new NumberPad(gameGraph,true,letters,5);
            this.help =  true;
        }
        gameGraph = new GameGraph(this,help,true);
        gameGraph.setVisible(true);
    }

    /**
     * Συνέχεια {@link Sudoku} και νεου {@link GameGraph} με τους παραμέτρους που οριστήκαν  
     * @param game Το παιχνίδι που θα αναπαρασταθεί  
     * @param players Για την διαχείριση τών παικτών  
     * @param player O {@link Player} του συγκεκριμένου παιχνιδίου
     * @param startMenu Το παράθυρο του αρχικού μενού
     */
    public TheGame(Sudoku game, Players players, Player player, StartMenu startMenu){
        this.players = players;
        this.player = player;
        this.startMenu = startMenu;
        this.letters = game.lettersIsOn();
        if(letters){
            myConverter = MyConverter.getInstance();
        }
        this.game = game;
        this.displayHelp = this.help = game.helpIsOn();
        
        if(game instanceof Hyper){
            this.gameType = GameType.HYPER;
        }else if(game instanceof Classic){
            this.gameType = GameType.CLASSIC;
        }
        gameGraph = new GameGraph(this,help,false);
        if(help){
            helper = new Helper(game, this);
        }
        numberPad = new NumberPad(gameGraph,true,letters,10);
        gameGraph.setVisible(true);
    }
    
    /**
     * Επιστρέφει αν στο συγκεκριμενο κέλι υπαρχεί τιμη που δεν μπορεί να αλλαχθει απο τον χρήστη
     * @param x Ο αριθμός σείρας τοθ {@link Sudoku}
     * @param y Ο αριθμος στήλης του {@link Sudoku}
     * @return Επιστρέφει αν μπορεί να αλαχθει το κελί ή οχι
     */
    public boolean isDefault(int x, int y){
        return game.isDefault(x,y);
    }

    /**
     * Έξοδος από το παράθυρο του παιχνιδίου και εμφάνιση αρχικού μενού
     */
    public void exitNoSave(){
        try {
            players.uncompleteGame(player, game);
        } catch (IOException ex) {
            Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
        }finally{ 
            startMenu.setVisible(true);
            gameGraph.dispose();
        }

    }

    /**
     * Τελος εφαρμογής ερώτηση για την αποθήκευση του παιχνιδίου
     */
    public void backToWindows(){ 
        String ObjButtons[] = {"Yes","No"};
        int PromptResult = JOptionPane.showOptionDialog(null, 
                "Do you want to save your Sudoku", "Exit",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons,ObjButtons[0]);
        try{
            if(PromptResult==0)
            {
                players.uncompleteGame(player, game);
            }else{
                players.clearLastPlayer(player);
            }
        } catch (IOException ex) {
            Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            System.exit(1);
        }
    }
     /**
     * Ειστρέφει τον τύπο του παιχνιδίου
     * @return  Τον τύπο του παιχνιδίου
     */
    public GameType getTheGameType(){
        return gameType;
    }
    /**
     * Εμφάνιση βοηθείας σε συγκεκριμένο κελί του {@link GameGraph}
     * @param x Ο αριθμός σειράς κελίου του  {@link GameGraph}
     * @param y Ο αριθμός στήλης κελιού του  {@link GameGraph}
     * @param help Ορισμός της βοηθείας απο τον  {@link Helper}
     */
    public void setHelp(int x, int y, HashSet<Integer> help){
          if(this.displayHelp){
              if(game.get(x,y) == 0){
                  if(letters){
                      gameGraph.setCellText(x,y,toHtmlTextLetters(myConverter.intToCharSet(game.getAvailables(x, y))));
                  }else{
                      gameGraph.setCellText(x,y,toHtmlText(game.getAvailables(x, y)));
                  }
              }
          }
    }

    /**
     * Επιστροφή βοηθείας για το κελί στην συγκεκριμένη γραμμή και στήλη
     * @param x Ο αριθμός σειράς του {@link Sudoku}
     * @param y Ο αριθμός στήλης  του  {@link Sudoku}
     * @return Επιστρέφει την βοηθεία (Σε μορφή html για καλύτερη αναπαράσταση)
     */
    public String getHelp(int x, int y){ //Xreisomopoite apo to gameGraph
        if(letters){
            return (toHtmlTextLetters(myConverter.intToCharSet(game.getAvailables(x, y))));
        }else{
            return (toHtmlText(game.getAvailables(x, y)));
        }
    }
    
     private String toHtmlTextLetters(HashSet<String> availables){
        StringBuilder startOfHtml = new StringBuilder();
        startOfHtml.append("<html> <b style=\"color:#707070 ; font-style:italic; font-size:11  \">");
        StringBuilder endOfHtml = new StringBuilder();
        endOfHtml.append("</span> <html>");
        int i = 1;
        for(String num : availables){
            startOfHtml.append(num).append(" ");
            if(i % 3 == 0){
                startOfHtml.append("<br>");
            }
            i++;
        }
        return startOfHtml.append(endOfHtml).toString();
    }
    
    private String toHtmlText(HashSet<Integer> availables){
        StringBuilder startOfHtml = new StringBuilder();
        startOfHtml.append("<html> <b style=\"color:#707070; font-style:italic; font-size:11 \">");
        StringBuilder endOfHtml = new StringBuilder();
        endOfHtml.append("</span> <html>");
        int i = 1;
        for(int num : availables){
            startOfHtml.append(num).append(" ");
            if(i % 3 == 0){
                startOfHtml.append("<br>");
            }
            i++;
        }
        return startOfHtml.append(endOfHtml).toString();
    }

        private void duiDoku(int x, int y, String num){
            if(!num.equals("")){
                int[] array = cpuPlayer.nextMove(x, y);
                if(array == null || game.isComplete()){
                    endOfGame(true);
                    return;
                }else{
                    if(letters){
                        gameGraph.setCellOpponents(array[0],array[1],myConverter.intToChar(array[2]));
                    }else{
                        gameGraph.setCellOpponents(array[0],array[1],String.valueOf(array[2]));
                    }
                }
                if(cpuPlayer.checkFreeCells()){
                    endOfGame(false);
                }
            }            
        }
        
        private boolean withLetters(int x, int y, String num){
            int prev = game.get(x, y);
            if(game.setCell(x, y,num.equals("") ? 0 : myConverter.charToInt(num))){
                if(help){
                    helper.getHelp(x,y,num.equals("") ? 0 : myConverter.charToInt(num),prev);
                    if(!num.equals("")){
                        gameGraph.setCellText(x, y, num);
                    }
                }else{
                    gameGraph.setCellText(x, y, num);
                }
                return true;
            }
            return false;
        }
        
        private boolean withNumber(int x, int y, String num){
            int prev = game.get(x, y);
            if(game.setCell(x, y,num.equals("") ? 0 : Integer.parseInt(num))){
                if(help){
                    helper.getHelp(x,y,num.equals("") ? 0 : Integer.parseInt(num),prev);
                    if(!num.equals("")){
                        gameGraph.setCellText(x, y, num);
                    }
                }else{
                    gameGraph.setCellText(x, y, num);
                }
                if(game.isComplete()){
                    endOfGame();
                }
                return true;
            }
            return false;
        }

    /**
     * Η κίνηση με την εμφάνιση του {@link NumberPad} έλεγχος της κινήσεις 
     * Καθώς και κίνηση αντίπαλου παίκτη στην περίπτωση του {@link DuiDoku}
     * @param x Ο αριθμός γραμμής κελιού 
     * @param y Ο αριθμός στήλης κελιού
     */
    public void playersMove(int x, int y){
        if(gameType.equals(GameType.DUIDOKU) && game.get(x, y) != 0 ){
            return;
        }
        numberPad.setTitle("Cell[" + x + "][" + y +"]" );
        String num = numberPad.action();
        if(num != null){
            boolean bo;
            if(letters){
                bo = withLetters(x,y,num);
            }else{
                bo = withNumber(x,y,num);
            }
            gameGraph.beCerfulSetVisible(!bo);
            if(gameType.equals(GameType.DUIDOKU) && bo){
                duiDoku(x,y,num);
            }
        }
    }
    private void endOfGame(){
        JOptionPane.showMessageDialog(gameGraph,"Complete the Puzzle");
        try {
            players.completeGame(player,game.getId(),gameType);
        } catch (IOException ex) {
            Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        startMenu.setVisible(true);
        gameGraph.dispose();
    }
    
    /**
     * Επιστρέφει την τιμή του κελιού 
     * @param x Ο αριθμός γραμμής του {@link Sudoku}
     * @param y Ο αριθμός στήλης του {@link Sudoku}
     * @return Την τιμή στο συγκεκριμένο κελί
     */
    public String getCellsNum(int x, int y){
        String num;
        if(letters){
            num = (game.get(x,y)  == 0? "" : myConverter.intToChar(game.get(x,y)));//<--gameGraph
        }else{
            num = (game.get(x,y)   == 0? "" : String.valueOf(game.get(x,y) ));//<--gameGraph

        }
        return num;
        
    }
    
    private void endOfGame(boolean win){
         if(win){
            JOptionPane.showMessageDialog(gameGraph,"You win Cpu Player!");
        }else{
            JOptionPane.showMessageDialog(gameGraph,"You lose! Cpu Player Wins!");
        }
        try {
            players.increaseGames(player,win);
        } catch (IOException ex) {
            Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        gameGraph.setVisible(false);
        gameGraph.dispose();
        startMenu.setVisible(true);
    }

}
