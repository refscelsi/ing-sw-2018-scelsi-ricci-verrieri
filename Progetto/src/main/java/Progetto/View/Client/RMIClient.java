package Progetto.View.Client;

import Progetto.Server.*;
import Progetto.Server.Exceptions.NotValidNicknameException;

import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    String nickname;
    ServerInterface server;

    public RMIClient(String nickname){
        this.nickname=nickname;
    }


    public void connectClient() throws RemoteException, NotBoundException, NotValidNicknameException {
        try {
            Registry registry = LocateRegistry.getRegistry();
            System.out.print("RMI registry bindings: ");
            server = (ServerInterface) registry.lookup("serverInterface");
        }
        catch (RemoteException | NotBoundException e){};
    }

    public void login(String nickname) throws NotValidNicknameException {
        server.login(this.nickname);
    }

}