package progetto.server.controller;

import progetto.model.Box;
import progetto.model.Dice;
import progetto.model.exceptions.NotValidException;
import progetto.model.toolCard.ToolCard;

public interface State {

    public void chooseDice(Dice dice);

    public void chooseBox(Box box) throws NotValidException;

    public void chooseCard(ToolCard toolCard);

    public void endTurn();

}
