<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/view/ObjectiveCardView.java
package it.polimi.ing.sw.view;
=======
package Progetto.View;
>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/View/ObjectiveCardView.java

import javax.swing.*;

public class ObjectiveCardView extends JLabel {

    private String id;

    public ObjectiveCardView(String id){
        this.id=id;
        String s=new String();
        s=id.concat(".png");
        s= "/it/polimi/ing/sw/view/img/po/po".concat(s);
        System.out.println(s);
        setIcon(new javax.swing.ImageIcon(getClass().getResource(s)));
        
    }
}
