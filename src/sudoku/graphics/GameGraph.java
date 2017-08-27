package sudoku.graphics;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.JLabel;
import sudoku.GameType;
import sudoku.TheGame;




/**
 * Γραφική αναπαράσταση του Sudoku
 * @author Manos Kirtas
 * @version 1.0.0
 * @see TheGame
 */
public class GameGraph extends javax.swing.JFrame {

    private javax.swing.JPanel box[][]; 
    private javax.swing.JLabel cell[][];
    private HashMap<JLabel,int[]> connect;
    private int limit;
    private TheGame theGame;
    private boolean displayHelp;
    private boolean isNewGame;
    
    /**
     *
     * Αναπαριστά γραφικά το Sudoku αναλογά με το {@link GameType} καθώς και την βοηθεία
     * Αρχικοποίηση κελιών και γραφική σχεδιαστή
     * @param theGame Αναλαμβάνει την επικοινωνία γραφικών και λογικής
     * @param displayHelp Αν είναι {@code true} εμφανίζει την βοήθεια σε κάθε κελί
     * @param newGame {@code true} στην περίπτωση οπού το Sudoku είναι καινούριου
     */
    public GameGraph(final TheGame theGame, boolean displayHelp, boolean newGame){
        this.isNewGame = newGame;
        this.displayHelp =  displayHelp;
        this.theGame = theGame;
        connect = new HashMap<>();
        limit = 9;
        if(theGame.getTheGameType().equals(GameType.DUIDOKU)){
            limit = 4;
        }
        initComponents();
        classic.setLayout(new java.awt.GridLayout((int)Math.sqrt(limit), (int)Math.sqrt(limit)));
        setCells(limit);
        if(theGame.getTheGameType().equals(GameType.HYPER)){
            hyper();  
        }
        this.setLocationRelativeTo(null);
        beCerful.setVisible(false);
        this.setDefaultCloseOperation(GameGraph.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            {
                theGame.backToWindows();
            }
        });
    }
    
    /**
     *Εμφανίζει μήνυμα σε περίπτωση λανθασμένης εισαγωγής
     * @param vis {@code true} άμα θέλουμε να εμφανιστεί το μήνυμα
     */
    public void beCerfulSetVisible(boolean vis){
        beCerful.setVisible(vis);
    }

    
    /**
     * Εμφανίζει στο συγκεκριμένο κελί μια συμβολοσειρά
     * @param x Ο αριθμός της γραμμής του κελιού
     * @param y  Ο αριθμός της στήλης του κελιού
     * @param text Η συμβολοσειρά που θέλουμε να εμφανιστεί στο συγκεκριμένο κελί
     */
    public void setCellText(int x, int y, String text){
        if(x < limit && y < limit){
            cell[x][y].setText(text);
        }
    }
    private void setCells(int limit){
        int sqrtLimit = (int) Math.sqrt(limit);
        box = new javax.swing.JPanel[sqrtLimit][sqrtLimit];
        for(int i=0; i<sqrtLimit; i++){
            for(int j=0; j<sqrtLimit; j++){
                box[i][j] = new javax.swing.JPanel();
                box[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
                box[i][j].setLayout(new java.awt.GridLayout(sqrtLimit , sqrtLimit));
                classic.add(box[i][j]);
            }
        }
        cell = new javax.swing.JLabel[limit][limit];
        String num;
        for(int i=0; i<limit; i++){
            for(int j=0; j<limit; j++){
                cell[i][j] =new javax.swing.JLabel();
                cell[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                cell[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                cell[i][j].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                cell[i][j].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                cell[i][j].setOpaque(true);
                connect.put(cell[i][j],new int[]{i,j});
                num = theGame.getCellsNum(i, j);
                cell[i][j].setText(num);
                if(isNewGame){
                    if(!num.equals("")){
                        cell[i][j].setForeground(Color.blue);
                    }
                    else{
                        if(displayHelp){
                            cell[i][j].setText(theGame.getHelp(i, j));
                        }
                        cell[i][j].addMouseListener(new MyListener());
                    }
                }else{
                    if(theGame.isDefault(i, j)){
                        cell[i][j].setForeground(Color.blue);
                    }else{
                        if(!num.equals("")){
                            cell[i][j].setText(num);
                        }else{
                            if(displayHelp){
                                cell[i][j].setText(theGame.getHelp(i, j));
                            }
                        }
                        cell[i][j].addMouseListener(new MyListener());
                    }
                }
                cell[i][j].setFont(new Font("Serif", Font.BOLD, 20));
                box[squareNumber(i,j,sqrtLimit)/sqrtLimit ][squareNumber(i,j,sqrtLimit)%sqrtLimit].add(cell[i][j]);
            }   
        }

    }

    private  int squareNumber(int row, int col, int sqrtLimit) {
           return col / sqrtLimit + row / sqrtLimit * sqrtLimit;
    }
    
    /**
     * Στην περίπτωση του DuiDoku εμφανίζει την κίνηση του αντιπάλου
     * @param x Ο αριθμός της γραμμής του κελιού
     * @param y  Ο αριθμός της στήλης του κελιού
     * @param num Η συμβολοσειρά πού αντίστοιχη στην κίνηση του αντιπάλου
     */
    public void setCellOpponents(int x, int y, String num){
        if(x < limit && y < limit){
            cell[x][y].setText(num);
            cell[x][y].setForeground(Color.red);
        }
    }

    private void hyper(){
        for(int i=1; i<4; i++){
            for(int j=1; j<4; j++){
                cell[i][j].setBackground(new java.awt.Color(145,172,255));
                cell[i][j].setOpaque(true);
                
                cell[4+i][4+j].setBackground(new java.awt.Color(145,172,255));
                cell[4+i][4+j].setOpaque(true);
                
                cell[4+i][j].setBackground(new java.awt.Color(145,172,255));
                cell[4+i][j].setOpaque(true);
                
                cell[i][4+j].setBackground(new java.awt.Color(145,172,255));
                cell[i][4+j].setOpaque(true);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        classic = new javax.swing.JPanel();
        beCerful = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        exit = new javax.swing.JMenu();
        exitNoSave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Game");
        setMinimumSize(new java.awt.Dimension(614, 549));

        javax.swing.GroupLayout classicLayout = new javax.swing.GroupLayout(classic);
        classic.setLayout(classicLayout);
        classicLayout.setHorizontalGroup(
            classicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );
        classicLayout.setVerticalGroup(
            classicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );

        beCerful.setForeground(new java.awt.Color(255, 0, 0));
        beCerful.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        beCerful.setText("Oups! Be careful");

        exit.setText("Exit");

        exitNoSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitNoSave.setText("Back to StartMenu");
        exitNoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitNoSaveActionPerformed(evt);
            }
        });
        exit.add(exitNoSave);

        jMenuBar1.add(exit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beCerful, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(beCerful, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitNoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitNoSaveActionPerformed
        theGame.exitNoSave();
    }//GEN-LAST:event_exitNoSaveActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beCerful;
    private javax.swing.JPanel classic;
    private javax.swing.JMenu exit;
    private javax.swing.JMenuItem exitNoSave;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
 
    private class MyListener extends MouseAdapter{        
        @Override
        public void mouseClicked(MouseEvent e){
            if (e.getClickCount() > 1) {
                JLabel jLabel = (JLabel)(e.getComponent());
                Color prevColor = jLabel.getBackground();
                jLabel.setBackground(Color.green);
                int p[] = connect.get(jLabel);
                theGame.playersMove(p[0], p[1]);
                jLabel.setBackground(prevColor);
            }
        }
    }

}
                  
