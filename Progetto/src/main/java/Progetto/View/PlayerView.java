package Progetto.View;

import Progetto.Controller.Observer;
import Progetto.Controller.ServerController;

public class PlayerView implements Observer {
    private final ServerController Controller;

    public PlayerView(ServerController controller) {
        Controller = controller;
    }

    //l'unica che parla col controller??
    @Override
    public void update() {

    }
}
