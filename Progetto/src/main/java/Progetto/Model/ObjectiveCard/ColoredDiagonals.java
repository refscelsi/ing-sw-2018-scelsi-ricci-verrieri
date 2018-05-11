package Progetto.Model.ObjectiveCard;

import Progetto.Model.*;
import java.util.*;

public class ColoredDiagonals extends ObjectiveCard {

    Box[][] boxes = new Box[4][5];

    public ColoredDiagonals () {
        super();
    }

    public int numSameColor1 (int row, int column, int length) {
        int num = 0;
        for (int i=0; i<length; i++) {
           if (boxes[row-i][column-i].getDice().getDiceColor() == boxes[row-i-1][column-i-1].getDice().getDiceColor())
               num++;
        }
        return num;
    }

    public int numSameColor2 (int row, int column, int length) {
        int num = 0;
        for (int i=0; i<length; i++) {
            if (boxes[row-i][column+i].getDice().getDiceColor() == boxes[row-i-1][column+i+1].getDice().getDiceColor())
                num++;
        }
        return num;
    }

    public int calculateScore (Scheme scheme) {
        boxes = scheme.getBoxes();
        int score = numSameColor1(3, 1, 1) + numSameColor1(3, 2, 2) + numSameColor1(3, 3, 3) + numSameColor1(3, 4, 3) + numSameColor1(2, 4, 2) + numSameColor1(1, 4, 1);
        score = score + numSameColor2(1, 0, 1) + numSameColor2(2, 0, 2) + numSameColor2(3, 0, 3) + numSameColor2(3, 1, 3) + numSameColor2(3, 2, 2) + numSameColor2(3, 3, 1);
        return score;
    }

}

