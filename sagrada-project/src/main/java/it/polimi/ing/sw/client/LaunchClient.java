package it.polimi.ing.sw.client;

import java.rmi.RemoteException;

/**
 * Classe che lancia classe il client e istanzia la View, su cui chiama @start()
 */
public class LaunchClient {
    public static void main(String[] args) {
        try {
            new View().start();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
