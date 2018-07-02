package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.Color;

import java.io.Serializable;

public interface DiceInterface extends Serializable {

    Color getDiceColor();

    int getNumFacciaUp();
}
