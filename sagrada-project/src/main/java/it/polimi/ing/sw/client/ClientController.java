package it.polimi.ing.sw.client;

import it.polimi.ing.sw.controller.ClientUpdate;
import it.polimi.ing.sw.controller.ControllerInterface;

//classe che gestisce gli input dei client e chiama i metodi di ControllerInterface (sulla rete)

public class ClientController implements ClientUpdate {
    private final ControllerInterface controller; //il client può chiamare solo i metodi di ControllerInterface

    public ClientController(ControllerInterface controllerInterface){
        this.controller=controllerInterface;
    }

    public void mainLoop(){
        System.out.println("inserisci il nickname");
        String nickname="ari";
        controller.loginPlayer(nickname);
    }

    @Override
    public void update(Object update) {
        //prende le info ricevute e le rende visibili nelle view
    }
}
