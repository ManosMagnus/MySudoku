package sudoku.graphics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import sudoku.Incognito;
import sudoku.Player;
import sudoku.Players;
import sudoku.TheGame;

/**
 * Γραφική διεπαφή για την επιλογή {@link Player} ή την δημιουργία νέου
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class LogIn extends javax.swing.JFrame {

    private Players players;

    /**
     * Αρχικοποιεί την λίστα για τους {@link Player} και στήνετε η γραφική διεπαφή
     * @param players Διαχείριση παικτών για την δημιουργία νέου και την  {@link Player}
     * @throws IOException  Πρόβλημα κατά την επικοινωνία για την αποθήκευση, ενημέρωση ή δημιουργία {@link Player}
     * @throws FileNotFoundException Το αρχείου του {@link Player} δεν βρέθηκε
     * @throws ClassNotFoundException Προβλημα με την κλάση {@link Player}
     */
    public LogIn(Players players) throws IOException, FileNotFoundException, ClassNotFoundException {
        this.players = players;
        initComponents();
        setList(players);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        lastGame();
    }
    private void lastGame(){
        Player nPlayer;
        try {
            nPlayer = players.loadLastPlayer();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showConfirmDialog(this, "Problem To load last games!", "Problem!", 0);
            return;
        }
        if(nPlayer != null){
            Object[] options = {"Yes","No"};
            int option = JOptionPane.showOptionDialog(this,"Player: " + nPlayer.getName() + " had an uncomplete Sudoku do you want load?",
            "LoadGame",JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);
            if(option == 0){
                StartMenu startMenu= new StartMenu(nPlayer,players);
                new TheGame(nPlayer.getSavedGame(),players,nPlayer,startMenu);
                this.dispose();
            }else{
                try {
                    players.clearLastPlayer(nPlayer);
                } catch (IOException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    private void setList(Players players){
        DefaultListModel<String> model;
        model = new DefaultListModel<String>();

        for(String player : players.getPlayersList()){
           model.addElement(player);
        }    
        playerList.setModel(model);
        playerList.setSelectedIndex(0);
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        playerList = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        newPlayer = new javax.swing.JButton();
        incognito = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log In");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Available Players");

        playerList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        playerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playerListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(playerList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 11, -1, -1));

        jPanel2.setOpaque(false);

        newPlayer.setText("New Player");
        newPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPlayerActionPerformed(evt);
            }
        });

        incognito.setText("Incognito");
        incognito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incognitoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(incognito, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(newPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPlayer)
                    .addComponent(incognito))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 223, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/LogIn2.gif"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 340, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void incognitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incognitoActionPerformed
        this.setVisible(false);
        new StartMenu(new Incognito(),players).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_incognitoActionPerformed

    private void newPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPlayerActionPerformed
        boolean flag = false;
        Player activePlayer = null;
        do{
            String name = JOptionPane.showInputDialog(this,"Give a nickname:");
            if(name == null || name.equals("")){
                return;
            }
            try {
                activePlayer = players.addPlayer(name);
            } catch (IOException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showConfirmDialog(this, "Problem to save player please try again!", "Problem!", 0);
                flag =  true;
            }
            if(activePlayer == null){
                JOptionPane.showMessageDialog(this,"This name exists!");
                flag = true;
            }
        }while(flag);
        this.setVisible(false);
        new StartMenu(activePlayer,players).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_newPlayerActionPerformed

    private void playerListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerListMouseClicked
        JList list = (JList)evt.getSource();
        Player activePlayer;
        if (evt.getClickCount() > 1) {
            int index = list.locationToIndex(evt.getPoint());
            try {
                activePlayer = players.loadPlayer(index);
                if(activePlayer == null){
                    JOptionPane.showConfirmDialog(this, "Problem to load player please try again!", "Problem!", 0); 
                    return;
                }
            } catch (IOException | ClassNotFoundException ex) { 
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);   
                return; 
            }
            this.setVisible(false);
            new StartMenu(activePlayer,players).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_playerListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton incognito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton newPlayer;
    private javax.swing.JList playerList;
    // End of variables declaration//GEN-END:variables
}
