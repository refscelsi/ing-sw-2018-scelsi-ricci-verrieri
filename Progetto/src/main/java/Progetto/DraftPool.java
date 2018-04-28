package Progetto;

import java.util.ArrayList;

public class DraftPool {
    private ArrayList<Dice> draftPool;

    public DraftPool(){
        this.draftPool=new ArrayList<Dice>();
    }

    public void addDice(Dice dice){
        draftPool.add(dice);
    }

    public void removeDice(Dice dice){
        draftPool.remove(dice);
    }

    public ArrayList<Dice> getDraftPool() {
        return draftPool;
    }

    public void setDraftPool(ArrayList<Dice> draftPool) {
        this.draftPool = draftPool;
    }

}
