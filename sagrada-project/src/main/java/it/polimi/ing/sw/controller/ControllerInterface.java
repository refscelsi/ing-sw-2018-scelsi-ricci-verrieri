package it.polimi.ing.sw.controller;

import java.rmi.Remote;

public interface ControllerInterface extends Remote {

    public void loginPlayer(String nickname);


}
