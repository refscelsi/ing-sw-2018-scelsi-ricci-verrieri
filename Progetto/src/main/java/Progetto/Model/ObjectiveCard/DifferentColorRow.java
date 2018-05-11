package Progetto.Model.ObjectiveCard;

import Progetto.Model.*;
import java.util.*;

public class DifferentColorRow extends ObjectiveCard {

    public DifferentColorRow () {
        super();
    }

    public int calculateScore (Scheme scheme) {
        int i, j, score=0;
        Box[][] boxes = new Box[4][5];
        boxes = scheme.getBoxes();
        ArrayList<Color> colors = new ArrayList<>();
        for (i=0; i<4; i++) {
            colors.add(Color.RED);
            colors.add(Color.GREEN);
            colors.add(Color.BLUE);
            colors.add(Color.YELLOW);
            colors.add(Color.PURPLE);
            for (j=0; j<5; j++)
                colors.remove(boxes[i][j].getDice().getDiceColor());
            if (colors.isEmpty())
                score = score + 6;
            colors.clear();
        }
        return score;
    }

}
