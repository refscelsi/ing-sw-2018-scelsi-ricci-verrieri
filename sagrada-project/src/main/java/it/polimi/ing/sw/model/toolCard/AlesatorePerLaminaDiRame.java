package it.polimi.ing.sw.model.toolCard;


import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

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
