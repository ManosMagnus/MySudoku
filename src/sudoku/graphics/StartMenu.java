package sudoku.graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sudoku.NamedPlayer;
import sudoku.Player;
import sudoku.Players;

/**
 * Γραφική διεπαφή για το κεντρίκο μενού
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class StartMenu extends javax.swing.JFrame {

    private Player player;
    private Players players;
    private StartGame startGameMenu;

    /**
     * Σχεδίαση γραφικής διεπαφής
     * @param player Ο παίκτης  
     * @param players Ο διαχειριστής παικτών
     */
    public StartMenu(Player player,Players players) {
        this.players = players;
        this.player = player;
        initComponents();
        name.setText(player.getName());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(GameGraph.DO_NOTHING_ON_CLOSE);
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

        jPanel1 = new javax.swing.JPanel();
        startGame = new javax.swing.JButton();
        statistics = new javax.swing.JButton();
        options = new javax.swing.JButton();
        help = new javax.swing.JButton();
        javax.swing.JButton exit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Start Menu");
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout(5, 0));

        startGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/StartGame.gif"))); // NOI18N
        startGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameActionPerformed(evt);
            }
        });
        jPanel1.add(startGame);

        statistics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/Statistics.gif"))); // NOI18N
        statistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsActionPerformed(evt);
            }
        });
        jPanel1.add(statistics);

        options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/Options.gif"))); // NOI18N
        options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsActionPerformed(evt);
            }
        });
        jPanel1.add(options);

        help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/Help.gif"))); // NOI18N
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        jPanel1.add(help);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/Exit.gif"))); // NOI18N
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel1.add(exit);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 200, 230));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jPanel2.setName(""); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameLabel.setText("Player:");
        jPanel2.add(nameLabel);

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        name.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(name);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/MainMenu4.gif"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 500, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        backToWindows();
    }//GEN-LAST:event_exitActionPerformed

    private void startGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameActionPerformed
        this.setVisible(false);
        startGameMenu = new StartGame(player,this,players);
        startGameMenu.setVisible(true);
    }//GEN-LAST:event_startGameActionPerformed

    private void optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsActionPerformed
        new Options(this,true,players,player).setVisible(true);
    }//GEN-LAST:event_optionsActionPerformed

    private void statisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticsActionPerformed

        if(player.hasStats()){
            NamedPlayer nPlayer = (NamedPlayer) player;
            Object[] options = {"Ok","Clear"};
            int option = JOptionPane.showOptionDialog(this,player.getStats(),
            "Statistics",JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);
            if(option == 1){
                try {
                    players.clearStatsFor(nPlayer);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,"Problem to clear the statistics","Problem",0);
                    Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,player.getStats(),"Statistics",0);
        }
    }//GEN-LAST:event_statisticsActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
         JOptionPane.showMessageDialog(this,"This option isn't available yet! Sorry","Help",0);
    }//GEN-LAST:event_helpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton help;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton options;
    private javax.swing.JButton startGame;
    private javax.swing.JButton statistics;
    // End of variables declaration//GEN-END:variables
}
