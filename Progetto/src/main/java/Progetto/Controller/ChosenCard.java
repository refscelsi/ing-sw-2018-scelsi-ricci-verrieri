package Progetto.Controller;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.ToolCard.ToolCard;

public class ChosenCard implements State {
    Controller controller;

    public ChosenCard(Controller newStateController){
        controller=newStateController;
    }

    public void handleCard(){
        String s=controller.getCard().toString();

        switch (s){
        }


    }

    @Override
    public void chooseDice(Dice dice) {

    }

    @Override
    public void chooseBox(Box box) {

    }

    @Override
    public void chooseCard(ToolCard toolCard) {

    }

    @Override
    public void endTurn() {

    }
}
