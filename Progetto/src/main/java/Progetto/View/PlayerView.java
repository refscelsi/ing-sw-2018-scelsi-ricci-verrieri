package Progetto.View;

import Progetto.Server.Controller.TurnController;
import Progetto.Server.Observer;
import Progetto.Model.DraftPool;
import Progetto.Model.ObjectiveCard.ObjectiveCard;
import Progetto.Model.Player;
import Progetto.Model.SchemeCard;

import java.util.ArrayList;

public class PlayerView implements Observer {
    private final TurnController turnController;

    public PlayerView(TurnController turnController) {
        this.turnController = turnController;
    }

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
