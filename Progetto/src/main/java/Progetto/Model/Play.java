package Progetto.Model;

public class Play {

    public void moveToNext(){
    }

    public void useDice(Box box,Dice dice,Scheme scheme, DraftPool draftPool) {
        if(!box.isFull()&& scheme.checkBox(box,dice) && scheme.checkDiceAdjacent(box) && scheme.checkOrthogonal(box,dice)){
            box.placeDice(dice);
            draftPool.removeDice(dice);
        }
    }

    public void useCard(){
    }
}
