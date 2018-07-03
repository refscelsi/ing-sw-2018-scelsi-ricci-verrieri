package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Dice;

import java.util.ArrayList;
import java.util.List;

public class DiceFieldPanel extends javax.swing.JPanel {

    private static final int dimXdice = 45;
    private static final int dimYdice = 45;

    private GUI gui;
    private List<DiceGUI> diceGUIList;

    public DiceFieldPanel(GUI gui) {
        this.gui=gui;
        diceGUIList = new ArrayList<>();
        initComponents();
        positionateDiceGUIboxes();
    }

    private void positionateDiceGUIboxes() {
        for (int i = 0; i < 11; i++) {
            DiceGUI diceGUI = new DiceGUI(false, dimXdice, dimYdice);
            diceGUI.setController(gui);
            diceGUIList.add(diceGUI);
            positionateBox(diceGUI, i);
        }
    }

    private void positionateBox(DiceGUI diceGUI, int index) {
        int deltaXinit = 0;
        int deltaYinit;
        int spaceBetweenDices = 85;
        int multiplier = 0;

        int deltaX = 0;
        int deltaY = 0;
        if (index == 0 || index == 3 || index == 6 || index == 9) {
            deltaXinit = 42;
            deltaYinit = 15;
            if (index != 0)
                multiplier = index / 3;
            deltaX = deltaXinit + multiplier * (spaceBetweenDices + dimXdice);
            deltaY = deltaYinit;
        }
        if (index == 2 || index == 5 || index == 8) {
            deltaXinit = 42;
            switch (index) {
                case 2:
                    multiplier = 0;
                    break;
                case 5:
                    multiplier = 1;
                    break;
                case 8:
                    multiplier = 2;
                    break;
            }
            deltaX = 42 + 20 + dimXdice + multiplier * (spaceBetweenDices + dimXdice);
            deltaY = 47;
        }
        if (index == 1 || index == 4 || index == 7 || index == 10) {
            deltaXinit = 42 + deltaXinit;
            deltaYinit = 15 + 20 + dimXdice;
            switch (index) {
                case 4:
                    multiplier = 1;
                    break;
                case 7:
                    multiplier = 2;
                    break;
                case 10:
                    multiplier = 3;
                    break;
            }
            deltaX = deltaXinit + multiplier * (spaceBetweenDices + dimXdice);
            deltaY = deltaYinit;
        }
        add(diceGUI);
        diceGUI.setBounds(deltaX, deltaY, dimXdice, dimYdice);
        diceGUI.setVisible(false);
    }

    public void setDices(List<Dice> dices) {
        for (int i = 0; i < dices.size(); i++) {
            diceGUIList.get(i).setDice(dices.get(i));
            diceGUIList.get(i).setVisible(true);
            diceGUIList.get(i).setName(String.valueOf(i));
        }
    }

    public void resetDices() {
        for (DiceGUI diceGui : diceGUIList) {
            diceGui.setVisible(false);
            diceGui.setDice(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(520, 140));
        setMinimumSize(new java.awt.Dimension(520, 140));
        setOpaque(false);
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
