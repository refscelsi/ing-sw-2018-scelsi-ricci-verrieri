package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.ui.gui.toolCardsActrionFrames.ToolCard1ActionForm;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import static it.polimi.ing.sw.model.Color.*;
import static it.polimi.ing.sw.util.Constants.IMAGE_PATH;
import static it.polimi.ing.sw.util.Constants.NOT_A_DICE;

public class DiceGUI extends javax.swing.JPanel {

    private Box box;
    private View controller;

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

    public void setController(View controller) {
        this.controller = controller;
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
        updateColor(box.getColor());
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
    }//GEN-LAST:event_diceLabelMouseEntered

    private void diceLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceLabelMouseExited
        diceLabel.setBorder(null);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        TableFrame.setIsAdiceGui(false);
    }//GEN-LAST:event_diceLabelMouseExited

    private void diceLabelMousePressed(java.awt.event.MouseEvent evt) throws RemoteException {//GEN-FIRST:event_diceLabelMousePressed
        if (TableFrame.aToolCardIsUsed()) {
            switch (TableFrame.idSelectedTc) {
                case "1":
                    ToolCard1ActionForm toolCard1ActionForm = new ToolCard1ActionForm(box.getDice(), controller);
                    toolCard1ActionForm.setVisible(true);
                    TableFrame.isNotToolCardAnymore(1 - 1);
                    break;
                case "6":
                    if (0 <= Integer.valueOf(getName()) && 9 > Integer.valueOf(getName())) {
                        //TODO handling empty cose
                        controller.useToolCard(6, Integer.valueOf(getName()), -1, -1, -1, -1, -1);
                        TableFrame.isNotToolCardAnymore(6 - 1);
                    }
                    break;
                case "10":
                    if (0 <= Integer.valueOf(getName()) && 9 > Integer.valueOf(getName())) {
                        //TODO handling empty cose
                        controller.useToolCard(10, Integer.valueOf(getName()), -1, -1, -1, -1, -1);
                        TableFrame.isNotToolCardAnymore(10 - 1);
                    }
                    break;
                case "11":
                    if (0 <= Integer.valueOf(getName()) && 9 > Integer.valueOf(getName())) {
                        //TODO handling empty cose
                        controller.useToolCard(11, Integer.valueOf(getName()), -1, -1, -1, -1, -1);
                        TableFrame.isNotToolCardAnymore(11 - 1);
                    }
                    break;
            }
        } else {
            if (box.isFull()) {
                wasPressed = true;
                floatingDiceFrame = new FloatingDiceFrame(box.getDice(), DIM_X_FLOATING_DICE, DIM_Y_FLOATING_DICE);
            }
        }
    }//GEN-LAST:event_diceLabelMousePressed

    private void diceLabelMouseReleased(java.awt.event.MouseEvent evt) throws RemoteException {//GEN-FIRST:event_diceLabelMouseReleased
        wasPressed = false;
        if (this != evt.getComponent() && floatingDiceFrame != null) {
            floatingDiceFrame.setVisible(false);
            removeDice();
            injectDice(evt);
            floatingDiceFrame = null;
        }
    }//GEN-LAST:event_diceLabelMouseReleased

    private void removeDice() {
        box.removeDice();
        diceLabel.setIcon(null);
    }

    private void diceLabelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceLabelMouseDragged
        if (wasPressed) {
            floatingDiceFrame.setVisible(true);
            floatingDiceFrame.setLocation(evt.getXOnScreen() + 3, evt.getYOnScreen() - DIM_Y_FLOATING_DICE - 3);
        }
    }//GEN-LAST:event_diceLabelMouseDragged

    private void injectDice(MouseEvent evt) throws RemoteException {
        String nameComponent = TableFrame.getCurrentComponentName();
        char id = nameComponent.charAt(0);

        if (!NOT_A_DICE.equals(nameComponent) && TableFrame.isPlayerTurn(controller.getNickname(), id)) {
            int destY = (int) nameComponent.charAt(2) - 48;
            int destX = (int) nameComponent.charAt(1) - 48;
            handleToolCards(id, destX, destY);
        }
    }

    private void handleToolCards(char id, int destX, int destY) throws RemoteException {
        if (TableFrame.isToolCard.get(2 - 1)) {
            if (TableFrame.isPlayerScheme(controller.getNickname(), id)) {
                controller.useToolCard(Integer.valueOf(TableFrame.idSelectedTc), -1, -1, floatingDiceFrame.getDiceX() - 1, floatingDiceFrame.getDiceY() - 1, destX - 1, destY - 1);
                TableFrame.isNotToolCardAnymore(2 - 1);
                reprindDices(id, destX, destY);
            }
        } else if (TableFrame.isToolCard.get(3 - 1)) {
            if (TableFrame.isPlayerScheme(controller.getNickname(), id)) {
                controller.useToolCard(Integer.valueOf(TableFrame.idSelectedTc), -1, -1, floatingDiceFrame.getDiceX() - 1, floatingDiceFrame.getDiceY() - 1, destX - 1, destY - 1);
                TableFrame.isNotToolCardAnymore(3 - 1);
                reprindDices(id, destX, destY);
            }
        } else if (TableFrame.isToolCard.get(4 - 1)) {
            if (TableFrame.isPlayerScheme(controller.getNickname(), id)) {
                controller.useToolCard(Integer.valueOf(TableFrame.idSelectedTc), -1, -1, floatingDiceFrame.getDiceX() - 1, floatingDiceFrame.getDiceY() - 1, destX - 1, destY - 1);
                TableFrame.isNotToolCardAnymore(4 - 1);
                reprindDices(id, destX, destY);
            }
        } else if (TableFrame.isToolCard.get(12 - 1)) {
            boolean roundTrackIsFull = controller.checkIfRoundTrackIsFull();
            if (!roundTrackIsFull) {
                JOptionPane.showMessageDialog(null,
                        "Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round",
                        "Not valid Action",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                controller.useToolCard(Integer.valueOf(TableFrame.idSelectedTc), -1, -1, floatingDiceFrame.getDiceX() - 1, floatingDiceFrame.getDiceY() - 1, destX - 1, destY - 1);
                TableFrame.isNotToolCardAnymore(12 - 1);
                reprindDices(id, destX, destY);            }
        } else {
            if (!TableFrame.isToolCard.get(9 - 1)) {
                if (0 <= Integer.valueOf(getName()) && 9 > Integer.valueOf(getName())) {
                    controller.useDice(Integer.valueOf(getName()), box.getX() - 1, box.getY() - 1);
                }
            } else {
                if (0 <= Integer.valueOf(getName()) && 9 > Integer.valueOf(getName())) {
                    TableFrame.isNotToolCardAnymore(9 - 1);
                    controller.useToolCard(9, Integer.valueOf(getName()), -1, box.getX() - 1, box.getY() - 1, -1, -1);
                }
            }
            reprindDices(id, destX, destY);
        }
    }

    private void reprindDices(char id, int destX, int destY) {
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
    // End of variables declaration//GEN-END:variables
}