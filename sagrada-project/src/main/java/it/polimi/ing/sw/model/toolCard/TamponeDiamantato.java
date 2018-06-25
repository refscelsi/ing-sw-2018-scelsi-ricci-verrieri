package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class TamponeDiamantato extends ToolCard {

    private final int id=10;


    public TamponeDiamantato() {
        super();
    }

    @Override
    public void execute(Dice dice) {

        switch (dice.getNumFacciaUp()){
            case 1:
                dice.setNumFacciaUp(6);
                break;
            case 2:
                dice.setNumFacciaUp(5);
                break;
            case 3:
                dice.setNumFacciaUp(4);
                break;
            case 4:
                dice.setNumFacciaUp(3);
                break;
            case 5:
                dice.setNumFacciaUp(2);
                break;
            case 6:
                dice.setNumFacciaUp(1);
                break;

            default:
                break;
        }
        incrementNumOfTokens();
    }


}
