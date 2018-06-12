package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.util.ConsoleColors;

import java.util.ArrayList;

public class ShowDraftPool {

    private DraftPool draftPool;

    public ShowDraftPool (DraftPool draftPool) {
        this.draftPool = draftPool;
        show();
    }

    public void show () {
        int j, k, h;
        ArrayList<Dice> draft = draftPool.getDraftPool();

        for (j=0; j<5; j++) {
            // seleziono la riga della console

            for (k = 0; k < draftPool.getSize(); k++) {
                // seleziono il dado

                getBackgroundColor(draft.get(k).getDiceColor());
                for (h = 0; h < 11; h++) {
                    // seleziono la singola casella della console

                    if (j == 2 && h == 5)
                        System.out.print(draft.get(k).getNumFacciaUp());
                    else
                        System.out.print(" ");

                }
                getBackgroundColor(Color.WHITE);
                System.out.print("     ");
            }

            getBackgroundColor(Color.WHITE);
            System.out.print("\n");
        }

        System.out.print("\n");

        for (k = 0; k < draftPool.getSize(); k++) {
            // seleziono la casella del roundtrack

            for (h = 0; h < 11; h++) {
                // seleziono la singola casella della console

                if (h == 5)
                    System.out.print(k+1);
                else
                    System.out.print(" ");

            }
            System.out.print("     ");
        }

        System.out.print("     DICE\n");

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
        else if (color.equals(Color.BLUE))
            System.out.print(ConsoleColors.BLUE_BACKGROUND);
        else
            System.out.print(ConsoleColors.WHITE_BACKGROUND);
    }

}
