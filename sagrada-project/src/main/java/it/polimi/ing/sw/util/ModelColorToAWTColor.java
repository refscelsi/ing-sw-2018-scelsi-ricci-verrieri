package it.polimi.ing.sw.util;

import java.awt.*;

public class ModelColorToAWTColor {
    public static Color toColor(it.polimi.ing.sw.model.Color color) {
        switch (color.getValue()) {
            case 1:
                return Color.red;
            case 2:
                return Color.blue;
            case 3:
                return Color.green;
            case 4:
                return Color.yellow;
            case 5:
                return Color.magenta;
            case 6:
                return Color.white;
            default:
                return Color.white;
        }
    }
}
