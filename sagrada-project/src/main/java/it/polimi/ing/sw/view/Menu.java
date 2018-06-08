package it.polimi.ing.sw.view;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;

public class Menu extends javax.swing.JFrame implements Observer {

    private NewMatchForm nmf;
    private AddScheme as;
<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/view/Menu.java
    private static final String IMAGE_PATH="it/polimi/ing/sw/view/";

    private ImageIcon icon;
    private Image scaledImage;

=======
    
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/Menu.java
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

<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/view/Menu.java
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

        //newMatchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"home.png"))); // NOI18N
        newMatchLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPanel.add(newMatchLabel);
        newMatchLabel.setBounds(191, 344, 100, 100);

        //backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"menu.jpg"))); // NOI18N
        menuPanel.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 480, 630);
=======
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
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/Menu.java

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

    @Override
    public void updateChoiseScheme(ArrayList<SchemeCard> scheme, Player player) {

    }

    @Override
    public void updateNewRound(Player firstPlayer, DraftPool draftPool) {

    }

    @Override
    public void updateRanking(ArrayList<Player> ranking) {

    }

    @Override
    public void updateNextPlayer(Player player) {

    }

    @Override
    public void updatePublicObjectivesChoosen(ArrayList<ObjectiveCard> publicObjectives) {

    }

    @Override
    public void updateUsedDice(boolean ok) {

    }

    @Override
    public void showMessage(String message) {

    }
    // End of variables declaration//GEN-END:variables
}
