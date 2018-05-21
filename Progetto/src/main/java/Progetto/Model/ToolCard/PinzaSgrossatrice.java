package Progetto.Model.ToolCard;

import Progetto.Model.Dice;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;

public class PinzaSgrossatrice extends ToolCard {


    public PinzaSgrossatrice()throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice, char operation) { //gestire eccezioni per 1 e 6 --> valori non validi
        int value = dice.getNumFacciaUp();

        switch(operation){
            case '+':
                if(value<6 && value>0){
                    dice.setNumFacciaUp(value++);
                }
                break;

            case '-':
                if(value>2 && value<7){
                    dice.setNumFacciaUp(value--);
                }
                break;
        }
    }
}