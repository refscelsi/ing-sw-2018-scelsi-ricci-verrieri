package it.polimi.ing.sw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe che rappresenta la Riserva contenenta i dadi del Round.
 * Rappresentata da un ArrayList di dadi
 */

public class DraftPool implements Serializable{

    private ArrayList<Dice> draftPool;


    public DraftPool(){
        draftPool=new ArrayList<Dice>();
    }


    /**
     * Metodo che aggiunge un dado, ricevuto come parametro, alla draftPool
     * @param dice
     */
    public void addDice(Dice dice){
        draftPool.add(dice);
    }


    public void setDraftPool(ArrayList<Dice> draftPool) {
        this.draftPool = draftPool;
    }

    /**
     * Metodo che aggiunge un set di dadi ad una draftPool
     * @param draft
     */
    public void addDraftPool(DraftPool draft){
        for(Dice dice : draft.getDraftPool()){
            addDice(dice);
        }
    }


    /**
     * metodo che rimuove un dado dalla draftPool
     * @param dice
     */
    public void removeDice(Dice dice){
        draftPool.remove(dice);
    }


    public ArrayList<Dice> getDraftPool() {
        return draftPool;
    }


    public int getSize(){
        return draftPool.size();
    }


    /**
     * Metodo per cercare uno specifico dado nella Riserva
     * @param diceToFind
     * @return true se il dado è presente nella DraftPool,
     * false altrimenti
     */
    public boolean diceInDraftpool(Dice diceToFind){
        for(Dice dice: draftPool){
            if(diceToFind.equals(dice)){
                return true;
            }
        }
        return false;
    }


    /**
     * Metodo per cercare un dado nella Draftpool
     * @param index
     * @return
     */
    public Dice getDice(int index) {
        return draftPool.get(index);
    }


    /**
     *  Metodo per inserire un dado in una determinata posizion
     * @param dice
     * @param position
     */
    public void setDice(Dice dice, int position) {
        draftPool.get(position).setDice(dice.getNumFacciaUp(), dice.getDiceColor());
    }


    /**
     * ritorna tutti i colori dei dadi presenti nella draftpool
     * @return
     */
    public ArrayList<Color> getColorsInDraftPool() {
        ArrayList<Color> colors = new ArrayList<Color>();
        for (Dice dice: draftPool)
            colors.add(dice.getDiceColor());
        return colors;
    }


    /**Metodo necessario a clonare la Riserva,
     *
     * @return
     */
    public DraftPool cloneDraftPool(){
        DraftPool draftPoolClone=new DraftPool();
        ArrayList<Dice> dicesClone=new ArrayList<Dice>();
        for(Dice dice: draftPool){
            dicesClone.add(dice.cloneDice());
        }
        draftPoolClone.setDraftPool(dicesClone);
        return draftPoolClone;
    }

}

