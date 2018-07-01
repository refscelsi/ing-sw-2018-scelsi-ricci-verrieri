package it.polimi.ing.sw.controller.common;

import it.polimi.ing.sw.model.Color;

import java.io.Serializable;
import java.util.ArrayList;

public interface DraftPoolInterface extends Serializable {

    int getSize();

    DiceInterface getDice(int index);

    public ArrayList<Color> getColorsInDraftPool();


}
