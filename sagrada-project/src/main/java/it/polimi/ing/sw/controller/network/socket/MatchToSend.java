package it.polimi.ing.sw.controller.network.socket;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.toolCard.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;

public class MatchToSend implements Serializable {
    private String method;
    private Match match;
    private String message;
    private int id;

    public MatchToSend(String method){
        this.method=method;
    }

    public MatchToSend(String method, Match match){
        this.method=method;
        this.match=match;
    }
    public MatchToSend (String method, String message){
        this.method=method;
        this.message=message;
    }

    public MatchToSend (String method, String message, int id){
        this.method=method;
        this.message=message;
        this.id=id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Match getMatch() {
        return this.match;
    }

    public String getMethod() {
        return method;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }


}
