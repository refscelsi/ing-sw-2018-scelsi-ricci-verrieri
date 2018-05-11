package Progetto.Model.ObjectiveCard;

import Progetto.Model.*;
import java.util.*;

public class DifferentShadeRow extends ObjectiveCard {

    public DifferentShadeRow () {
        super();
    }

    public int calculateScore (Scheme scheme) {
        int i, j, score=0;
        Box[][] boxes = new Box[4][5];
        boxes = scheme.getBoxes();
        ArrayList<Integer> shades = new ArrayList<>();
        for (i=0; i<4; i++) {
            shades.add(1);
            shades.add(2);
            shades.add(3);
            shades.add(4);
            shades.add(5);
            shades.add(6);
            for (j=0; j<5; j++)
                shades.remove(boxes[i][j].getDice().getNumFacciaUp());
            if (shades.size()==1)
                score = score + 5;
            shades.clear();
        }
        return score;
    }

}
