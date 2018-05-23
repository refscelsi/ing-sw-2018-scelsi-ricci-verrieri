package Progetto.Controller;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.ToolCard.ToolCard;

public class ChosenDice implements State {
    Controller controller;

    public ChosenDice(Controller newStateController){
        controller=newStateController;
    }

    @Override
    public void chooseDice(Dice dice) {
        controller.getMatch().notifyMessage("Hai già scelto il dado");
    }

    @Override
    public void chooseBox(Box box) throws NotValidException {
        if(checkBox(box)){
            controller.setBox(box);
            controller.getMatch().useDice(box,controller.getDice(),controller.getPlayer());
        }
    }

    @Override
    public void chooseCard(ToolCard toolCard) {
        controller.getMatch().notifyMessage("Hai già scelto la tua mossa");
    }

    @Override
    public void endTurn() {
        if (controller.getBox() == null) {
            controller.getMatch().notifyMessage("termina la mossa");
        } else {
            controller.getMatch().changePlayer();
            controller.setPlayer(controller.getMatch().getPlayerPlaying());
            controller.setState(controller.getStartedTurn());
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
