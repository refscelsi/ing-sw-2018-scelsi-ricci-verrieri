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
            for (j = 0; j < 4; j++) {
                if (boxes[j][i].isFull()) {
                    if (!shades.contains(boxes[j][i].getDice().getNumFacciaUp())) {
                        shades.add((boxes[j][i].getDice().getNumFacciaUp()));
                    }
                }
            }
            if (shades.size() == 4) {
                score = score + 4;
            }
            shades.clear();
        }
        return score;
    }

}
