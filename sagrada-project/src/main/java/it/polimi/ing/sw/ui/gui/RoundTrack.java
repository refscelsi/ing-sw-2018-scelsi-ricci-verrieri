package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Dice;

import java.util.ArrayList;
import java.util.List;

public class RoundTrack extends javax.swing.JPanel {

    public List<DiceGUI> diceGUIList;

    public RoundTrack() {
        initComponents();
        diceGUIList = new ArrayList<>();
        setDices();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(660, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 666, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 70, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setDices() {
        for (int i = 0; i < 10; i++) {
            DiceGUI diceGUI = new DiceGUI();
            add(diceGUI);
            diceGUI.setBounds(10 + i * (50 + 10), 10, 50, 50);
            diceGUIList.add(diceGUI);
        }
    }

    public void setDiceGUIList(List<DiceGUI> diceGUIList) {
        this.diceGUIList = diceGUIList;
    }

    public void setDiceGUI(int selected,Dice dice) {
        diceGUIList.get(selected);
    }

    public List<DiceGUI> getDiceGUIList() {
        return diceGUIList;
    }

    public DiceGUI getDiceGUI(int selected) {
        return diceGUIList.get(selected);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
