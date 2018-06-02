package it.polimi.ing.sw.server.controller;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.toolCard.ToolCard;

public class StartedTurn implements State {
    TurnController turnController;

    public StartedTurn(TurnController newStateTurnController){
        turnController = newStateTurnController;
    }


    @Override
    public void chooseDice(Dice dice) {
        //ho bisogno di controlli sul dice? non credo proprio
        turnController.setDice(dice);
        turnController.setState(turnController.getChosenDice());
    }

    @Override
    public void chooseBox(Box box) {
        turnController.getMatch().notifyMessage("mossa non consentita");
    }

    @Override
    public void chooseCard(ToolCard toolCard) {
        turnController.setCard(toolCard);
        turnController.setState(turnController.getChosenCard());
    }

    @Override
    public void endTurn() {
        turnController.getMatch().changePlayer();
        turnController.setPlayer(turnController.getMatch().getPlayerPlaying());
        turnController.setState(turnController.getStartedTurn());
    }
}
