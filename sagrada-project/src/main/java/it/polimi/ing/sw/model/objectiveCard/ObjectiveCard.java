package it.polimi.ing.sw.model.objectiveCard;


import it.polimi.ing.sw.model.Scheme;

import java.io.Serializable;

public abstract class ObjectiveCard implements Serializable {

    private int score;
    private String name;
    private String description;
    private String id;

    public ObjectiveCard() {

    }

    public int calculateScore(Scheme scheme) {
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

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}