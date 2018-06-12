package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public abstract class ToolCard {

    private int numOfTokens;
    private int id;
    private String name;
    private String description;

    public ToolCard () throws ToolCardException, NotValidException {
        numOfTokens = 1;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getNumOfTokens () {
        return numOfTokens;
    }

    public void setNumOfTokens (int num) {
        numOfTokens = num;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setDescription (String description) {
        this.description = description;
    }

}
