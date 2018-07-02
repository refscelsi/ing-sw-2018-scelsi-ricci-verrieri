package it.polimi.ing.sw.controller.network.socket;

import it.polimi.ing.sw.model.Match;

import java.io.Serializable;

public class MatchToSend implements Serializable {
    private String method;
    private Match match;
    private String message;
    private int id;

    public MatchToSend(String method){
        this.method=method;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getMethod() {
        return method;
    }

    public int getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }
}
