package it.polimi.ing.sw.server.controller;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.toolCard.ToolCard;

public class ChosenDice implements State {
    TurnController turnController;

    public ChosenDice(TurnController newStateTurnController){
        turnController = newStateTurnController;
    }

    @Override
    public void chooseDice(Dice dice) {
        turnController.getMatch().notifyMessage("Hai già scelto il dado");
    }

    @Override
    public void chooseBox(Box box) throws NotValidException {
        if(checkBox(box)){
            turnController.setBox(box);
            turnController.getMatch().useDice(box, turnController.getDice(), turnController.getPlayer());
        }
    }

    @Override
    public void chooseCard(ToolCard toolCard) {
        turnController.getMatch().notifyMessage("Hai già scelto la tua mossa");
    }

    @Override
    public void endTurn() {
        if (turnController.getBox() == null) {
            turnController.getMatch().notifyMessage("termina la mossa");
        } else {
            turnController.getMatch().changePlayer();
            turnController.setPlayer(turnController.getMatch().getPlayerPlaying());
            turnController.setState(turnController.getStartedTurn());
        }
    }

    public boolean checkBox(Box box){
        if(box.getX()<5 && box.getX()>0 && box.getY()>0 && box.getY()<4){
            return true;
        }
        else
            return false;
    }
}
