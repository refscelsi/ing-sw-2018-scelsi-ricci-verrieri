package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Scheme;

import java.util.ArrayList;

public class DifferentShadeColumn extends ObjectiveCard {

    public DifferentShadeColumn() {
        super();
    }

    public int calculateScore(Scheme scheme) {
        int i, j, score = 0;
        Box[][] boxes = scheme.getBoxes();
        ArrayList<Integer> shades = new ArrayList<>();
        for (i = 0; i < 5; i++) {
            shades.add(1);
            shades.add(2);
            shades.add(3);
            shades.add(4);
            shades.add(5);
            shades.add(6);
            for (j = 0; j < 4; j++) {
                if (boxes[j][i].isFull()) {
                    shades.remove((boxes[j][i].getDice().getNumFacciaUp()) - 1);
                }
            }
            if (shades.size() == 2)
                score = score + 4;
            shades.clear();
        }
        return score;
    }

}
