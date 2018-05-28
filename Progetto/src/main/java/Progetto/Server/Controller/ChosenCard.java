package Progetto.Server.Controller;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.ToolCard.*;

public class ChosenCard implements State {
    TurnController turnController;

    public ChosenCard(TurnController newStateTurnController){
        turnController = newStateTurnController;
    }

    public void handleCard() throws ToolCardException, NotValidException {
    int id= turnController.getCard().getId();
        //devo già avere le varie toolcard, oppure devo ritornare all'interfaccia per chiamare execute!
    }

    @Override
    public void chooseDice(Dice dice) {

    }


    @Override
    public void chooseBox(Box box) {

    }

    @Override
    public void chooseCard(ToolCard toolCard) {
        turnController.getMatch().notifyMessage("hai già scelto una carta");
    }

    @Override
    public void endTurn() {
        //controllare che abbia veramente finito
        turnController.getMatch().changePlayer();
        turnController.setPlayer(turnController.getMatch().getPlayerPlaying());
        turnController.setState(turnController.getStartedTurn());
    }
}
