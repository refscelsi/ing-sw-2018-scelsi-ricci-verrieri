package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Scheme;


public class Shades extends ObjectiveCard {

    private int shade1;

    public Shades(int shade1) {
        super();
        this.shade1 = shade1;
    }

    public int calculateScore(Scheme scheme) {
        int i, j, score = 0, shade2 = shade1 + 1, count1 = 0, count2 = 0;
        Box[][] boxes = scheme.getBoxes();
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 5; j++) {
                if (boxes[i][j].isFull()) {
                    if (boxes[i][j].getDice().getNumFacciaUp() == shade1)
                        count1++;
                    else if (boxes[i][j].getDice().getNumFacciaUp() == shade2)
                        count2++;
                }
            }
        }
        if (count1 > count2)
            score = count2 * 2;
        else
            score = count1 * 2;

        return score;
    }
}