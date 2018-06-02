package progetto.model.objectiveCard;

import progetto.model.Box;
import progetto.model.Color;
import progetto.model.Scheme;

import java.util.*;

public class DifferentColorColumn extends ObjectiveCard {

    public DifferentColorColumn () {
        super();
    }

    public int calculateScore (Scheme scheme) {
        int i, j, score=0;
        Box[][] boxes = new Box[4][5];
        boxes = scheme.getBoxes();
        ArrayList<Color> colors = new ArrayList<>();
        for (i=0; i<5; i++) {
            colors.add(Color.RED);
            colors.add(Color.GREEN);
            colors.add(Color.BLUE);
            colors.add(Color.YELLOW);
            colors.add(Color.PURPLE);
            for (j=0; j<4; j++)
                colors.remove(boxes[j][i].getDice().getDiceColor());
            if (colors.size()==1)
                score = score + 5;
            colors.clear();
        }
        return score;
    }


}
