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
        //ritorno mex --> non puoi farlo
    }

    @Override
    public void chooseBox(Box box) throws NotValidException {
        if(checkBox(box)){
            controller.setBox(box);
            controller.getMatch().useDice(box,controller.getDice(),controller.getPlayer());
            controller.setState(controller.getChosenBox());
        }
    }

    @Override
    public void chooseCard(ToolCard toolCard) {
        //balzi
    }

    @Override
    public void endTurn() {
        //che cazzo fai?
    }

    public boolean checkBox(Box box){
        if(box.getX()<5 && box.getX()>0 && box.getY()>0 && box.getY()<4){
            return true;
        }
        else
            return false;
    }
}
