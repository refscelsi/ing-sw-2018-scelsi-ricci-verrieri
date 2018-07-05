package it.polimi.ing.sw.controller;
import it.polimi.ing.sw.client.View;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class TurnTimer extends TimerTask {

    private PlayerController controller;

    public TurnTimer (PlayerController controller) {
        this.controller = controller;
    }

    public void run() {
        try {
            controller.timeout(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
