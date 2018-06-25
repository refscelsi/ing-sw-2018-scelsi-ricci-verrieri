package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.RoundTrack;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class PinzaSgrossatrice extends ToolCard {

    private final int id=1;


    public PinzaSgrossatrice() {
        super();
    }

    @Override
    public void execute(DraftPool draftPool, int indexInDraftPool, char operation) throws NotValidException {
        int value = draftPool.getDice(indexInDraftPool).getNumFacciaUp();

        switch(operation){
            case 'a':
                if(value<6 && value>0) {
                    draftPool.getDice(indexInDraftPool).setNumFacciaUp(value++);
                    incrementNumOfTokens();
                }
                else
                    throw new NotValidException("Non puoi cambiare un 6 in 1");
                break;

            case 'd':
                if(value>1 && value<7) {
                    draftPool.getDice(indexInDraftPool).setNumFacciaUp(value--);
                    incrementNumOfTokens();
                }
                else
                    throw new NotValidException("Non puoi cambiare un 1 in 6");
                break;
        }
    }

}