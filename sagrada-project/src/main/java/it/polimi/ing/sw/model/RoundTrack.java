package it.polimi.ing.sw.model;


import it.polimi.ing.sw.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class RoundTrack implements Serializable{

    private DraftPool[] roundTrack;
    private int round;


    public RoundTrack(){
        roundTrack = new DraftPool[Constants.NUM_ROUNDS];
        round = 0;
    }


    // aggiunge sul roundTrack tutti i dadi avanzati alla fine del round

    public void addDicesRound(DraftPool draftPool){
        roundTrack[round] = new DraftPool();
        roundTrack[round].addDraftPool(draftPool);
        round++;
    }

    public int getRound(){return round;}

    public void setRoundTrack(DraftPool[] roundTrack) {
        this.roundTrack = roundTrack;
    }

    public void setRound(int round) {
        this.round = round;
    }


    // ritorna tutto il roundTrack

    public DraftPool[] getRoundTrack() {
        return roundTrack;
    }


    // ritorna i dadi avanzati alla fine di un determinato round

    public DraftPool getDicesRound(int round){
        if (this.round+1>=round&&!isEmpty())
            return roundTrack[round-1];
        else
            return null;
    }


    // ritorna il numero di dadi avanzati alla fine di un determinato round

    public int getNumberOfDices(int round) {
        if (this.round+1>=round&&!isEmpty())
            return roundTrack[round-1].getSize();
        else
            return 0;
    }


    public int getRoundTrackSize() {
        return round;
    }


    public boolean isEmpty() {
        return round==0;
    }


    // ritorna il numero massimo di dadi avanzati in un round

    public int getMaxNumberOfDices() {
        int num, max = 0;
        for (int i=1; i<=round; i++) {
            num = getNumberOfDices(i);
            if (num > max)
                max = num;
        }

        return max;

    }


    // ritorna tutti i colori dei dadi presenti sul roundtrack

    public ArrayList<Color> getColorsInRoundTrack() {
        ArrayList<Color> colors = new ArrayList<Color>();
        for (int i=0; i<round; i++)
            for (Color color: roundTrack[i].getColorsInDraftPool())
                colors.add(color);
        return colors;
    }


    public RoundTrack cloneRoundTrack(){
        RoundTrack roundTrackClone=new RoundTrack();
        roundTrackClone.setRound(this.round);
        DraftPool[] draftPoolsClone=new DraftPool[Constants.NUM_ROUNDS];
        if(round>0){
            for(int i=0;i<round;i++){
                DraftPool draftPoolClone= getDicesRound(i+1).cloneDraftPool();
                draftPoolsClone[i]=draftPoolClone;
            }
        }

        roundTrackClone.setRoundTrack(draftPoolsClone);
        return roundTrackClone;
    }
}

