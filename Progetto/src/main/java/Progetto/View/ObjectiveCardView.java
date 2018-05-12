package Progetto.View;

import javax.swing.*;

public class ObjectiveCardView extends JLabel {

    private String id;

    public ObjectiveCardView(String id){
        this.id=id;
        String s=new String();
        s=id.concat(".png");
        s="/Progetto/View/img/po/po".concat(s);
        System.out.println(s);
        setIcon(new javax.swing.ImageIcon(getClass().getResource(s)));
        
    }
}
