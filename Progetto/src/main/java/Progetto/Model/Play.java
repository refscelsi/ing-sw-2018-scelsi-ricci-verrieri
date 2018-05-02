package Progetto.Model;

public class Play {

    public void moveToNext(){
    }

    public void useDice(Box box,Dice dice,Scheme scheme, DraftPool draftPool) {
        if(scheme.isEmpty()){
            if(scheme.checkFirst(box, dice)){
                box.placeDice(dice);
                draftPool.removeDice(dice);
                scheme.setNotEmpty();
            }
        }
        else if(!box.isFull()&& scheme.checkBox(box,dice) && scheme.checkDiceAdjacent(box,dice)){
            box.placeDice(dice);
            draftPool.removeDice(dice);
        }
    }

    public void useCard(){
    }
}
