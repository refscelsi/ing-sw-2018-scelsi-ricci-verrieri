package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.Color;

import java.io.Serializable;
import java.util.ArrayList;

public interface RoundTrackInterface extends Serializable {

    DraftPoolInterface getDicesRound(int round);

    int getNumberOfDices(int round);

    int getRoundTrackSize();

    int getMaxNumberOfDices();

    ArrayList<Color> getColorsInRoundTrack();
}
