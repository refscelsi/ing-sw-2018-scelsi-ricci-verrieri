package it.polimi.ing.sw.model;

import java.util.TimerTask;

public class MatchTimer extends TimerTask{
    Thread match;

    public MatchTimer(Thread match){
        this.match=match;
    }

    @Override
    public void run() {
        match.notifyAll();
    }
}
