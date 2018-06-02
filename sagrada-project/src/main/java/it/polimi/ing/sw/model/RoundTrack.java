package it.polimi.ing.sw.model;
import java.util.ArrayList;

public class RoundTrack {
    private ArrayList<DraftPool> roundTrack;

    public RoundTrack(){
        this.roundTrack= new ArrayList<DraftPool>();
    }

    //
    public void addDice(int round, Dice dice){
        roundTrack.get(round).addDice(dice);
    }

    public ArrayList<DraftPool> getRoundTrack() {
        return roundTrack;
    }

    public DraftPool getDicesRound(int round){
        return roundTrack.get(round);
    }

    public int getNumberOfDice(int round){
        return roundTrack.get(round).getSize();
    }


}

