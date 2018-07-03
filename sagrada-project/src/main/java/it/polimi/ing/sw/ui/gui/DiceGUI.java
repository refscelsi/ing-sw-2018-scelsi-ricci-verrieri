package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import static it.polimi.ing.sw.model.Color.*;
import static it.polimi.ing.sw.util.Constants.IMAGE_PATH;

public class DiceGUI extends javax.swing.JPanel {

    private Box box;
    private GUI gui;

    private int dimXdice;
    private int dimYdice;

    public DiceGUI(Boolean isVoid, int dimXdice, int dimYdice) {
        this.dimXdice = dimXdice;
        this.dimYdice = dimYdice;
        initComponents();
        box = new Box();
        if (!isVoid)
            updateIcon("1g", false);
    }

    public void setController(GUI gui) {
        this.gui = gui;
    }

    public void setDice(Dice dice) {
        box.placeDice(dice);
        updateDice();
    }

    public void setBox(Box box) {
        this.box = box;
        updateBox();
    }

    private void updateBox() {
        updateIcon(String.valueOf(box.getShade()), false);
        if (box.isFull()) {
            updateDice();
        } else {
            updateColor(box.getColor());
        }

        diceLabel.repaint();
    }

    private void updateDice() {
        updateIcon(String.valueOf(box.getDice().getNumFacciaUp()), true);
        updateColor(box.getDice().getDiceColor());
        diceLabel.repaint();
    }

    private void updateColor(it.polimi.ing.sw.model.Color color) {
        if (RED == color) {
            diceLabel.setBackground(new java.awt.Color(255, 0, 51));
        }
        if (BLUE == color) {
            diceLabel.setBackground(new java.awt.Color(0, 102, 255));
        }
        if (GREEN == color) {
            diceLabel.setBackground(new java.awt.Color(4, 175, 98));
        }
        if (YELLOW == color) {
            diceLabel.setBackground(new java.awt.Color(255, 255, 51));
        }
        if (PURPLE == color) {
            diceLabel.setBackground(new java.awt.Color(204, 0, 255));
        }
        if (WHITE == color) {
            diceLabel.setBackground(new java.awt.Color(255, 255, 255));
        }
        diceLabel.repaint();
    }

    public Box getBox() {
        return box;
    }

    public static String getImagePath() {
        return IMAGE_PATH;
    }

    private void updateIcon(String name, Boolean isSetDice) {
        String PATH = new String();
        if (name != null) {
            if (name.equals("1g") || name.equals("2g") || name.equals("3g") || name.equals("4g") || name.equals("5g") || name.equals("6g") || isSetDice) {
                PATH = IMAGE_PATH + name + ".png";
            } else {
                int intName = Integer.valueOf(name);
                if (intName > 0 && intName < 7) {
                    PATH = IMAGE_PATH + name + "g.png";
                }
            }
            if (!PATH.isEmpty()) {
                ImageIcon icon;
                Image scaledImage;
                icon = new javax.swing.ImageIcon(getClass().getResource(PATH));
                scaledImage = icon.getImage().getScaledInstance(dimXdice, dimYdice, Image.SCALE_DEFAULT);
                icon.setImage(scaledImage);
                diceLabel.setIcon(icon);
                diceLabel.repaint();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diceLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(50, 50));

        diceLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        diceLabel.setOpaque(true);
        diceLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                diceLabelMouseDragged(evt);
            }
        });
        diceLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                diceLabelMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                diceLabelMouseExited(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    diceLabelMousePressed(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                try {
                    diceLabelMouseReleased(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(diceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(diceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void diceLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceLabelMouseEntered
        diceLabel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        TableFrame.setCurrentComponentName(this.getName(), true);
        System.out.println(getName());
    }//GEN-LAST:event_diceLabelMouseEntered

    private void diceLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceLabelMouseExited
        diceLabel.setBorder(null);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        TableFrame.setIsAdiceGui(false);
    }//GEN-LAST:event_diceLabelMouseExited

    private void diceLabelMousePressed(java.awt.event.MouseEvent evt) throws RemoteException {//GEN-FIRST:event_diceLabelMousePressed
        if (gui.isPlaying()) {
            if (TableFrame.aToolCardIsUsed()) {
                switch (TableFrame.idSelectedTc) {
                    case "1":
                        gui.useToolCard1(this);
                        break;
                    case "5":
                        if (20 > Integer.valueOf(evt.getComponent().getName()) && 9 < Integer.valueOf(evt.getComponent().getName())) {
                            if (null == TableFrame.toolCard5Dice) {
                                TableFrame.toolCard5Dice = this;
                            } else if (Integer.valueOf(evt.getComponent().getName()) < 10) {
                                gui.useToolCard5(Integer.valueOf(evt.getComponent().getName()),Integer.valueOf(TableFrame.toolCard5Dice.getName())-10);
                            }
                        }
                        break;
                    case "6":
                        gui.useToolCard6(Integer.valueOf(getName()));
                        break;
                    case "10":
                        gui.useToolCard10(Integer.valueOf(getName()));
                        break;
                    case "11":
                        gui.useToolCard11(Integer.valueOf(getName()));
                        break;
                }
            } else {
                if (box.isFull()) {
                    //if(gui.getNickname()==TableFrame.getMatch().getPlayerPlaying()){
                    wasPressed = true;
                    floatingDiceFrame = new FloatingDiceFrame(box.getDice(), DIM_X_FLOATING_DICE, DIM_Y_FLOATING_DICE);
                    //}
                }
            }
        }

    }//GEN-LAST:event_diceLabelMousePressed

    private void diceLabelMouseReleased(java.awt.event.MouseEvent evt) throws RemoteException {//GEN-FIRST:event_diceLabelMouseReleased
        wasPressed = false;
        if (this != evt.getComponent() && floatingDiceFrame != null /*&& gui.isPlaying()*/) {
            floatingDiceFrame.setVisible(false);
            injectDice(evt);
            floatingDiceFrame = null;
        }
    }//GEN-LAST:event_diceLabelMouseReleased

    private void removeDice() {
        box.removeDice();/*
        diceLabel.setIcon(null);
        box.setColor(WHITE);*/
        this.setVisible(false);
    }

    private void diceLabelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceLabelMouseDragged
        if (wasPressed) {
            floatingDiceFrame.setVisible(true);
            floatingDiceFrame.setLocation(evt.getXOnScreen() + 3, evt.getYOnScreen() - DIM_Y_FLOATING_DICE - 3);
            floatingDiceFrame.setGUI(gui);
        }
    }//GEN-LAST:event_diceLabelMouseDragged

    private void injectDice(MouseEvent evt) throws RemoteException {
        String nameComponent = TableFrame.getCurrentComponentName();
        char id = nameComponent.charAt(0);
        System.out.println(id);

        if (TableFrame.isPlayerTurn(gui.getNickname(), id)) {
            int destY = (int) nameComponent.charAt(2) - 48;
            int destX = (int) nameComponent.charAt(1) - 48;
            handleToolCards(id, destX, destY);
        }
    }

    private void handleToolCards(char id, int destX, int destY) throws RemoteException {
        if (TableFrame.isToolCard.get(2 - 1)) {
            if (TableFrame.isPlayerScheme(gui.getNickname(), id)) {
                reprindDices(id, destX, destY);
                gui.useToolCard2(floatingDiceFrame, destX, destY);
            }
        } else if (TableFrame.isToolCard.get(3 - 1)) {
            if (TableFrame.isPlayerScheme(gui.getNickname(), id)) {
                reprindDices(id, destX, destY);
                gui.useToolCard3(floatingDiceFrame, destX, destY);
            }
        } else if (TableFrame.isToolCard.get(4 - 1)) {
            if (TableFrame.isPlayerScheme(gui.getNickname(), id)) {
                reprindDices(id, destX, destY);
                gui.useToolCard4(floatingDiceFrame, destX, destY);
            }
        } else if (TableFrame.isToolCard.get(12 - 1)) {
            boolean roundTrackIsFull = gui.checkIfRoundTrackIsFull();
            if (!roundTrackIsFull) {
                JOptionPane.showMessageDialog(null,
                        "Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round",
                        "Not valid Action",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                reprindDices(id, destX, destY);
                gui.useToolCard12(floatingDiceFrame, destX, destY);

            }
        } else {
            if (!TableFrame.isToolCard.get(9 - 1)) {
                if (0 <= Integer.valueOf(getName()) && 9 > Integer.valueOf(getName())) {
                    reprindDices(id, destX, destY);
                    gui.useDice(Integer.valueOf(getName()), destX, destY);

                }
            } else {
                reprindDices(id, destX, destY);
                gui.useToolCard9(Integer.valueOf(getName()), destX, destY);

            }
            //reprindDices(id, destX, destY);
        }
    }

    private void reprindDices(char id, int destX, int destY) {
        removeDice();
        switch (id) {
            case '1':
                TableFrame.updateDice(1, floatingDiceFrame.getDice(), destX, destY);
                break;
            case '2':
                TableFrame.updateDice(2, floatingDiceFrame.getDice(), destX, destY);
                break;
            case '3':
                TableFrame.updateDice(3, floatingDiceFrame.getDice(), destX, destY);
                break;
            case '4':
                TableFrame.updateDice(4, floatingDiceFrame.getDice(), destX, destY);
                break;
        }
    }

    private static final int DIM_X_FLOATING_DICE = 34;
    private static final int DIM_Y_FLOATING_DICE = 34;
    private FloatingDiceFrame floatingDiceFrame;
    private Boolean wasPressed;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel diceLabel;

    public void setX(int i) {
        box.setX(i);
    }

    public void setY(int i) {
        box.setY(i);
    }
    // End of variables declaration//GEN-END:variables
}