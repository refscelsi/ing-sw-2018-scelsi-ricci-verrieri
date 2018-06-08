package it.polimi.ing.sw.server;

import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.SchemeCard;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;


import java.util.*;

public interface Observer {

    public void updateChoiseScheme (ArrayList<SchemeCard> scheme, Player player);

    public void updateNewRound (Player firstPlayer, DraftPool draftPool);

    public void updateRanking (ArrayList<Player> ranking);

    public void updateNextPlayer (Player player);

    public void updatePublicObjectivesChoosen (ArrayList<ObjectiveCard> publicObjectives);

    public void updateUsedDice (boolean ok);

    //mostra messaggio di errore
    public void showMessage(String message);

    //public <Object> void update(Object o);

    //tutti i vari update che mi serviranno --> cambia turno/fai mossa, con cui passo anche gli oggetti
}
