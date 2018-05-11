package Progetto.Model.ObjectiveCard;

import Progetto.Model.*;
import java.util.*;

public class DifferentColors extends ObjectiveCard {

    public DifferentColors () {
        super();
    }

    public int calculateScore (Scheme scheme) {
        int i, j, min=4;
        int red=0, yellow=0, blue=0, green=0, purple=0;
        Box[][] boxes = new Box[4][5];
        boxes = scheme.getBoxes();
        for (i=0; i<4; i++)
            for (j=0; j<5; j++) {
                switch (boxes[i][j].getDice().getDiceColor()) {
                    case YELLOW:
                        yellow++;
                        break;
                    case RED:
                        red++;
                        break;
                    case BLUE:
                        blue++;
                        break;
                    case GREEN:
                        green++;
                        break;
                    case PURPLE:
                        purple++;
                        break;
                    default:
                        break;
                }
            }
        for (i=0; i<4; i++)
            if (red==i || yellow==i || blue==i || green==i || purple==i)
                return i*4;

        return 0;
    }

}
