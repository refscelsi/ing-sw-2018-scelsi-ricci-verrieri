package it.polimi.ing.sw.ui.gui.advanced;

import it.polimi.ing.sw.App;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.ui.gui.SchemeListFileConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ing.sw.model.Color.*;

public class AddScheme extends javax.swing.JFrame {

    private final JLabel box[][] = new JLabel[4][5];
    private final JLabel c1[][] = new JLabel[4][5];
    private final JLabel c2[][] = new JLabel[4][5];
    private final JLabel c3[][] = new JLabel[4][5];
    private final JLabel c4[][] = new JLabel[4][5];
    private final JLabel c5[][] = new JLabel[4][5];
    private final JLabel c6[][] = new JLabel[4][5];
    private int sfumatura[][] = new int[4][5];
    private int colore[][] = new int[4][5];
    private ArrayList<Scheme> schemeArrayList;
    private boolean bc1[][] = new boolean[4][5];
    private boolean bc2[][] = new boolean[4][5];
    private boolean bc3[][] = new boolean[4][5];
    private boolean bc4[][] = new boolean[4][5];
    private boolean bc5[][] = new boolean[4][5];
    private boolean bc6[][] = new boolean[4][5];


    private static final String IMAGE_PATH = "/img/";

    public AddScheme() {
        initComponents( );
        setLocationRelativeTo(null);
        setIcons( );
        setBoxesModules( );
        openFile( );
        fillLista( );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addSchemePanel = new javax.swing.JPanel();
        pBox = new javax.swing.JPanel();
        difficultiesTextField = new javax.swing.JTextField();
        idSchemeTextField = new javax.swing.JTextField();
        diffLabel = new javax.swing.JLabel();
        idSchemeLAbel = new javax.swing.JLabel();
        findButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        separator2 = new javax.swing.JSeparator();
        saveButton = new javax.swing.JButton();
        saveAsNewButton = new javax.swing.JButton();
        schemeList = new javax.swing.JScrollPane();
        schemeInnerList = new javax.swing.JList<>();
        separator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addSchemePanel.setBackground(new java.awt.Color(204, 204, 204));

        pBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        javax.swing.GroupLayout pBoxLayout = new javax.swing.GroupLayout(pBox);
        pBox.setLayout(pBoxLayout);
        pBoxLayout.setHorizontalGroup(
                pBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        pBoxLayout.setVerticalGroup(
                pBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 356, Short.MAX_VALUE)
        );

        diffLabel.setText("Difficulties");

        idSchemeLAbel.setText("ID Scheme");

        findButton.setText("Find");

        deleteButton.setText("Delete");

        resetButton.setText("Reset");

        saveButton.setText("Save");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        saveAsNewButton.setText("Save as New");
        saveAsNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsNewButtonActionPerformed(evt);
            }
        });

        schemeInnerList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        schemeList.setViewportView(schemeInnerList);

        javax.swing.GroupLayout addSchemePanelLayout = new javax.swing.GroupLayout(addSchemePanel);
        addSchemePanel.setLayout(addSchemePanelLayout);
        addSchemePanelLayout.setHorizontalGroup(
                addSchemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addSchemePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(addSchemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(separator2)
                                        .addComponent(pBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(addSchemePanelLayout.createSequentialGroup()
                                                .addGroup(addSchemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(addSchemePanelLayout.createSequentialGroup()
                                                                .addComponent(difficultiesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(diffLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(addSchemePanelLayout.createSequentialGroup()
                                                                .addComponent(idSchemeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(idSchemeLAbel)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(addSchemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(addSchemePanelLayout.createSequentialGroup()
                                                                .addComponent(findButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(resetButton)))
                                                .addGap(204, 204, 204)
                                                .addGroup(addSchemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(saveAsNewButton, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                                        .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(schemeList)
                                        .addComponent(separator1))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addSchemePanelLayout.setVerticalGroup(
                addSchemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addSchemePanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(addSchemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idSchemeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idSchemeLAbel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(findButton)
                                        .addComponent(resetButton)
                                        .addComponent(saveButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addSchemePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(difficultiesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(diffLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(deleteButton)
                                        .addComponent(saveAsNewButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(schemeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addSchemePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addSchemePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveAsNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsNewButtonActionPerformed

        //schemeArrayList.add()

        saveStateActualScheme( );

        save();
    }//GEN-LAST:event_saveAsNewButtonActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels( )) {
                if ("Nimbus".equals(info.getName( ))) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName( ));
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable( ) {
            public void run() {
                new AddScheme( ).setVisible(true);
            }
        });
    }

    //<editor-fold defaultstate="collapsed" desc=" openFile Method ">
    public void openFile() {
        try {
            SchemeListFileConverter schemeListFileConverter = new SchemeListFileConverter( );
            schemeArrayList = schemeListFileConverter.readFromFile( );

            System.out.println(schemeArrayList);
        } catch (Exception e) {
            //messaggio di errore
            System.out.println("Eh no");
        }
    }
    //</editor-fold>

    public void fillLista() {
        System.out.println(schemeArrayList.size());
        Scheme firstScheme = schemeArrayList.get(schemeArrayList.size()-1);
        idSchemeTextField.setText(String.valueOf(firstScheme.getId( )));
        difficultiesTextField.setText(String.valueOf(firstScheme.getDifficulty( )));

        it.polimi.ing.sw.model.Box boxes[][] = firstScheme.getBoxes( );
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                getColorBoxToFill(boxes[i][j].getColor( ), i, j);
                box[i][j].setText(String.valueOf(boxes[i][j].getShade( )));
                sfumatura[i][j] = boxes[i][j].getShade( );
            }
        }
    }

    private void getColorBoxToFill(it.polimi.ing.sw.model.Color color, int i, int j) {
        int value=color.getValue( );

        //System.out.println(RED.getValue() + " + " + value);
        if (RED == toColor(value)){
            c1BorderClicked(i, j);
        }
        if (BLUE == toColor(value)){
            c2BorderClicked(i, j);
        }
        if (GREEN == toColor(value)){
            c3BorderClicked(i, j);
        }
        if (YELLOW == toColor(value)){
            c4BorderClicked(i, j);
        }
        if (PURPLE == toColor(value)){
            c5BorderClicked(i, j);
        }
        if (WHITE == toColor(value)){
            c6BorderClicked(i, j);
        }
    }


    //<editor-fold defaultstate="collapsed" desc=" setBoxesModules Method ">
    private void setBoxesModules() {
        int nameBox = 0;

        int x = 7;
        int y = 10;
        int dist = 10;
        int h = 30;
        int k = 120;
        int h1 = 45;
        int k1 = 20;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                box[i][j] = new JLabel( );
                c1[i][j] = new JLabel( );
                c2[i][j] = new JLabel( );
                c3[i][j] = new JLabel( );
                c4[i][j] = new JLabel( );
                c5[i][j] = new JLabel( );
                c6[i][j] = new JLabel( );

                pBox.add(box[i][j]);

                box[i][j].setName(String.valueOf(nameBox));
                box[i][j].setText("0");

                c1[i][j].setName("c1" + String.valueOf(nameBox));
                c2[i][j].setName("c2" + String.valueOf(nameBox));
                c3[i][j].setName("c3" + String.valueOf(nameBox));
                c4[i][j].setName("c4" + String.valueOf(nameBox));
                c5[i][j].setName("c5" + String.valueOf(nameBox));
                c6[i][j].setName("c6" + String.valueOf(nameBox));

                colore[i][j] = 0;

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

                box[i][j].setBounds(x + (k * j) + (dist * j), y + (75 * i) + (dist * i), k, h);
                c1[i][j].setBounds(x + (k * j) + (dist * j), y + h + (75 * i) + (dist * i), k1, h1);
                c2[i][j].setBounds(x + (k * j) + (dist * j) + k1, y + h + (75 * i) + (dist * i), k1, h1);
                c3[i][j].setBounds(x + (k * j) + (dist * j) + (k1 * 2), y + h + (75 * i) + (dist * i), k1, h1);
                c4[i][j].setBounds(x + (k * j) + (dist * j) + (k1 * 3), y + h + (75 * i) + (dist * i), k1, h1);
                c5[i][j].setBounds(x + (k * j) + (dist * j) + (k1 * 4), y + h + (75 * i) + (dist * i), k1, h1);
                c6[i][j].setBounds(x + (k * j) + (dist * j) + (k1 * 5), y + h + (75 * i) + (dist * i), k1, h1);
                //120x75

                c6[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

                bc1[i][j] = false;
                bc2[i][j] = false;
                bc3[i][j] = false;
                bc4[i][j] = false;
                bc5[i][j] = false;
                bc6[i][j] = true;

                box[i][j].addMouseListener(new java.awt.event.MouseAdapter( ) {
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

                c1[i][j].addMouseListener(new java.awt.event.MouseAdapter( ) {
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

                c2[i][j].addMouseListener(new java.awt.event.MouseAdapter( ) {
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

                c3[i][j].addMouseListener(new java.awt.event.MouseAdapter( ) {
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

                c4[i][j].addMouseListener(new java.awt.event.MouseAdapter( ) {
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

                c5[i][j].addMouseListener(new java.awt.event.MouseAdapter( ) {
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

                c6[i][j].addMouseListener(new java.awt.event.MouseAdapter( ) {
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

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                sfumatura[i][j] = 0;
                colore[i][j] = 6;
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" setIcons Method ">
    private void setIcons() {
        ImageIcon icon;
        Image scaledImage;
        //icon.setImage(scaledImage);

        setIconImage(Toolkit.getDefaultToolkit( ).getImage(getClass( ).getResource(IMAGE_PATH + "sagrada.png")));
        this.setTitle("Sagrada Boardgame");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" tornaMenu Method ">
    public void tornaMenu() {
        this.setVisible(false);
        App.menu.setVisible(true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" saveButtonMouseClicked Method ">
    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {
        saveStateActualScheme( );
        System.out.println("Ci siamo");
        //ObjectOutputStream oss;
        save();
    }
    //</editor-fold>

    private void save(){
        try {
            System.out.println("ancora meglio");

            SchemeListFileConverter schemeListFileConverter = new SchemeListFileConverter( );

            schemeListFileConverter.writeToFile(schemeArrayList);
        } catch (Exception e) {
            //messaggio di errore
        }
    }

    //<editor-fold defaultstate="collapsed" desc=" elementoMouseEntered Method ">
    public void elementoMouseEntered(java.awt.event.MouseEvent evt) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (evt.getComponent( ).getName( ).equals(c1[i][j].getName( ))) {
                    if (bc1[i][j] == false)
                        c1[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c2[i][j].getName( ))) {
                    if (bc2[i][j] == false)
                        c2[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c3[i][j].getName( ))) {
                    if (bc3[i][j] == false)
                        c3[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c4[i][j].getName( ))) {
                    if (bc4[i][j] == false)
                        c4[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c5[i][j].getName( ))) {
                    if (bc5[i][j] == false)
                        c5[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c6[i][j].getName( ))) {
                    if (bc6[i][j] == false)
                        c6[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 2));
                    break;
                }
                //}
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" elementoMouseExited Method ">
    public void elementoMouseExited(java.awt.event.MouseEvent evt) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (evt.getComponent( ).getName( ).equals(c1[i][j].getName( ))) {
                    if (bc1[i][j] == false)
                        c1[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c2[i][j].getName( ))) {
                    if (bc2[i][j] == false)
                        c2[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c3[i][j].getName( ))) {
                    if (bc3[i][j] == false)
                        c3[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c4[i][j].getName( ))) {
                    if (bc4[i][j] == false)
                        c4[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c5[i][j].getName( ))) {
                    if (bc5[i][j] == false)
                        c5[i][j].setBorder(null);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c6[i][j].getName( ))) {
                    if (bc6[i][j] == false)
                        c6[i][j].setBorder(null);
                    break;
                }
                //}
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" cMouseClicked Method ">
    public void cMouseClicked(java.awt.event.MouseEvent evt) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                //JLabel temp=uncheck(i,j, evt);
                if (evt.getComponent( ).getName( ).equals(c1[i][j].getName( ))) {
                    c1BorderClicked(i, j);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c2[i][j].getName( ))) {
                    c2BorderClicked(i, j);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c3[i][j].getName( ))) {
                    c3BorderClicked(i, j);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c4[i][j].getName( ))) {
                    c4BorderClicked(i, j);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c5[i][j].getName( ))) {
                    c5BorderClicked(i, j);
                    break;
                }
                if (evt.getComponent( ).getName( ).equals(c6[i][j].getName( ))) {
                    c6BorderClicked(i, j);
                    break;
                }
            }
        }
    }//</editor-fold>

    private void c1BorderClicked(int i, int j) {
        uncheck(i, j);
        c1[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        colore[i][j] = 1;
        bc1[i][j] = true;
    }

    private void c2BorderClicked(int i, int j) {
        uncheck(i, j);
        c2[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        colore[i][j] = 2;
        bc2[i][j] = true;
    }

    private void c3BorderClicked(int i, int j) {
        uncheck(i, j);
        c3[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        colore[i][j] = 3;
        bc3[i][j] = true;
    }

    private void c4BorderClicked(int i, int j) {
        uncheck(i, j);
        c4[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        colore[i][j] = 4;
        bc4[i][j] = true;
    }

    private void c5BorderClicked(int i, int j) {
        uncheck(i, j);
        c5[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        colore[i][j] = 5;
        bc5[i][j] = true;
    }

    private void c6BorderClicked(int i, int j) {
        uncheck(i, j);
        c6[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        colore[i][j] = 6;
        bc6[i][j] = true;
    }


    //<editor-fold defaultstate="collapsed" desc=" boxMouseClicked Method ">
    public void boxMouseClicked(MouseEvent evt) {

        //TODO use lambdas
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    if (evt.getComponent( ).getName( ).equals(box[i][j].getName( ))) {
                        if (sfumatura[i][j] == 6) {
                            sfumatura[i][j] = 0;
                        } else {
                            sfumatura[i][j]++;
                        }
                        box[i][j].setText(String.valueOf(sfumatura[i][j]));
                    }
                }
            }
        } catch (Exception exc) {
            Logger.getLogger(this.getClass( ).getName( )).log(Level.SEVERE, exc.getMessage( ));
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" uncheck Method ">
    public void uncheck(int i, int j) {
        if (bc1[i][j] == true) {
            c1[i][j].setBorder(null);
            bc1[i][j] = false;
        }
        if (bc2[i][j] == true) {
            c2[i][j].setBorder(null);
            bc2[i][j] = false;
        }
        if (bc3[i][j] == true) {
            c3[i][j].setBorder(null);
            bc3[i][j] = false;
        }
        if (bc4[i][j] == true) {
            c4[i][j].setBorder(null);
            bc4[i][j] = false;
        }
        if (bc5[i][j] == true) {
            c5[i][j].setBorder(null);
            bc5[i][j] = false;
        }
        if (bc6[i][j] == true) {
            c6[i][j].setBorder(null);
            bc6[i][j] = false;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" saveStateActualScheme Method ">
    public void saveStateActualScheme() {

        it.polimi.ing.sw.model.Box boxes[][] = new it.polimi.ing.sw.model.Box[4][5];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                boxes[i][j] = new it.polimi.ing.sw.model.Box( );
                boxes[i][j].setShade(Integer.parseInt(box[i][j].getText( )));
                boxes[i][j].setColor(toColor(colore[i][j]));
                boxes[i][j].setX(i);
                boxes[i][j].setY(j);
                System.out.println("Box: " + i + " - " + j + " Shade: " + Integer.parseInt(box[i][j].getText( )) + " Color: " + toColor(colore[i][j]));

            }
        }
        //inserire controllo sull id e difficoltà che siano solo numeri
        int difficulty, idScheme;
        try {
            difficulty = Integer.parseInt(difficultiesTextField.getText( ));
            System.out.println("Difficolt: " + difficultiesTextField.getText( ));
        } catch (Exception e) {
            difficulty = 3;
            System.out.println("Errore nel caricamento difficolt");
        }
        try {
            idScheme = Integer.parseInt(idSchemeTextField.getText( ));
            System.out.println("ID: " + idSchemeTextField.getText( ));
        } catch (Exception e) {
            idScheme = 12345;
            System.out.println("Errore nel caricamento id");
        }
        Scheme schema = new Scheme(idScheme, difficulty, boxes);
        //schema.setId(idScheme);
        //schema.setDifficulty(difficulty);
        schema.setBoxes(boxes);
        System.out.println(schema);

        schemeArrayList.add(schema);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" toColor Method ">
    public it.polimi.ing.sw.model.Color toColor(int i) {
        switch (i) {
            case 1:
                return it.polimi.ing.sw.model.Color.PURPLE;
            case 2:
                return it.polimi.ing.sw.model.Color.RED;
            case 3:
                return it.polimi.ing.sw.model.Color.BLUE;
            case 4:
                return it.polimi.ing.sw.model.Color.GREEN;
            case 5:
                return it.polimi.ing.sw.model.Color.YELLOW;
            case 6:
                return it.polimi.ing.sw.model.Color.WHITE;
            default:
                return it.polimi.ing.sw.model.Color.WHITE;
        }
    }
    //</editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addSchemePanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel diffLabel;
    private javax.swing.JTextField difficultiesTextField;
    private javax.swing.JButton findButton;
    private javax.swing.JLabel idSchemeLAbel;
    private javax.swing.JTextField idSchemeTextField;
    private javax.swing.JPanel pBox;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton saveAsNewButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JList<String> schemeInnerList;
    private javax.swing.JScrollPane schemeList;
    private javax.swing.JSeparator separator1;
    private javax.swing.JSeparator separator2;
    // End of variables declaration//GEN-END:variables
}