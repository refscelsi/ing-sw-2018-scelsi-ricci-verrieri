package it.polimi.ing.sw.ui.gui.model;

import javax.swing.*;

public class ObjectiveCardView extends JLabel {

    private String id;

    public ObjectiveCardView(String id) {
        this.id = id;
        String s = new String();
        s = id.concat(".png");
        s = "/it/polimi/ing/sw/view/img/po/po".concat(s);
        System.out.println(s);
        setIcon(new javax.swing.ImageIcon(getClass().getResource(s)));

    }
}
