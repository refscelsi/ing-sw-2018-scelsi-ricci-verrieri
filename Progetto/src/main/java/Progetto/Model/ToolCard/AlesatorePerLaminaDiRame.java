package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;


public class AlesatorePerLaminaDiRame extends ToolCard{
    final int id=3;

    public AlesatorePerLaminaDiRame () throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice, Player player, Box box1, Box box2) throws ToolCardException {
        Box source = box1;
        Box destination = box2;
        Scheme scheme=player.getScheme();
        if(scheme.isEmpty()){
            throw new ToolCardException("Non hai dadi da spostare babbazzo");
        }

        source.removeDice();
        if(!destination.isFull() && scheme.checkDiceAdjacent(destination,dice) &&
                ((scheme.checkColor(destination.getColor(),dice.getDiceColor()))
                        || (scheme.checkColor(dice.getDiceColor(),Color.WHITE)))){
            destination.placeDice(dice);
            player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
            setNumOfTokens(2);
        }
        else
            source.placeDice(dice);
    }
}
