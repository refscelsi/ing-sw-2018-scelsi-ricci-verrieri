package it.polimi.ing.sw.view;

import it.polimi.ing.sw.App;
import it.polimi.ing.sw.model.Scheme;

import java.awt.event.*;
import java.io.*;
import Progetto.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AddScheme extends javax.swing.JFrame implements FocusListener,MouseListener, KeyListener{


    private final JLabel box[][]=new JLabel[4][5];
    private final JLabel c1[][]=new JLabel[4][5];
    private final JLabel c2[][]=new JLabel[4][5];
    private final JLabel c3[][]=new JLabel[4][5];
    private final JLabel c4[][]=new JLabel[4][5];
    private final JLabel c5[][]=new JLabel[4][5];
    private final JLabel c6[][]=new JLabel[4][5];
    private int sfumatura[][]=new int[4][5];
    private int colore[][]=new int[4][5];
    private ArrayList<Scheme> schemi =new ArrayList<Scheme>();
    private boolean bc1[][]=new boolean[4][5];
    private boolean bc2[][]=new boolean[4][5];
    private boolean bc3[][]=new boolean[4][5];
    private boolean bc4[][]=new boolean[4][5];
    private boolean bc5[][]=new boolean[4][5];
    private boolean bc6[][]=new boolean[4][5];

    public AddScheme() {
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

                c1[i][j].setName("c1"+String.valueOf(nameBox));
                c2[i][j].setName("c2"+String.valueOf(nameBox));
                c3[i][j].setName("c3"+String.valueOf(nameBox));
                c4[i][j].setName("c4"+String.valueOf(nameBox));
                c5[i][j].setName("c5"+String.valueOf(nameBox));
                c6[i][j].setName("c6"+String.valueOf(nameBox));

                colore[i][j]=0;

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

                bc1[i][j]=false;
                bc2[i][j]=false;
                bc3[i][j]=false;
                bc4[i][j]=false;
                bc5[i][j]=false;
                bc6[i][j]=false;

                box[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        boxMouseClicked(evt);
                    }
                });

                c1[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        cMouseClicked(evt);
                    }
                });

                c2[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        cMouseClicked(evt);
                    }
                });

                c3[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        cMouseClicked(evt);
                    }
                });

                c4[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        cMouseClicked(evt);
                    }
                });

                c5[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        cMouseClicked(evt);
                    }
                });

                c6[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        elementoMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        elementoMouseExited(evt);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        cMouseClicked(evt);
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

        openFile();
        fillLista();
    }

    public void openFile(){
        String path = "Schemes";
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Schemes"));
            schemi = (ArrayList<Scheme>) ois.readObject();
        }
        catch(Exception e){
            //messaggio di errore
        }
    }

    public void fillLista(){
        int j = schemi.size();
        String nomi[] = null;
        for(int i =0;i<j;i++){
            nomi[i]=new String(String.valueOf(schemi.get(i).getId()));
        }
        DefaultListModel data = new DefaultListModel();
        for (int i=0; i < j; i++){
            data.add(i, nomi[i]);
        }
        lista= new JList(data);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
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
        jScrollPane3 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tornaMenu();
            }
        };
        addWindowListener(exitListener);

        p.setBackground(new java.awt.Color(204, 255, 204));
        p.setPreferredSize(new java.awt.Dimension(775, 400));

        jScrollPane1.setViewportView(IDSPanel);

        IDSchemaLabel.setText("ID Schema");

        DiffLabel.setText("Difficoltà");

        jScrollPane2.setViewportView(DiffPanel);

        pBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        pBox.setLayout(null);

        saveButton.setText("Salva");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Resetta");

        findButton.setText("Cerca");

        cancelButton.setText("Cancella");

        jButton1.setText("Salva come nuovo");

        jScrollPane3.setViewportView(lista);

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
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                        .addGroup(pLayout.createSequentialGroup()
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
                                                        .addComponent(jButton1)))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pBox, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
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
    }// </editor-fold>

    public void tornaMenu(){
        this.setVisible(false);
<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/view/AddScheme.java
        App.menu.setVisible(true);
=======
        Progetto.App.menu.setVisible(true);
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/AddScheme.java
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {
        saveStateActualScheme();

        ObjectOutputStream oss;
        try{
            oss = new ObjectOutputStream(new FileOutputStream("Schemes"));
            oss.writeObject(schemi );
            oss.close();
        }
        catch(Exception e){
            //messaggio di errore
        }
    }

    public void elementoMouseEntered(java.awt.event.MouseEvent evt){
        for (int i=0; i<4;i++){
            for(int j=0;j<5;j++){
                if (evt.getComponent().getName().equals(c1[i][j].getName())){
                    if (bc1[i][j]==false)
                        c1[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent().getName().equals(c2[i][j].getName())){
                    if (bc2[i][j]==false)
                        c2[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent().getName().equals(c3[i][j].getName())){
                    if (bc3[i][j]==false)
                        c3[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent().getName().equals(c4[i][j].getName())){
                    if (bc4[i][j]==false)
                        c4[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent().getName().equals(c5[i][j].getName())){
                    if (bc5[i][j]==false)
                        c5[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent().getName().equals(c6[i][j].getName())){
                    if (bc6[i][j]==false)
                        c6[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                //}
            }
        }
    }

    public void elementoMouseExited(java.awt.event.MouseEvent evt){
        for (int i=0; i<4;i++){
            for(int j=0;j<5;j++){
                if (evt.getComponent().getName().equals(c1[i][j].getName())){
                    if (bc1[i][j]==false)
                        c1[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent().getName().equals(c2[i][j].getName())){
                    if (bc2[i][j]==false)
                        c2[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent().getName().equals(c3[i][j].getName())){
                    if (bc3[i][j]==false)
                        c3[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent().getName().equals(c4[i][j].getName())){
                    if (bc4[i][j]==false)
                        c4[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent().getName().equals(c5[i][j].getName())){
                    if (bc5[i][j]==false)
                        c5[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent().getName().equals(c6[i][j].getName())){
                    if (bc6[i][j]==false)
                        c6[i][j].setBorder(null);
                    break;
                }
                //}
            }
        }
    }

    public void cMouseClicked(java.awt.event.MouseEvent evt){
        for (int i=0; i<4;i++){
            for(int j=0;j<5;j++){
                //JLabel temp=uncheck(i,j, evt);

                if (evt.getComponent().getName().equals(c1[i][j].getName())){
                    uncheck(i,j);
                    c1[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
                    colore[i][j]=1;
                    bc6[i][j]=true;
                    break;
                }
                if (evt.getComponent().getName().equals(c2[i][j].getName())){
                    uncheck(i,j);
                    c2[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
                    colore[i][j]=2;
                    bc2[i][j]=true;
                    break;
                }
                if (evt.getComponent().getName().equals(c3[i][j].getName())){
                    uncheck(i,j);
                    c3[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
                    colore[i][j]=3;
                    bc3[i][j]=true;
                    break;
                }
                if (evt.getComponent().getName().equals(c4[i][j].getName())){
                    uncheck(i,j);
                    c4[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
                    colore[i][j]=4;
                    bc4[i][j]=true;
                    break;
                }
                if (evt.getComponent().getName().equals(c5[i][j].getName())){
                    uncheck(i,j);
                    c5[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
                    colore[i][j]=5;
                    bc5[i][j]=true;
                    break;
                }
                if (evt.getComponent().getName().equals(c6[i][j].getName())){
                    uncheck(i,j);
                    c6[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
                    colore[i][j]=6;
                    bc6[i][j]=true;
                    break;
                }
            }
        }
    }

    public void uncheck(int i, int j){
        if(bc1[i][j]==true){
            c1[i][j].setBorder(null);
            bc1[i][j]=false;
        }if(bc2[i][j]==true){
            c2[i][j].setBorder(null);
            bc2[i][j]=false;
        }if(bc3[i][j]==true){
            c3[i][j].setBorder(null);
            bc3[i][j]=false;
        }if(bc4[i][j]==true){
            c4[i][j].setBorder(null);
            bc4[i][j]=false;
        }if(bc5[i][j]==true){
            c5[i][j].setBorder(null);
            bc5[i][j]=false;
        }if(bc6[i][j]==true){
            c6[i][j].setBorder(null);
            bc6[i][j]=false;
        }
    }

    public void saveStateActualScheme(){

<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/view/AddScheme.java
        it.polimi.ing.sw.model.Box boxes[][]= new it.polimi.ing.sw.model.Box[4][5];

        for (int i=0; i<4;i++){
            for(int j=0;j<5;j++){
                boxes[i][j]=new it.polimi.ing.sw.model.Box();
=======
        Progetto.Model.Box boxes[][]= new Progetto.Model.Box[4][5];

        for (int i=0; i<4;i++){
            for(int j=0;j<5;j++){
                boxes[i][j]=new Progetto.Model.Box();
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/AddScheme.java
                boxes[i][j].setShade(Integer.parseInt(box[i][j].getText()));
                boxes[i][j].setColor(toColor(colore[i][j]));
            }
        }
        //inserire controllo sull id e difficoltà che siano solo numeri
        Scheme schema = new Scheme(Integer.parseInt(IDSPanel.getText()),Integer.parseInt(DiffPanel.getText()),boxes);
        schemi.add(schema);
    }

<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/view/AddScheme.java
    public it.polimi.ing.sw.model.Color toColor(int i){
        switch(i){
            case 1: return it.polimi.ing.sw.model.Color.PURPLE;
            case 2: return it.polimi.ing.sw.model.Color.RED;
            case 3: return it.polimi.ing.sw.model.Color.BLUE;
            case 4: return it.polimi.ing.sw.model.Color.GREEN;
            case 5: return it.polimi.ing.sw.model.Color.YELLOW;
            case 6: return it.polimi.ing.sw.model.Color.WHITE;
            default: return it.polimi.ing.sw.model.Color.WHITE;
=======
    public Progetto.Model.Color toColor(int i){
        switch(i){
            case 1: return Progetto.Model.Color.PURPLE;
            case 2: return Progetto.Model.Color.RED;
            case 3: return Progetto.Model.Color.BLUE;
            case 4: return Progetto.Model.Color.GREEN;
            case 5: return Progetto.Model.Color.YELLOW;
            case 6: return Progetto.Model.Color.WHITE;
            default: return Progetto.Model.Color.WHITE;
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/AddScheme.java
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel DiffLabel;
    private javax.swing.JTextPane DiffPanel;
    private javax.swing.JTextPane IDSPanel;
    private javax.swing.JLabel IDSchemaLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton findButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> lista;
    private javax.swing.JPanel p;
    private javax.swing.JPanel pBox;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration

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
        try{
            for (int i=0;i<4;i++){
                for (int j=0;j<5;j++){
                    if (evt.getComponent().getName().equals(box[i][j].getName())){
                        if (sfumatura[i][j]==6){
                            sfumatura[i][j]=0;
                        }else{
                            sfumatura[i][j]++;
                        }
                        box[i][j].setText(String.valueOf(sfumatura[i][j]));
                    }
                }
            }
        }
        catch(Exception exc){Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,exc.getMessage());}
    }
}