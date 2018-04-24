package Progetto;

public class Play {

    public void moveToNext(){
    }

    public void useDice(Box box,Dice dice,Scheme scheme) {
        if (box.isEmpty()) {
            if (box.checkColor(dice)) {
                if (box.checkShade(dice)) {
                    if (scheme.checkAdjacent(dice, box)) {
                        box.placeDice(dice);
                    }
                }
            } else
                return;
        }
    }

    public void useCard(){
    }
}
