package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.model.toolCard.ToolCard;

import java.util.ArrayList;

public class ShowToolCards {

    private ArrayList<ToolCard> toolCards;

    public ShowToolCards(ArrayList<ToolCard> toolCards) {
        this.toolCards = toolCards;
        show();
    }

    public void show() {
        int points;
        System.out.println("Le carte utensili sono:");
        for (int i = 0; i < 3; i++)
            System.out.print(i + 1 + ") " + toolCards.get(i).getName() + ": " + toolCards.get(i).getDescription() + " Per utilizzarla sono necessari " + toolCards.get(i).getNumOfTokens() + " segnalini favore.\n");
    }

}


