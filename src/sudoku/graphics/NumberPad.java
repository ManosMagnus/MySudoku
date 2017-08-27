package sudoku.graphics;

import sudoku.MyConverter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




/**
 * Γραφική διεπαφή για τήν αναπαράσταση τών επιλογών τού παίκτη και την εισαγωγή αριθμού στο Sudoku 
 * @author Manos
 * @version 1.0.0
 * @see GameGraph
 * @see MyConverter
 */
public class NumberPad extends javax.swing.JDialog {


    private javax.swing.JButton num[];
    private String number;
    private MyConverter myConverter;
    private int buttonsNum;

    /**
     * Στήσιμο γραφικής διεπαφής 
     * @param parent To frame το οποίο θέλουμε να "παγώσουμε" κατά την εμφάνιση του παραθύρου
     * @param modal {@code true} Αν θέλουμε να παγώσουμε το παραθύρου
     * @param letters {@code true} Αν το Sudoku έχει την μορφή  DuiDoku
     * @param buttonsNum Τον αριθμό των κουμπιών που θα χρειαστούμε (Ενα είναι πάντα η κενή είσοδος)
     */
    public NumberPad(java.awt.Frame parent, boolean modal,boolean letters,int buttonsNum) {
        super(parent, modal);
        if(letters){
            myConverter = MyConverter.getInstance();
        }
        this.buttonsNum = buttonsNum;
        initComponents();
        jPanel1.setLayout(new java.awt.GridLayout((int)Math.sqrt(buttonsNum-1), (int)Math.sqrt(buttonsNum-1), 10, 10));
        setButtons(letters);
        this.setSize(300, 300);
    }
    
    private void setButtons(boolean letters){
        num = new javax.swing.JButton[10];
        for(int i=1; i<buttonsNum; i++){
           num[i] = new javax.swing.JButton();
           num[i].setFont(new java.awt.Font("Felix Titling", 0, 18));
           if(letters){
                num[i].setText(myConverter.intToChar(i));
           }else{
                num[i].setText(String.valueOf(i));
           }
           num[i].addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent ae) {
                  number = ae.getActionCommand();
                  setVisible(false);
               }
           });
           jPanel1.add(num[i]);
        }
        num[0] = new javax.swing.JButton();
        num[0].setFont(new java.awt.Font("Felix Titling", 0, 18));
        num[0].setText("Null");
        num[0].setSize(100, 100);
        num[0].addActionListener(new ActionListener() {

                  @Override
                  public void actionPerformed(ActionEvent ae) {
                     number = "";
                     setVisible(false);
                  }
              });
        jPanel2.add(num[0]);
    }

    /**
     * Εμφανίζει την γραφική διεπαφή προκυμμένου ο χρήστης να επιλέξει τον αριθμό που θέλει
     * @return Επιστέφει τον αριθμό που εξέλεξε ο χρήστης σε μορφή {@link String}
     */
    public String action(){
        number = null;
        this.setVisible(true);
        return number;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables


}
