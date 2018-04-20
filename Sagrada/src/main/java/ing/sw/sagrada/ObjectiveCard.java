package Progetto;

public class ObjectiveCard {
    private String name;
    private short points;
    //comportamento

    public ObjectiveCard(String name, short points){
        this.name=name;
        this.points=points;
        //comportamento
    }

    public short getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(short points) {
        this.points = points;
    }

    //da aggiungere il comportamento
}
