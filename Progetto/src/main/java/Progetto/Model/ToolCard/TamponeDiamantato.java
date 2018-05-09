package Progetto.Model.ToolCard;

import Progetto.Model.Dice;

import Progetto.Model.Exceptions.*;

public class TamponeDiamantato implements ToolCard{
    private Dice dice;

    public TamponeDiamantato(Dice dice){
        this.dice=dice;
    }

    public void execute() throws ToolCardException {
        int value=dice.getNumFacciaUp();

        switch (value){
            case 1: dice.setNumFacciaUp(6);
            break;
            case 2:dice.setNumFacciaUp(5);
            break;
            case 3:dice.setNumFacciaUp(4);
            break;
            case 4:dice.setNumFacciaUp(3);
            break;
            case 5:dice.setNumFacciaUp(2);
            break;
            case 6:dice.setNumFacciaUp(1);
            break;

            default: throw new ToolCardException("Valore non valido");
        }

    }
}
