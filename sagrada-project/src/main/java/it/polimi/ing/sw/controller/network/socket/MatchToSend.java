package it.polimi.ing.sw.controller.network.socket;

import it.polimi.ing.sw.model.Match;

import java.io.Serializable;

public class MatchToSend implements Serializable{
    private String method;
    private Match match;
    private String message;

    public MatchToSend(String method, Match match){
        this.method=method;
        this.match=match;
    }

    public MatchToSend(String method, String message){
        this.method=method;
        this.message=message;
    }

    public MatchToSend(String method){
        this.method=method;
    }

    public String getMethod(){
        return this.method;
    }

    public String getMessage() {
        return message;
    }

    public Match getMatch() {
        return match;
    }
}
