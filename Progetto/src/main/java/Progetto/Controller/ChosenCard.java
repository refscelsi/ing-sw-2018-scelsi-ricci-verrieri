package Progetto.Controller;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.ToolCard.*;

import javax.swing.text.html.HTML;

public class ChosenCard implements State {
    Controller controller;

    public ChosenCard(Controller newStateController){
        controller=newStateController;
    }

    public void handleCard() throws ToolCardException, NotValidException {
    int id=controller.getCard().getId();
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
        controller.getMatch().notifyMessage("hai già scelto una carta");
    }

    @Override
    public void endTurn() {
        //controllare che abbia veramente finito
        controller.getMatch().changePlayer();
        controller.setPlayer(controller.getMatch().getPlayerPlaying());
        controller.setState(controller.getStartedTurn());
    }
}
