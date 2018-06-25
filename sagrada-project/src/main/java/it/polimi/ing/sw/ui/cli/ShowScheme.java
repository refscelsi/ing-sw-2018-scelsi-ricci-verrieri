package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.util.ConsoleColors;

public class ShowScheme {
    private Scheme scheme;

    public ShowScheme (Scheme scheme) {
        this.scheme = scheme;
        show();
    }

    public void show () {
        int i, j, k, h;
        Box boxes [][] = scheme.getBoxes();

        for (i=0; i<4; i++) {
            // seleziono la riga dello schema

            for (j = 0; j < 5; j++) {
                // seleziono la riga della console

                for (k = 0; k < 5; k++) {
                    // seleziono la casella dello schema

                    for (h = 0; h < 9; h++) {
                        // seleziono la casella della console

                        if (j == 0 || h == 0) {
                            getTextColor(boxes[i][k].getColor());
                            System.out.print("*" + ConsoleColors.RESET);
                        } else if (j == 2 && h == 4) {
                            boolean hasShadeRestriction = boxes[i][k].getShade() != 0;
                            boolean isFull = boxes[i][k].isFull();
                            if (isFull && hasShadeRestriction) {
                                getBackgroundColor(boxes[i][k].getDice().getDiceColor());
                                System.out.print(ConsoleColors.BLACK_BOLD + boxes[i][k].getShade() + ConsoleColors.RESET);
                            } else if (isFull) {
                                getBackgroundColor(boxes[i][k].getDice().getDiceColor());
                                System.out.print(boxes[i][k].getDice().getNumFacciaUp() + ConsoleColors.RESET);
                            } else if (hasShadeRestriction)
                                System.out.print(ConsoleColors.BLACK_BOLD + boxes[i][k].getShade() + ConsoleColors.RESET);
                            else
                                System.out.print(" ");
                        } else {
                            if (boxes[i][k].isFull())
                                getBackgroundColor(boxes[i][k].getDice().getDiceColor());
                            System.out.print(" " + ConsoleColors.RESET);
                        }
                        if (/*(i == 3 && j == 4) ||*/ (k == 4 && h == 8)) {
                            getTextColor(boxes[i][k].getColor());
                            System.out.print("*" + ConsoleColors.RESET);
                        }

                    }
                }
                System.out.print("\n");
            }
        }
        for (h=0; h<9*5+1; h++)
            System.out.print("*" + ConsoleColors.RESET);

    }


    public void getTextColor (Color color) {
        if (color.equals(Color.RED))
            System.out.print(ConsoleColors.RED);
        else if (color.equals(Color.GREEN))
            System.out.print(ConsoleColors.GREEN);
        else if (color.equals(Color.YELLOW))
            System.out.print(ConsoleColors.YELLOW);
        else if (color.equals(Color.PURPLE))
            System.out.print(ConsoleColors.PURPLE);
        else if (color.equals(Color.GREEN))
            System.out.print(ConsoleColors.GREEN);
        else if (color.equals(Color.BLUE))
            System.out.print(ConsoleColors.BLUE);
        else
            System.out.print(ConsoleColors.RESET);
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
