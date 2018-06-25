package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.util.ConsoleColors;


public class ShowRoundTrack {

    private RoundTrack roundTrack;

    public ShowRoundTrack (RoundTrack roundTrack) {
        this.roundTrack = roundTrack;
        show();
    }

    public void show () {
        int i, j, k, h, num;
        //DraftPool [] draft = roundTrack.getRoundTrack();
        DraftPool dices;
        int max = roundTrack.getMaxNumberOfDices();

        for (i=max-1; i>0; i--) {
            // seleziono la riga di dadi

            for (j = 0; j < 3; j++) {
                // seleziono la riga della console

                for (k = 0; k < roundTrack.getRoundTrackSize(); k++) {
                    // seleziono la casella del roundtrack

                    System.out.print("  ");
                    dices = roundTrack.getDicesRound(k+1);
                    num = roundTrack.getNumberOfDices(k+1);
                    if (num>i) {
                        getBackgroundColor(dices.getDice(i).getDiceColor());
                        for (h = 0; h < 7; h++) {
                            // seleziono la singola casella della console

                            if (j == 1 && h == 3)
                                System.out.print(dices.getDice(i).getNumFacciaUp());
                            else
                                System.out.print(" ");

                        }
                    }
                    else for (h = 0; h < 7; h++)
                        System.out.print(" ");
                    getBackgroundColor(Color.WHITE);
                    System.out.print("  ");
                }

                getBackgroundColor(Color.WHITE);
                if (j==1)
                    System.out.print("   " + i);
                System.out.print("\n");
            }
            System.out.print("\n");
        }

        for (j=0; j<5; j++) {
            // seleziono la riga della console

            for (k = 0; k < roundTrack.getRoundTrackSize(); k++) {
                // seleziono la casella del roundtrack

                dices = roundTrack.getDicesRound(k+1);
                getBackgroundColor(dices.getDice(0).getDiceColor());
                for (h = 0; h < 11; h++) {
                    // seleziono la singola casella della console

                    if (j == 2 && h == 5)
                        System.out.print(dices.getDice(0).getNumFacciaUp());
                    else
                        System.out.print(" ");

                }
            }

            getBackgroundColor(Color.WHITE);
            if (j==2)
                System.out.print("   0");
            System.out.print("\n");
        }

        System.out.print("\n");

        for (k = 0; k < 10; k++) {
            // seleziono la casella del roundtrack

            for (h = 0; h < 11; h++) {
                // seleziono la singola casella della console

                if (h == 5)
                    System.out.print(k+1);
                else
                    System.out.print(" ");

            }
        }

        System.out.print("     ROUND\n");

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

