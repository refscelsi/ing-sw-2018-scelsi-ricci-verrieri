package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Scheme;

import java.util.*;

public class DifferentColorColumn extends ObjectiveCard {

    public DifferentColorColumn () {
        super();
    }

    public int calculateScore (Scheme scheme) {
        int i, j, score=0;
        Box[][] boxes = scheme.getBoxes();
        ArrayList<Color> colors = new ArrayList<>();
        for (i=0; i<5; i++) {
            colors.add(Color.RED);
            colors.add(Color.GREEN);
            colors.add(Color.BLUE);
            colors.add(Color.YELLOW);
            colors.add(Color.PURPLE);
            for (j = 0; j < 4; j++) {
                if (boxes[j][i].isFull()) {
                    colors.remove(boxes[j][i].getDice().getDiceColor());
                }
            }
            if (colors.size()==1)
                score = score + 5;
            colors.clear();
        }
        return score;
    }


}
