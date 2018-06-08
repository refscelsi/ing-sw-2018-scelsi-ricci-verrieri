<<<<<<< HEAD:sagrada-project/src/main/java/it/polimi/ing/sw/model/toolCard/Martelletto.java
package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
=======
package Progetto.Model.ToolCard;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.DraftPool;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.Player;

>>>>>>> parent of 30b3cf2... package corrections:Progetto/src/main/java/Progetto/Model/ToolCard/Martelletto.java

public class Martelletto extends ToolCard {
    private Box destination;
    private Dice dice;
    final int id=7;



    public Martelletto() throws ToolCardException, NotValidException {
        super();
    }

    public void execute(DraftPool draftPool, Player player) throws NotValidException {
        //controllo che sia il secondo turno
        for(Dice dice: draftPool.getDraftPool()){
            dice.throwDice();
        }
        player.useDice(destination,dice/*,draftPool*/);
    }

    public void setDice(Dice dice){
        this.dice=dice;
    }

    public void setDestination(Box box){
        this.destination=box;
    }
}
