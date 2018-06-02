package progetto.server.controller;

import progetto.model.Box;
import progetto.model.Dice;
import progetto.model.exceptions.NotValidException;
import progetto.model.exceptions.ToolCardException;
import progetto.model.toolCard.ToolCard;

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
    public void chooseBox(Box box) throws NotValidException {

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
