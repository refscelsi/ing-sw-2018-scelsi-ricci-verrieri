package it.polimi.ing.sw.view;

import it.polimi.ing.sw.App;
import it.polimi.ing.sw.model.Match;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NewMatchForm extends javax.swing.JFrame {

    private TableFrame tf;
    private boolean controllo=true;

    public NewMatchForm() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameMatchTF = new javax.swing.JTextField();
        playerNameTF = new javax.swing.JTextField();
        nuumPlayerCB = new javax.swing.JComboBox<>();
        nameMatch = new javax.swing.JLabel();
        nomePlayer1 = new javax.swing.JLabel();
        numplayers = new javax.swing.JLabel();
        annulla = new javax.swing.JButton();
        avviaMatch = new javax.swing.JButton();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tornaMenu();
            }
        };
        addWindowListener(exitListener);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setOpaque(true);

        nameMatchTF.setText("Nome partita");
        nameMatchTF.setForeground(new java.awt.Color(153, 153, 153));
        nameMatchTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameMatchTFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nameMatchTFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nameMatchTFMouseExited(evt);
            }
        });

        playerNameTF.setText("Nome Giocatore 1");
        playerNameTF.setForeground(new java.awt.Color(153, 153, 153));
        playerNameTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playerNameTFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playerNameTFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playerNameTFMouseExited(evt);
            }
        });

        nuumPlayerCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        nameMatch.setForeground(new java.awt.Color(51, 51, 51));
        nameMatch.setText("Nome partita");

        nomePlayer1.setForeground(new java.awt.Color(51, 51, 51));
        nomePlayer1.setText("Nome Player 1");

        numplayers.setForeground(new java.awt.Color(51, 51, 51));
        numplayers.setText("Numero giocatori");

        annulla.setText("Annulla");
        annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaActionPerformed(evt);
            }
        });

        avviaMatch.setText("Avvia partita");
        avviaMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avviaMatchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nomePlayer1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(nameMatch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(numplayers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameMatchTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(playerNameTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nuumPlayerCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(avviaMatch)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(annulla)))
                                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameMatchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameMatch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(playerNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nomePlayer1))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nuumPlayerCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numplayers))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(annulla, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(avviaMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/it/polimi/ing/sw/view/img/sagrada.png")));
        this.setTitle("Sagrada Boardgame - New Match");

        pack();
    }// </editor-fold>

    private void avviaMatchActionPerformed(ActionEvent evt) {

        //this.setVisible(false);
        Match match=new Match(nameMatchTF.getText(),nuumPlayerCB.getSelectedIndex()+1,nomePlayer1.getText());
        System.out.println(nuumPlayerCB.getSelectedIndex());
        
        tf=new TableFrame(match);
        
        tf.setVisible(true);
        this.setVisible(false);
        //match.startMatch();
        
        //nel caso ci siano ulteriori informazioni da passare  verranno inserite qui
    }

    private void annullaActionPerformed(java.awt.event.ActionEvent evt) {
        tornaMenu();
    }

    public void tornaMenu(){
        this.setVisible(false);
        App.menu.setVisible(true);
    }

    private void nameMatchTFMouseEntered(java.awt.event.MouseEvent evt) {
        if (controllo)
            nameMatchTF.setText("");
    }

    private void nameMatchTFMouseClicked(java.awt.event.MouseEvent evt) {
        controllo=false;
        nameMatchTF.setForeground(new java.awt.Color(0, 0, 0));
    }

    private void nameMatchTFMouseExited(java.awt.event.MouseEvent evt) {
        if (controllo)
            nameMatchTF.setText("Nome partita");
    }

    private void playerNameTFMouseEntered(java.awt.event.MouseEvent evt) {
        if (controllo)
            playerNameTF.setText("");
    }

    private void playerNameTFMouseClicked(java.awt.event.MouseEvent evt) {
        controllo=false;
        playerNameTF.setForeground(new java.awt.Color(0, 0, 0));
    }

    private void playerNameTFMouseExited(java.awt.event.MouseEvent evt) {
        if (controllo)
            playerNameTF.setText("Name Player 1");
    }

    // Variables declaration - do not modify
    private javax.swing.JButton annulla;
    private javax.swing.JButton avviaMatch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nomePlayer1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField playerNameTF;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel nameMatch;
    private javax.swing.JTextField nameMatchTF;
    private javax.swing.JLabel numplayers;
    private javax.swing.JComboBox<String> nuumPlayerCB;
    // End of variables declaration
}
