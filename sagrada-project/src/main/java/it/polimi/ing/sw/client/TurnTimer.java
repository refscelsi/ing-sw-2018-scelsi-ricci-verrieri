package it.polimi.ing.sw.client;
import java.util.TimerTask;

public class TurnTimer extends TimerTask {

    private View controller;

    public TurnTimer (View controller) {
        this.controller = controller;
    }

    public void run() {
        controller.endTurn();
    }
}
