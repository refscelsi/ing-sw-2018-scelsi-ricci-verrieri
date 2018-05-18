package Progetto.Model.ToolCard;

import Progetto.Model.Dice;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;

public class PinzaSgrossatrice extends ToolCard {
    private Dice dice;
    private char operation;

    public PinzaSgrossatrice(Dice dice, char operation)throws ToolCardException, NotValidException {
        this.dice = dice;
        this.operation = operation;
    }

    public void execute() { //gestire eccezioni per 1 e 6 --> valori non validi
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