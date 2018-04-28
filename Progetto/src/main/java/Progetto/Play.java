package Progetto;

public class Play {

    public void moveToNext(){
    }

    public void useDice(Box box,Dice dice,Scheme scheme) {
        if((scheme.checkBox(box,dice)) && scheme.checkPosition(box) && scheme.checkAdjacent(box,dice)){
            box.placeDice(dice);
        }
    }

    public void useCard(){
    }
}
