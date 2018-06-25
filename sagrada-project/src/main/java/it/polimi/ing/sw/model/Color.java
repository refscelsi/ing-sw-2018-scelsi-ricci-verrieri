package it.polimi.ing.sw.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum Color implements Serializable{
    RED(1), BLUE(2), GREEN(3), YELLOW(4), PURPLE(5), WHITE(6);

    private int value;
    private static Map<Integer, Color> map = new HashMap<>( );

    Color(int value) {
        this.value = value;
    }

    static {
        for (Color color : Color.values()) {
            map.put(color.value, color);
        }
    }

    public static Color valueOf(int color) {
        return map.get(color);
    }

    public int getValue() {
        return value;
    }
}
