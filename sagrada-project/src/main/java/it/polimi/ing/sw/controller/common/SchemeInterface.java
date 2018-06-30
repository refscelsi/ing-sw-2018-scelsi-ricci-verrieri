package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.Box;

import java.io.Serializable;

public interface SchemeInterface extends Serializable {
    int getDifficulty();

    int getId();

    int getIdRetro();

    Box getBox(int row, int col );

}
