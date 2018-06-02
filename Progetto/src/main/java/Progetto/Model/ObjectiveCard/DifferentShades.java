package progetto.model.objectiveCard;

import progetto.model.Box;
import progetto.model.Scheme;

public class DifferentShades extends ObjectiveCard {

    public DifferentShades () {
        super();
    }

    public int calculateScore (Scheme scheme) {
        int i, j, index, min=3;
        int count[] = {0, 0, 0, 0, 0, 0};
        Box[][] boxes = new Box[4][5];
        boxes = scheme.getBoxes();
        for (i=0; i<4; i++)
            for (j=0; j<5; j++) {
                index = boxes[i][j].getDice().getNumFacciaUp();
                if (index>0 && index<7)
                    count[index+1]++;
            }
        for (i=0; i<6; i++)
            if (count[i]<min)
                min = count[i];

        return min*5;
    }

}
