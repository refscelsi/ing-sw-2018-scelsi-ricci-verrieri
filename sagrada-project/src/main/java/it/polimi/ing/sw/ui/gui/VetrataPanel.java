package it.polimi.ing.sw.ui.gui;

public class VetrataPanel extends javax.swing.JPanel {

    private DiceGUI dices[][] = new DiceGUI[4][5];

    public VetrataPanel() {
        initComponents();

        setUpSchemeField();
    }

    public void setDices(DiceGUI[][] dices) {
        this.dices = dices;
    }

    public DiceGUI[][] getDices() {

        return dices;
    }

    private void setUpSchemeField() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                dices[i][j] = new DiceGUI(true);

                imageLabel.add(dices[i][j]);
                dices[i][j].setBounds(17 + i * (45 + 5), 30 + j * (45 + 5), 45, 45);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerNameLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(230, 270));
        setMinimumSize(new java.awt.Dimension(230, 270));
        setLayout(null);

        playerNameLabel.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 12)); // NOI18N
        playerNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        playerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerNameLabel.setText("ProvaProvaa");
        add(playerNameLabel);
        playerNameLabel.setBounds(60, 12, 110, 20);

        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/texture.jpg"))); // NOI18N
        imageLabel.setAlignmentY(0.0F);
        imageLabel.setMaximumSize(new java.awt.Dimension(230, 290));
        imageLabel.setMinimumSize(new java.awt.Dimension(230, 290));
        imageLabel.setPreferredSize(new java.awt.Dimension(230, 290));
        add(imageLabel);
        imageLabel.setBounds(0, 0, 230, 300);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel playerNameLabel;
    // End of variables declaration//GEN-END:variables
}
