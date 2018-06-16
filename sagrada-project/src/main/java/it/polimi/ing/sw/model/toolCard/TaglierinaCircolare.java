package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.RoundTrack;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class TaglierinaCircolare extends ToolCard {

    final int id=5;


    public TaglierinaCircolare () throws ToolCardException, NotValidException {
        super();
    }


    public void execute () {

        // questa carta la gestisce il controller, che semplicemente verrà notificato di questa azione e scambierà i due dadi

    }

}
