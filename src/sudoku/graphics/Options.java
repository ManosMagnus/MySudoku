package sudoku.graphics;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import sudoku.Player;
import sudoku.Players;

/**
 * Γραφική διεπάφη για την αφαίρεση παίκτη
 * @author Manos
 * @version 1.0.0
 * @see Players
 * @see Player
 */
public class Options extends javax.swing.JDialog {
    private Players players;
    private Player player;
    /**
     * Δημιουργεί την γραφική διαπαφή
     * @param parent Το παράθυρο οπού το καλεί
     * @param modal {@code true} αν θέλουμε να παγώσουμε το παράθυρο
     * @param players Ο διαχειριστής των παικτών
     * @param player Ο τρέχον παίκτης
     */
    public Options(java.awt.Frame parent, boolean modal, Players players, Player player) {
        super(parent, modal);
        this.player = player;
        this.players = players;
        initComponents();
        setList();
        this.setLocationRelativeTo(null);
    }
    private void setList(){
        DefaultListModel<String> model;
        model = new DefaultListModel<String>();

        for(String player : players.getPlayersList()){
           model.addElement(player);
        }
        playersList.setModel(model);
        playersList.setSelectedIndex(0);        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        playersList = new javax.swing.JList();
        remove = new javax.swing.JButton();
        ok = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Options");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(playersList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 21, 162, -1));

        remove.setText("Remove Player");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        getContentPane().add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 71, 124, -1));

        ok.setText("Ok");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });
        getContentPane().add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 201, 127, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sudoku/graphics/icons/LogIn2.gif"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        this.dispose();
    }//GEN-LAST:event_okActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        int index = playersList.getSelectedIndex();
        String name = (String) playersList.getSelectedValue();
        if(name.equals(player.getName())){
            JOptionPane.showMessageDialog(this,"You can't remove current player","Remove Player",0);
            return;
        }
        Object[] options = {"Yes","No"};
        int option = JOptionPane.showOptionDialog(this,"Are you sure you want to remove Player:" + name,
                "Remove Player",JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
        if(option == 0){
            try {
                players.removePlayer(name);
                DefaultListModel model = (DefaultListModel) playersList.getModel();
                model.remove(index);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Some problem to remove this player","Remove Player",0);
                Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_removeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ok;
    private javax.swing.JList playersList;
    private javax.swing.JButton remove;
    // End of variables declaration//GEN-END:variables
}
