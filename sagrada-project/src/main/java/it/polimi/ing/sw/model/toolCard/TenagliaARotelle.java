package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.util.ArrayList;


public class TenagliaARotelle extends ToolCard {

    private final int id=8;


    public TenagliaARotelle() {
        super(8);
    }


    @Override
    public void execute(DraftPool neverUsed1, RoundTrack neverUsed2, Scheme neverUsed3, Player[] playersRound, Bag neverUsed4, int playersRoundIndex, int neverUsed5, int neverUsed6, int neverUsed7, int neverUsed8, int neverUsed9){
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
            //incrementNumOfTokens();
        }
    }

}
