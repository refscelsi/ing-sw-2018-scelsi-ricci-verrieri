package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public interface PrivateObjectiveCardDeckInterface extends Serializable {

    ArrayList<PrivateObjectiveCard> drawObjectiveCard (int numPlayers);

    int getSize();

    int getDrawnCardsSize();
}
