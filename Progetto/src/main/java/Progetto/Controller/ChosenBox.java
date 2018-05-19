package Progetto.Controller;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.ToolCard.ToolCard;

public class ChosenBox implements State {
    Controller controller;

    public ChosenBox(Controller newStateController){
        controller=newStateController;
    }



    @Override
    public void chooseDice(Dice dice) {
            //puoi fare un'altra mossa??
    }

    @Override
    public void chooseBox(Box box) {
        //balzi
    }

    @Override
    public void chooseCard(ToolCard toolCard) {
        //puoi farlo?
    }

    @Override
    public void endTurn() {
        //controllo che non possa più fare niente il coglione
        controller.getMatch().changePlayer();
        controller.setState(controller.getStartedTurn());
    }
}
