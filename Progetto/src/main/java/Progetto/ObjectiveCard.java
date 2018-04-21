package Progetto;

public class ObjectiveCard {
    private String name;
    private int points;
    private boolean isPrivate;

    public ObjectiveCard(String name, int points, boolean isPrivate){
        this.name=name;
        this.points=points;
        this.isPrivate=isPrivate;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    /*public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }*/

    public void comportamento () {
        // switch (name) : in base al nome definiamo un comportamento
    }
}
