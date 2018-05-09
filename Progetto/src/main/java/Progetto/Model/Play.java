package Progetto.Model;

import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.ToolCard.ToolCard;

import javax.swing.plaf.metal.MetalBorders;

public class Play {

    public void moveToNext(){
    }

    public void useDice(Box box, Dice dice, Scheme scheme, DraftPool draftPool) throws NotValidException {
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
        else throw new NotValidException("L'inserimento non è corretto");
    }

    public void useToolCard(ToolCard toolCard) throws ToolCardException, NotValidException { //il controller passa la tool che mi serve e che creo ogni volta che devo usare
        toolCard.execute();
    }
}
