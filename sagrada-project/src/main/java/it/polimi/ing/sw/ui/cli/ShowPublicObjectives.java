package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.model.objectiveCard.*;

import java.util.ArrayList;

public class ShowPublicObjectives {

    private ArrayList<ObjectiveCard> publicObjectives;

    public ShowPublicObjectives (ArrayList<ObjectiveCard> publicObjectives) {
        this.publicObjectives = publicObjectives;
        show();
    }

    public void show () {
        int points;
        System.out.println("Gli obiettivi pubblici sono:");
        for (int i=0; i<3; i++) {
            points = publicObjectives.get(i).getScore();
            System.out.print(i + 1 + ") " + publicObjectives.get(i).getName() + ": " + publicObjectives.get(i).getDescription() + " (");
            if (points != 0)
                System.out.println(points + " punti)");
            else
                System.out.println("# punti)");
        }
    }

}

