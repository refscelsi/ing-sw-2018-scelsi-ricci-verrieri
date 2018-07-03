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
            for (j = 0; j < 5; j++) {
                if (boxes[i][j].isFull()) {
                    if (!shades.contains(boxes[i][j].getDice().getNumFacciaUp())) {
                        shades.remove(boxes[i][j].getDice().getNumFacciaUp());
                    }
                }
            }
            if (shades.size() == 5) {
                score = score + 5;
            }
            shades.clear();
        }
        return score;
    }

}
