package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Scheme;

import java.io.Serializable;

public interface PrivateObjectiveCardInterface extends Serializable{

    Color getColor();

    String getName();

    String getDescription();


}
