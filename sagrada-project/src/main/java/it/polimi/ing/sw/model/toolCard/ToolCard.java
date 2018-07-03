package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.io.Serializable;


public abstract class ToolCard implements Serializable{

    private int numOfTokens;
    private int id;
    private String name;
    private String description;

    public ToolCard (int id) {
        this.id=id;
        numOfTokens = 1;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getNumOfTokens () {
        return numOfTokens;
    }

    public void incrementNumOfTokens () {
        numOfTokens = 2;
    }

    public int getId(){
        return id;
    }

    public boolean getFirstExecutionDone(){
        return false;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNumOfTokens(int numOfTokens) {
        this.numOfTokens = numOfTokens;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void execute(DraftPool draftPool, RoundTrack roundTrack, Scheme scheme, Player[] playersRound, Bag bag, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException, NotValidPlayException {
        System.out.println("problemi toolcard");
    }

    public ToolCard toolCardClone(){
        return null; //!!!!!!!!!!!!!!!!!!!!!
    }
}
