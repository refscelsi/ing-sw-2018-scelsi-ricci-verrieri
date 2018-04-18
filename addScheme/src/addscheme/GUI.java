/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addscheme;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Riccardo
 */
public class GUI extends javax.swing.JFrame implements FocusListener,MouseListener, KeyListener{

    /**
     * Creates new form GUI
     */
    
    private JLabel box[][]=new JLabel[4][5];
    private JLabel c1[][]=new JLabel[4][5];
    private JLabel c2[][]=new JLabel[4][5];
    private JLabel c3[][]=new JLabel[4][5];
    private JLabel c4[][]=new JLabel[4][5];
    private JLabel c5[][]=new JLabel[4][5];
    private JLabel c6[][]=new JLabel[4][5];
    private int sfumatura[][]=new int[4][5];
    private int colore[][]=new int[4][5];
    
    public GUI() {
        initComponents();
                
        pBox.setLayout(null);
        int nameBox=0;
        
        int x=20;
        int y=10;
        int dist=10;
        int h=30;
        int k=120;
        
        int h1=45;
        int k1=20;
        
        for (int i=0; i<4;i++){            
            for(int j=0;j<5;j++){
                box[i][j]=new JLabel();
                c1[i][j]=new JLabel();
                c2[i][j]=new JLabel();
                c3[i][j]=new JLabel();
                c4[i][j]=new JLabel();
                c5[i][j]=new JLabel();
                c6[i][j]=new JLabel();
                
                pBox.add(box[i][j]);
                
                box[i][j].setName(String.valueOf(nameBox));
                box[i][j].setText("0");
                
                c1[i][j].setName(String.valueOf(nameBox));
                c2[i][j].setName(String.valueOf(nameBox));
                c3[i][j].setName(String.valueOf(nameBox));
                c4[i][j].setName(String.valueOf(nameBox));
                c5[i][j].setName(String.valueOf(nameBox));
                c6[i][j].setName(String.valueOf(nameBox));
                
                nameBox++;
                
                pBox.add(c1[i][j]);
                pBox.add(c2[i][j]);
                pBox.add(c3[i][j]);
                pBox.add(c4[i][j]);
                pBox.add(c5[i][j]);
                pBox.add(c6[i][j]);
                
                box[i][j].setOpaque(true);
                
                box[i][j].setBackground(Color.white);
                
                c1[i][j].setOpaque(true);
                c2[i][j].setOpaque(true);
                c3[i][j].setOpaque(true);
                c4[i][j].setOpaque(true);
                c5[i][j].setOpaque(true);
                c6[i][j].setOpaque(true);
                
                c1[i][j].setBackground(Color.magenta);
                c2[i][j].setBackground(Color.red);
                c3[i][j].setBackground(Color.blue);
                c4[i][j].setBackground(Color.green);
                c5[i][j].setBackground(Color.yellow);
                c6[i][j].setBackground(Color.white);                          
                
                box[i][j].setBounds(x+(k*j)+(dist*j), y+(75*i)+(dist*i), k, h);
                c1[i][j].setBounds(x+(k*j)+(dist*j), y+h+(75*i)+(dist*i), k1, h1);
                c2[i][j].setBounds(x+(k*j)+(dist*j)+k1, y+h+(75*i)+(dist*i), k1, h1);
                c3[i][j].setBounds(x+(k*j)+(dist*j)+(k1*2), y+h+(75*i)+(dist*i), k1, h1);
                c4[i][j].setBounds(x+(k*j)+(dist*j)+(k1*3), y+h+(75*i)+(dist*i), k1, h1);
                c5[i][j].setBounds(x+(k*j)+(dist*j)+(k1*4), y+h+(75*i)+(dist*i), k1, h1);
                c6[i][j].setBounds(x+(k*j)+(dist*j)+(k1*5), y+h+(75*i)+(dist*i), k1, h1);
                //120x75
                
                box[i][j].addMouseListener(new java.awt.event.MouseAdapter() {            
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {

                        //elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        //elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        boxMouseClicked(evt);
                    }
                });
                
                c1[i][j].addMouseListener(new java.awt.event.MouseAdapter() {            
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {

                        //elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        //elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        //boxMouseClicked(evt);
                    }
                });
                
                c2[i][j].addMouseListener(new java.awt.event.MouseAdapter() {            
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {

                        //elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        //elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        //boxMouseClicked(evt);
                    }
                });
                
                c3[i][j].addMouseListener(new java.awt.event.MouseAdapter() {            
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {

                        //elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        //elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        //boxMouseClicked(evt);
                    }
                });
                
                c4[i][j].addMouseListener(new java.awt.event.MouseAdapter() {            
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {

                        //elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        //elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        //boxMouseClicked(evt);
                    }
                });
                
                c5[i][j].addMouseListener(new java.awt.event.MouseAdapter() {            
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {

                        //elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        //elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        //boxMouseClicked(evt);
                    }
                });
                
                c6[i][j].addMouseListener(new java.awt.event.MouseAdapter() {            
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {

                        //elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        //elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        //boxMouseClicked(evt);
                    }
                });
            }
        }
        
        for (int i=0; i<4;i++){
            for(int j=0;j<5;j++){
               sfumatura[i][j]=0;
               colore[i][j]=6;
            }
        }      
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        IDSPanel = new javax.swing.JTextPane();
        IDSchemaLabel = new javax.swing.JLabel();
        DiffLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DiffPanel = new javax.swing.JTextPane();
        pBox = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        findButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        p.setBackground(new java.awt.Color(204, 255, 204));
        p.setPreferredSize(new java.awt.Dimension(775, 400));

        jScrollPane1.setViewportView(IDSPanel);

        IDSchemaLabel.setText("ID Schema");

        DiffLabel.setText("Difficoltà");

        jScrollPane2.setViewportView(DiffPanel);

        pBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        pBox.setLayout(null);

        saveButton.setText("Salva");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Resetta");

        findButton.setText("Cerca");

        cancelButton.setText("Cancella");

        jButton1.setText("Salva come nuovo");

        javax.swing.GroupLayout pLayout = new javax.swing.GroupLayout(p);
        p.setLayout(pLayout);
        pLayout.setHorizontalGroup(
            pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pLayout.createSequentialGroup()
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IDSchemaLabel))
                            .addGroup(pLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DiffLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(findButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pLayout.setVerticalGroup(
            pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(IDSchemaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(findButton)
                        .addComponent(resetButton)
                        .addComponent(saveButton))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DiffLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelButton))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pBox, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DiffLabel;
    private javax.swing.JTextPane DiffPanel;
    private javax.swing.JTextPane IDSPanel;
    private javax.swing.JLabel IDSchemaLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton findButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel p;
    private javax.swing.JPanel pBox;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void focusGained(FocusEvent fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void boxMouseClicked(MouseEvent evt){
        System.out.println("Added");
        
        try{          
            for (int i=0;i<4;i++){
                for (int j=0;j<5;j++){
                    if (evt.getComponent().getName().equals(box[i][j].getName())){
                        int temp;                        
                        temp=Integer.valueOf(box[i][j].getText());
                        
                        if (temp==6){
                            temp=0;
                        }else{
                            temp++;
                        }
                        box[i][j].setText(String.valueOf(temp));
                    }
                }            
            }
        }
        catch(Exception exc){Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,exc.getMessage());} 
    }  
}
