package it.polimi.ing.sw.server.controller;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.toolCard.ToolCard;

public interface State {

    public void chooseDice(Dice dice);

    public void chooseBox(Box box) throws NotValidException;

    public void chooseCard(ToolCard toolCard);

    public void endTurn();

}
