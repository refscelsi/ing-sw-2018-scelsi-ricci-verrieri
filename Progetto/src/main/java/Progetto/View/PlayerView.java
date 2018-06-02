package progetto.view;

import progetto.model.DraftPool;
import progetto.model.Player;
import progetto.model.SchemeCard;
import progetto.model.objectiveCard.ObjectiveCard;
import progetto.server.Observer;
import progetto.server.controller.TurnController;

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
