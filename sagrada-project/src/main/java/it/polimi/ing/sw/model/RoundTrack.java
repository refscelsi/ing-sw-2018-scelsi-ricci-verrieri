package it.polimi.ing.sw.model;


import it.polimi.ing.sw.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che rappresenta il Tracciato dei Round , considerato come un Array di DraftPool
 */

public class RoundTrack implements Serializable{

    /**
     * Insieme delle riserve da cui è costituito il tracciato
     */
    private DraftPool[] roundTrack;
    /**
     * numero del Round che si sta giocando
     */
    private int round;


    public RoundTrack(){
        roundTrack = new DraftPool[Constants.NUM_ROUNDS];
        round = 0;
    }


    /**
     * Metodo per aggiungere sul roundTrack tutti i dadi avanzati alla fine del Round
     * @param draftPool , i dadi da inserire sul Tracciato
     */
    public void addDicesRound(DraftPool draftPool){
        roundTrack[round] = new DraftPool();
        roundTrack[round].addDraftPool(draftPool);
        round++;
    }


    public boolean isEmpty() {
        return round==0;
    }

    public void setRoundTrack(DraftPool[] roundTrack) {
        this.roundTrack = roundTrack;
    }

    public void setRound(int round) {
        this.round = round;
    }


    public DraftPool[] getRoundTrack() {
        return roundTrack;
    }

    public int getRound(){return round;}

    public int getRoundTrackSize() {
        return round;
    }

    /**
     * Metodo che restituisce i dadi avanzati alla fine di un determinato Round
     * @param round
     * @return
     */
    public DraftPool getDicesRound(int round){
        if (this.round+1>=round&&!isEmpty())
            return roundTrack[round-1];
        else
            return null;
    }


    /**
     * Metodo che restituisce il numero di dadi avanzati alla fine di un determinato round
     * @param round
     * @return
     */
    public int getNumberOfDices(int round) {
        if (this.round+1>=round&&!isEmpty())
            return roundTrack[round-1].getSize();
        else
            return 0;
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


    /**
     * @return tutti i colori dei dadi presenti sul Tracciato
     *
     */
    public ArrayList<Color> getColorsInRoundTrack() {
        ArrayList<Color> colors = new ArrayList<Color>();
        for (int i=0; i<round; i++)
            for (Color color: roundTrack[i].getColorsInDraftPool())
                colors.add(color);
        return colors;
    }

    /**
     * Metodo che clona il RoundTrack
     * @return un nuovo oggetto dado con le stesse caratteristiche del Tracciato clonato
     */
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

