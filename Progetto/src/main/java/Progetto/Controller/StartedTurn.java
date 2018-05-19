package Progetto.Controller;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.ToolCard.ToolCard;

public class StartedTurn implements State {
    Controller controller;

    public StartedTurn(Controller newStateController){
        controller=newStateController;
    }


    @Override
    public void chooseDice(Dice dice) {
        //ho bisogno di controlli sul dice? non credo proprio
        controller.setDice(dice);
        controller.setState(controller.getChosenDice());
    }

    @Override
    public void chooseBox(Box box) {
        //che vuoi fare?
    }

    @Override
    public void chooseCard(ToolCard toolCard) {
        controller.setCard(toolCard);
        controller.setState(controller.getChosenCard());
    }

    @Override
    public void endTurn() {
        //sicuro di voler passare il turno??
    }
}
