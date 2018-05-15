package Progetto.View;

import Progetto.Controller.*;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class Menu extends javax.swing.JFrame {

    private NewMatchForm nmf;
    private AddScheme as;
    
    public Menu() {
        initComponents();
        setLocationRelativeTo(null);        
        setIcons();
    }
    
    public void setIcons(){
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Progetto/View/img/home.png"));
        Image scaledImage = icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        
        newmatch.setIcon(icon);
        newmatch.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource("/Progetto/View/img/settings.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        settings.setIcon(icon);
        settings.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource("/Progetto/View/img/globe.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        multiplayer.setIcon(icon);
        multiplayer.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource("/Progetto/View/img/find.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        loadmatch.setIcon(icon);
        loadmatch.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource("/Progetto/View/img/add.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        addmap.setIcon(icon);
        addmap.repaint();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Progetto/View/img/sagrada.png")));
        this.setTitle("Sagrada Boardgame");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        addmap = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        newmatch = new javax.swing.JLabel();
        loadmatch = new javax.swing.JLabel();
        multiplayer = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure to Close Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    System.exit(0);
                }
            }
        };
        addWindowListener(exitListener);

        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(480, 658));
        setPreferredSize(new java.awt.Dimension(480, 658));
        setResizable(false);

        p1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        p1.setLayout(null);

        addmap.setBackground(new java.awt.Color(255, 102, 0));
        addmap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addmap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addmapMouseClicked(evt);
            }
        });
        p1.add(addmap);
        addmap.setBounds(214, 210, 53, 53);

        settings.setBackground(new java.awt.Color(255, 102, 0));
        settings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsMouseClicked(evt);
            }
        });
        p1.add(settings);
        settings.setBounds(214, 523, 53, 53);

        newmatch.setBackground(new java.awt.Color(255, 102, 0));
        newmatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newmatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newmatchMouseClicked(evt);
            }
        });
        p1.add(newmatch);
        newmatch.setBounds(191, 344, 100, 100);

        loadmatch.setBackground(new java.awt.Color(255, 102, 0));
        loadmatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadmatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadmatchMouseClicked(evt);
            }
        });
        p1.add(loadmatch);
        loadmatch.setBounds(58, 367, 53, 53);

        multiplayer.setBackground(new java.awt.Color(255, 102, 0));
        multiplayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        multiplayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                multiplayerMouseClicked(evt);
            }
        });
        p1.add(multiplayer);
        multiplayer.setBounds(370, 367, 53, 53);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Progetto/View/img/menu.jpg"))); //\Progetto\View\img
        //background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Progetto/View/img/menu.jpg"))); // NOI18N
        p1.add(background);
        background.setBounds(0, 0, 480, 630);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void newmatchMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
        nmf= new NewMatchForm();
        nmf.setVisible(true);
    }

    private void addmapMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
        as=new AddScheme();
        as.setVisible(true);
    }

    private void loadmatchMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
    }

    private void settingsMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
    }

    private void multiplayerMouseClicked(java.awt.event.MouseEvent evt) {
        scompareMenu();
    }

    public void scompareMenu(){
        this.setVisible(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addmap;
    private javax.swing.JLabel background;
    private javax.swing.JLabel loadmatch;
    private javax.swing.JLabel multiplayer;
    private javax.swing.JLabel newmatch;
    private javax.swing.JPanel p1;
    private javax.swing.JLabel settings;
    // End of variables declaration//GEN-END:variables
}
