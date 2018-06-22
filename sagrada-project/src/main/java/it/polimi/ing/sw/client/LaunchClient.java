package it.polimi.ing.sw.client;

import it.polimi.ing.sw.controller.PlayerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// classe che lancia il client e crea un nuovo clientController su cui chiama il mainLoop
public class LaunchClient {
    public static void main(String[] args){


        try {
            Registry reg = LocateRegistry.getRegistry();
            PlayerInterface controller= (PlayerInterface) reg.lookup("GameController");
            new View(controller).start();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
