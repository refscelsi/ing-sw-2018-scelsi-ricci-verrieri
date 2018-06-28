package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Scheme;

import java.util.ArrayList;

public class DifferentColorRow extends ObjectiveCard {

    public DifferentColorRow() {
        super();
    }

    public int calculateScore(Scheme scheme) {
        int i, j, score = 0;
        Box[][] boxes = scheme.getBoxes();
        ArrayList<Color> colors = new ArrayList<>();
        for (i = 0; i < 4; i++) {
            colors.add(Color.RED);
            colors.add(Color.GREEN);
            colors.add(Color.BLUE);
            colors.add(Color.YELLOW);
            colors.add(Color.PURPLE);
            for (j = 0; j < 5; j++) {
                if (boxes[i][j].isFull()) {
                    colors.remove(boxes[i][j].getDice().getDiceColor());
                }
            }
            if (colors.isEmpty())
                score = score + 6;
            colors.clear();
        }
        return score;
    }

}
