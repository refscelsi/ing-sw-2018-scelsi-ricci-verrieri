package it.polimi.ing.sw.client;

import it.polimi.ing.sw.controller.ControllerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// classe che lancia il client e crea un nuovo clientController su cui chiama il mainLoop
public class LaunchClient {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserire nome...");
        String name = sc.nextLine();
        try {
            Registry reg = LocateRegistry.getRegistry();
            ControllerInterface controller= (ControllerInterface) reg.lookup("Server");
            ClientController client = new ClientController(controller);
            client.mainLoop();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
