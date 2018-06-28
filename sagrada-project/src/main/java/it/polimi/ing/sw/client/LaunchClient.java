package it.polimi.ing.sw.client;

import java.rmi.RemoteException;

// classe che lancia il client e crea un nuovo clientController su cui chiama il mainLoop
public class LaunchClient {
    public static void main(String[] args) {
        try {
            new View().start();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
