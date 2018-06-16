package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class TenagliaARotelle extends ToolCard {

    final int id=8;


    public TenagliaARotelle() throws ToolCardException, NotValidException {
        super();
    }


    // questa carta è gestita dal controller: cambierà lo stato del giocatore allo stato in cui ancora non ha piazzato un dado e modificherà l'array dei giocatori del round

}
