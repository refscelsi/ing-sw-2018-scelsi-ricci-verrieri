package progetto.model.objectiveCard;
import progetto.model.*;

public class PrivateObjectiveCard {

    Color color;

    public PrivateObjectiveCard (Color color) {
        this.color = color;
    }

    public Color getColor () {
        return color;
    }

    public int calculateScore (Scheme scheme) {
        int score = 0;
        Box boxes[][] = new Box[4][5];
        boxes = scheme.getBoxes();
        for (int i=0; i<4; i++)
            for (int j=0; j<5; j++)
                if (boxes[i][j].getColor()==color)
                    score = score + boxes[i][j].getShade();
        return score;
    }
}
