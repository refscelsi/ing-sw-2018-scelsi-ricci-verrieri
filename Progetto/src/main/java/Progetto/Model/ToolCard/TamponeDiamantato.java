package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.ToolCard.*;
import Progetto.Model.Exceptions.*;

public class TamponeDiamantato extends ToolCard {

    public TamponeDiamantato() throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice, int newFace, Player player) throws ToolCardException {

        switch (dice.getNumFacciaUp()){
            case 1:
                if (newFace == 6) {
                    player.setNumOfToken(player.getNumOfToken() - getNumOfTokens());
                    setNumOfTokens(2);
                    dice.setNumFacciaUp(newFace);
                }
                break;
            case 2:
                if (newFace == 5) {
                    player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                    setNumOfTokens(2);
                    dice.setNumFacciaUp(newFace);
                }
                break;
            case 3:
                if (newFace == 4) {
                    player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                    setNumOfTokens(2);
                    dice.setNumFacciaUp(newFace);
                }
                break;
            case 4:
                if (newFace == 3) {
                    player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                    setNumOfTokens(2);
                    dice.setNumFacciaUp(newFace);
                }
                break;
            case 5:
                if (newFace == 2) {
                    player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                    setNumOfTokens(2);
                    dice.setNumFacciaUp(newFace);
                }
                break;
            case 6:
                if (newFace == 1) {
                    player.setNumOfToken(player.getNumOfToken()-getNumOfTokens());
                    setNumOfTokens(2);
                    dice.setNumFacciaUp(newFace);
                }
                break;

            default:
                throw new ToolCardException("Valore non valido");
        }

    }
}
