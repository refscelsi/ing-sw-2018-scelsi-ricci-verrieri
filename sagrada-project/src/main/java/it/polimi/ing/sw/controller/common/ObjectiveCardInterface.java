package it.polimi.ing.sw.controller.common;

import java.io.Serializable;

public interface ObjectiveCardInterface extends Serializable {
    int getScore();

    String getName();

    String getDescription();
}
