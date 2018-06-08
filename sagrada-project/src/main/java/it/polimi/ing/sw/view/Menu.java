package it.polimi.ing.sw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Menu extends javax.swing.JFrame {


    private NewMatchForm nmf;
    private AddScheme as;
    private static final String IMAGE_PATH="img/";

    public Menu() {
        initComponents();
        setIcons();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuLabel = new javax.swing.JPanel();
        addSchemeLabel = new javax.swing.JLabel();
        singleplayerLabel = new javax.swing.JLabel();
        loadMatchLabel = new javax.swing.JLabel();
        settingsLabel = new javax.swing.JLabel();
        newMatchLabel = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        WindowListener exitListener = new WindowAdapter( ) {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int confirm = JOptionPane.showOptionDialog(
                        null,
                        "Are you shure you want to leave the game?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null /*new javax.swing.ImageIcon(getClass().getResource("/img/sagrada.png"))*/,
                        null,
                        null);
                if (confirm == 0){
                    System.exit(0);
                }else{
                    setVisible(true);
                }
            }
        };
        addWindowListener(exitListener);
        setResizable(false);

        menuLabel.setBackground(new java.awt.Color(102, 255, 51));
        menuLabel.setLayout(null);

        addSchemeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSchemeLabelMouseClicked(evt);
            }
        });
        menuLabel.add(addSchemeLabel);
        addSchemeLabel.setBounds(215, 211, 53, 53);

        singleplayerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                singleplayerLabelMouseClicked(evt);
            }
        });
        menuLabel.add(singleplayerLabel);
        singleplayerLabel.setBounds(370, 368, 53, 53);

        loadMatchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadMatchLabelMouseClicked(evt);
            }
        });
        menuLabel.add(loadMatchLabel);
        loadMatchLabel.setBounds(50, 368, 53, 53);

        settingsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsLabelMouseClicked(evt);
            }
        });
        menuLabel.add(settingsLabel);
        settingsLabel.setBounds(215, 524, 53, 53);

        newMatchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newMatchLabelMouseClicked(evt);
            }
        });
        menuLabel.add(newMatchLabel);
        newMatchLabel.setBounds(190, 343, 102, 102);

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu.jpg"))); // NOI18N
        menuLabel.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 480, 630);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
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
        singleplayerLabel.setIcon(icon);
        singleplayerLabel.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"find.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        loadMatchLabel.setIcon(icon);
        loadMatchLabel.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"add.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        addSchemeLabel.setIcon(icon);
        addSchemeLabel.repaint();

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMAGE_PATH+"sagrada.png")));
        this.setTitle("Sagrada Boardgame");
    }

    private void newMatchLabelMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
        nmf= new NewMatchForm();
        nmf.setVisible(true);
    }

    private void addSchemeLabelMouseClicked(java.awt.event.MouseEvent evt) {
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

    private void singleplayerLabelMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
    }

    public void scompareMenu(){
        this.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addSchemeLabel;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel loadMatchLabel;
    private javax.swing.JPanel menuLabel;
    private javax.swing.JLabel newMatchLabel;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JLabel singleplayerLabel;
    // End of variables declaration//GEN-END:variables
}