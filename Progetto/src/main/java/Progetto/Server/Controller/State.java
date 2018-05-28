package Progetto.Server.Controller;

import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.ToolCard.ToolCard;

public interface State {

    public void chooseDice(Dice dice);

    public void chooseBox(Box box) throws NotValidException;

    public void chooseCard(ToolCard toolCard);

    public void endTurn();

}
