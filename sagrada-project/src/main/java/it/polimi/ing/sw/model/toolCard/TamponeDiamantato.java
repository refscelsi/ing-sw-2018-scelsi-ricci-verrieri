package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class TamponeDiamantato extends ToolCard {
    private int id=10;

    public TamponeDiamantato() throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice, Player player) throws ToolCardException {

        switch (dice.getNumFacciaUp()){
            case 1:
                player.setNumOfToken(player.getNumOfToken() - getNumOfTokens());
                setNumOfTokens(2);
                dice.setNumFacciaUp(6);
                break;
            case 2:
                player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                setNumOfTokens(2);
                dice.setNumFacciaUp(5);
                break;
            case 3:
                player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                setNumOfTokens(2);
                dice.setNumFacciaUp(4);
                break;
            case 4:
                player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                setNumOfTokens(2);
                dice.setNumFacciaUp(3);
                break;
            case 5:
                player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                setNumOfTokens(2);
                dice.setNumFacciaUp(2);
                break;
            case 6:
                player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                setNumOfTokens(2);
                dice.setNumFacciaUp(1);
                break;

            default:
                throw new ToolCardException("Valore non valido");
        }

    }
}
