package it.polimi.ing.sw.network.client.rmi;

import it.polimi.ing.sw.network.server.ServerInterface;
import it.polimi.ing.sw.network.server.exceptions.NotValidNicknameException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    String nickname;
    ServerInterface server;

    public RMIClient(String nickname) {
        this.nickname = nickname;
    }


    public void connectClient() throws RemoteException, NotBoundException, NotValidNicknameException {
        try {
            Registry registry = LocateRegistry.getRegistry();
            System.out.print("RMI registry bindings: ");
            server = (ServerInterface) registry.lookup("serverInterface");
        } catch (RemoteException | NotBoundException e) {
        }
        ;
    }

    public void login(String nickname) throws NotValidNicknameException {
        //server.login(this.nickname);
    }

}