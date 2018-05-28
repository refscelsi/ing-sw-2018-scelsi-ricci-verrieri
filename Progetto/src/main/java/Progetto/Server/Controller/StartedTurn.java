package Progetto.Server.Controller;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.ToolCard.ToolCard;

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
