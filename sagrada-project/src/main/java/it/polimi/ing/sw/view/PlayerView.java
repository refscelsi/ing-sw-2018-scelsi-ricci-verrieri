package it.polimi.ing.sw.view;

import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.SchemeCard;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.network.protocol.Observer;

import java.util.ArrayList;

public class PlayerView implements Observer {
    //private final TurnController turnController;

    /*public PlayerView(TurnController turnController) {
        this.turnController = turnController;
    }*/

    @Override
    public void updateChoiseScheme(ArrayList<SchemeCard> scheme, Player player) {

    }

    @Override
    public void updateNewRound(Player firstPlayer, DraftPool draftPool) {

    }

    @Override
    public void updateRanking(ArrayList<Player> ranking) {

    }

    @Override
    public void updateNextPlayer(Player player) {

    }

    @Override
    public void updatePublicObjectivesChoosen(ArrayList<ObjectiveCard> publicObjectives) {

    }

    @Override
    public void updateUsedDice(boolean ok) {
    }

    @Override
    public void showMessage(String message) {

    }
}
