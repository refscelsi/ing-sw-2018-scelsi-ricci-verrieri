package progetto.view;

import javax.swing.*;
import java.awt.*;

public class Menu extends javax.swing.JFrame {

    private NewMatchForm nmf;
    private AddScheme as;
    private static final String IMAGE_PATH="progetto/view/";

    private ImageIcon icon;
    private Image scaledImage;

    public Menu() {
        System.out.println("diocane");
        initComponents();
        //setIcons();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        addMapLabel = new javax.swing.JLabel();
        multiplayerLabel = new javax.swing.JLabel();
        settingsLabel = new javax.swing.JLabel();
        loadMatchLabel = new javax.swing.JLabel();
        newMatchLabel = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(null);

        menuPanel.setBackground(new java.awt.Color(204, 153, 0));
        menuPanel.setLayout(null);

        addMapLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPanel.add(addMapLabel);
        addMapLabel.setBounds(215, 212, 53, 53);

        //multiplayerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("progetto/view/globe.png"))); // NOI18N
        multiplayerLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPanel.add(multiplayerLabel);
        multiplayerLabel.setBounds(373, 368, 53, 53);
        menuPanel.add(settingsLabel);
        settingsLabel.setBounds(215, 525, 53, 53);
        menuPanel.add(loadMatchLabel);
        loadMatchLabel.setBounds(59, 368, 53, 53);

        newMatchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("progetto/view/img/home.png"))); // NOI18N
        newMatchLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPanel.add(newMatchLabel);
        newMatchLabel.setBounds(191, 344, 100, 100);

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("progetto/view/menu.jpg"))); // NOI18N
        menuPanel.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 480, 630);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void setIcons(){
        ImageIcon icon =new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"home.png")) ;
        Image scaledImage = icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);

        newMatchLabel.setIcon(icon);
        newMatchLabel.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"settings.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        settingsLabel.setIcon(icon);
        settingsLabel.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"globe.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        multiplayerLabel.setIcon(icon);
        multiplayerLabel.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"find.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        loadMatchLabel.setIcon(icon);
        loadMatchLabel.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"add.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        addMapLabel.setIcon(icon);
        addMapLabel.repaint();

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMAGE_PATH+"sagrada.png")));
        this.setTitle("Sagrada Boardgame");
    }

    private void newMatchLabelMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
        nmf= new NewMatchForm();
        nmf.setVisible(true);
    }

    private void addMapLAbelMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
        as=new AddScheme();
        as.setVisible(true);
    }

    private void loadMatchLabelMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
    }

    private void settingsLabelMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
    }

    private void multiplayerLabelMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
    }

    public void scompareMenu(){
        this.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addMapLabel;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel loadMatchLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel multiplayerLabel;
    private javax.swing.JLabel newMatchLabel;
    private javax.swing.JLabel settingsLabel;
    // End of variables declaration//GEN-END:variables
}
