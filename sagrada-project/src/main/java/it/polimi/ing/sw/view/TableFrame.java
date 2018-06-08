package it.polimi.ing.sw.view;

import it.polimi.ing.sw.model.Match;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TableFrame extends javax.swing.JFrame {

    private Match match;

    public TableFrame(Match match) {
        this.match=match;
        initComponents();
        setLocationRelativeTo(null);        
        setIcons();
    }
    
    public void setIcons(){        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/it/polimi/ing/sw/view/img/sagrada.png")));
        this.setTitle("Sagrada Boardgame");
=======
        setLocationRelativeTo(null); 
        //ObjectiveCardView oc = new ObjectiveCardView("01");
        //add(oc);
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/TableFrame.java
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        background = new javax.swing.JLabel();

    //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tornaMenu();
            }
        };
        addWindowListener(exitListener);
        background.setBackground(new java.awt.Color(153, 128, 92));
        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Progetto/View/img/sagrada.png"))); // NOI18N
        background.setOpaque(true);

<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/view/TableFrame.java
        jPanel1.setBackground(new java.awt.Color(153, 102, 0));
        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/polimi/ing/sw/view/img/sagrada.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 950, 700);
=======
        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/TableFrame.java

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void tornaMenu(){
        this.setVisible(false);
        Progetto.App.menu.setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel p1;
    // End of variables declaration//GEN-END:variables
}
