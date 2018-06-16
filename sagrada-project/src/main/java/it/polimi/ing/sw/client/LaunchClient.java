package it.polimi.ing.sw.client;

import it.polimi.ing.sw.controller.ControllerInterface;
import it.polimi.ing.sw.ui.cli.CLI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// classe che lancia il client e crea un nuovo clientController su cui chiama il mainLoop
public class LaunchClient {
    public static void main(String[] args){


        try {
            Registry reg = LocateRegistry.getRegistry();
            ControllerInterface controller= (ControllerInterface) reg.lookup("Controller");
            new ClientController(controller).start();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
