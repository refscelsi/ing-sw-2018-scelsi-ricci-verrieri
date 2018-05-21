package Progetto.Controller;

import Progetto.Model.*;
import Progetto.Model.ObjectiveCard.ObjectiveCard;

import java.util.*;

public interface Observer {

    public void update();

    public void update(ArrayList<SchemeCard> scheme, Player player);

    public void update(Player firstPlayer, DraftPool draftPool);

    public void update (ArrayList<Player> ranking);

    public void update (Player player);

    public void update1 (ArrayList<ObjectiveCard> publicObjectives);

    public void update (Dice dice);

    public void notifyMessage(String message);

    //public <Object> void update(Object o);

    //tutti i vari update che mi serviranno --> cambia turno/fai mossa, con cui passo anche gli oggetti
}
