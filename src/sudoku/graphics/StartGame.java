package sudoku.graphics;

import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sudoku.GameType;
import sudoku.Player;
import sudoku.Players;
import sudoku.TheGame;

/**
 * Γραφική διαπαφή για την επιλογή τύπου παιχνιδιού
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class StartGame extends javax.swing.JFrame {

    private GameType gameType;
    private boolean help;
    private boolean letters;
    private Player player;
    private StartMenu startMenu;
    private Players players;

    /**
     * Γραφική διεπαφή για την επιλογή {@link GameType} καθώς και την επιλογή για WordDoku ή οχι
     * @param player Ο παίκτης  
     * @param startMenu Η γραφική διεπαφη για τον κεντρίκο μενου
     * @param players Ο διαχειρηστής τών παικτών
     */
    public StartGame(Player player, StartMenu startMenu, Players players) {
        this.players = players;
        this.player = player;
        help = false;
        letters = false;
        this.startMenu = startMenu;
        gameType = GameType.CLASSIC;
        initComponents();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            {
                backToWindows();
            }
        });
    }
    
    private void backToWindows(){
        if(player.hasSavedGame()){
            Object[] options = {"Yes","No"};
            int option = JOptionPane.showOptionDialog(this,"You have uncomplete Sudoku do you want to save it?",
                    "Save Game",JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(option == 1){
                try {
                    players.clearLastPlayer(player);
                } catch (IOException ex) {
                    Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        System.exit(1);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        helpBox = new javax.swing.JCheckBox();
        lettersBox = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        classic = new javax.swing.JRadioButton();
        hyper = new javax.swing.JRadioButton();
        duiDoku = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cancel = new javax.swing.JButton();
        startGame = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Start Game");
        setPreferredSize(new java.awt.Dimension(501, 382));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        helpBox.setText("Help");
        helpBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                helpBoxItemStateChanged(evt);
            }
        });
        getContentPane().add(helpBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 72, 60, -1));

        lettersBox.setText("Letters");
        lettersBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lettersBoxItemStateChanged(evt);
            }
        });
        getContentPane().add(lettersBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 164, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(classic);
        classic.setSelected(true);
        classic.setText("Classic Sudoku");
        classic.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                classicItemStateChanged(evt);
            }
        });

        buttonGroup1.add(hyper);
        hyper.setText("Hyper Sudoku");
        hyper.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hyperItemStateChanged(evt);
            }
        });

        buttonGroup1.add(duiDoku);
        duiDoku.setText("DuiDoku");
        duiDoku.setToolTipText("");
        duiDoku.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                duiDokuItemStateChanged(evt);
            }
        });

        jLabel1.setText("Game Type: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(duiDoku, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(classic))
                    .addComponent(hyper)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(classic)
                .addGap(18, 18, 18)
                .addComponent(hyper)
                .addGap(18, 18, 18)
                .addComponent(duiDoku)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 50, -1, -1));

        jPanel2.setOpaque(false);

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        startGame.setText("Start Game");
        startGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(startGame, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startGame, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 231, 481, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/StartMenuBkg.gif"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 510, 390));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void classicItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_classicItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            gameType = GameType.CLASSIC;
        }
    }//GEN-LAST:event_classicItemStateChanged

    private void hyperItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hyperItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            gameType = GameType.HYPER;
        }   
    }//GEN-LAST:event_hyperItemStateChanged

    private void duiDokuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_duiDokuItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            gameType = GameType.DUIDOKU;
        }

    }//GEN-LAST:event_duiDokuItemStateChanged

    private void helpBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_helpBoxItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            help = true;
        }if(evt.getStateChange() == ItemEvent.DESELECTED){
            help = false;
        }
    }//GEN-LAST:event_helpBoxItemStateChanged

    private void lettersBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lettersBoxItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            letters = true;
        }if(evt.getStateChange() == ItemEvent.DESELECTED){
            letters = false;
        }
    }//GEN-LAST:event_lettersBoxItemStateChanged

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.setVisible(false);
        startMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void startGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameActionPerformed
        try {
            players.clearLastPlayer(player);
            new TheGame(gameType,help,letters,player,startMenu,players);
        } catch (IOException ex) {
            Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_startGameActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancel;
    private javax.swing.JRadioButton classic;
    private javax.swing.JRadioButton duiDoku;
    private javax.swing.JCheckBox helpBox;
    private javax.swing.JRadioButton hyper;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox lettersBox;
    private javax.swing.JButton startGame;
    // End of variables declaration//GEN-END:variables
}
