package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class PennelloPerEglomise extends ToolCard{
    final int id=2;

    public PennelloPerEglomise()throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice, Player player, Box source, Box destination) throws ToolCardException{
        Scheme scheme=player.getScheme();
        if(scheme.isEmpty()){
            throw new ToolCardException("Non hai dadi da spostare babbazzo");
        }
        source.removeDice();
        if(!destination.isFull() && scheme.checkDiceAdjacent(destination,dice) &&
                ((scheme.checkShade(destination.getShade(),dice.getNumFacciaUp()))
                || (scheme.checkShade(dice.getNumFacciaUp(),0)))){
            destination.placeDice(dice);
            player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
            setNumOfTokens(2);
        }
        else
            source.placeDice(dice);
    }

}

