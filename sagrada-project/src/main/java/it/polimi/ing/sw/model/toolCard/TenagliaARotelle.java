package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.util.ArrayList;


public class TenagliaARotelle extends ToolCard {

    private final int id=8;


    public TenagliaARotelle() {
        super();
    }

    public void execute (Player[] playersRound, int playersRoundIndex){
        Player temp, succ;
        succ=playersRound[playersRoundIndex+1];
        playersRound[playersRoundIndex+1] = playersRound[playersRoundIndex];
        for (int j = playersRoundIndex+2; j < playersRound.length; j++) {
            if (playersRound[j] != playersRound[playersRoundIndex]) {
                temp = playersRound[j];
                playersRound[j] = succ;
                succ = temp;
            } else {
                playersRound[j] = succ;
                j = playersRound.length;
            }
            incrementNumOfTokens();
        }
    }

}
