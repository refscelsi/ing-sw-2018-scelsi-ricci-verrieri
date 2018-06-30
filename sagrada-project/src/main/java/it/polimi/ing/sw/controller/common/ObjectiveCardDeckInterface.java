package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;

import java.io.Serializable;
import java.util.ArrayList;

public interface ObjectiveCardDeckInterface extends Serializable {
    ArrayList<ObjectiveCard> drawObjectiveCard ();

    int getSize();

    int getDrawnCardsSize();
}
