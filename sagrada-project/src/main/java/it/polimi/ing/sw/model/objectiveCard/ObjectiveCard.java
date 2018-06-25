package it.polimi.ing.sw.model.objectiveCard;


import java.io.Serializable;

public abstract class ObjectiveCard implements Serializable{

    private int score;
    private String name;
    private String description;

    public ObjectiveCard () {

    }

    public int calculateScore() {
        return 0;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setScore (int score) {
        this.score = score;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setDescription (String description) {
        this.description = description;
    }

}