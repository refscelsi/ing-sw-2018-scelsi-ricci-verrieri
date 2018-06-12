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

        for (i=0; i<4; i++)
            // seleziono la riga

            for (j=0; j<5; j++)
                // seleziono la colonna

                for (k=0; k<5; k++)
                    // seleziono la riga della console

                    for (h=0; h<5; h++) {
                        // seleziono la singola casella della console

                        if (k==0 || k==4 || h==0 || h==4) {
                            getTextColor(boxes[i][j].getColor());
                            System.out.print("*"+ConsoleColors.RESET);
                        }
                        else if (k==2 && h==2) {
                            boolean hasShadeRestriction = boxes[i][j].getShade()!=0;
                            boolean isFull = boxes[i][j].isFull();
                            if (isFull && hasShadeRestriction) {
                                getBackgroundColor(boxes[i][j].getDice().getDiceColor());
                                System.out.print(ConsoleColors.BLACK_BOLD + boxes[i][j].getShade() + ConsoleColors.RESET);
                            }
                            else if (isFull) {
                                getBackgroundColor(boxes[i][j].getDice().getDiceColor());
                                System.out.print(boxes[i][j].getDice().getNumFacciaUp() + ConsoleColors.RESET);
                            }
                            else if (hasShadeRestriction)
                                System.out.print(ConsoleColors.BLACK_BOLD + boxes[i][j].getShade() + ConsoleColors.RESET);
                            else
                                System.out.print(" ");
                        }
                        else {
                            if (boxes[i][j].isFull())
                                getBackgroundColor(boxes[i][j].getDice().getDiceColor());
                            System.out.print(" " + ConsoleColors.RESET);
                        }

                    }

        System.out.print("\n");

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
