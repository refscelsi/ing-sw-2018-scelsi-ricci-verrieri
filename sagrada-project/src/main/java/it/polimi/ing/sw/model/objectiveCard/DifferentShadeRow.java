package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Scheme;

import java.util.ArrayList;

public class DifferentShadeRow extends ObjectiveCard {

    public DifferentShadeRow() {
        super();
    }

    public int calculateScore(Scheme scheme) {
        int i, j, score = 0;
        Box[][] boxes = scheme.getBoxes();
        ArrayList<Integer> shades = new ArrayList<>();
        for (i = 0; i < 4; i++) {
            shades.add(1);
            shades.add(2);
            shades.add(3);
            shades.add(4);
            shades.add(5);
            shades.add(6);
            for (j = 0; j < 5; j++) {
                if (boxes[i][j].isFull()) {
                    shades.remove(boxes[i][j].getDice().getNumFacciaUp());
                }
            }
            if (shades.size() == 1)
                score = score + 5;
            shades.clear();
        }
        return score;
    }

}
