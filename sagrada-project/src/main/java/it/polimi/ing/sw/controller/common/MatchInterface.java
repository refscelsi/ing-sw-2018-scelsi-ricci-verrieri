package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.RoundTrack;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.toolCard.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;

public interface MatchInterface extends Serializable {

    Player getPlayer(String nickname);

    DraftPoolInterface getDraftPool();

    ArrayList<ObjectiveCard> getPublicObjectives();

    RoundTrackInterface getRoundTrack();

    ArrayList<Player> getRanking();

    Player getPlayerPlaying();

    int getNumPlayers();

    ArrayList<ToolCard> getToolCards();

}
