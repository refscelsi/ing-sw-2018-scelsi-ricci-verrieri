package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;
import it.polimi.ing.sw.util.ConsoleColors;

import java.util.ArrayList;

public class ShowPrivateObjectiveCard {

    private PrivateObjectiveCard privateObjective;

    public ShowPrivateObjectiveCard (PrivateObjectiveCard privateObjective) {
        this.privateObjective = privateObjective;
        show();
    }

    public void show () {
        System.out.print("Il tuo obiettivo privato è: ");
        getBackgroundColor(privateObjective.getColor());
        System.out.println(privateObjective.getName() + ConsoleColors.WHITE_BACKGROUND);
        System.out.println("Descrizione: "+privateObjective.getDescription());
    }

    public void getBackgroundColor (Color color) {
        if (color.equals(Color.RED))
            System.out.print(ConsoleColors.RED_BACKGROUND);
        else if (color.equals(Color.GREEN))
            System.out.print(ConsoleColors.GREEN_BACKGROUND);
        else if (color.equals(Color.YELLOW))
            System.out.print(ConsoleColors.YELLOW_BACKGROUND);
        else if (color.equals(Color.PURPLE))
            System.out.print(ConsoleColors.PURPLE_BACKGROUND);
        else if (color.equals(Color.GREEN))
            System.out.print(ConsoleColors.GREEN_BACKGROUND);
        else if (color.equals(Color.BLUE)) {
            System.out.print(ConsoleColors.BLUE_BACKGROUND);
            System.out.print(ConsoleColors.WHITE);
        }
        else
            System.out.print(ConsoleColors.WHITE_BACKGROUND);
    }

}
